package com.company.model;

import java.util.Objects;

public class CoordGeo {

    private float coordX;

    private float coordY;

    public float getCoordX() {
        return coordX;
    }

    public void setCoordX(float coordX) {
        this.coordX = coordX;
    }

    public float getCoordY() {
        return coordY;
    }

    public void setCoordY(float coordY) {
        this.coordY = coordY;
    }

    public CoordGeo(float coordX, float coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

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
