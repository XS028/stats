package stats;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ui{
	
	public JFrame viewForm;
	public static Color white = new Color(255,255,255);
	public static Color green = new Color(209,240,117);
	
	public static BufferedImage ind_ok = null;{
		try {
			ind_ok = ImageIO.read(new File("C:/stats/ind_ok.png"));
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public static JTextArea text = new JTextArea();
	
	public ui() {
		viewForm = new JFrame("Collecting info");
		viewForm.setSize(400, 130);
		viewForm.setLocation(stats.ww/2-viewForm.getSize().width/2,stats.wh/2-viewForm.getSize().height/2);
		viewForm.setMinimumSize(viewForm.getSize());
		viewForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewForm.setResizable(false);
		viewForm.getContentPane().setBackground(white);
		viewForm.setIconImage(ind_ok);

		text.setVisible(true);
		text.setLocation(10,10);
		text.setSize(viewForm.getSize().width-30,viewForm.getSize().height-90);
		text.setEditable(false);
		text.setFont(new Font("Arial", 12, 12));
		text.setRows(10);
	
		JButton button = new JButton("STOP");
		button.setVisible(true);
		button.setLocation(viewForm.getSize().width-95,viewForm.getSize().height-75);
		button.setSize(70, 30);
		button.setBackground(white);
		button.setFocusable(false);
		
		JButton button2 = new JButton("CURR.");
		button2.setVisible(true);
		button2.setLocation(viewForm.getSize().width-175,viewForm.getSize().height-75);
		button2.setSize(70, 30);
		button2.setBackground(white);
		button2.setFocusable(false);
	
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (stats.sleeping==1){stats.getchart(); io.t();} else
				{stats.wannaexit = 1;}
			}  
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (stats.sleeping==1){
					stats.getchart();
				} else{
					stats.getcurrent = 1;
				}
			}  
		});
	
		viewForm.getContentPane().add(text);
		viewForm.getContentPane().add(button);
		viewForm.getContentPane().add(button2);
		viewForm.getContentPane().add(new JLabel());
		viewForm.setVisible(true);
	}

}
