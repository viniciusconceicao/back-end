package br.com.searchdevelopers.godev.usecases.importupload;


import br.com.searchdevelopers.godev.domain.Experience;
import br.com.searchdevelopers.godev.domain.Formation;
import br.com.searchdevelopers.godev.dto.FormationExperienceDto;
import br.com.searchdevelopers.godev.repository.ExperienceRepository;
import br.com.searchdevelopers.godev.repository.FormationRepository;
import br.com.searchdevelopers.godev.usecases.fila.FilaObj;
import br.com.searchdevelopers.godev.usecases.pilha.PilhaObj;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private FormationRepository formationRepository;


    @PostMapping
    public ResponseEntity criarArquivo(@RequestParam MultipartFile arquivo) throws IOException {
        ImportService importService = new ImportService();

        if (arquivo.isEmpty()){
            return ResponseEntity.status(400).body("Arquivo não enviado");
        }

        System.out.println("Recebendo um arquivo de nome "+arquivo.getOriginalFilename());
        System.out.println("Recebendo um arquivo do tipo "+ arquivo.getContentType());

        byte[] conteudo = arquivo.getBytes();

        Path path = Paths.get(arquivo.getOriginalFilename());
        Files.write(path, conteudo);

        List<Object> formationExperienceDtoList1 = importService.leArquivo(arquivo.getOriginalFilename());



        for (Object e : formationExperienceDtoList1){
            if (e.getClass().equals(Experience.class)){

                Experience experience = new Experience(((Experience) e).getNameCompany(),
                        ((Experience) e).getStartDateExperience(),
                        ((Experience) e).getEndDateExperience(),
                        ((Experience) e).getDescriptionExperience(),
                        ((Experience) e).getPosition(),
                        ((Experience) e).getFunctions(),
                        ((Experience) e).getLocality());
                experienceRepository.save(experience);
                System.out.println(experience);

            }
            else {

                Formation formation = new Formation(((Formation) e).getNameInstitution(),
                        ((Formation) e).getCourse(),
                        ((Formation) e).getLanguageFormation(),
                        ((Formation) e).getStartDateFormation(),
                        ((Formation) e).getEndDateFormation());
                formationRepository.save(formation);
            }



            }


        return ResponseEntity.status(201).body(formationExperienceDtoList1);
    }





}
