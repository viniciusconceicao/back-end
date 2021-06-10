package br.com.searchdevelopers.godev.usecases.exportdownload;

import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.usecases.ListaObj;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class ExportacaoLayoutUsuario2 {

    public void executarTxt(ListaObj<Users> list) {
        FileWriter arq = null;
        Formatter saida = null;
        StringBuilder conteudo;
        boolean deuRuim = false;
        String nomeArquivo = "usuarios.txt";
        String nome, tipoDocumento, nroDocumento, email, telefone, tipoUsuario;
        int registros = 0;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");

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
                Users users = list.getElemento(i);
                nome = users.getNameUser();
                email = users.getEmail();
                telefone = users.getPhone();
                tipoUsuario = users.getRole();

                if (!users.getCnpj().isEmpty()) {
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
        } finally { // bloco finally é executado independente de dar erro ou não
            // usado para fechar os objetos saida e close.
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


    public void executarCsv(ListaObj<Users> list){
        FileWriter arq = null;
        Formatter saida = null;
        StringBuilder conteudo;
        boolean deuRuim = false;
        String nomeArquivo = "usuarios.csv";
        String nome, tipoDocumento, nroDocumento, email, telefone, tipoUsuario;
        int registros = 0;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");

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
                Users users = list.getElemento(i);
                nome = users.getNameUser();
                email = users.getEmail();
                telefone = users.getPhone();
                tipoUsuario = users.getRole();

                if (!users.getCnpj().isEmpty()) {
                    tipoDocumento = "01";
                    nroDocumento = users.getCnpj();


                } else {
                    tipoDocumento = "02";
                    nroDocumento = users.getCpf();
                }

                registros++;
                conteudo.append(String.format("%-2s;%-40s;%2s;%20s;%40s;%15s;%4s%n",
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
        } finally { // bloco finally é executado independente de dar erro ou não
            // usado para fechar os objetos saida e close.
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

