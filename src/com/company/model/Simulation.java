package com.company.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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


    public static Simulation initSimulation() {

        System.out.println("Init liste camion");

        List<Camion> mesCamions = null;

        System.out.println("Init liste capteur");

        List<Capteur> mesCapteurs = null;

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
