package e1;

interface ShipState {
    void setMissionStarted(Ship ship);
    void setInBase(Ship ship);
    void setInactive(Ship ship, String reason);
    void setUnderRepair(Ship ship);
}
