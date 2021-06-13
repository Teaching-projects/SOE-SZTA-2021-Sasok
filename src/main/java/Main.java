import org.json.JSONObject;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class Main extends JPanel implements ActionListener {

    private static JPanel northJPanel, centerJPanel, westJPanel;
    private static JLabel harcosJLabel, levelJLabel;
    private static JTextArea harcJTextArea;
    private static JButton harcosJButton, startJButton, up, down, right, left;
    private static JFileChooser fc;
    private static File harcosFile = new File("Player2.json"), samanFile;
    private static TextField t1;
    private static BufferedImage image;
    private static Player player;

    public static void main(String[] args) {
        harcJTextArea = new JTextArea(30, 40);
        player = JsonToPlayer(harcosFile.getName());
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void createAndShowGUI() throws IOException {
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
        //harcosJLabel = new JLabel("Valassza ki a Harcos JSON fajljat");
        //harcosJButton = new JButton("Fajl kivalasztasa");
        levelJLabel = new JLabel("");
        //samanJButton = new JButton("Fajl kivalasztasa");
        startJButton = new JButton("Csata kezdete");
        startJButton.setEnabled(false);
        t1=new TextField("0");
        t1.setEnabled(false);
        //northJPanel.add(harcosJLabel);
        //northJPanel.add(harcosJButton);
        northJPanel.add(levelJLabel);
        //northJPanel.add(samanJButton);
        northJPanel.add(t1);
        northJPanel.add(startJButton);

        //import image and resize it
        BufferedImage myPicture = ImageIO.read(new File("hero.png"));
        Image dimg = myPicture.getScaledInstance(200, 200,
                Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(dimg));

        JPanel centerJPanel = new JPanel();
        centerJPanel.setLayout(new BoxLayout(centerJPanel, BoxLayout.PAGE_AXIS));
        up = (new BasicArrowButton(BasicArrowButton.NORTH));
        down = (new BasicArrowButton(BasicArrowButton.SOUTH));
        right = (new BasicArrowButton(BasicArrowButton.EAST));
        left = (new BasicArrowButton(BasicArrowButton.WEST));

        up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String move = readMap(player.getxCoordinate(),player.getyCoordinate(),"up");
                System.out.println(move);
                if(move.equals("SZ") || move.equals("X")){
                    player.move("up");
                    if(move.equals("X")){
                        harcJTextArea.append("\nRHRAHAAHA!!! Hogy mertél a barlangomba lépni? ");
                        levelJLabel.setText("Szörny szintje: ");
                        t1.setText(szörnyszint());
                        t1.setEnabled(false);
                        startJButton.setEnabled(true);
                    }
                    else harcJTextArea.append("\nÉszaknak megyek!");
                }
                else if(move.equals("F")){
                    harcJTextArea.append("\nÚgylátszik falnak ütköztél...");
                }
                else harcJTextArea.append("\nMegbolondultál ifjú? A világ szélén állsz, tán le akarsz pottyanni?");

            }
        });
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String move = readMap(player.getxCoordinate(),player.getyCoordinate(),"down");
                System.out.println(move);
                if(move.equals("SZ") || move.equals("X")){
                    player.move("down");
                    if(move.equals("X")){
                        harcJTextArea.append("\nRHRAHAAHA!!! Hogy mertél a barlangomba lépni? ");
                        levelJLabel.setText("Szörny szintje: ");
                        t1.setText(szörnyszint());
                        t1.setEnabled(false);
                        startJButton.setEnabled(true);
                    }
                    else harcJTextArea.append("\nDélnek megyek!");
                }
                else if(move.equals("F")){
                    harcJTextArea.append("\nÚgylátszik falnak ütköztél...");
                }
                else harcJTextArea.append("\nMegbolondultál ifjú? A világ szélén állsz, tán le akarsz pottyanni?");
            }
        });
        right.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String move = readMap(player.getxCoordinate(),player.getyCoordinate(),"right");
                System.out.println(move);
                if(move.equals("SZ") || move.equals("X")){
                    player.move("right");
                    if(move.equals("X")){
                        harcJTextArea.append("\nRHRAHAAHA!!! Hogy mertél a barlangomba lépni? ");
                        levelJLabel.setText("Szörny szintje: ");
                        t1.setText(szörnyszint());
                        t1.setEnabled(false);
                        startJButton.setEnabled(true);
                    }
                    else harcJTextArea.append("\nKeletnek megyek!");
                }
                else if(move.equals("F")){
                    harcJTextArea.append("\nÚgylátszik falnak ütköztél...");
                }
                else harcJTextArea.append("\nMegbolondultál ifjú? A világ szélén állsz, tán le akarsz pottyanni?");
            }
        });
        left.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String move = readMap(player.getxCoordinate(),player.getyCoordinate(),"left");
                if(move.equals("SZ") || move.equals("X")){
                    player.move("left");
                    if(move.equals("X")){
                        harcJTextArea.append("\nRHRAHAAHA!!! Hogy mertél a barlangomba lépni? ");
                        levelJLabel.setText("Szörny szintje: ");
                        t1.setText(szörnyszint());
                        t1.setEnabled(false);
                        startJButton.setEnabled(true);
                    }
                    else harcJTextArea.append("\nNyugatnak megyek!");
                }
                else if(move.equals("F")){
                    harcJTextArea.append("\nÚgylátszik falnak ütköztél...");
                }
                else harcJTextArea.append("\nMegbolondultál ifjú? A világ szélén állsz, tán le akarsz pottyanni?");         }
        });

        centerJPanel.add(up);
        centerJPanel.add(down);
        centerJPanel.add(right);
        centerJPanel.add(left);
        centerJPanel.add(picLabel);
        harcJTextArea = new JTextArea(20, 20);
        harcJTextArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(harcJTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        centerJPanel.add(scroll);
        c.add(northJPanel, BorderLayout.NORTH);
        c.add(centerJPanel, BorderLayout.CENTER);

        fc = new JFileChooser();
        /*harcosJButton.addActionListener(e -> {
            int result = fc.showSaveDialog(frame);
            if(result == JFileChooser.APPROVE_OPTION){
                harcosFile = fc.getSelectedFile();
            }
            else{
                harcosFile = new File("Player.json");
            }
        });*/
        startJButton.addActionListener(e -> {
            battle(JsonToPlayer(harcosFile.getName()), JsonToUnit(Integer.parseInt(t1.getText())));
        });

        frame.pack();
        frame.setVisible(true);
    }


    ///////////////////////////FUNCTIONS/////////////////////////////////
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
        if(harcJTextArea!=null) {
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
                    harcJTextArea.append("\nA Csata elkezdodott! " + name1 + " es " + name2 +
                            " megtamadtak egymast. eletük: " + name1 + " " + unit1.getHp() +
                            ", " + name2 + " " + unit2.getHp() + "\n");

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

                    if (unit1.getLvl() != playerLvl) {
                        harcJTextArea.append(unit1.getName() + " a(z)" + unit1.getLvl() + ". szintre lépett. Ezzel elete " + (unit1.getHp() - unit1.getHp_increase_per_level()) + " => " + unit1.getHp() + " sebzese " + (unit1.getDMG() - unit1.getDmg_increase_per_level()) + "=>" + unit1.getDMG() + "\n");
                        playerLvl++;
                    }
                }
                if (unit2.getAs() == 0) {
                    unit2.attack(unit1);
                    unit2.setAs(defaultAs2);
                    harcJTextArea.append(name2 + " megtamadta " + name1 + ", igy " +
                            name1 + " elete - " + unit1.getHp() + "\n");
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
                harcJTextArea.append(unit1.isAlive() ? "\n" + unit1.getName() +
                        " GYOZEDELMESKEDETT!" : "\n" + unit2.getName() + " GYOZEDELMESKEDETT!\n");
            if (unit1.getHp() <= 0) {
                JOptionPane.showMessageDialog(null, "Sajnos hősünk elveszett", "Csata", JOptionPane.INFORMATION_MESSAGE);
            } else
                JOptionPane.showMessageDialog(null, "Hősünk győzedelmeskedett", "Csata", JOptionPane.INFORMATION_MESSAGE);
        }
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
    public static String szörnyszint(){
        int random = (int)(Math.random()*3)+1;
        return Integer.toString(random);
    }
    private static String readMap(int x, int y, String move){
        String [][] map = new String [6][6];
        try (BufferedReader br = new BufferedReader(new FileReader("map.txt"))) {
            String line;
            String[] a;
            int i = 0;
            while ((line = br.readLine()) != null){
                //System.out.println(line);
                a = line.split(",");
                for (int j = 0; j < 6; j++){
                    map[i][j] = a[j];
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if(move == "up"){
            if(x == 0){
                return "T";
            }
            else return map[x-1][y];
        }
        else if(move == "down"){
            if(x == 5){
                return "T";
            }
            else return map[x+1][y];
        }
        else if(move == "right"){
            if(y == 5){
                return "T";
            }
            else return map[x][y+1];
        }
        else if(move == "left"){
            if(y == 0){
                return "T";
            }
            else return map[x][y-1];
        }
        return "";
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}