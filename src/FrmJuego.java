import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FrmJuego extends JFrame {

    private JButton btnRepartir;
    private JButton btnVerificar;
    private JLabel lblPuntaje1, lblPuntaje2;
    private JPanel pnlJugador1;
    private JPanel pnlJugador2;
    private JTabbedPane tpJugadores;
    private int puntaje1, puntaje2;

    private Jugador jugador1, jugador2;

    public FrmJuego() {
        btnRepartir = new JButton();
        btnVerificar = new JButton();
        lblPuntaje1 = new JLabel("Puntaje: 0");
        lblPuntaje2 = new JLabel("Puntaje: 0");
        tpJugadores = new JTabbedPane();
        pnlJugador1 = new JPanel();
        pnlJugador2 = new JPanel();

        setSize(600, 300);
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pnlJugador1.setBackground(new Color(153, 255, 51));
        pnlJugador1.setLayout(null);
        pnlJugador2.setBackground(new Color(0, 255, 255));
        pnlJugador2.setLayout(null);

        tpJugadores.setBounds(10, 40, 550, 170);
        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raul Vidal", pnlJugador2);

        btnRepartir.setBounds(10, 10, 100, 25);
        btnRepartir.setText("Repartir");
        btnRepartir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRepartirClick(evt);
            }
        });

        btnVerificar.setBounds(120, 10, 100, 25);
        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVerificarClick(evt);
            }
        });

        lblPuntaje1.setBounds(230, 10, 200, 25);
        lblPuntaje2.setBounds(230, 10, 200, 25);

        getContentPane().setLayout(null);
        getContentPane().add(tpJugadores);
        getContentPane().add(btnRepartir);
        getContentPane().add(btnVerificar);
        getContentPane().add(lblPuntaje1);
        getContentPane().add(lblPuntaje2);

        jugador1 = new Jugador();
        jugador2 = new Jugador();

        // Añadir ChangeListener al JTabbedPane
        tpJugadores.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                actualizarVisibilidadPuntaje();
            }
        });

        // Inicializar la visibilidad de los puntajes
        actualizarVisibilidadPuntaje();
    }

    private void btnRepartirClick(ActionEvent evt) {
        jugador1.repartir();
        jugador1.mostrar(pnlJugador1);

        jugador2.repartir();
        jugador2.mostrar(pnlJugador2);
    }

    private void btnVerificarClick(ActionEvent evt) {
        puntaje1 = 0;
        puntaje2 = 0;
        switch (tpJugadores.getSelectedIndex()) {
            case 0:
                puntaje1 = jugador1.calcularPuntajeNoAgrupadas();
                jugador1.ordenar(pnlJugador1);
                JOptionPane.showMessageDialog(null, jugador1.getGrupos());
                break;
            case 1:
                puntaje2 = jugador2.calcularPuntajeNoAgrupadas();
                jugador2.ordenar(pnlJugador2);
                JOptionPane.showMessageDialog(null, jugador2.getGrupos());
                break;
        }

        // Actualizar el JLabel con el puntaje calculado
        lblPuntaje1.setText("Puntaje: " + puntaje1);
        lblPuntaje2.setText("Puntaje: " + puntaje2);

        // Actualizar la visibilidad de los puntajes
        actualizarVisibilidadPuntaje();
    }

    private void actualizarVisibilidadPuntaje() {
        if (tpJugadores.getSelectedIndex() == 0) {
            lblPuntaje1.setVisible(true);
            lblPuntaje2.setVisible(false);
        } else {
            lblPuntaje1.setVisible(false);
            lblPuntaje2.setVisible(true);
        }
    }
}