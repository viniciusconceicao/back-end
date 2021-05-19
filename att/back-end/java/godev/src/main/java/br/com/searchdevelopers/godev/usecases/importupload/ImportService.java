package br.com.searchdevelopers.godev.usecases.importupload;

import br.com.searchdevelopers.godev.domain.Experience;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImportService {

    List<Experience> listaExperiencia = new ArrayList<Experience>();

    public void leArquivo(String nomeArq) {
        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        Integer contRegistro = 0;


        // Abre o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }


        // Lê os registros do arquivo
        try {
            // Lê um registro
            registro = entrada.readLine();

            while (registro != null) {


                // Obtém o tipo do registro
                tipoRegistro = registro.substring(0, 2); // obtém os 2 primeiros caracteres do registro


                if (tipoRegistro.equals("00")) {
                    System.out.println("Header");
                    System.out.println(registro);
                }

                else if (tipoRegistro.equals("01")) {

                    System.out.println("\nTrailer");

                    int qtdRegistro = Integer.parseInt(registro.substring(2,7).trim());

                    if (qtdRegistro == contRegistro ) {

                        System.out.println(qtdRegistro);
                        System.out.println(contRegistro);
                        System.out.println("Quantidade de registros gravados compatível com quantidade lida");

                    }
                    else {
                        System.out.println(qtdRegistro);
                        System.out.println(contRegistro);
                        System.out.println("Quantidade de registros gravados não confere com quantidade lida");

                    }
                }


                else if (tipoRegistro.equals("02")) {

                    if (contRegistro == 0) {
                        System.out.println();
                        System.out.printf("%-2s%-70s%-10s%-10s%-200s\n", "TIPO DE REGISTRO","NOME DA EMPRESA","DATA INICIAL","DATA FINAL",
                                "DESCRIÇÃO");

                    }


                    String nomeEmpresa = registro.substring(1,72).trim();
                    String dataInicio = registro.substring(72,82).trim();
                    String dataFim = registro.substring(82,92).trim();
                    String descricao = registro.substring(92,291).trim();



                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate conversaoDataInicio = LocalDate.parse(dataInicio,formatter);
                    LocalDate conversaoDatafim = LocalDate.parse(dataFim,formatter);



                    System.out.printf("%-2s%-70s%-10s%-10s%-200s\n", tipoRegistro, nomeEmpresa, conversaoDataInicio, conversaoDatafim,descricao);
                    contRegistro++;

                    listaExperiencia.add(new Experience(nomeEmpresa, conversaoDataInicio, conversaoDatafim,descricao));


                    }

                else if (tipoRegistro.equals("03")) {
                    if (contRegistro == 0) {
                        System.out.println();
                        System.out.printf("%-2s%-50s%-40s%-10s%-10s%-20s\n", "TIPO DE REGISTRO","NOME DA INSTITUIÇÃO","CURSO",
                                "DATA INICIAL","DATA FINAL","LINGUAGEM UTILIZADA");

                    }

                    String nomeInstituicao = registro.substring(2,52).trim();
                    String curso = registro.substring(52,92).trim();
                    String dataInicio = registro.substring(92,102).trim();
                    String dataFim = registro.substring(102,112).trim();
                    String linguagem = registro.substring(112,131).trim();

                    System.out.printf("%-2s%-50s%-40s%-10s%-10s%-20s\n",tipoRegistro, nomeInstituicao, curso, dataInicio, dataFim,
                            linguagem);
                     contRegistro++;

                }

                else {
                    System.out.println("Tipo de registro inválido");
                }


                // lê o próximo registro
                registro = entrada.readLine();
            }

            // Fecha o arquivo
            entrada.close();
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }

    }

}


