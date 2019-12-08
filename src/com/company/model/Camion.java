package com.company.model;

public class Camion {

    private CoordGeo coordActuel;

    private CoordGeo coordDest;

    private String immatriculation;

    public String getImmatriculation() {
        return immatriculation;
    }

    public CoordGeo getCoordActuel() {
        return coordActuel;
    }

    public void setCoordActuel(CoordGeo coordActuel) {
        this.coordActuel = coordActuel;
    }

    public CoordGeo getCoordDest() {
        return coordDest;
    }

    public void setCoordDest(CoordGeo coordDest) {
        this.coordDest = coordDest;
    }

    public Camion(CoordGeo coordActuel, CoordGeo coordDest, String immatriculation) {
        this.coordActuel = coordActuel;
        this.coordDest = coordDest;
        this.immatriculation = immatriculation;
    }

    public void simulation() {
        if (!this.hasArrived()) {
            this.moveForward();
        }
    }

    private boolean hasArrived() {
        return this.coordActuel.equals(this.getCoordDest());
    }

    private void moveForward() {
        CoordGeo nouvelledestination = null; // trouver la nouvelle coord
        this.setCoordActuel(nouvelledestination);
    }
}
