package br.com.searchdevelopers.godev.usecases;

import br.com.searchdevelopers.godev.controller.service.NotificationController;
import br.com.searchdevelopers.godev.domain.Notification;
import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.domain.UserService;
import br.com.searchdevelopers.godev.repository.ServiceRepository;
import br.com.searchdevelopers.godev.repository.UserRepository;
import br.com.searchdevelopers.godev.repository.UserServiceRepository;
import br.com.searchdevelopers.godev.usecases.fila.FilaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class AsyncFilaService {

    @Autowired
    private UserServiceRepository userServiceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public void expirarPublicacao(){
        FilaObj<UserService> filaUserService = new FilaObj<>(200);

        NotificationController notificationController = new NotificationController();

        List<UserService> list = userServiceRepository.findAll();

        for (UserService userService : list){
            filaUserService.insert(userService);
        }

        UserService primeiroItemFila = filaUserService.peek();

        LocalDate inicioData = primeiroItemFila.getService().getStartDateService();
        @Valid Users receiver = primeiroItemFila.getUserClt();
        @Valid Users sender = userRepository.getOne(1);

        //DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // LocalDate dataInicio = LocalDate.parse(inicioData.format(formatacao));

        LocalDate dataAtual = LocalDate.now();
        Duration duracao = (Duration.between(inicioData,dataAtual));



        // 7_884_000 segundos é igual a 3 meses
        if (duracao.getSeconds() > 7_884_000 && primeiroItemFila.getService().getProgress().equals(0)){

            Notification notification = new Notification("Publicação expirada",
                    "Publicação pendente a três meses", true,sender,receiver);

            serviceRepository.delete(primeiroItemFila.getService());
            notificationController.postNotification(sender,receiver,notification);
        }
    }

}
