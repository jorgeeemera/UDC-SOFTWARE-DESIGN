package e1;

import java.util.ArrayList;
import java.util.List;

public class BattleSimulator {

    private Aldea attacker;
    private Aldea defender;

    public BattleSimulator(Aldea attacker, Aldea defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public List<String> simulateBattle() {
        List<String> battleReport = new ArrayList<>();

        // Iniciar la batalla
        battleReport.add("### Starts the battle ! --> " + attacker.getName() + " Attacks " + defender.getName() + " ! ###");

        // Ejércitos del atacante
        battleReport.add(attacker.getName() + " have the following soldiery:");
        attacker.getArmy().forEach(soldier -> battleReport.add(soldier.toString()));
        double attackPower = attacker.calculateAttackingPower();
        battleReport.add("Total " + attacker.getName() + " attack power : " + attackPower);

        // Ejércitos del defensor
        battleReport.add(defender.getName() + " have the following soldiery:");
        defender.getArmy().forEach(soldier -> battleReport.add(soldier.toString()));
        double defensePower = defender.calculateDefensePower();
        battleReport.add("Total " + defender.getName() + " defense power : " + defensePower);

        // Determinación del ganador
        String winner = attackPower > defensePower
                ? attacker.getName() + " with an age of " + attacker.getAge() + " years WINS!"
                : defender.getName() + " with an age of " + defender.getAge() + " years WINS!";
        battleReport.add(winner);

        return battleReport;
    }
}
