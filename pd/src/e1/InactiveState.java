package e1;

class InactiveState implements ShipState {
    private String reason;

    public InactiveState(String reason) {
        this.reason = reason;
    }

    @Override
    public void setMissionStarted(Ship ship) {
        throw new IllegalStateException("El barco está inactivo");
    }

    @Override
    public void setInBase(Ship ship) {
        throw new IllegalStateException("El barco está inactivo");
    }

    @Override
    public void setInactive(Ship ship, String reason) {
        this.reason = reason;
    }

    @Override
    public void setUnderRepair(Ship ship) {
        throw new IllegalStateException("El barco está inactivo y no se puede reparar");
    }

    @Override
    public String toString() {
        return "Inactive: " + reason;
    }
}