package com.company.model;

import java.io.IOException;
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


    public static Simulation initSimulation() throws IOException, InterruptedException {

        System.out.println("Init liste camion");

        List<Camion> mesCamions = factory.GetListCamion();

        System.out.println("Init liste capteur");

        List<Capteur> mesCapteurs = factory.getListCapteur();

        Simulation simu = new Simulation(mesCapteurs, mesCamions);

        return simu;
    }

    private void UpdateDb() {
        // generer a partir des deux listes des object json et les injecter dans les deux url que tom me fourni
    }

    private void Refresh() {

        System.out.println("refresh liste camion");

        System.out.println("refresh liste capteur");
    }


}
