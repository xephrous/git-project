import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JavaAWT {

    //creating objects for creating frames
    JFrame inputFrame = new JFrame();
    JFrame outputFrame = new JFrame();

    JPanel inputPanel = new JPanel();
    JPanel outputPanel = new JPanel();

    //scanner thingss
    JTextField inputField = new JTextField(20);
    JLabel inputLabel = new JLabel("Enter Host Address: ");

    JLabel outputIp = new JLabel();
    JTextArea outputArea = new JTextArea(5, 15);

    //buttons objects
    JButton btnFindIp = new JButton("Find IP");
    JButton btnClear = new JButton("Clear");
    JButton btnOkay = new JButton("Okay");

    //Font setFont = new Font("", Font.BOLD, 14);

    public JavaAWT() {

        //input frame
        inputFrame.setSize(280, 150);
        inputFrame.setLocation(200, 200);
        inputFrame.setTitle("INPUT");
        inputFrame.setResizable(true);
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        add(label);
//        add(inputField);
//        add(btnClear);
//        add(btnFind);
//        add(outputArea);
//        add(btnOkay);
//        
//        setVisible(true);

        inputFrame.add(inputPanel);

        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        inputPanel.add(btnFindIp);
        btnFindIp.addActionListener(new btnFindIp());

        inputPanel.add(btnClear);
        btnClear.addActionListener(new btnClear());

        inputFrame.setVisible(true);
    }

    //finding the person's ip
    class btnFindIp implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            //trying to get the information of the person's ip
            try {
                String host = inputField.getText();
                String ip = java.net.InetAddress.getByName(host).getHostAddress();
                outputArea.append("Host Address: \n" + host + "\n\nIP Address: \n" + ip + "\n");

                //if not found
            } catch (Exception ex) {
                outputArea.setText("Invalid Host Address!");
            }
            
            //output frame display
            outputFrame.setSize(230, 185);
            outputFrame.setLocation(430, 240);
            outputFrame.setTitle("OUTPUT");
            outputFrame.setResizable(false);
            outputFrame.setVisible(true);
            outputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close frame

            outputArea.setEditable(false);
            //optional 
            outputArea.setBackground(Color.LIGHT_GRAY);
//            outputArea.setFont(setFont);

            //adding the panel and frame to display when running
            outputFrame.add(outputPanel);

            outputPanel.add(outputArea);
            outputPanel.add(outputIp);
            outputPanel.add(btnOkay);

            btnFindIp.setEnabled(false);
            btnOkay.addActionListener(new btnOkay());
        }
    }

//        clear and okay functions
//        }else if (e.getSource() == btnClear){
//            inputField.setText("");
//            outputArea.setText("");
    
    class btnClear implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            inputField.setText("");
            outputArea.setText("");

            btnFindIp.setEnabled(true);
            outputFrame.dispose();
        }
    }

//        }else if (e.getSource() == btnOkay){
//            inputField.setText("");
//            outputArea.setText("");
//        }
//  }
    
    //button okay
    class btnOkay implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            inputField.setText("");
            outputArea.setText("");

            btnFindIp.setEnabled(true);
            outputFrame.dispose();
        }
    }

    public static void main(String[] args) {
        JavaAWT java = new JavaAWT();
    }
}
