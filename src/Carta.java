import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Carta {

    private int indice;

    // metodo constructor
    public Carta(Random r) {
        // se genera un valor entre 1 y 52
        indice = r.nextInt(52) + 1;
    }

    public void mostrar(JPanel pnl, int x, int y) {
        String nombreArchivo = "/Imagenes/CARTA" + indice + ".jpg";
        ImageIcon imgCarta=new ImageIcon(getClass().getResource(nombreArchivo));

        JLabel lbl=new JLabel();
        lbl.setIcon(imgCarta);
        lbl.setBounds(x, y, imgCarta.getIconWidth(), imgCarta.getIconHeight());

        pnl.add(lbl);
    }

}
