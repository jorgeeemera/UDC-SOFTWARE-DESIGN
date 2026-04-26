package e1;

class InMissionState implements ShipState {
    @Override
    public void setMissionStarted(Ship ship) {
        throw new IllegalStateException("Un barco que está en una misión activa no puede empezar otra misión");
    }

    @Override
    public void setInBase(Ship ship) {
        ship.changeState(new InBaseState());
        ship.incrementCompletedMissions();
    }

    @Override
    public void setInactive(Ship ship, String reason) {
        ship.changeState(new InactiveState(reason));
    }

    @Override
    public void setUnderRepair(Ship ship) {
        throw new IllegalStateException("No se puede reparar un barco en medio de una misión");
    }

    @Override
    public String toString() {
        return "Ship under mission";
    }
}

