

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;

public class Notepad extends JFrame implements ActionListener {

    private JTextArea area;
    private JScrollPane scrollPane;
    String text = "";
    public Notepad() {
        super("Notepad");

        setSize(1950, 1050);

        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem newdoc = new JMenuItem("New");

        newdoc.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");

        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");

        save.addActionListener(this);

        JMenuItem print = new JMenuItem("Print");

        print.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");

        exit.addActionListener(this);

         area= new JTextArea();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        setJMenuBar(menuBar);
        menuBar.add(file);

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("New")) {
            area.setText("");

        } else if (ae.getActionCommand().equals("Open")) {
            JFileChooser chooser = new JFileChooser("D:/Java");
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
            chooser.addChoosableFileFilter(restrict);

            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();

                try {
                    System.out.println("HEki");
                    FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader);
                    area.read(br, null);
                    br.close();
                    area.requestFocus();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }
        } else if (ae.getActionCommand().equals("Save")) {
            final JFileChooser SaveAs = new JFileChooser();
            SaveAs.setApproveButtonText("Save");
            int actionDialog = SaveAs.showOpenDialog(this);
            if (actionDialog != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File fileName = new File(SaveAs.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(fileName));
                area.write(outFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ae.getActionCommand().equals("Print")) {
            try {
                area.print();
            } catch (Exception e) {
            }
        } else if (ae.getActionCommand().equals("Exit")) {
            System.exit(0);

        }
    }

    public static void main(String[] args) {
        Notepad notepad = new Notepad();
    }
}