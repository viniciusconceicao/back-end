package br.com.searchdevelopers.godev.dto;

import br.com.searchdevelopers.godev.domain.Users;

public class PhotoUserDto {

    private Integer idUser;

    private byte[] photo;

    private String namePhoto;

    public PhotoUserDto(Users users) {
        this.idUser = users.getIdUser();
        this.photo = users.getPhoto();
        this.namePhoto = users.getNamePhoto();
    }

    public byte[] getPhoto() {
        return photo;
    }

    public String getNamePhoto() {
        return namePhoto;
    }

    public Integer getIdUser() {
        return idUser;
    }
}
