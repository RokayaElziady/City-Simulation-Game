package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.junit.validator.ValidateWith;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CannotTreatException;
import exceptions.CitizenAlreadyDeadException;
import exceptions.IncompatibleTargetException;
import model.events.SOSListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import model.units.Ambulance;
import model.units.DiseaseControlUnit;
import model.units.Evacuator;
import model.units.FireTruck;
import model.units.GasControlUnit;
import model.units.Unit;
import model.units.UnitState;
import simulation.Rescuable;
import simulation.Simulator;
import view.StartView;
import view.WorldView;

public class CommandCenter implements SOSListener , ActionListener{

	private Simulator engine;
	private ArrayList<ResidentialBuilding> visibleBuildings;
	private ArrayList<Citizen> visibleCitizens;
	private WorldView worldview;
	private ArrayList<JButton> worldptns;
	private ArrayList<JButton>  unitbtns;
	private JButton nextcycle;
	private JButton help;
	private ArrayList<Unit> actunits;
	private static Unit lcu;
	private static Rescuable lcr;
	private ArrayList<JButton> bo;
	@SuppressWarnings("unused")
	private ArrayList<Unit> emergencyUnits;
	private JDialog f;
	private JDialog o; 
	 private boolean w=true;
	 private  boolean c=false;
	
	//private ArrayList<JButton> handleres;

	public CommandCenter() throws Exception {
		engine = new Simulator(this);
		visibleBuildings = new ArrayList<ResidentialBuilding>();
		visibleCitizens = new ArrayList<Citizen>();
		emergencyUnits = engine.getEmergencyUnits();
		worldview = new WorldView();
		worldptns=new ArrayList<JButton>();
		unitbtns=new ArrayList<JButton>();
		actunits=new  ArrayList<Unit>();
		//handleres=new ArrayList<JButton>();
		bo=new ArrayList<JButton>();
		for( int i=0; i<10;i++) {
             w=!w;
             c=!c;
			for(int j=0;j<10;j++) {
			  
			   
				JButton b=new JButton();
				b.setVisible(true);
				b.setName(i+""+j);
				b.addActionListener(this);
				worldview.getWorld().add(b);
				worldptns.add(b);
				if(w==true) {
				b.setBackground(Color.WHITE);
				w=false;
				c=true;
				}
				else {
					if(c==true) {
						b.setBackground(new Color(155, 191, 227));
						c=false;
						w=true;		
					}
						
				}
                
			}
		}
		
		for(int i=0;i<worldptns.size();i++) {
			int c=0;
			boolean btnadded=false;
			JButton b=worldptns.get(i);
			String n=b.getName();
			int x=Integer.parseInt(n.charAt(0)+"");
			int y=Integer.parseInt(n.charAt(1)+"");		
			for(int j=0;j<engine.getBuildings().size() && btnadded==false;j++ ) {
				if(engine.getBuildings().get(j).getLocation().getX()==x && engine.getBuildings().get(j).getLocation().getY()==y) {
					if(engine.getBuildings().get(j).getOccupants().size()>0 ) {
						ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/aztcmanycitizens.jpg");
						Image image = t.getImage();
						Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
						t= new ImageIcon(newImg);
						b.setIcon(t);
						//b.setBackground(Color.BLACK);
						btnadded=true;
					}
					else {
						ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/sipsAztec.png");
						Image image = t.getImage();
						Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
						t= new ImageIcon(newImg);
						b.setIcon(t);
						btnadded=true;
					}
				}
			}
			for(int j=0;j<engine.getCitizens().size() && btnadded==false;j++) {
				if(engine.getCitizens().get(j).getLocation().getX()==x && engine.getCitizens().get(j).getLocation().getY()==y) {
					c++;
				}
			}
			if(c==1) {
					
					ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/citizenph.jpg");
					Image image = t.getImage();
					Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
					t= new ImageIcon(newImg);
					b.setIcon(t);
					btnadded=true;
				}
			if(c>1) {
				ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/manysimpsons.jpg");
				Image image = t.getImage();
				Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
				t= new ImageIcon(newImg);
				b.setIcon(t);
				btnadded=true;
			}
			
		}

		nextcycle=new JButton();
		   nextcycle.setVisible(true);
		   nextcycle.setPreferredSize(new Dimension(200, 40));
		   nextcycle.setText("NEXT CYCLE");
		   nextcycle.setName("nextcycle");
		   nextcycle.addActionListener(this);
		   nextcycle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));	 
		    nextcycle.setBackground(Color.YELLOW); 
		   // nextcycle.setPreferredSize(new Dimension(120, 65));
		   UIManager.put("ToolTip.font",
		           new FontUIResource("SansSerif", Font.PLAIN, 18));
		   nextcycle.setToolTipText("CLICK ME FOR NEXT CYCLE");
		   worldview.getControl().add(nextcycle);
		   
		   help=new JButton();
		   help.setVisible(true);
		   help.setPreferredSize(new Dimension(200, 40));
		   help.setText("RESPOND");
		   help.setName("help");
		   help.addActionListener(this);
		   help.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));	 
		   help.setBackground(Color.YELLOW); 
		   //
		   //help.setPreferredSize(new Dimension(120, 65));
		   UIManager.put("ToolTip.font",
		           new FontUIResource("SansSerif", Font.PLAIN, 18));
		   help.setToolTipText("RESPOND TO SIMPSON");
		   worldview.getControl().add(help);

		   
		  
		for(int i=0;i<engine.getEmergencyUnits().size();i++) {
			if(engine.getEmergencyUnits().get(i) instanceof Ambulance) {
				ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/ambulance-van-vector-807446.jpg");
				Image image = t.getImage();
				Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
				t= new ImageIcon(newImg);
				JButton b=new JButton(t);
				unitbtns.add(b);
				actunits.add(engine.getEmergencyUnits().get(i));
				b.setVisible(true);
				b.addActionListener(this);                  
				b.setSize(20, 20);
				b.setBackground(Color.WHITE);
				b.setToolTipText(engine.getEmergencyUnits().get(i).toString());
				UIManager.put("ToolTip.font",
				           new FontUIResource("SansSerif", Font.BOLD, 18));
				worldview.getIdle().add(b);
				b.setName("unit");
				//worldview.getI().getViewport().add(b);
			}
			if(engine.getEmergencyUnits().get(i) instanceof DiseaseControlUnit) {
				ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/dcu.jpg");
				Image image = t.getImage();
				Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
				t= new ImageIcon(newImg);
				JButton b=new JButton(t);
				unitbtns.add(b);
				actunits.add(engine.getEmergencyUnits().get(i));
				b.setVisible(true);
				b.addActionListener(this);
				b.setSize(20, 20);
				b.setBackground(Color.WHITE);
				UIManager.put("ToolTip.font",
				           new FontUIResource("SansSerif", Font.BOLD, 18));
				b.setToolTipText(engine.getEmergencyUnits().get(i).toString());
				worldview.getIdle().add(b);
				b.setName("unit");
				//worldview.getI().getViewport().add(b)
			}
			if(engine.getEmergencyUnits().get(i) instanceof Evacuator) {
				ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/evacuator.jpg");
				Image image = t.getImage();
				Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
				t= new ImageIcon(newImg);
				JButton b=new JButton(t);
				unitbtns.add(b);
				actunits.add(engine.getEmergencyUnits().get(i));
				b.setVisible(true);
				b.setBackground(Color.WHITE);
				b.addActionListener(this);
				b.setSize(20, 20);
//				UIManager.put("ToolTip.font",
//				           new FontUIResource("SansSerif", Font.BOLD, 18));
//				b.setToolTipText(engine.getEmergencyUnits().get(i).toString());
				worldview.getIdle().add(b);
				b.setName("unit");
				//worldview.getI().getViewport().add(b);
			}
			if(engine.getEmergencyUnits().get(i) instanceof FireTruck) {
				ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/fire truck.jpg");
				Image image = t.getImage();
				Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
				t= new ImageIcon(newImg);
				JButton b=new JButton(t);
				unitbtns.add(b);
				actunits.add(engine.getEmergencyUnits().get(i));
				b.setVisible(true);
				b.addActionListener(this);
				b.setBackground(Color.WHITE);
				b.setSize(20, 20);
				b.setToolTipText(engine.getEmergencyUnits().get(i).toString());
				worldview.getIdle().add(b);
				b.setName("unit");
				UIManager.put("ToolTip.font",
				           new FontUIResource("SansSerif", Font.BOLD, 18));
				//worldview.getI().getViewport().add(b);
			}
			if(engine.getEmergencyUnits().get(i) instanceof GasControlUnit) {
				ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/gas control unit.jpg");
				Image image = t.getImage();
				Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
				t= new ImageIcon(newImg);
				JButton b=new JButton(t);
				
				unitbtns.add(b);
				actunits.add(engine.getEmergencyUnits().get(i));
				b.setVisible(true);
				b.addActionListener(this);
				b.setSize(20, 20);
				b.setBackground(Color.WHITE);
				b.setToolTipText(engine.getEmergencyUnits().get(i).toString());
				UIManager.put("ToolTip.font",
				           new FontUIResource("SansSerif", Font.BOLD, 18));
				worldview.getIdle().add(b);
				b.setName("unit");
				//worldview.getI().getViewport().add(b);
			}
		}
	

	}
	public void actionPerformed(ActionEvent e) {
		String a=engine.getActiveDisasters();
		worldview.getDistxt().setText(a);
		worldview.getDistxt().setForeground(Color.red);
		String r=engine.getLog();
		worldview.getLogtxt().setText(r);
		worldview.getLogtxt().setForeground(Color.black);
		if(((JButton)e.getSource()).getName().equals("help")){
			worldview.getInfotxt().setText("");
			if(lcu==null || lcr==null) {
				lcu=null;
				lcr=null;
				JOptionPane.showMessageDialog(worldview, "I CAN'T TREAT PLEASE PRESS A UNIT THEN A TARGET YOU WANNA TREAT");
//				JDialog o=new JDialog();
//				o.setSize(600, 400);
//				o.setBounds(600, 500, 600, 400);
//				o.setVisible(true);
//				o.setBackground(Color.BLUE);//blue for cant ttreat
//				JTextField t=new JTextField();
//				
//				t.setText("I CAN'T TREAT PLEASE PRESS A UNIT THEN A TARGET YOU WANNA TREAT");
//				t.setVisible(true);
//				o.add(t);
				lcu=null;
				lcr=null;
			}
			else {
				try {
					//System.out.println("SDFGHJKHGFDSFGHJ");
					lcu.respond(lcr);
					lcu=null;
					lcr=null;
				} catch (IncompatibleTargetException | CannotTreatException e1) {
					
					JOptionPane.showMessageDialog(worldview, e1.getMessage());
//					System.out.println("blabla");
//					JDialog x=new JDialog();
//					x.setVisible(true);
//					System.out.println("");
//					x.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
//					x.setPreferredSize(new Dimension(600,400));
//					x.setBackground(Color.RED);
//					JTextField q=new JTextField();
//					q.setText(e1.getMessage());
//					q.setVisible(true);
//					q.setPreferredSize(new Dimension(600,400));
//					x.add(q);
			        
				}
			}
			
		}
	    if(((JButton) e.getSource()).getName().equals("exit")) {
	    	
	    	f.setVisible(false);
	    	worldview.setVisible(false);
	    	
	    	
	    }
	    if(((JButton) e.getSource()).getName().equals("playagain")) {
	    	f.setVisible(false);
	    	worldview.setVisible(false);
	    	new StartView();
	    }
	    if(((JButton) e.getSource()).getName().charAt(0)=='o') {
	    	o.setVisible(false);
	    	int a1=bo.indexOf(((JButton) e.getSource()));
	    	lcr=((ResidentialBuilding)lcr).getOccupants().get(a1);
	    	String m=lcr.toString();
	    	worldview.getInfotxt().setText(m);
	    	
	    }
		
		if(((JButton) e.getSource()).getName()!=null && ((JButton) e.getSource()).getName().length()==2) {
			String m="";
			JButton b=(JButton) e.getSource();
			String n=b.getName();
			int x=Integer.parseInt(n.charAt(0)+"");
			int y=Integer.parseInt(n.charAt(1)+"");
			boolean d=false;
			for(int i=0;i<engine.getBuildings().size();i++) {
			
				if(engine.getBuildings().get(i).getLocation().getX()==x && engine.getBuildings().get(i).getLocation().getY()==y && lcu!=null) {
					   if(engine.getBuildings().get(i).getOccupants().size()==0 ) {
					   lcr=engine.getBuildings().get(i);
					   }
					   else {
						   while(bo.size()>0) {
							   bo.remove(0);
							   
						   }
						    o=new JDialog();
						   o.setLayout(new FlowLayout());
						   o.setPreferredSize(new Dimension(800, 500));
						   o.setBounds(600,300,800,500);
						   o.getContentPane().setBackground(Color.WHITE);
						   o.setVisible(true);
						   for(int j=0;j<engine.getBuildings().get(i).getOccupants().size();j++) {
							   lcr=engine.getBuildings().get(i);
							   
							  
							   JButton b1=new JButton();
							   b1.setName("o"+engine.getBuildings().get(i).getOccupants().get(j).getNationalID());
							   b1.setText(engine.getBuildings().get(i).getOccupants().get(j).getName());
							   b1.setPreferredSize(new Dimension(100, 50));
							   b1.setVisible(true);
							   b1.setBackground(Color.YELLOW);
							   b1.addActionListener(this);
							   o.add(b1);
							   bo.add(b1);
							   
							   
						   }
						   
					   }
				}
				
					
			}
			for(int i=0;i<engine.getCitizens().size();i++) {
				if(engine.getCitizens().get(i).getLocation().getX()==x && engine.getCitizens().get(i).getLocation().getY()==y && lcu!=null && lcr==null) {
					lcr=engine.getCitizens().get(i);
				}
			}
			

			for(int i=0;i<engine.getBuildings().size();i++) {
				if(engine.getBuildings().get(i).getLocation().getX()==x && engine.getBuildings().get(i).getLocation().getY()==y ) {
					m+=engine.getBuildings().get(i).toString();
					d=true;
				}

			}

			for(int j=0;j<engine.getCitizens().size() && !d;j++) {
				if(engine.getCitizens().get(j).getLocation().getX()==x && engine.getCitizens().get(j).getLocation().getY()==y) {
					m+=engine.getCitizens().get(j).toString();
				}

			}
			worldview.getInfotxt().setText(m);
			worldview.getInfotxt().setForeground(Color.black);
		}
		
		
		
	  if(((JButton) e.getSource()).getName()=="nextcycle") {
		  
		  
		  worldview.getInfotxt().setText("");	
		 
			
						try {
							engine.nextCycle();
						} catch (CitizenAlreadyDeadException | BuildingAlreadyCollapsedException e1) {
							JOptionPane.showMessageDialog(worldview, e1.getMessage());
//							JOptionPane o=new JOptionPane(); 
//							o.setVisible(true);
//							o.setPreferredSize(new Dimension(600,300));
//							o.setBackground(Color.RED);
//							o.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
//							o.showMessageDialog(worldview.getWorld(),e1.getMessage());
//							
						}
						 worldview.getCurrcycle().setText("CURRENT CYCLE: "+engine.getCurrentCycle());
						remAllUnits();
						AddAllUnits();
						worldview.getIdle().revalidate();
						worldview.getIdle().repaint();
						
						worldview.getResponding().revalidate();
						worldview.getResponding().repaint();
						
						worldview.getTreating().revalidate();
						worldview.getTreating().repaint();
						
						for(int i=0;i<worldptns.size();i++) {
							
							int c=0;
							boolean d=false;
							boolean btnadded=false;
							JButton b=worldptns.get(i);
							String n=b.getName();
							int x=Integer.parseInt(n.charAt(0)+"");
							int y=Integer.parseInt(n.charAt(1)+"");		
							for(int j=0;j<engine.getBuildings().size() && btnadded==false;j++ ) {
								if(engine.getBuildings().get(j).getLocation().getX()==x && engine.getBuildings().get(j).getLocation().getY()==y) {
									if(engine.getBuildings().get(j).getOccupants().size()>0  && engine.getBuildings().get(j).getStructuralIntegrity()>0) {
										ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/aztcmanycitizens.jpg");
										Image image = t.getImage();
										Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
										t= new ImageIcon(newImg);
										b.setIcon(t);
										//b.setBackground(Color.BLACK);
										btnadded=true;
									}
									else {
										if(engine.getBuildings().get(j).getOccupants().size()==0 && engine.getBuildings().get(j).getStructuralIntegrity()>0) {
										ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/sipsAztec.png");
										Image image = t.getImage();
										Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
										t= new ImageIcon(newImg);
										b.setIcon(t);
										btnadded=true;
										}
										else {
											if(engine.getBuildings().get(j).getOccupants().size()>0  && engine.getBuildings().get(j).getStructuralIntegrity()==0) {
												ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/destroyedbuildinsipmsons.png");
												Image image = t.getImage();
												Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
												t= new ImageIcon(newImg);
												b.setIcon(t);
												btnadded=true;
											}
											else {
												if(engine.getBuildings().get(j).getOccupants().size()==0 && engine.getBuildings().get(j).getStructuralIntegrity()==0) {
													ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/destroyedbuildingff.jpg");
													Image image = t.getImage();
													Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
													t= new ImageIcon(newImg);
													b.setIcon(t);
													btnadded=true;
												}
											}
										}
									}
								}
							}
							for(int j=0;j<engine.getCitizens().size() && btnadded==false;j++) {
								if(engine.getCitizens().get(j).getLocation().getX()==x && engine.getCitizens().get(j).getLocation().getY()==y) {
									c++;
									if(engine.getCitizens().get(j).getState().equals(CitizenState.DECEASED))
										d=true;
								}
							}
							if(c==1 && !d) {
									
									ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/citizenph.jpg");
									Image image = t.getImage();
									Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
									t= new ImageIcon(newImg);
									b.setIcon(t);
									btnadded=true;
								}
							else{
								if(c>1  && !d) {
							
								ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/manysimpsons.jpg");
								Image image = t.getImage();
								Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
								t= new ImageIcon(newImg);
								b.setIcon(t);
								btnadded=true;
							}
								else {
									if(c>1  && d) {
										
										ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/citizensalldies.jpg");
										Image image = t.getImage();
										Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
										t= new ImageIcon(newImg);
										b.setIcon(t);
										btnadded=true;
									
								}
									else {
										if(c==1 && d) {
											
											ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/simsonres.jpg");
											Image image = t.getImage();
											Image newImg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
											t= new ImageIcon(newImg);
											b.setIcon(t);
											btnadded=true;
										}
									}
							}
						}
						
						}						
					
					worldview.getCausalities().setText("CAUSALITIES: "+engine.calculateCasualties());
					if(engine.checkGameOver()) {      //MAKE GRID LAYOUT AND PANNELS TO MAKE CAUSALITIES APPEAR DOWN
					    f=new JDialog();
						f.setTitle("GAME OVER");
						//f.getTitle().setFont(f.getTitle().getFont().deriveFont(20f));
						f.setLayout(new BorderLayout());
						f.setBackground(Color.white);
						f.setBounds(500, 330, 700, 600);
						f.setPreferredSize(new Dimension(700,600));
						f.setVisible(true);
						//f.setUndecorated(true);
						
						JPanel down =new JPanel();
						down.setVisible(true);
						down.setLayout(new GridLayout(1,2));
						down.setSize(700,100);
						down.setBackground(Color.black);
						
						JButton b=new JButton();
						b.setText("EXIT");
						b.setName("exit");
						b.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));	
						b.setVisible(true);
						b.setBackground(Color.yellow);
						JButton c=new JButton();
						c.setText("PLAY AGAIN");
						c.setName("playagain");
						c.setVisible(true);
						c.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));	
						c.setBackground(Color.yellow);
						b.setPreferredSize(new Dimension(336,100));
						c.setPreferredSize(new Dimension(336,100));
						
						b.addActionListener(this);
						c.addActionListener(this);
						down.add(b);
						down.add(c);
						ImageIcon t=new ImageIcon("C:/Users/hp/Desktop/RescueSimulation-M2/gameover.jpg");
						Image image = t.getImage();
						Image newImg = image.getScaledInstance(700,400, java.awt.Image.SCALE_SMOOTH);
						t= new ImageIcon(newImg);
						
						JTextField ca =new JTextField();
						ca.setVisible(true);
						ca.setEditable(false);
						ca.setPreferredSize(new Dimension(700,100));  //THINK OF DISPLAYING MESSAGES TO TELL HE IS PLAYING GOOD OR WHAT
						ca.setBackground(Color.RED);
						ca.setText("GAME OVER         YOUR SCORE IS  : "+engine.calculateCasualties()+"");
						ca.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));	
						f.add(ca,BorderLayout.NORTH);
						f.add(new JLabel(t),BorderLayout.CENTER);
						f.add(down,BorderLayout.SOUTH);
			     		worldview.setEnabled(false);
					    



					
		
	}
					
					
					for(int i=0;i<actunits.size();i++) {
						unitbtns.get(i).setToolTipText(actunits.get(i).toString());
						UIManager.put("ToolTip.font",
						           new FontUIResource("SansSerif", Font.BOLD, 18));
					}
					lcu=null;
					}
			
							if(((JButton) e.getSource()).getName()=="unit"){
								int q=unitbtns.indexOf(((JButton) e.getSource()));
								Unit u=actunits.get(q);
								if(u instanceof Evacuator) {
									JOptionPane.showMessageDialog(worldview, u.toString());
									
								}
								worldview.getInfotxt().setText("");
								lcr=null;
								JButton b=((JButton) e.getSource());
								int a1= unitbtns.indexOf(b);
								lcu=actunits.get(a1);
								worldview.getInfotxt().setText("");
								
								
								
							}			
						
	}
 
		  
	  
  



	@Override
	public void receiveSOSCall(Rescuable r) {

		if (r instanceof ResidentialBuilding) {

			if (!visibleBuildings.contains(r))
				visibleBuildings.add((ResidentialBuilding) r);

		} else {

			if (!visibleCitizens.contains(r))
				visibleCitizens.add((Citizen) r);
		}

	}
//	public static void main (String []  args) throws Exception {
//
//		new CommandCenter();
//	}

public void remAllUnits() {
	Component [] i=worldview.getIdle().getComponents();
	for(int c=0;c<i.length;c++) {
		if(i[c] instanceof JButton) {
			i[c].setVisible(false);
		}
	}
	Component [] r=worldview.getIdle().getComponents();
	for(int c=0;c<r.length;c++) {
		if(r[c] instanceof JButton) {
			r[c].setVisible(false);
		}
	}
	Component [] t=worldview.getIdle().getComponents();
	for(int c=0;c<t.length;c++) {
		if(t[c] instanceof JButton) {
			t[c].setVisible(false);
		}
	}
}

public void AddAllUnits() {
	for(int i=0;i<unitbtns.size();i++) {
		unitbtns.get(i).setVisible(true);
	}
	for(int i=0;i<actunits.size();i++) {
		if(actunits.get(i).getState().equals(UnitState.IDLE)) {
			worldview.getIdle().add(unitbtns.get(i));
			worldview.getIdle().revalidate();
			worldview.getIdle().repaint();
		}
		if(actunits.get(i).getState().equals(UnitState.RESPONDING)) {
			worldview.getResponding().add(unitbtns.get(i));
			worldview.getResponding().revalidate();
			worldview.getResponding().repaint();
		}
		if(actunits.get(i).getState().equals(UnitState.TREATING)) {
			worldview.getTreating().add(unitbtns.get(i));
			worldview.getTreating().revalidate();
			worldview.getTreating().repaint();
		}
	}
}


}
