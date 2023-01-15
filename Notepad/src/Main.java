import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import javax.swing.text.*;


class notepad extends JFrame implements ActionListener{
    JTextArea textArea;
    JFrame frame;

    notepad()
    {
        frame = new JFrame("Notepad");
        textArea = new JTextArea();
        JMenuBar menu = new JMenuBar();

        //Creating a File Menu
        JMenu file = new JMenu("File");
        JMenuItem f1 = new JMenuItem("New");
        JMenuItem f2 = new JMenuItem("Open");
        JMenuItem f3 = new JMenuItem("Save");
        JMenuItem f4 = new JMenuItem("Print");

        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

        //Creating an Edit Menu
        JMenu edit = new JMenu("Edit");
        JMenuItem e1 = new JMenuItem("Cut");
        JMenuItem e2 = new JMenuItem("Copy");
        JMenuItem e3 = new JMenuItem("Paste");

        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);

        edit.add(e1);
        edit.add(e2);
        edit.add(e3);

        menu.add(file);
        menu.add(edit);

        frame.setJMenuBar(menu);
        frame.add(textArea);
        frame.setSize(750,700);
        frame.show();
    }


    public void actionPerformed(ActionEvent e)
    {
        //getting user clicked label text
        String command=e.getActionCommand();

        //performing switch case to check what action string contains
        switch (command)
        {
            case "New":
                textArea.setText("");
                break;
            case "Open":
                JFileChooser fileChooser=new JFileChooser("Documents");

                int r=fileChooser.showOpenDialog(null);

                if(r==JFileChooser.APPROVE_OPTION)
                {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                    String s1, s2;

                    FileReader fr = null;
                    try {
                        fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);

                        //Storing the first Line in s1
                        s1=br.readLine();

                        //Appending subsequent lines till end of file is reached
                         while((s2=br.readLine())!=null)
                         {
                             s1=s1+"\n"+s2;
                         }
                        textArea.setText(s1);


                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(frame,"Operation Cancelled");
                }

                break;
            case "Save":
                JFileChooser fileSaver=new JFileChooser("Documents");

                int s=fileSaver.showSaveDialog(null);

                if(s==JFileChooser.APPROVE_OPTION)
                {
                    File file = new File(fileSaver.getSelectedFile().getAbsolutePath());

                    FileWriter fw = null;
                    try {
                        fw = new FileWriter(file);
                        BufferedWriter br = new BufferedWriter(fw);

                        br.write(textArea.getText());
                        br.flush();
                        br.close();

                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(frame,"Operation Cancelled");
                }



                break;
            case "Print":
                try {
                    textArea.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Cut":
                textArea.cut();
                break;
            case "Copy":
                textArea.copy();
                break;
            case "Paste":
                textArea.paste();
                break;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        notepad note=new notepad();
    }
}