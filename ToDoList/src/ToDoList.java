import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * Created by Евгений on 14.06.2017.
 */
public class ToDoList extends JFrame {
    private JPanel panelMain;
    private JTextField textField1;
    private JButton buttonAdd;
    private JProgressBar progressBar;
    private JButton doneButton;
    private JList list1;
    private int max = 0;
    public String element = null;
    public ArrayList<String> list2 = new ArrayList<>();

    public ToDoList(){
        super("Список заданий");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(panelMain);
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout(5, 5));
        panelMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(1, 2, 5, 0));
        panelMain.add(textPanel, BorderLayout.SOUTH);

        textPanel.add(textField1);
        readFile();
        if (list2.size() != 0) max = list2.size();

        JPanel pbPanel = new JPanel();
        pbPanel.setLayout(new GridLayout(1, 2, 5, 0));
        panelMain.add(pbPanel,BorderLayout.NORTH);
        progressBar.setStringPainted(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);

        pbPanel.add(progressBar);

        this.getContentPane().add(list1);
        final DefaultListModel list = new DefaultListModel();
        for (int i = 0; i < list2.size(); i++){
            list.addElement(list2.get(i));
        }

        final JList list1 = new JList(list);
        list1.setSelectedIndex(0);
        list1.setFocusable(false);
        panelMain.add(new JScrollPane(list1), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 5, 0));
        panelMain.add(buttonsPanel, BorderLayout.EAST);

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                element = ("- " + textField1.getText());
                list.addElement(element);
                int index = list.size() - 1;
                textField1.setText(null);
                list1.setSelectedIndex(index);
                list1.ensureIndexIsVisible(index);
                writeToFile(element);
                if (list.size() > max){
                    max = list.size();
                    progressBar.setValue(0);
                } else {
                    progressBar.setValue(100 - (list.size() * 100)/max);
                }
            }
        });

        JButton doneButton = new JButton("Выполнено");
        doneButton.setFocusable(false);
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.remove(list1.getSelectedIndex());
                progressBar.setValue(100 - (list.size() * 100)/max);
                clearFile();
                for (int i = 0; i < list.size(); i++){
                    writeToFile((String) list.get(i));
                }
            }
        });

        buttonsPanel.add(doneButton);

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                     if (list1.getSelectedIndex() >= 0) {
                        doneButton.setEnabled(true);
                    } else {
                        doneButton.setEnabled(false);
                    }
                }

        });

        buttonsPanel.add(buttonAdd);

        getContentPane().add(panelMain);
        setPreferredSize(new Dimension(500, 500));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) throws IOException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new ToDoList();
            }

        });

    }

    public static void writeToFile(String element){
        FileWriter fw;
        try{
            fw = new FileWriter("ToDoList.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(element);
            bw.newLine();
            bw.close();
            } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void clearFile(){
        try{
            FileWriter fw = new FileWriter("ToDoList.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readFile(){
        BufferedReader rff = null;
        try{
            rff = new BufferedReader(new FileReader("ToDoList.txt"));
            while ((element = rff.readLine()) != null){
                list2.add(element);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if (rff != null)
                try {
                rff.close();
                }catch (IOException e){
                e.printStackTrace();
                }
        }
    }

    }


