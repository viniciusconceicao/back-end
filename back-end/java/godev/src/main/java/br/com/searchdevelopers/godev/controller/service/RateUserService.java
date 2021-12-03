package br.com.searchdevelopers.godev.controller.service;

import br.com.searchdevelopers.godev.domain.Users;
import br.com.searchdevelopers.godev.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RateUserService {


  public Double avaliar(Double numberStars, Integer id, UserRepository userRepository) {

      Users users = userRepository.findUserByIdUser(id);
      Double regatarEstrelas = users.getNumberOfStars();
      Integer resgatarNumVotos = users.getRatingNumber();
      double mediaEstrelas = 0.0;

      if (resgatarNumVotos == null) {
        users.setRatingNumber(0);
      } else if (regatarEstrelas == null) {
        users.setNumberOfStars(0.0);
      } else {
        resgatarNumVotos++;
        users.setRatingNumber(resgatarNumVotos);
        regatarEstrelas += numberStars;
        users.setNumberOfStars(regatarEstrelas);
        mediaEstrelas = regatarEstrelas / resgatarNumVotos;
        users.setStarsUser(mediaEstrelas);
      }
        return  mediaEstrelas;

    }
}
