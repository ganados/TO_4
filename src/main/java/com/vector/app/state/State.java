package com.vector.app.state;

import com.vector.app.individual.Individual;
import com.vector.app.state.exceptions.CanNotBeHealthyHaveSymptomsException;
import com.vector.app.state.exceptions.CanNotBeResistException;
import com.vector.app.state.exceptions.CanNotHaveNotSymptoms;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public abstract class State {
    Individual individual;

    public void makeResist() {
        if (this.individual.getIsHealthy()) {
            individual.setIsResist(true);
        }
        throw new CanNotBeResistException("Individual is not healthy");
    }

    public void makeNotResist() {
        individual.setIsResist(false);
    }

    public void makeHealthy() {
        if (!this.individual.getHaveSymptoms()) {
            individual.setIsHealthy(true);
        }
        throw new CanNotBeHealthyHaveSymptomsException("Individual have symptoms, can not be healthy");
    }

    public void makeUnHealthy() {
        this.individual.setIsHealthy(false);
    }

    public void makeHaveSymptoms() {
        if (!this.individual.getIsHealthy()) {
            this.individual.setHaveSymptoms(true);
        }
        throw new CanNotHaveNotSymptoms("Individual is healthy and can not have symptoms");
    }

    public void makeHaveNoSymptoms() {
        this.individual.setHaveSymptoms(false);
    }
}
