import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Jugador {

    private int TOTAL_CARTAS = 10;
    private int MARGEN = 10;
    private int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];

    Random r = new Random();

    public void repartir() {
        for (int i = 0; i < cartas.length; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void ordenar(JPanel pnl) {
        pnl.removeAll();

        Map<NombreCarta, List<Carta>> grupos = new HashMap<>();
        for (Carta carta : cartas) {
            grupos.computeIfAbsent(carta.getNombre(), k -> new ArrayList<>()).add(carta);
        }

        List<Carta> ordenadas = new ArrayList<>();
        for (List<Carta> grupo : grupos.values()) {
            ordenadas.addAll(grupo);
        }

        ordenadas.sort((c1, c2) -> {
            int size1 = grupos.get(c1.getNombre()).size();
            int size2 = grupos.get(c2.getNombre()).size();
            if (size1 != size2) {
                return Integer.compare(size2, size1);
            }
            return c1.getNombre().ordinal() - c2.getNombre().ordinal();
        });

        for (int i = 0; i < cartas.length; i++) {
            cartas[i] = ordenadas.get(i);
        }

        mostrar(pnl);
    }

    public int calcularPuntajeNoAgrupadas() {
        int puntaje = 0;

        // Crear mapa para agrupar cartas por nombre
        Map<NombreCarta, List<Carta>> grupos = new HashMap<>();
        for (Carta carta : cartas) {
            grupos.computeIfAbsent(carta.getNombre(), k -> new ArrayList<>()).add(carta);
        }

        // Identificar cartas no agrupadas (las que están solas, sin formar grupos)
        List<Carta> noAgrupadas = new ArrayList<>();
        for (Map.Entry<NombreCarta, List<Carta>> entry : grupos.entrySet()) {
            if (entry.getValue().size() == 1) {
                // Solo añadir cartas que no forman parte de ningún grupo
                noAgrupadas.addAll(entry.getValue());
            }
        }

        // Calcular el puntaje solo de las cartas no agrupadas
        System.out.println("Cartas no agrupadas:");
        for (Carta carta : noAgrupadas) {
            System.out.println(carta.getNombre() + " - " + carta.getValor());
            puntaje += carta.getValor();
        }

        System.out.println("Puntaje de cartas no agrupadas: " + puntaje);
        return puntaje;

    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int posicion = MARGEN + cartas.length * DISTANCIA;
        for (Carta c : cartas) {
            c.mostrar(pnl, posicion, MARGEN);
            posicion -= DISTANCIA;
        }
        pnl.repaint();
    }

    public String getGrupos() {
        String mensaje = "No se encontraron grupos";

        int[] contadores = new int[NombreCarta.values().length];
        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }

        boolean hayGrupos = false;
        for (int c : contadores) {
            if (c >= 2) {
                hayGrupos = true;
                break;
            }
        }

        if (hayGrupos) {
            mensaje = "Se encontraron los siguientes grupos:\n";
            int p = 0;
            for (int c : contadores) {
                if (c >= 2) {
                    mensaje += Grupo.values()[c + 1] + " de " + NombreCarta.values()[p] + "\n";
                }
                p++;
            }
        }

        return mensaje;
    }

}
