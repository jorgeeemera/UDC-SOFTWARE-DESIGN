package e1;

class UnderRepairState implements ShipState {
    private int estimatedReparationCost;

    public UnderRepairState(int estimatedReparationCost) {
        this.estimatedReparationCost = estimatedReparationCost;
    }

    @Override
    public void setMissionStarted(Ship ship) {
        throw new IllegalStateException("El barco está bajo reparación y no puede empezar una misión");
    }

    @Override
    public void setInBase(Ship ship) {
        ship.changeState(new InBaseState());
    }

    @Override
    public void setInactive(Ship ship, String reason) {
        ship.changeState(new InactiveState(reason));
    }

    @Override
    public void setUnderRepair(Ship ship) {
        throw new IllegalStateException("El barco ya está en reparación");
    }

    @Override
    public String toString() {
        return "Expected repair cost: " + estimatedReparationCost;
    }
}