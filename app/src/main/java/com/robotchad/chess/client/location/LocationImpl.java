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

    public boolean toBoolean() {
        return getXAxis() >= 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LocationImpl)) {
            return false;
        }
        return xAxis == ((LocationImpl) obj).getXAxis() && yAxis == ((LocationImpl) obj).getYAxis();
    }
}
