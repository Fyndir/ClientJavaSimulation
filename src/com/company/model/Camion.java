package com.company.model;
import com.company.Tool.HTTPTools;
import com.company.Tool.PolylineDecoder;
import java.io.IOException;
import java.util.List;

public class Camion {

    private CoordGeo coordActuel;

    private CoordGeo coordDest;

    private String immatriculation;

    private List<CoordGeo> trajet;

    private int i = 0;

    /**
     * Recupere le trajet du camion via l'api osrm
     */
    private void getTrajet() throws IOException, InterruptedException {
        // Recup avec l'api osrm
        String StrDecode = HTTPTools.get("https://router.project-osrm.org/route/v1/driving/"+getCoordActuel().getCoordY()+","+getCoordActuel().getCoordX()+";"+getCoordDest().getCoordY()+","+getCoordDest().getCoordX()+"?overview=full");

        List<CoordGeo> trajet =  PolylineDecoder.decode(StrDecode);
        this.trajet=trajet;
        this.i=0;

    }

    /**
     * getter de la propriete immatriculation
     * @return
     */
    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * Get de la propriete CoordActuel
     * @return
     */
    public CoordGeo getCoordActuel() {
        return coordActuel;
    }

    /**
     * Setter de la propriete CoordActuel
     * @param coordActuel
     */
    public void setCoordActuel(CoordGeo coordActuel) {
        this.coordActuel = coordActuel;
    }

    /**
     * getter de la propriete CoordDest
     * @return
     */
    public CoordGeo getCoordDest() {
        return coordDest;
    }

    /**
     * setter de la propriete CoordDest
     * @param coordDest
     */
    public void setCoordDest(CoordGeo coordDest) {
        this.coordDest = coordDest;
    }

    /**
     * Constructeur de la class camion
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
     * fait evoluer l'object en fct des regles de la simulations
     */
    public void simulation() throws IOException, InterruptedException {
        if (trajet == null) {
            // mettre un petit time out
            this.getTrajet();
        }
        if (!this.hasArrived()) {
            this.moveForward();
        }
    }

    /**
     * Permet de savoir si le camion est arrive a destination
     * @return
     */
    private boolean hasArrived() {
        return this.coordActuel.equals(this.getCoordDest());
    }

    /**
     * Avance le camion d'une coord sur son trajet
     */
    private void moveForward() {
        CoordGeo nouvellecoord = null;
        if (trajet == null) {
            // calcul de la coord avec la ligne droite
            nouvellecoord = this.getCoordActuel();
        } else {
            nouvellecoord = this.trajet.get(i); // trouver la nouvelle coord
            this.i++;
        }
        this.setCoordActuel(nouvellecoord);

    }

}
