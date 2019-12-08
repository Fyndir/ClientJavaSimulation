package com.company.model;

import java.util.List;
import java.util.Random;

public class Capteur {

    public final int probaIncendie = 5;

    private CoordGeo coordActuel;

    private int intensite;

    private List<Camion> mesCamions;

    public List<Camion> getMesCamions() {
        return mesCamions;
    }

    public void setMesCamions(List<Camion> mesCamions) {
        this.mesCamions = mesCamions;
    }


    public int getIntensite() {
        return intensite;
    }

    public void setIntensite(int intensite) {
        this.intensite = intensite;
    }


    public CoordGeo getCoordActuel() {
        return coordActuel;
    }

    public void setCoordActuel(CoordGeo coordActuel) {
        this.coordActuel = coordActuel;
    }

    public void simulation() {

        if (this.getIntensite() != 0) {
            if (this.hasTruck()) {
                this.setIntensite(this.getIntensite() - 1);
            }
        } else {
            Random rand = new Random();
            int proba = rand.nextInt(100);

            if (proba < probaIncendie) {
                this.setIntensite(rand.nextInt(9));
            }
        }

    }

    private boolean hasTruck() {
        for (Camion camion : mesCamions) {
            if (this.coordActuel.equals(camion.getCoordActuel())) {
                return true;
            }
        }
        return false;
    }

}
