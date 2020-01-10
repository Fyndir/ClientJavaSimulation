package com.company.model;

import java.util.List;
import java.util.Random;

public class Capteur {

    private CoordGeo coordActuel;

    private int id;

    private int intensite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter de l intensite
     * @return
     */
    public int getIntensite() {
        return intensite;
    }

    /**
     * setter de l intensite
     * @param intensite
     */
    public void setIntensite(int intensite) {
        this.intensite = intensite;
    }

    /**
     * getter CoordActuel
     * @return
     */
    public CoordGeo getCoordActuel() {
        return coordActuel;
    }

    /**
     * setter CoordActuel
     * @param coordActuel
     */
    public void setCoordActuel(CoordGeo coordActuel) {
        this.coordActuel = coordActuel;
    }

    /**
     * Constructeur de la classe
     * @param longitude
     * @param latitude
     * @param intensite
     */
    public Capteur(int id , float longitude, float latitude, int intensite) {
        this.setId(id);
        this.setIntensite(intensite);
        CoordGeo coord = new CoordGeo(longitude, latitude);
        setCoordActuel(coord);
    }

    /**
     * Detecte la presence d'un camion
     * @return
     */
    public boolean hasTruck(List<Camion> mesCamions) {
        int intensite = 0;
        for (Camion camion : mesCamions) {
            if (this.coordActuel.equals(camion.getCoordActuel())) {
                return true;
            }
        }
        return false;
    }

}
