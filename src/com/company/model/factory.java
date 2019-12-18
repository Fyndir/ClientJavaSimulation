package com.company.model;

import com.company.Tool.HTTPTools;

import java.io.IOException;
import java.util.List;

public class factory {

    public static List<Camion> GetListCamion() throws IOException, InterruptedException {
        List<Camion> camions = null;

        HTTPTools.get("https://emergencymanager.azurewebsites.net/camion/get");

        return camions;
    }

    public static List<Capteur> getListCapteur() throws IOException, InterruptedException {
        List<Capteur> capteurs = null;

        String result = HTTPTools.get("https://emergencymanager.azurewebsites.net/fire/get");

        System.out.println(result);

        return capteurs;
    }
}
