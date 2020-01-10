package com.company.model;

import com.company.Tool.HTTPTools;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Camion {

    private CoordGeo coordActuel;

    private CoordGeo coordDest;

    private String immatriculation;

    public List<CoordGeo> trajet;

    public String StrDecode ;

    private int i = 0;

    /**
     * Recupere le trajet du camion via l'api osrm
     */
    public void getTrajet() throws IOException, InterruptedException {

        try {
            // Recup avec l'api osrm
            this.StrDecode = HTTPTools.get("https://router.project-osrm.org/route/v1/driving/" + getCoordActuel().getCoordY() + "," + getCoordActuel().getCoordX() + ";" + getCoordDest().getCoordY() + "," + getCoordDest().getCoordX() + "?geometries=geojson");

            JSONObject decode = new JSONObject(this.StrDecode);
            JSONArray test = decode.getJSONArray("routes");
            JSONArray route = test.getJSONObject(0).getJSONObject("geometry").getJSONArray("coordinates");
            List<CoordGeo> trajet = new ArrayList<>();
            float x , y  = 0;
            for (var coord : route)
            {
                x = ((JSONArray) coord).getFloat(1);
                y = ((JSONArray) coord).getFloat(0);
                trajet.add(new CoordGeo(x,y));
            }
            this.trajet = trajet;
            this.trajet.add(this.getCoordDest());
            this.i = 0;
        } catch (Exception e ) {
            System.out.println(this.StrDecode);
            System.out.println("Erreur de recup de trajet du camion :"+ this.getImmatriculation());
            System.out.println("https://router.project-osrm.org/route/v1/driving/" + getCoordActuel().getCoordY() + "," + getCoordActuel().getCoordX() + ";" + getCoordDest().getCoordY() + "," + getCoordDest().getCoordX() + "?geometries=geojson");
        }

    }

    /**
     * getter de la propriete immatriculation
     *
     * @return
     */
    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * Get de la propriete CoordActuel
     *
     * @return
     */
    public CoordGeo getCoordActuel() {
        return coordActuel;
    }

    /**
     * Setter de la propriete CoordActuel
     *
     * @param coordActuel
     */
    public void setCoordActuel(CoordGeo coordActuel) {
        this.coordActuel = coordActuel;
    }

    /**
     * getter de la propriete CoordDest
     *
     * @return
     */
    public CoordGeo getCoordDest() {
        return coordDest;
    }

    /**
     * setter de la propriete CoordDest
     *
     * @param coordDest
     */
    public void setCoordDest(CoordGeo coordDest) {
        this.coordDest = coordDest;
    }

    /**
     * Constructeur de la class camion
     *
     * @param coordActuel
     * @param coordDest
     * @param immatriculation
     */
    public Camion(CoordGeo coordActuel, CoordGeo coordDest, String immatriculation) {
        this.coordActuel = coordActuel;
        this.coordDest = coordDest;
        this.immatriculation = immatriculation;
    }

    /**
     * Permet de savoir si le camion est arrive a destination
     *
     * @return
     */
    public boolean hasArrived() {
        return this.coordActuel.equals(this.getCoordDest());
    }

    /**
     * Avance le camion d'une coord sur son trajet
     */
    public void moveForward() {
        CoordGeo nouvellecoord = null;
        if (trajet == null) {
            // calcul de la coord avec la ligne droite
            nouvellecoord = this.getCoordActuel();
        } else {
            nouvellecoord = this.trajet.get(i); // trouver la nouvelle coord
            if (i != this.trajet.size()) {
                this.i++;
            }
            else
            {
                System.out.println("le camion "+this.immatriculation+" est arrivé");
            }
        }
        this.setCoordActuel(nouvellecoord);

    }

    /**
     * reset le trajet d'un camion
     */
    public void resetTrajet() {
        this.trajet = null;
    }
}
