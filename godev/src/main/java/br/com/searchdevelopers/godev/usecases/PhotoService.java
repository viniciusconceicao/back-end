package br.com.searchdevelopers.godev.usecases;

import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.ResponseEntity.status;

@Service
public class PhotoService {

    @Autowired
    private final UserRepository userRepository;

    public PhotoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void savePhotoUser(Integer id, MultipartFile file) {
        try {
            userRepository.existsByIdUser(id);
            Users users = userRepository.findByIdUser(id);
            users.setPhoto(file.getBytes());
            users.setNamePhoto(file.getOriginalFilename());

            userRepository.save(users);
        } catch (Exception e) {
            status(400).body(e.getMessage());
        }
    }
}
