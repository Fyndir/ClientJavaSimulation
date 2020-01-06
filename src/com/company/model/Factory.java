package com.company.model;

import com.company.Tool.HTTPTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Factory {

    /**
     * renvoi la list des camions renvoyer par l'api
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static List<Camion> GetListCamion() throws IOException, InterruptedException {
        List<Camion> camions = new ArrayList<Camion>();

        String result = HTTPTools.get("https://emergencymanager.azurewebsites.net/camion/get");
        if (result.contains("no data"))
        {
            return camions;
        }
        else
        {
                result = result.substring(2, result.length() - 3);
                String[] split = result.split("\\],\\[");

                for (String res : split) {

                    String[] resSplit = res.split(",");
                    camions.add(new Camion(new CoordGeo(Float.parseFloat(resSplit[0]),Float.parseFloat(resSplit[1])),new CoordGeo(Float.parseFloat(resSplit[2]),Float.parseFloat(resSplit[3])),resSplit[4]));
                }

                return camions;
        }

    }

    /**
     * renvoi la list des capteur renvoyer par l'api
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static List<Capteur> getListCapteur() throws IOException, InterruptedException {
        List<Capteur> capteurs = new ArrayList<Capteur>();

        String result = HTTPTools.get("https://emergencymanager.azurewebsites.net/fire/get");

        result = result.substring(2, result.length() - 3);
        String[] split = result.split("\\],\\[");

        for (String res : split) {

            String[] resSplit = res.split(",");
            capteurs.add(new Capteur(Float.parseFloat(resSplit[0]), Float.parseFloat(resSplit[1]), Integer.parseInt(resSplit[2])));
        }

        return capteurs;
    }
}
