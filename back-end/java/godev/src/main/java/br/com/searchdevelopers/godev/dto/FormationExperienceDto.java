package br.com.searchdevelopers.godev.dto;

import br.com.searchdevelopers.godev.domain.Experience;
import br.com.searchdevelopers.godev.domain.Formation;


public class FormationExperienceDto {

    private Experience experience;

    private  Formation formation;


    public FormationExperienceDto(Experience experience, Formation formation) {
        this.experience = experience;
        this.formation = formation;
    }

    public FormationExperienceDto() {
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
}
