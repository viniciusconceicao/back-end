package br.com.searchdevelopers.godev.usecases.exportdownload;

import br.com.searchdevelopers.godev.domain.Users;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExportService {

    public String exportUser(List<Users> lista, String tipoArquivo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        StringBuilder corpo = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        int registros = 0;
        Object headerDoc, trailerDoc;

        if (tipoArquivo.equals("csv")) {
            headerDoc = String.format("%s;%-8s;%-19s;%02d%n", "00", "USUÁRIOS", now.format(dateTimeFormatter), 1);
        } else {
            headerDoc = String.format("%s %-8s %-19s %02d %n", "00", "USUÁRIOS", now.format(dateTimeFormatter), 1);
        }
        corpo.append(headerDoc);
        for (Users users : lista) {
            String nome = users.getNameUser();
            String tipoDoc;
            String numeroDoc;
            String email = users.getEmail();
            String telefone = users.getPhone();
            String tipoUsuario = users.getRole();
            registros++;
            if (!(users.getCnpj() == null)) {
                tipoDoc = "01";
                numeroDoc = users.getCnpj();
            } else {
                tipoDoc = "02";
                numeroDoc = users.getCpf();
            }


            if (tipoArquivo.equals("csv")) {
                corpo.append(String.format("%-2s;%-40s;%2s;%20s;%40s;%15s;%4s%n",
                        "02",
                        nome,
                        tipoDoc,
                        numeroDoc,
                        email,
                        telefone,
                        tipoUsuario));

            } else {
                corpo.append(String.format("%-2s %-40s %2s %20s %40s %15s %4s%n",
                        "02",
                        nome,
                        tipoDoc,
                        numeroDoc,
                        email,
                        telefone,
                        tipoUsuario));
            }

        }

        if (tipoArquivo.equals("csv")) {
            trailerDoc = String.format("%s;%05d%n", "01", registros);
        } else {
            trailerDoc = String.format("%s %05d%n", "01", registros);
        }

        corpo.append(trailerDoc);
        return corpo.toString();
    }
}
