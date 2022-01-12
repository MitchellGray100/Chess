package location;

public class LastLocationImpl implements LastLocation {

    /** The last location clicked on the chess board, may be null */
    private Location location;

    /**
     * Creates a new LastLocation object with an invalid location
     */
    public LastLocationImpl() {
        location = null;
    }

    /**
     * Creates a new LastLocation object with the given valid location
     * @param loc - the valid location
     */
    public LastLocationImpl(Location loc) {
        location = loc;
    }

    public boolean isValid() {
        return location == null;
    }

    public void validateLocation(Location newLocation) {
        location = newLocation;
    }

    public void invalidateLocation() {
        location = null;
    }

    public Location getLocation() {
        if (location == null) {
            throw new IllegalStateException();
        }
        return location;
    }

    public boolean equals(Object obj) {
        return location.equals(obj);
    }
}
