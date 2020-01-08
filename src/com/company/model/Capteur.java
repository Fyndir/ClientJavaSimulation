package com.company.model;

import java.util.List;
import java.util.Random;

public class Capteur {

    public final int probaIncendie = 0;

    private CoordGeo coordActuel;

    private int intensite;

    private List<Camion> mesCamions;

    /**
     * getter des camions
     * @return
     */
    public List<Camion> getMesCamions() {
        return mesCamions;
    }

    /**
     * setter des camions
     * @param mesCamions
     */
    public void setMesCamions(List<Camion> mesCamions) {
        this.mesCamions = mesCamions;
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
     * Fait evoluer l'object en fct des regles metier
     */
    public void run() {

        if (this.getIntensite() != 0) {
            if (this.hasTruck()) {
                this.setIntensite(this.getIntensite() - 1);
            }
        } else {
            Random rand = new Random();
            int proba = rand.nextInt(1000);

            if (proba < probaIncendie) {
                this.setIntensite(rand.nextInt(9));
            }
        }

    }

    /**
     * Detecte la presence d'un camion
     * @return
     */
    private boolean hasTruck() {
        int intensite = 0;
        for (Camion camion : mesCamions) {
            if (this.coordActuel.equals(camion.getCoordActuel())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Constructeur de la classe
     * @param longitude
     * @param latitude
     * @param intensite
     */
    public Capteur(float longitude, float latitude, int intensite) {
        this.setIntensite(intensite);
        CoordGeo coord = new CoordGeo(longitude, latitude);
        setCoordActuel(coord);
    }

    @Override
    public String toString() {
        return "Capteur{" +
                "probaIncendie=" + probaIncendie +
                ", coordActuel=" + coordActuel.toString() +
                ", intensite=" + intensite +
                ", mesCamions=" + mesCamions +
                '}';
    }
}
