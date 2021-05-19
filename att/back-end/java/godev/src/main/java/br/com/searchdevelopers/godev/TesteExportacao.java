package br.com.searchdevelopers.godev;

import br.com.searchdevelopers.godev.domain.User;
import br.com.searchdevelopers.godev.usecases.exportdownload.ExportacaoLayoutUsuario2;
import br.com.searchdevelopers.godev.usecases.ListaObj;

public class TesteExportacao {

    public static void main(String[] args) {
        ListaObj<User> listaUsers = new ListaObj<>(10);
        User user = new User("Vinicius", "48296654830", "", "vinicius@gmail.com", "DEV", "11954092560");
        User user1 = new User("Vinicius", "", "48296654830", "vinicius@gmail.com", "USER", "11954092560");
        User user2 = new User("Vinicius", "48296654830", "", "vinicius@gmail.com", "DEV", "11954092560");


        ExportacaoLayoutUsuario2 exp = new ExportacaoLayoutUsuario2();

        listaUsers.adiciona(user);
        listaUsers.adiciona(user1);
        listaUsers.adiciona(user2);

        exp.executarTxt(listaUsers);
    }
}
