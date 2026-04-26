package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NavalFleetTest {
    private Fleet fleet;
    private Ship ultraLightShip;
    private Ship heavyShip;
    private Ship mediumShip;

    @BeforeEach
    void setUp() {
        fleet = new Fleet(1000000);
        ultraLightShip = new Ship("DE Test", ShipType.DE);
        heavyShip = new Ship("CA Test", ShipType.CA);
        mediumShip = new Ship("CL Test", ShipType.CL);
        fleet.addShip(ultraLightShip);
        fleet.addShip(heavyShip);
        fleet.addShip(mediumShip);
    }

    @Test
    void testShipInitialState() {
        assertEquals("DE Test", ultraLightShip.getName());
        assertEquals(ShipType.DE, ultraLightShip.getType());
        assertFalse(ultraLightShip.isDamaged());
        assertEquals(0, ultraLightShip.getCompletedMissions());
    }

    @Test
    void testMissionLifecycle() {
        ultraLightShip.startMission();
        fleet.completeMission(ultraLightShip);

        assertEquals(1, ultraLightShip.getCompletedMissions());
        assertTrue(ultraLightShip.getCurrentStateDetails().contains("Ship in base"));
    }

    @Test
    void testRepairProcess() {
        fleet.addShip(ultraLightShip);
        fleet.setDamaged(ultraLightShip);

        assertTrue(ultraLightShip.isDamaged(), "El barco debería estar marcado como dañado");

        fleet.setUnderRepair(ultraLightShip);
        assertTrue(ultraLightShip.getCurrentStateDetails().contains("Expected repair cost"),
                "El barco debería estar en estado de reparación");

        int repairCost = ultraLightShip.getEstimatedRepair();
        fleet.setRepaired(ultraLightShip, repairCost);

        assertFalse(ultraLightShip.isDamaged(), "El barco no debería estar dañado tras la reparación");

        String stateDetails = ultraLightShip.getCurrentStateDetails();
        assertTrue(stateDetails.contains("Ship in base"),
                "El barco debería estar en estado 'Ship in base' tras la reparación. Estado actual: " + stateDetails);
    }



    @Test
    void testInactivation() {
        fleet.moveToInactive(ultraLightShip, "Decommissioned");

        assertEquals(2, fleet.getActiveShips().size());
        assertEquals(1, fleet.getInactiveShips().size());
        assertTrue(ultraLightShip.getCurrentStateDetails().contains("Decommissioned"));
    }

    @Test
    void testFundsManagement() {
        assertEquals(1000000, fleet.getFunds());

        fleet.addFunds(500000);
        assertEquals(1500000, fleet.getFunds());

        assertTrue(fleet.deductFunds(200000));
        assertEquals(1300000, fleet.getFunds());

        assertFalse(fleet.deductFunds(1500000));
        assertEquals(1300000, fleet.getFunds());
    }

    @Test
    void testMissionRewards() {
        assertEquals(100, ultraLightShip.getReward());
        assertEquals(300, heavyShip.getReward());
    }

    @Test
    void testShipTypeProperties() {
        assertEquals("Destructor de Escolta", ShipType.DE.getFullName());
        assertEquals("Ultraligero", ShipType.DE.getWeightClass());

        assertEquals("Crucero Pesado", ShipType.CA.getFullName());
        assertEquals("Pesado", ShipType.CA.getWeightClass());

        assertEquals("Acorazado", ShipType.BB.getFullName());
        assertEquals("Ultrapesado", ShipType.BB.getWeightClass());
    }

    @Test
    void testInvalidMissionScenarios() {
        assertThrows(IllegalStateException.class, () -> ultraLightShip.completeMission());

        ultraLightShip.setDamaged(true);
        assertThrows(IllegalStateException.class, () -> ultraLightShip.startMission());
    }

    @Test
    void testRepairCost() {
        assertEquals(100, ultraLightShip.getEstimatedRepair());
        assertEquals(300, heavyShip.getEstimatedRepair());
    }

    @Test
    void testRepairOnUndamagedShip() {
        assertThrows(IllegalStateException.class, () -> ultraLightShip.setRepaired());
    }

    @Test
    void testAddShipAndFundsManagement() {
        Fleet fleet = new Fleet(1000);

        Ship ship1 = new Ship("DE Test", ShipType.DE);
        Ship ship2 = new Ship("CA Test", ShipType.CA);

        fleet.addShip(ship1);
        fleet.addShip(ship2);

        assertEquals(2, fleet.getActiveShips().size(), "Debería haber 2 barcos activos en la flota");
        assertEquals(1000, fleet.getFunds(), "Los fondos iniciales deben ser 1000");

        fleet.addFunds(500);
        assertEquals(1500, fleet.getFunds(), "Los fondos deberían incrementarse a 1500");

        boolean success = fleet.deductFunds(300);
        assertTrue(success, "Debería ser posible deducir 300 fondos");
        assertEquals(1200, fleet.getFunds(), "Los fondos deberían reducirse a 1200");

        boolean fail = fleet.deductFunds(2000);
        assertFalse(fail, "No debería ser posible deducir más fondos de los disponibles");
        assertEquals(1200, fleet.getFunds(), "Los fondos deberían permanecer en 1200");
    }

    @Test
    void testStateTransitions() {
        Ship ship = new Ship("CL Test", ShipType.CL);

        assertEquals("Ship in base", ship.getCurrentStateDetails(), "El barco debería comenzar en estado 'Ship in base'");

        ship.startMission();
        assertEquals("Ship under mission", ship.getCurrentStateDetails(), "El barco debería estar en misión");

        ship.completeMission();
        assertEquals("Ship in base", ship.getCurrentStateDetails(), "El barco debería regresar a la base tras completar la misión");

        ship.setInactive("Decommissioned");
        assertEquals("Inactive: Decommissioned", ship.getCurrentStateDetails(), "El barco debería estar inactivo con la razón proporcionada");
    }

    @Test
    void testExceptionsInStates() {
        Ship ship = new Ship("BB Test", ShipType.BB);

        ship.setDamaged(true);
        assertThrows(IllegalStateException.class, ship::startMission, "No debería permitir iniciar misión si el barco está dañado");

        ship.setDamaged(false);
        assertThrows(IllegalStateException.class, ship::setRepaired, "No debería permitir reparar un barco que no está dañado");

        ship.setUnderRepair();
        assertThrows(IllegalStateException.class, ship::startMission, "No debería permitir iniciar misión desde estado 'UnderRepair'");

        assertThrows(IllegalStateException.class, ship::setUnderRepair, "No debería permitir poner en reparación un barco ya en reparación");
    }

    @Test
    void testFleetWithMultipleStates() {
        Fleet fleet = new Fleet(1000);

        Ship activeShip = new Ship("DE Test", ShipType.DE);
        Ship inactiveShip = new Ship("CV Test", ShipType.CV);
        Ship repairingShip = new Ship("CL Test", ShipType.CL);

        fleet.addShip(activeShip);
        fleet.addShip(inactiveShip);
        fleet.addShip(repairingShip);

        fleet.moveToInactive(inactiveShip, "Decommissioned");
        assertEquals(1, fleet.getInactiveShips().size(), "Debería haber un barco inactivo");

        fleet.setUnderRepair(repairingShip);
        assertEquals(1, fleet.getRepairingShips().size(), "Debería haber un barco en reparación");

        assertEquals(1, fleet.getActiveShips().size(), "Debería haber un barco activo");
    }

    @Test
    void testMissionRewards2() {
        Fleet fleet = new Fleet(500);

        Ship ship = new Ship("CA Test", ShipType.CA);
        fleet.addShip(ship);

        fleet.startMission(ship);
        assertEquals("Ship under mission", ship.getCurrentStateDetails(), "El barco debería estar en misión");

        fleet.completeMission(ship);
        assertEquals("Ship in base", ship.getCurrentStateDetails(), "El barco debería estar de vuelta en la base");
        assertEquals(1, ship.getCompletedMissions(), "El barco debería tener 1 misión completada");
        assertEquals(800, fleet.getFunds(), "Los fondos deberían incrementarse con la recompensa de la misión");
    }


    @Test
    void testTakeOutFromRepair() {
        Fleet fleet = new Fleet(1000);

        Ship repairingShip = new Ship("CL Test", ShipType.CL);
        Ship nonRepairingShip = new Ship("DE Test", ShipType.DE);

        fleet.addShip(repairingShip);
        fleet.addShip(nonRepairingShip);

        fleet.setUnderRepair(repairingShip);
        assertTrue(fleet.getRepairingShips().contains(repairingShip), "El barco debería estar en la lista de reparación");

        fleet.takeOutFromRepair(repairingShip);
        assertFalse(fleet.getRepairingShips().contains(repairingShip), "El barco no debería estar en la lista de reparación");
        assertTrue(fleet.getActiveShips().contains(repairingShip), "El barco debería estar en la lista de activos");
        assertEquals("Ship in base", repairingShip.getCurrentStateDetails(), "El barco debería estar en estado 'Ship in base'");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fleet.takeOutFromRepair(nonRepairingShip),
                "Debería lanzar una excepción si se intenta retirar un barco que no está en reparación");
        assertEquals("El barco no está en la lista de reparación", exception.getMessage());
    }
}

