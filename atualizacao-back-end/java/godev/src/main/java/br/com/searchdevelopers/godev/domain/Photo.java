package br.com.searchdevelopers.godev.domain;

import javax.persistence.*;

@Entity
public class Photo {

    @Id
    @GeneratedValue
    private Integer idPhoto;

    @Column(length = 500_000_000)
    private byte[] photoContent;

    private String namePhoto;

    @OneToOne
    @JoinColumn(name = "users_id")
    private Users users;

    public Integer getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }

    public byte[] getPhotoContent() {
        return photoContent;
    }

    public void setPhotoContent(byte[] photoContent) {
        this.photoContent = photoContent;
    }

    public String getNamePhoto() {
        return namePhoto;
    }

    public void setNamePhoto(String namePhoto) {
        this.namePhoto = namePhoto;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
