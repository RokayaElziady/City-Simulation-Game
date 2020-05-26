package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.CommandCenter;

public class StartView extends JFrame implements ActionListener {
	
	//private JPanel world;
	private JButton play;
	private JButton help;
    private JDialog d;	
	
	
	public StartView() {
		
		setVisible(true);
		setPreferredSize(new Dimension(2000, 1120));
		setSize(2000,1120);
	    setLayout(new GridLayout());
		getContentPane().setBackground(Color.BLACK);
		 play= new JButton();
		 help=new JButton();
		 play.setName("play");
		 play.setText("YALLA");
		 play.setFont(new Font("Arial", Font.BOLD, 30));
		 play.setVisible(true);
		 help.setVisible(true);
		 play.setBackground(Color.YELLOW);
		 play.setPreferredSize(new Dimension(950, 120));
		 revalidate();
		 repaint();
		 
		 ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/The_Simpsons_Tapped_Out.jpg");
			Image image = t.getImage();
			Image newImg = image.getScaledInstance(2000, 900, java.awt.Image.SCALE_SMOOTH);
			
			t= new ImageIcon(newImg);
			
			add(new JLabel(t),BorderLayout.NORTH);
		    revalidate();
		    repaint();
		    
		    
		 
		JPanel down=new JPanel();
	    down.setLayout(new GridLayout(1,2));
	    down.setVisible(true);
	    down.setPreferredSize(new Dimension(2000,120));
	  
		 help= new JButton();
		 help.setName("help");
		 help.setText("THE STORY");
		 help.setBackground(Color.YELLOW);
		 help.setFont(new Font("Arial", Font.BOLD, 30));
		 
		 help.setPreferredSize(new Dimension(950, 120));
		 help.addActionListener(this);
		 play.addActionListener(this);
		 this.setLayout(new FlowLayout());
		 down.add(play);
		 down.add(help);
		 add(down,BorderLayout.SOUTH);
		revalidate();
		repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton) e.getSource()).getName().equals("play")) {
			try {
				setVisible(false);
				new CommandCenter();
			} catch (Exception e1) {
				
				System.out.println(e1.getMessage());
			}
			
		}
			if(((JButton) e.getSource()).getName().equals("help")) {
				
				setVisible(false);
				 d=new JDialog();
			//	getContentPane().setBackground(Color.BLACK);
				d.setPreferredSize(new Dimension(1450, 800));
				d.setSize(1450, 800);
				d.setVisible(true);
				d.setLayout(new BorderLayout());
				d.setBounds(200,180,1450,800);
				JButton b=new JButton();
				b.setFont(new Font("Arial", Font.BOLD, 30));
				b.addActionListener(this);    //MOMKEN A3ML TWO PANNELS WAHDA FEEHA TEXT W WAHDA FEEHA BUTTON
				b.setText("GOT IT, LET'S STAART !");
				b.setName("fehemt");
				b.setBackground(Color.YELLOW);
				b.setPreferredSize(new Dimension(1450, 100));
				b.setVisible(true);
				
				JTextArea t=new JTextArea();
				t.setPreferredSize(new Dimension(950,650));
				t.setBackground(Color.CYAN);
				t.setEditable(false);
				t.setText("The game consists of a main simulation 10x10 grid where the whole map of the Simpsons world"+"\n"+
						"is taking place. You are responsible for managing the rescue units"+"\n"+
						"to handle Aztec “simpsons building” and Simpon citizens suffering from the disasters that ensue"+"\n"+
						"during game play throughout the whole map, applied on them by Mr. Burns “HE IS TRYING"+"\n"+
						"TO DESTROY THE WORLD !!”;"+"\n"+
						"- An ambulance should respond to a simpson suffering from an injury Disaster"+"\n"+
						"- A Disease Control Unit should respond to a simpson suffering from an infection Disaster."+"\n"+
						"- An Evacuator is responsible for saving Simpsons inside a suffering Aztec."+"\n"+
						"- A fire truck should respond to a Aztec suffering from a fire Disaster."+"\n"+
						"- A gas control unit should respond to a Aztec suffering from a Gasleak Disaster."+"\n"+""+"\n"+

						"The main goal of the game is for the player to efficiently employ the"+"\n"+
						"rescue units to rescue as many Simpsons and minimize the number of deaths. The lower the"+"\n"+
						"number of the deceased Simpsons the highest is your score."+"\n");
				//t.setLayout(new GridLayout(1,2));
				t.setFont(t.getFont().deriveFont(22f));
				t.setVisible(true);
				JPanel p=new JPanel();
				p.setVisible(true);
				p.setPreferredSize(new Dimension(1450,650));
				p.setLayout(new BorderLayout());
				p.add(new JScrollPane(t),BorderLayout.WEST);
				 ImageIcon i=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/MisterBurn.jpg");
					Image image = i.getImage();
					Image newImg = image.getScaledInstance(500,700, java.awt.Image.SCALE_SMOOTH);
					
					i= new ImageIcon(newImg);
					
					p.add(new JLabel(i),BorderLayout.EAST);
						
				
				
				d.add(p,BorderLayout.NORTH);
				d.add(b,BorderLayout.SOUTH);
				
				
				
			}
			if(((JButton) e.getSource()).getName().equals("fehemt")) {
				try {
					d.setVisible(false);
					new StartView();
					
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}
				
				
		}
		
	}
	
	public static void main(String []   args) throws IOException {
	new StartView();
//	File file = new File("name.java");
//    String path = file.getCanonicalPath();
//    System.out.println(path);
	}
	
	

}
