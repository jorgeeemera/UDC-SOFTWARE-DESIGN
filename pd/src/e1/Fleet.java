package e1;

import java.util.ArrayList;
import java.util.List;

class Fleet {
    private List<Ship> activeShips;
    private List<Ship> inactiveShips;
    private List<Ship> repairingShips;
    private double funds;

    public Fleet(double initialFunds) {
        activeShips = new ArrayList<>();
        inactiveShips = new ArrayList<>();
        repairingShips = new ArrayList<>();
        this.funds = initialFunds;
    }

    public void addShip(Ship ship) {
        activeShips.add(ship);
        System.out.println("Ship added to Fleet: " + ship.getName());
    }

    public void moveToInactive(Ship ship, String reason) {
        activeShips.remove(ship);
        ship.setInactive(reason);
        inactiveShips.add(ship);
        System.out.println("Ship declared inactive: " + ship.getName() + " | Reasons: " + reason);
    }

    public void startMission(Ship ship) {
        ship.startMission();
        System.out.println("Ship started mission: " + ship.getName());
    }

    public void completeMission(Ship ship) {
        ship.completeMission();
        this.addFunds(ship.getReward());
        System.out.println("Ship completed mission: " + ship.getName() + " | Reward: " + ship.getReward());
    }

    public void setUnderRepair(Ship ship) {
        activeShips.remove(ship);
        repairingShips.add(ship);
        ship.setUnderRepair();
        System.out.println("A repair request has been filed for " + ship.getName() + " | Expected repair cost: " + ship.getEstimatedRepair());
    }

    public void takeOutFromRepair(Ship ship) {
        if (!repairingShips.contains(ship)) {
            throw new IllegalArgumentException("El barco no está en la lista de reparación");
        }
        repairingShips.remove(ship);
        activeShips.add(ship);
        ship.setInBase();
        System.out.println("A repair request has been taken out from repair: " + ship.getName());
    }

    public void setRepaired(Ship ship, int cost) {
        if (deductFunds(cost)) {
            ship.setRepaired();
            ship.changeState(new InBaseState());
            repairingShips.remove(ship);
            activeShips.add(ship);
            System.out.println("A ship has been declared repaired: " + ship.getName() + " | Reparation cost: " + cost);
        } else {
            throw new IllegalStateException("Insufficient funds for repair.");
        }
    }


    public void setDamaged(Ship ship) {
        ship.setDamaged(true);
        System.out.println("A ship has been declared damaged: " + ship.getName());
    }

    public List<Ship> getActiveShips() {
        return new ArrayList<>(activeShips);
    }

    public List<Ship> getInactiveShips() {
        return new ArrayList<>(inactiveShips);
    }

    public List<Ship> getRepairingShips() {
        return new ArrayList<>(repairingShips);
    }

    public double getFunds() {
        return funds;
    }

    public void addFunds(double amount) {
        funds += amount;
    }

    public boolean deductFunds(double amount) {
        if (funds >= amount) {
            funds -= amount;
            return true;
        }
        return false;
    }
}