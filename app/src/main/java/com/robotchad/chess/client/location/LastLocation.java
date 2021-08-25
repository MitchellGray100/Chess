package com.robotchad.chess.client.location;

public interface LastLocation {
    /**
     * Returns if the last location is valid
     * @return true if last location is valid, false otherwise
     */
    boolean isValid();

    /**
     * Validates the last location with a new location
     * @param newLocation - the new validated location
     */
    void validateLocation(Location newLocation);

    /**
     * Invalidates the last location
     */
    void invalidateLocation();

    /**
     * Returns the last location if valid. Throws an IllegalStateException if the location is not
     * valid
     * @return the last validated location
     * @throws IllegalStateException if location is not valid
     */
    Location getLocation();
}
