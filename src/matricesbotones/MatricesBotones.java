package matricesbotones;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

public class MatricesBotones extends JFrame {

    //Variables globales
    private JPanel panelBotones;
    private int jugada = 0;
    private static String[][] matrix;
    private String letra;
    private final Font font = new Font("Times New Roman", Font.BOLD, 18);

    public static void main(String args[]) {

        int filas = 0, columnas = 0;
        do {
            filas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese las Filas: \n"));
            columnas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese las Columnas: \n"));

            if (filas != columnas) {
                JOptionPane.showMessageDialog(null, "Ingresa el mismo valor para las columnas y filas \n");
            }

        } while (filas != columnas);

        MatricesBotones v = new MatricesBotones(filas, columnas);

        matrix = new String[filas][columnas];

    }

    public MatricesBotones(int filas, int columnas) {
        initComponents(filas, columnas);
    }

    private void initComponents(int filas, int columnas) {
        setLocationRelativeTo(null);
        panelBotones = new JPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        getContentPane().setLocation(100, 100);
        panelBotones.setLayout(new GridLayout(filas, columnas, 0,0));

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(120, 120));
                Font font = new Font("Arial", Font.BOLD, 60);
                boton.setFont(font);
                boton.setText(" ");
                panelBotones.add(boton);

                final int x = i;
                final int y = j;

                boton.addActionListener((ActionEvent evt) -> {
                    letra = validar(x, y);
                    boton.setText(letra);
                    
                    boton.setEnabled(false);

                    if (revisarGanador(filas, columnas)) {
                        reiniciarJuego(filas, columnas);
                    }
                });
            }
        }

        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.addActionListener((ActionEvent evt) -> {
            reiniciarJuego(filas, columnas);
        });
        getContentPane().add(btnReiniciar, BorderLayout.NORTH);
        getContentPane().add(panelBotones, BorderLayout.CENTER);
        setTitle("Tricky Traqui");
        pack();
        setVisible(true);
    }

    private String validar(int i, int j) {
        jugada++;
        if (jugada % 2 == 0) {
            matrix[i][j] = "X";
            return "X";
        }
        matrix[i][j] = "O";
        return "O";
    }

    public boolean revisarGanador(int filas, int columnas) {

        for (int i = 0; i < filas; i++) {
            letra = matrix[i][0];
            boolean filaCompleta = true;
            for (int j = 1; j < columnas; j++) {
                if (matrix[i][j] == null || matrix[i][j] != letra) {
                    filaCompleta = false;
                    break;
                }
            }
            if (filaCompleta) {
                JOptionPane.showMessageDialog(null, "El jugador " + letra + " Gano el juego!");
                return true;
            }
        }

        for (int j = 0; j < columnas; j++) {
            letra = matrix[0][j];
            boolean columnaCompleta = true;
            for (int i = 1; i < filas; i++) {
                if (matrix[i][j] == null || matrix[i][j] != letra) {
                    columnaCompleta = false;
                    break;
                }
            }
            if (columnaCompleta) {
                JOptionPane.showMessageDialog(null, "El jugador " + letra + " Gano el juego!");
                return true;
            }
        }

        letra = matrix[0][0];
        boolean diagonalCompleta = true;
        for (int i = 1; i < filas; i++) {
            if (matrix[i][i] == null || matrix[i][i] != letra) {
                diagonalCompleta = false;
                break;
            }
        }
        if (diagonalCompleta) {
            JOptionPane.showMessageDialog(null, "El jugador " + letra + " Gano el juego!");
            return true;
        }

        int k = filas - 1;
        letra = matrix[0][k];
        diagonalCompleta = true;
        for (int i = 1; i < filas; i++) {
            if (matrix[i][k - i] == null || matrix[i][k - i] != letra) {
                diagonalCompleta = false;
                break;
            }
        }
        if (diagonalCompleta) {
            JOptionPane.showMessageDialog(null, "El jugador " + letra + " Gano el juego!");
            return true;
        }

        boolean revisarEmpate = true;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matrix[i][j] == null) {
                    revisarEmpate = false;
                    break;
                }
            }
        }
        if (revisarEmpate) {
            JOptionPane.showMessageDialog(null, "Es un Empate");
            return true;

        }

        return false;
    }

    private void reiniciarJuego(int filas, int columnas) {
        jugada = 0;
        matrix = new String[matrix.length][matrix[0].length];

        // Habilita los botones y los vacía
        for (Component c : panelBotones.getComponents()) {
            JButton boton = (JButton) c;
            boton.setEnabled(true);
            boton.setText(" ");
        }
    }
}
