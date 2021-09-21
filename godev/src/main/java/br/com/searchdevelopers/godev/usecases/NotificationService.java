package br.com.searchdevelopers.godev.usecases;

import br.com.searchdevelopers.godev.domain.Notification;
import br.com.searchdevelopers.godev.repository.NotificationRepository;
import br.com.searchdevelopers.godev.usecases.pilha.PilhaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository repository;


    public List orderNotification(int id){
        List<Notification> list = repository.findNotificationByUsersReceiverIdUser(id);
        List<Notification> listAtt = new ArrayList<>();
        PilhaObj<Notification> pilhaNotification = new PilhaObj<>(30);

        System.out.println(list);

        for (int a = 0; a < list.size(); a++) {
            if (!list.isEmpty()) {
                Integer elemento = list.size() - 1;


                if (list.size() >= 30) {
                    for (int i = 0; i <= 30; i++) {
                        pilhaNotification.push(list.get(elemento - i));

                        listAtt.add(pilhaNotification.peek());
                    }
                    System.out.println(pilhaNotification);
                    return listAtt;

                } else {
                    for (int i = 0; i < list.toArray().length; i++) {
                        pilhaNotification.push(list.get(elemento - i));
                        listAtt.add(pilhaNotification.peek());
                    }
                    System.out.println(pilhaNotification);
                    return listAtt;
                }

            }
            else {
                return null;
            }
        }
        return listAtt;
    }




}
