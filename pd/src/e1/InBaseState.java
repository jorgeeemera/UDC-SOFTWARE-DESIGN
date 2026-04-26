package e1;

class InBaseState implements ShipState {
    @Override
    public void setMissionStarted(Ship ship) {
        if (!ship.isDamaged()) {
            ship.changeState(new InMissionState());
            ship.setCompletedMissions(ship.getCompletedMissions()); // Mantener contador
        } else {
            throw new IllegalStateException("El barco está dañado y no puede empezar una misión");
        }
    }

    @Override
    public void setInBase(Ship ship) {
        throw new IllegalStateException("El barco ya está en la base");
    }

    @Override
    public void setInactive(Ship ship, String reason) {
        ship.changeState(new InactiveState(reason));
    }

    @Override
    public void setUnderRepair(Ship ship) {
        ship.changeState(new UnderRepairState(ship.getEstimatedRepair()));
    }

    @Override
    public String toString() {
        return "Ship in base";
    }
}