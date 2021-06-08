import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.FieldPosition;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static JPanel northJPanel, centerJPanel, southJPanel;
    private static JLabel harcosJLabel, levelJLabel;
    private static JTextArea harcJTextArea;
    private static JButton harcosJButton, samanJButton, startJButton;
    private static JFileChooser fc;
    private static File harcosFile, samanFile;
    private static TextField t1;
    private static int level;

    public static void main(String[] args) {
        harcJTextArea = new JTextArea(30, 40);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    //METÓDUSOK
    private static void createAndShowGUI() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        JFrame frame = new JFrame("Csata");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setBounds(20, 20, 1000, 800);
        Container c = frame.getContentPane();
        c.setLayout(new BorderLayout());

        northJPanel = new JPanel();
        northJPanel.setLayout(new FlowLayout());
        harcosJLabel = new JLabel("Valassza ki a Harcos JSON fajljat");
        harcosJButton = new JButton("Fajl kivalasztasa");
        levelJLabel = new JLabel("Valassza ki a nehézségi szintet(1-3)");
        //samanJButton = new JButton("Fajl kivalasztasa");
        startJButton = new JButton("Csata kezdete");
        t1=new TextField();
        northJPanel.add(harcosJLabel);
        northJPanel.add(harcosJButton);
        northJPanel.add(levelJLabel);
        //northJPanel.add(samanJButton);
        northJPanel.add(t1);
        northJPanel.add(startJButton);


        centerJPanel = new JPanel();
        centerJPanel.setLayout(new FlowLayout());
        harcJTextArea = new JTextArea(30, 40);
        harcJTextArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(harcJTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        centerJPanel.add(scroll);

        c.add(northJPanel, BorderLayout.NORTH);
        c.add(centerJPanel, BorderLayout.CENTER);

        fc = new JFileChooser();
        harcosJButton.addActionListener(e -> {
            fc.showOpenDialog(frame);
            try{
                harcosFile = fc.getSelectedFile();
                if (harcosFile.length()>0){
                    harcosJButton.setEnabled(false);
                }
            }catch (NullPointerException ex){
                ex.printStackTrace();
            }
        });
        /*samanJButton.addActionListener(e -> {
            fc.showOpenDialog(frame);
            try{
                samanFile = fc.getSelectedFile();
                if (samanFile.length()>0){
                    samanJButton.setEnabled(false);
                }
            }catch (NullPointerException ex){
                ex.printStackTrace();
            }
        });*/
        startJButton.addActionListener(e -> {
            battle(JsonToPlayer(harcosFile.getName()), JsonToUnit(Integer.parseInt(t1.getText())));
        });

        frame.pack();
        frame.setVisible(true);
    }



    ////////////////////////////////////////////////////////////

    private static double readIn(Scanner sc, String msg) {
        double answer = 0;

        System.out.print(msg);
        if (sc.hasNextFloat()) {
            answer = sc.nextFloat();
            return answer;
        } else {
            // Akkor tér vissza vele, ha pl. betű van megadva
            answer = -1;
            return answer;
        }
    }
    protected static void battle(Player unit1, Unit unit2) {
        if (unit1.getDMG() == 0 && unit2.getDMG() == 0) {
            // System.out.println("A csapatok visszavonultak, a harc dontetlennel
            // vegzodott.");
            harcJTextArea.append("A csapatok visszavonultak, a harc dontetlennel vegzodott.\n");

            return;
        }
        String name1 = unit1.getName();
        String name2 = unit2.getName();
        boolean bothAlive = true; // mindkettő él
        boolean isFirstRound = true; // első kör
        double defaultAs = unit1.getAs();
        double defaultAs2 = unit2.getAs();
        int playerLvl = unit1.getLvl();
        while (bothAlive) {

            // Ha első kör akkor egyszerre megütik egymást 1. ütés!
            if (isFirstRound) {
                isFirstRound = false;
                unit1.attack(unit2);
                unit2.attack(unit1);
                if(harcJTextArea != null ) {
                    harcJTextArea.append("\nA Csata elkezdodott! " + name1 + " es " + name2 +
                            " megtamadtak egymast. eletük: " + name1 + " " + unit1.getHp() +
                            ", " + name2 + " " + unit2.getHp() + "\n");
                }
            }
            // Attack speed számolása, melyiké kisebb --> az üthet elősször
            double lowestAs = Math.min(unit1.getAs(), unit2.getAs());
            unit1.setAs(unit1.getAs() - lowestAs);
            unit2.setAs(unit2.getAs() - lowestAs);
            // Ütések
            if (unit1.getAs() == 0) {
                unit1.attack(unit2);
                unit1.setAs(defaultAs);
                 harcJTextArea.append(name1 + " megtamadta " + name2 + ", igy " +
                         name2 + " elete - " + unit2.getHp() + "\n");
                if(unit1.getLvl() != playerLvl) {
                    harcJTextArea.append(unit1.getName() + " a(z)" + unit1.getLvl() + ". szintre lépett. Ezzel elete "+ (unit1.getHp() - unit1.getHp_increase_per_level()) +  " => " + unit1.getHp() + " sebzese "+ (unit1.getDMG()-unit1.getDmg_increase_per_level())+"=>"+unit1.getDMG() +"\n");
                    playerLvl ++;
                }
            }
            if (unit2.getAs() == 0) {
                unit2.attack(unit1);
                unit2.setAs(defaultAs2);
                if(harcJTextArea != null ) {
                    harcJTextArea.append(name2 + " megtamadta " + name1 + ", igy " +
                            name1 + " elete - " + unit1.getHp() + "\n");
                }
            }
            // Éltek még?
            if (unit1.isAlive() == false || unit2.isAlive() == false) {
                bothAlive = false;
            }
        }
//         Első ütésnél meghalt e vagy nem...
         if (isFirstRound)
         harcJTextArea.append(unit1.isAlive() ? "\n" + unit1.getName() +
                 " EGY CSAPASSAL GYOZOTT" : "\n" + unit2.getName() + " EGY CSAPASSAL GYOZOTT! \n");
         else
         if(harcJTextArea != null ) {
             harcJTextArea.append(unit1.isAlive() ? "\n" + unit1.getName() +
                     " GYOZEDELMESKEDETT!" : "\n" + unit2.getName() + " GYOZEDELMESKEDETT!\n");
         }
    }

    public static Unit JsonToUnit(int szörnyszint) {
        File firstjson = new File("szörnyek/szörny1.json");
        File secondjson = new File("szörnyek/szörny2.json");
        File thirdjson = new File("szörnyek/szörny3.json");
        String first = firstjson.toString();
        String second = secondjson.toString();
        String third = thirdjson.toString();
        String[] szörnyek = {first,second,third};
        try {
            String contents = new String((Files.readAllBytes(Paths.get(szörnyek[szörnyszint-1]))));
            JSONObject o = new JSONObject(contents);
            String unitjsonNAME = o.getString("NAME");
            int unitjsonHP = o.getInt("HP");
            int unitjsonDMG = o.getInt("DMG");
            double unitjsonAS = o.getDouble("AS");
            Unit unit = new Unit(unitjsonNAME, unitjsonDMG, unitjsonHP, unitjsonAS);
            return unit;
        } catch (IOException e) {
            e.printStackTrace();
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"Nincs ilyen magas vagy alacsony szint","Warning",JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    public static Player JsonToPlayer(String arg) {
        String first = arg;
        try {
            String contents = new String((Files.readAllBytes(Paths.get(first))));
            JSONObject o = new JSONObject(contents);
            String playerjsonNAME = o.getString("NAME");
            int playerjsonHP = o.getInt("HP");
            int playerjsonDMG = o.getInt("DMG");
            double playerjsonAS = o.getDouble("AS");
            int playerXP = o.getInt("XP");
            int playerjsonDMGPL = o.getInt("DMGPL");
            int playerjsonHPPL = o.getInt("HPPL");
            float playerASPL = o.getFloat("ASPL");
            Player player = new Player(playerjsonNAME, playerjsonDMG, playerjsonHP, playerjsonAS, playerXP,
                    playerjsonDMG, playerjsonHPPL, playerASPL);
            return player;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}