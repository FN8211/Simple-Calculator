package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    //
    private JPanel jp_north = new JPanel();       //initialize the panel on the northern of window
    private JTextField input_text = new JTextField();       //setting texts on northern panel as input
    private JButton c_Btn = new JButton("C");    //initialize the "clear" button
    private JPanel jp_center = new JPanel();       //initialize the panel on the central of window

    public Calculator() throws HeadlessException{
        //
        this.init();
        this.addNorthComponent();
        this.addCenterButton();
    }


    public void init(){
        //initialize the window
        this.setTitle(Const.TITLE);                          //setting title of window
        this.setSize(Const.FRAME_W, Const.FRAME_H);          //setting size of window
        this.setLayout(new BorderLayout());
        this.setResizable(false);                     //fix the size of window
        this.setLocation(Const.FRAME_X, Const.FRAME_Y);        //setting location of window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //closing the program when exit the window
    }

    public void addNorthComponent(){
        //adding elements on northern panel
        this.input_text.setPreferredSize(new Dimension(230, 30));
        jp_north.add(input_text);                       //adding display window into panel
        this.c_Btn.setForeground(Color.RED);            //setting color of "clear" button as red
        jp_north.add(c_Btn);                            //adding "clear" button into panel
        c_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input_text.setText("");
            }
        });
        this.add(jp_north, BorderLayout.NORTH);        //putting panel on the northern side of window
    }

    public void addCenterButton(){
        //adding elements on central panel
        String btn_text = "123+456-789*0.=/";        //initializing the texts display on buttons
        String regex = "[\\+\\-\\*\\/\\.\\=]";       //initializing the operators display on buttons
        this.jp_center.setLayout(new GridLayout(4, 4));          //adding buttons on central panel as formation of 4 * 4
        for(int i = 0; i < 16; i++){
            String temp = btn_text.substring(i, i + 1);                  //extracting texts that would be added into buttons later
            JButton btn = new JButton();
            btn.setText(temp);                                           //setting texts in buttons
            if(temp.matches(regex)){     //if we are adding operators
                btn.setFont(new Font("粗体", Font.BOLD, 16));    //setting texts as bold
                btn.setForeground(Color.RED);                  //setting colors of operators as red
            }
            btn.addActionListener(this);                    //receiving the buttons that was pressed
            jp_center.add(btn);                                //adding buttons to central panel
        }
        this.add(jp_center, BorderLayout.CENTER);              //adding panel at the center of window
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setVisible(true);
    }

    private String firstInput = null;
    private String operator = null;
    @Override
    public void actionPerformed(ActionEvent e) {
        //calculating part of this program
        String clickStr = e.getActionCommand();            //receiving button been pressed
        if(".0123456789".indexOf(clickStr) != -1){                          //if pressed numbers
            this.input_text.setText(input_text.getText() + clickStr);       //show buttons been pressed in order
            this.input_text.setHorizontalAlignment(JTextField.RIGHT);       //setting displaying texts on the right side of window
        }
        else if(clickStr.matches("[\\+\\-\\*\\/]{1}")){               //if pressed operators
            operator = clickStr;
            firstInput = this.input_text.getText();
            this.input_text.setText("");                                     //clear the display window
        }
        else if(clickStr.equals("=")){
            Double a = Double.valueOf(firstInput);
            Double b = Double.valueOf(this.input_text.getText());
            Double result = null;
            switch (operator){               //different operations during calculating
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if(b != 0){
                        result = a / b;
                    }
                    break;
            }
            this.input_text.setText(result.toString());                    //change result of calculation to a string
                                                                           // and show on display window
        }
    }
}
