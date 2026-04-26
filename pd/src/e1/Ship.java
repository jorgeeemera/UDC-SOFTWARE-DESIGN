package e1;

class Ship {
    private String name;
    private ShipType type;
    private ShipState currentState;
    private int completedMissions;
    private boolean damaged;

    public Ship(String name, ShipType type) {
        this.name = name;
        this.type = type;
        this.currentState = new InBaseState();
        this.completedMissions = 0;
        this.damaged = false;
    }

    public String getName() {
        return name;
    }

    public ShipType getType() {
        return type;
    }

    public int getCompletedMissions() {
        return completedMissions;
    }

    public void setCompletedMissions(int missions) {
        this.completedMissions = missions;
    }

    public void incrementCompletedMissions() {
        this.completedMissions++;
    }

    public boolean getDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void startMission() {
        currentState.setMissionStarted(this);
    }

    public void completeMission() {
        currentState.setInBase(this);
    }

    public void setInBase() {
        currentState.setInBase(this);
    }

    public void setRepaired() {
        if (damaged) {
            damaged = false; // El barco ya no está dañado
        } else {
            throw new IllegalStateException("El barco no estaba dañado");
        }
    }

    public void setInactive(String reason) {
        currentState.setInactive(this, reason);
    }

    public void setUnderRepair() {
        currentState.setUnderRepair(this);
    }

    public void changeState(ShipState newState) {
        this.currentState = newState;
    }

    public String getCurrentStateDetails() {
        return currentState.toString();
    }

    public int getEstimatedRepair(){
        return switch (type.getWeightClass()) {
            case "Ultraligero" -> 100;
            case "Ligero" -> 200;
            case "Pesado" -> 300;
            case "Ultrapesado" -> 400;
            default -> 0;
        };
    }

    public int getReward(){
        return switch (type.getWeightClass()) {
            case "Ultraligero" -> 100;
            case "Ligero" -> 200;
            case "Pesado" -> 300;
            case "Ultrapesado" -> 400;
            default -> 0;
        };
    }
}