package br.com.searchdevelopers.godev.usecases.importupload;


import br.com.searchdevelopers.godev.domain.Experience;
import br.com.searchdevelopers.godev.domain.Formation;
import br.com.searchdevelopers.godev.repository.ExperienceRepository;
import br.com.searchdevelopers.godev.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    FormationRepository formationRepository;

    @PostMapping
    public ResponseEntity criarArquivo(@RequestParam MultipartFile arquivo) throws IOException {
        ImportService importService = new ImportService();

        List<Experience> lista = importService.listaExperiencia;
        List<Formation> listaFormacao = importService.listaFormacao;

        if (arquivo.isEmpty()){
            return ResponseEntity.status(400).body("Arquivo não enviado");
        }

        System.out.println("Recebendo um arquivo de nome "+arquivo.getOriginalFilename());
        System.out.println("Recebendo um arquivo do tipo "+ arquivo.getContentType());

        byte[] conteudo = arquivo.getBytes();

        Path path = Paths.get(arquivo.getOriginalFilename());
        Files.write(path, conteudo);

        for (Experience e : lista){
            experienceRepository.save(e);
        }

        for (Formation f : listaFormacao){
            formationRepository.save(f);
        }

        importService.leArquivo(arquivo.getOriginalFilename());
        return ResponseEntity.status(201).body(importService.listaExperiencia);
    }

}
