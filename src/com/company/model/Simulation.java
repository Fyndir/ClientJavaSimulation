package com.company.model;

import java.util.List;

public class Simulation {

    public List<Capteur> mesCapteurs;

    public List<Camion> mesCamions;

    public void InitSimulation() {

    }

    public Simulation(List<Capteur> mesCapteurs, List<Camion> mesCamions) {
        this.mesCapteurs = mesCapteurs;
        this.mesCamions = mesCamions;
    }

    public void run() {

    }

    private void UpdateDb() {

    }


}
