package com.company.model;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Simulation {

    public List<Capteur> mesCapteurs;

    public List<Camion> mesCamions;

    public Simulation(List<Capteur> mesCapteurs, List<Camion> mesCamions) {
        this.mesCapteurs = mesCapteurs;
        this.mesCamions = mesCamions;
    }

    public void run() throws InterruptedException {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                System.out.println("erreur de sleep");
            }

            Refresh();

            for (Camion camion : mesCamions) {
                camion.simulation();
            }
            for (Capteur capteur : mesCapteurs) {
                capteur.simulation();
            }
            this.UpdateDb();
        }
    }

    private void UpdateDb() {
        // generer a partir des deux listes des object json et les injecter dans les deux url que tom me fourni
    }

    private void Refresh(){
        // met a jour dp les urls des serveurs
    }


}
