package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BattleSimulatorTest {

    private BattleSimulator simulator;

    @BeforeEach
    public void setUp() {
        // Este método se ejecutará antes de cada prueba individual.
    }

    @Test
    public void testSimulateBattleRomanAttacksGalo() {
        // Romanos atacan a Galos
        Aldea romana = new AldeaRomana("Roman Village", 500, 3, Arrays.asList(
                new Pretoriano(true), new Pretoriano(false), new EquitesImperatoris()
        ));
        Aldea galo = new AldeaGala("Gallic Village", 400, 4, Arrays.asList(
                new RayoDeTeutates(true), new Falange(), new Druida()
        ));
        simulator = new BattleSimulator(romana, galo);

        List<String> result = simulator.simulateBattle();

        assertTrue(result.contains("### Starts the battle ! --> Roman Village Attacks Gallic Village ! ###"));
        assertTrue(result.stream().anyMatch(line -> line.contains("Roman Village have the following soldiery:")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Gallic Village have the following soldiery:")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Total Roman Village attack power")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Total Gallic Village defense power")));
        assertTrue(result.getLast().contains("WINS!"));
    }

    @Test
    public void testSimulateBattleGaloAttacksTeuton() {
        // Galos atacan a Teutones
        Aldea galo = new AldeaGala("Gallic Village", 450, 2, Arrays.asList(
                new Druida(), new RayoDeTeutates(false), new Falange()
        ));
        Aldea teuton = new AldeaTeutona("Teutonic Village", 400, 5, Arrays.asList(
                new GuerreroDeMaza(), new GuerreroDeHacha(true), new Paladin()
        ));
        simulator = new BattleSimulator(galo, teuton);

        List<String> result = simulator.simulateBattle();

        assertTrue(result.contains("### Starts the battle ! --> Gallic Village Attacks Teutonic Village ! ###"));
        assertTrue(result.stream().anyMatch(line -> line.contains("Gallic Village have the following soldiery:")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Teutonic Village have the following soldiery:")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Total Gallic Village attack power")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Total Teutonic Village defense power")));
        assertTrue(result.getLast().contains("WINS!"));
    }

    @Test
    public void testSimulateBattleTeutonAttacksRoman() {
        // Teutones atacan a Romanos
        Aldea teuton = new AldeaTeutona("Teutonic Village", 320, 1, Arrays.asList(
                new GuerreroDeHacha(false), new GuerreroDeMaza(), new Paladin()
        ));
        Aldea romana = new AldeaRomana("Roman Village", 600, 4, Arrays.asList(
                new Legionario(), new Pretoriano(true), new EquitesImperatoris()
        ));
        simulator = new BattleSimulator(teuton, romana);

        List<String> result = simulator.simulateBattle();

        assertTrue(result.contains("### Starts the battle ! --> Teutonic Village Attacks Roman Village ! ###"));
        assertTrue(result.stream().anyMatch(line -> line.contains("Teutonic Village have the following soldiery:")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Roman Village have the following soldiery:")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Total Teutonic Village attack power")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Total Roman Village defense power")));
        assertTrue(result.getLast().contains("WINS!"));
    }

    @Test
    public void testSimulateBattleRomanAttacksTeuton() {
        // Romanos atacan a Teutones con tropas diferentes
        Aldea romana = new AldeaRomana("Roman Village", 550, 6, Arrays.asList(
                new Legionario(), new Legionario(), new EquitesImperatoris()
        ));
        Aldea teuton = new AldeaTeutona("Teutonic Village", 300, 3, Arrays.asList(
                new GuerreroDeHacha(true), new GuerreroDeMaza(), new Paladin()
        ));
        simulator = new BattleSimulator(romana, teuton);

        List<String> result = simulator.simulateBattle();

        assertTrue(result.contains("### Starts the battle ! --> Roman Village Attacks Teutonic Village ! ###"));
        assertTrue(result.stream().anyMatch(line -> line.contains("Roman Village have the following soldiery:")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Teutonic Village have the following soldiery:")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Total Roman Village attack power")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Total Teutonic Village defense power")));
        assertTrue(result.getLast().contains("WINS!"));
    }

    @Test
    public void testSimulateBattleGaloAttacksRoman() {
        // Galos atacan a Romanos con tropas de alta defensa
        Aldea galo = new AldeaGala("Gallic Village", 470, 2, Arrays.asList(
                new RayoDeTeutates(false), new Falange(), new Druida()
        ));
        Aldea romana = new AldeaRomana("Roman Village", 500, 5, Arrays.asList(
                new Pretoriano(false), new Pretoriano(true), new Legionario()
        ));
        simulator = new BattleSimulator(galo, romana);

        List<String> result = simulator.simulateBattle();

        assertTrue(result.contains("### Starts the battle ! --> Gallic Village Attacks Roman Village ! ###"));
        assertTrue(result.stream().anyMatch(line -> line.contains("Gallic Village have the following soldiery:")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Roman Village have the following soldiery:")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Total Gallic Village attack power")));
        assertTrue(result.stream().anyMatch(line -> line.contains("Total Roman Village defense power")));
        assertTrue(result.getLast().contains("WINS!"));
    }
}
