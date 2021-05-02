package br.com.searchdevelopers.godev.usecases;

import br.com.searchdevelopers.godev.domain.Users;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

public class ExportacaoLayoutUsuario {

    public void executar(ListaObj<Users> list) {
        FileWriter arq = null;
        Formatter saida = null;
        StringBuilder conteudo;
        boolean deuRuim = false;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        DateTimeFormatter dateTimeFormatterOnlyDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String nomeArquivo = "usuarios"+now.format(dateTimeFormatterOnlyDate)+".csv";
        System.out.println(nomeArquivo);
        String nome, tipoDocumento, nroDocumento, email, telefone, tipoUsuario;
        int registros = 0;

        try {
            arq = new FileWriter(nomeArquivo, true);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.err.println("Erro ao abrir arquivo");
            System.exit(1);
        }
        conteudo = new StringBuilder(String.format("-".repeat(123) + "%n"));
        conteudo.append(String.format("%s%-8s%-19s%02d%n", "00", "USUÁRIOS", now.format(dateTimeFormatter), 1));
        try {
            for (int i = 0; i < list.getTamanho(); i++) {
                Users users = new Users();
                nome = users.getNameUser();
                email = users.getEmail();
                telefone = users.getPhone();
                tipoUsuario = users.getRole();

                if (!(users.getCnpj() == null)) {
                    tipoDocumento = "01";
                    nroDocumento = users.getCnpj();


                } else {
                    tipoDocumento = "02";
                    nroDocumento = users.getCpf();
                }

                registros++;
                conteudo.append(String.format("%-2s%-40s%2s%20s%40s%15s%4s%n",
                        "02",
                        nome,
                        tipoDocumento,
                        nroDocumento,
                        email,
                        telefone,
                        tipoUsuario));
            }
            conteudo.append(String.format("%s%05d%n", "01", registros));

            conteudo.append(String.format("-".repeat(123) + "%n"));

            saida.format(conteudo.toString());

        } catch (FormatterClosedException erro) {
            System.err.println("Erro ao gravar no arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
        System.out.println(conteudo);
    }
}

