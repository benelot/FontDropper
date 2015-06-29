package ch.test;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
public class UpdateLabel extends JFrame implements ActionListener
{
    private JLabel label;
    private JTextField field;
    public UpdateLabel()
    {
        super("The title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new FlowLayout());
        label = new JLabel("flag");
        add(label);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("myButton"))
        {
            label.setText(field.getText());
        }
    }
    public static void main(String[] args)
    {
        UpdateLabel up = new UpdateLabel();
        for(int i = 0; i < 100000;i++)
        {
        	up.label.setText(String.valueOf(i));
        }
    }
}