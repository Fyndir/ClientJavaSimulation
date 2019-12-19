package com.company.model;

import com.company.Tool.HTTPTools;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Object contenant les object de la simulation
 */
public class Simulation {

    public List<Capteur> mesCapteurs;

    public List<Camion> mesCamions;

    /**
     * Constructeur de la simulation
     *
     * @param mesCapteurs
     * @param mesCamions
     */
    public Simulation(List<Capteur> mesCapteurs, List<Camion> mesCamions) {
        this.mesCapteurs = mesCapteurs;
        this.mesCamions = mesCamions;
    }

    /**
     * Permet de lancer la simulation en parcourant la liste des objects qui la compose et lance leur methode simulation
     *
     * @throws InterruptedException
     * @throws IOException
     */
    public void run() throws InterruptedException, IOException {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                System.out.println("erreur de sleep");
            }
            Refresh();

            System.out.println("Run des camions");
            for (Camion camion : mesCamions) {
                camion.simulation();
            }
            System.out.println("Run des capteurs");
            for (Capteur capteur : mesCapteurs) {
                capteur.run();
            }
            System.out.println("Maj Bdd et simu");

            this.UpdateDb();
            this.Refresh();
        }
    }

    /**
     * Retourne une simulation initialiser avec les données de la bdd recup via l'api
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static Simulation initSimulation() throws IOException, InterruptedException {

        System.out.println("Init liste camion");

        List<Camion> mesCamions = Factory.GetListCamion();

        System.out.println("Init liste capteur");

        List<Capteur> mesCapteurs = Factory.getListCapteur();

        for (Capteur cap : mesCapteurs) {
            cap.setMesCamions(mesCamions);
        }

        Simulation simu = new Simulation(mesCapteurs, mesCamions);

        return simu;
    }

    /**
     * Met a jour la base de donnée en envoyant les données sur l'api
     *
     * @throws IOException
     */
    private void UpdateDb() throws IOException {

        String StrCapteur = "";

        for (Capteur cap : mesCapteurs) {
            StrCapteur += cap.getCoordActuel().getCoordX() + "," + cap.getCoordActuel().getCoordY() + "," + cap.getIntensite() + ";";
        }

        HTTPTools.post("https://cpefiresimulation.azurewebsites.net/send", StrCapteur.substring(0, StrCapteur.length() - 1));

        String StrCamion = "";

        for (Camion cam : mesCamions) {
            StrCamion += cam.getCoordActuel().getCoordX() + "," + cam.getCoordActuel().getCoordY() + "," + cam.getImmatriculation() + ";";
        }

        HTTPTools.post("https://emergencymanager.azurewebsites.net/camion/send", StrCapteur.substring(0, StrCapteur.length() - 1));



    }

    /**
     * Refresh les camions parce que l'evolution des capteurs est gerer par l'appli
     *
     * @throws IOException
     * @throws InterruptedException
     */
    private void Refresh() throws IOException, InterruptedException {

        System.out.println("refresh liste camion");
        mesCamions.clear();
        mesCamions.addAll(Factory.GetListCamion());
    }


}
