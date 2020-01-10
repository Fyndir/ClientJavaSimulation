package com.company.model;

import java.util.Objects;

public class CoordGeo {

    private float coordX;

    private float coordY;

    /**
     * getter CoordX
     *
     * @return
     */
    public float getCoordX() {
        return coordX;
    }

    /**
     * getter CoordY
     *
     * @return
     */
    public float getCoordY() {
        return coordY;
    }

    /**
     * Constructeur
     *
     * @param coordX
     * @param coordY
     */
    public CoordGeo(float coordX, float coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    /**
     * Override methode equals
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordGeo coordGeo = (CoordGeo) o;
        return Float.compare(coordGeo.coordX, coordX) == 0 &&
                Float.compare(coordGeo.coordY, coordY) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordX, coordY);
    }

}
