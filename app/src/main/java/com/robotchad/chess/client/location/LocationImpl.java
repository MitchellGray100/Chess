package com.robotchad.chess.client.location;

public class LocationImpl implements Location {
    int xAxis;
    int yAxis;

    public LocationImpl(int xCord, int yCord) {
        setXAxis(xCord);
        setYAxis(yCord);
    }

    public int getXAxis() {
        return xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    public void setXAxis(int x) {
        xAxis = x;
    }

    public void setYAxis(int y) {
        yAxis = y;
    }
}
