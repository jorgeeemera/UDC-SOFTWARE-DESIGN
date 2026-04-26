package e1;

import java.util.List;
import java.util.ArrayList;

public class AldeaGala extends Aldea {

    public AldeaGala(String name, int years, int wallLevel, List<Unidad> army) {
        super(name, years, wallLevel, army != null ? army : new ArrayList<>()); // Asegurar que el ejército nunca es
                                                                                // nulo
    }

    @Override
    public double calculateAttackingPower() {
        double poderAtaque = 0;
        for (Unidad unidad : army) {
            poderAtaque += unidad.getAttackingPoints();
        }
        return poderAtaque * 1.20; // 20% bonus de ataque para Galos
    }

    @Override
    public double calculateDefensePower() {
        double poderDefensa = 0;
        for (Unidad unidad : army) {
            poderDefensa += unidad.getDefensePoints();
        }
        return poderDefensa + (1.5 * wallResistance); // Bonus de defensa por muralla
    }
}
