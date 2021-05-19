package br.com.searchdevelopers.godev.usecases.exportdownload;


import br.com.searchdevelopers.godev.domain.User;
import br.com.searchdevelopers.godev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private ExportService service;

    @Autowired
    private UserRepository repository;

    @GetMapping(value = "/users/{tipoArquivo}")
    public ResponseEntity<String> exportUserCSV(HttpServletResponse response, @PathVariable String tipoArquivo) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy_HH-mm");
        List<User> lista = repository.findAll();
        String corpo = service.exportUsers(lista, tipoArquivo);
        String nome = String.format("usuarios_%s.%s", formato.format(new Date(System.currentTimeMillis())), tipoArquivo.equals("csv") ? "csv":"txt");

        response.setHeader("Content-Disposition","attachment; filename=" + nome);
        response.setContentType(tipoArquivo.equals("csv") ? "text/csv; charset:utf-8" : "text; charset=UTF-8" );
        response.setHeader("Filename", nome);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition","attachment");

        return  ResponseEntity.ok("\uFEFF" + corpo);
    }
}