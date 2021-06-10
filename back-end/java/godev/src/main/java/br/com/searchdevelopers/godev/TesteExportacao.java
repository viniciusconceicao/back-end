package br.com.searchdevelopers.godev;

import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.usecases.exportdownload.ExportacaoLayoutUsuario2;
import br.com.searchdevelopers.godev.usecases.ListaObj;

public class TesteExportacao {

    public static void main(String[] args) {
        ListaObj<Users> listaUsers = new ListaObj<>(10);
        Users users = new Users("Vinicius", "48296654830", "", "vinicius@gmail.com", "DEV", "11954092560");
        Users users1 = new Users("Vinicius", "", "48296654830", "vinicius@gmail.com", "USER", "11954092560");
        Users users2 = new Users("Vinicius", "48296654830", "", "vinicius@gmail.com", "DEV", "11954092560");


        ExportacaoLayoutUsuario2 exp = new ExportacaoLayoutUsuario2();

        listaUsers.adiciona(users);
        listaUsers.adiciona(users1);
        listaUsers.adiciona(users2);

        exp.executarTxt(listaUsers);
    }
}
