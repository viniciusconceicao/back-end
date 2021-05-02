package br.com.searchdevelopers.godev;

import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.usecases.ExportacaoLayoutUsuario2;
import br.com.searchdevelopers.godev.usecases.ListaObj;
import org.apache.catalina.User;

import java.time.LocalDate;

public class TesteExportacao {

    public static void main(String[] args) {
        ListaObj<Users> listaUsers = new ListaObj<>(10);
        Users user = new Users("Vinicius", "48296654830", "", "vinicius@gmail.com", "DEV", "11954092560");
        Users user1 = new Users("Vinicius", "", "48296654830", "vinicius@gmail.com", "USER", "11954092560");
        Users user2 = new Users("Vinicius", "48296654830", "", "vinicius@gmail.com", "DEV", "11954092560");


        ExportacaoLayoutUsuario2 exp = new ExportacaoLayoutUsuario2();

        listaUsers.adiciona(user);
        listaUsers.adiciona(user1);
        listaUsers.adiciona(user2);

        exp.executarTxt(listaUsers);
    }
}
