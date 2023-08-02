import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class Main extends JFrame {
    JButton pole1 = new JButton();
    JButton pole2 = new JButton();
    JButton pole3 = new JButton();
    JButton pole4 = new JButton();
    JButton pole5 = new JButton();
    JButton pole6 = new JButton();
    JButton pole7 = new JButton();
    JButton pole8 = new JButton();
    JButton pole9 = new JButton();
    int turn = 0;
    int a=0;
    String kolej;
    JPanel buttonsPanel;
    JPanel windowPanel;
    int k;


    public void checkWinner() {
        JButton[] fields = {pole1, pole2, pole3, pole4, pole5, pole6, pole7, pole8, pole9};
        String[] signs = new String[9];
        for (int i = 0; i < 9; i++) {
            signs[i] = fields[i].getText();
        }

        int[][] winPossibilities = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rzędy
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // kolumny
                {0, 4, 8}, {2, 4, 6} // przekątne
        };

        // Sprawdzenie, czy któryś z graczy wygrał
        for (int i = 0; i < winPossibilities.length; i++) {
            int a = winPossibilities[i][0];
            int b = winPossibilities[i][1];
            int c = winPossibilities[i][2];
            if (signs[a].equals(signs[b]) && signs[b].equals(signs[c]) && !signs[a].isEmpty()) {
                String winner = (signs[a].equals("X")) ? "Gracz X" : "Gracz O";
                JOptionPane.showMessageDialog(this, "Wygrał " + winner + "!", "Koniec gry", JOptionPane.INFORMATION_MESSAGE);
                return; // Wyjście z funkcji, bo już mamy zwycięzcę
            }
        }

        // Sprawdzenie, czy plansza jest zapełniona (czyli jest remis)
        boolean isBoardFull = true;
        for (String sign : signs) {
            if (sign.isEmpty()) {
                isBoardFull = false;
                break;
            }
        }

        if (isBoardFull) {
            JOptionPane.showMessageDialog(this, "Remis!", "Koniec gry", JOptionPane.INFORMATION_MESSAGE);
        }
    }







    public boolean Sign(JButton button, int turn) {

        Font font = new Font("Arial", Font.BOLD, 24);
        button.setFont(font);
        String CurrentSign = button.getText();
        if (CurrentSign.isEmpty()) {
            if (turn % 2 == 0) {
                button.setText("O");
                button.setForeground(Color.RED);
                k=0;
                kolej = "X";

            } else {
                button.setText("X");
                button.setForeground(Color.BLUE);
                k=1;
                kolej = "O";
            }


            Font wielkosc = new Font("Arial", Font.BOLD, 16);

            JLabel proba = new JLabel("Kolej gracza: " + kolej + " ");

            proba.setFont(wielkosc);

            JButton restart=new JButton("RESTART");
            windowPanel.add(restart, BorderLayout.SOUTH);
            windowPanel.removeAll();
            windowPanel.add(proba);

            windowPanel.add(restart, BorderLayout.SOUTH);
            restart.setBackground(Color.RED);

            restart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    kolej = "O";
                    windowPanel.removeAll();
                    Font wielkosc = new Font("Arial", Font.BOLD, 16);

                    JLabel proba = new JLabel("Kolej gracza: " + kolej + " ");

                    proba.setFont(wielkosc);
                    windowPanel.add(proba);

                    for (Component component : buttonsPanel.getComponents()) {
                        if (component instanceof JButton) {
                            JButton button = (JButton) component;
                            button.setText("");
                        }
                    }
                    windowPanel.revalidate();
                    windowPanel.repaint();
                    buttonsPanel.revalidate();
                    buttonsPanel.repaint();
                }
            });

            checkWinner();

            return true;
        }
        return false;

    }


    public void panel() {

        windowPanel = new JPanel(new BorderLayout());
        windowPanel.setPreferredSize(new Dimension(400, 150));
        windowPanel.setBackground(Color.WHITE);
        this.add(windowPanel, BorderLayout.SOUTH);
        JLabel proba = new JLabel("Kolej gracza: " + kolej);

        if (a % 2 == 0) {
            kolej = "O";
            proba.setText("Kolej gracza: " + kolej);
        } else {
            kolej = "X";
            proba.setText("Kolej gracza: " + kolej);
        }
        a++;
        windowPanel.add(proba, BorderLayout.CENTER);

        JButton restart=new JButton("RESTART");
        windowPanel.add(restart, BorderLayout.SOUTH);
        restart.setBackground(Color.RED);

    }



    public Main() {


        this.setTitle("TICTACTOE");
        this.setResizable(false);

        this.setSize(500, 700); // Adjusted size to accommodate the windowPanel
        // Pobranie rozmiaru ekranu
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Pobranie rozmiaru ramki
        Dimension frameSize = this.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        // Ustawienie nowej pozycji ramki
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("tictac.png");
        this.setIconImage(icon.getImage());

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 3));
        this.add(buttonsPanel, BorderLayout.CENTER); // Add the buttons panel to the main frame's center



        Color color = new Color(200, 220, 200);
        pole1.setBackground(color);
        pole2.setBackground(color);
        pole3.setBackground(color);
        pole4.setBackground(color);
        pole5.setBackground(color);
        pole6.setBackground(color);
        pole7.setBackground(color);
        pole8.setBackground(color);
        pole9.setBackground(color);

        buttonsPanel.add(pole1);
        buttonsPanel.add(pole2);
        buttonsPanel.add(pole3);
        buttonsPanel.add(pole4);
        buttonsPanel.add(pole5);
        buttonsPanel.add(pole6);
        buttonsPanel.add(pole7);
        buttonsPanel.add(pole8);
        buttonsPanel.add(pole9);

        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        pole1.setBorder(blackBorder);
        pole2.setBorder(blackBorder);
        pole3.setBorder(blackBorder);
        pole4.setBorder(blackBorder);
        pole5.setBorder(blackBorder);
        pole6.setBorder(blackBorder);
        pole7.setBorder(blackBorder);
        pole8.setBorder(blackBorder);
        pole9.setBorder(blackBorder);

        JButton[] fields1 = {pole1, pole2, pole3, pole4, pole5, pole6, pole7, pole8, pole9};

        for (int i = 0; i < 9; i++) {
            int index = i;
            fields1[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (Sign(fields1[index], turn)) {
                        turn++;
                    }
                }
            });
        }

        panel();

    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}
