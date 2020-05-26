package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class WorldView extends JFrame {
	private JPanel world;
	private JPanel units;
	private JPanel control;
	private JPanel infopnl;
	private JPanel main ;
	private JPanel up;
	private JTextField title;
	private JPanel  idle;
	private JPanel responding;
	private JPanel treating;
	private JScrollPane info;
	private JScrollPane log;
	private JTextArea infotxt;
	private JTextArea logtxt;
//	private JButton nextcycle;
//	private JButton help;
	private JButton endcycle;
	private JTextField causalities;
	private JTextField Idl;
	private JTextField Res;
	private JTextField Tre;
	private JTextField currcycle;
	private JScrollPane disaster;
	private JTextArea distxt;
	private JPanel iniunits;
	private JTextField avunits;
	private JScrollPane i;
	private JScrollPane t;
	private JScrollPane r;
	
	public WorldView() {
		setLayout(new BorderLayout());
		setBounds(0, 0, 2000, 1050);
		setPreferredSize(new Dimension(2000, 1050));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("THE SIMPSONS TAPPED OUT");
		setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		//setTitle("Rescue Simulation");
		validate();
		repaint();
		
		
		main=new JPanel();
	    main.setBounds(0,0,2000,1050);
	    
		main.setVisible(true);
	    main.setLayout(new BorderLayout());
	    main.setPreferredSize(new Dimension(2000, 1050));
		add(main);
		validate();
		repaint();
		
		up=new JPanel();
	    up.setVisible(true);
	    up.setLayout(new GridLayout(1,1));
	   // up.setBounds(0,0,2000,100);
	    up.setBackground(new Color(255,255,113));
	    up.setPreferredSize(new Dimension(2000,65));
	    main.add(up,BorderLayout.NORTH);
	    revalidate();
	    repaint();
	    
	    title=new JTextField();
	    title.setVisible(true);
	    title.setText("RESCUE THE SIMPSONS");
	    title.setHorizontalAlignment(JTextField.CENTER);
	    
	    title.setBounds(300,0, 2000, 60);
	    title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));	
	    title.setForeground(Color.black);
	    title.setBackground(Color.yellow); 
	    title.setPreferredSize(new Dimension(2000, 70));
	    title.setEditable(false);
	    revalidate();
	    repaint();
	       causalities=new JTextField();
		   causalities.setVisible(true);
		   causalities.setPreferredSize(new Dimension(260, 40));
		   causalities.setText("CAUSALITIES: 0");
		   causalities.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));
		   causalities.setBackground(Color.YELLOW); 
		   causalities.setEditable(false);
		  // causalities.setBounds(1000, 0, 200, 32);
		   validate();
		   repaint();
		   
		   currcycle=new JTextField();
		   currcycle.setVisible(true);
		   currcycle.setPreferredSize(new Dimension(260, 40)); 
		   currcycle.setText("CURRENT CYCLE : 0");  //MSH BYTLA3 GHEER LW HATETLOO TEXT
		   currcycle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));
		   currcycle.setBackground(Color.YELLOW); 
		   currcycle.setEditable(false);
		   //currcycle.setBounds(1000, 32, 200, 32);
		   validate();
		   repaint();
	    up.add(title);
	    //up.add(currcycle);
	    //up.add(causalities);
	    validate();
	    repaint();
	   
		
		world=new JPanel();
        world.setLayout(new GridLayout(10,10));
        //world.setBounds(400,50,800,1100);
		world.setVisible(true);
		world.setPreferredSize(new Dimension(1200, 1000));
	    main.add(world);
		validate();
		repaint();
		
	    units=new JPanel();
	    units.setLayout(new GridLayout(3,1));
	    //units.setBounds(800,50,400,1100);
	    units.setVisible(true);
	    units.setPreferredSize(new Dimension(400, 1000));
         validate();
         repaint();
//	    i=new JScrollPane();
//	    i.setVisible(true);
//	    i.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//	    i.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//	    //i.setBounds(800,50,400,330);
	    
	    
	    idle=new JPanel();
	    responding=new JPanel();
	    treating=new JPanel();
		idle.setVisible(true);
		responding.setVisible(true);
		treating.setVisible(true);
	    idle.setLayout(new FlowLayout());	
	    responding.setLayout(new FlowLayout());
	    treating.setLayout(new FlowLayout());
	    iniunits=new JPanel();
	    iniunits.setVisible(true);
	    iniunits.setLayout(new FlowLayout());
	    
	    idle.setBackground(new Color(255, 179, 181));
	    responding.setBackground(new Color(255, 176, 138));
	    treating.setBackground(new Color(142, 255, 142));
	   // idle.setSize(200,200);
	    //responding.setBounds(800,380,400,330);
	    //treating.setBounds(800,710,400,330);
//	    idle.setVisibleRowCount(3);
//	    responding.setVisibleRowCount(3);
//	    treating.setVisibleRowCount(3);
	    idle.setPreferredSize(new Dimension(400, 1000));
	    treating.setPreferredSize(new Dimension(400, 1000));
	    responding.setPreferredSize(new Dimension(400,1000));
	    
	    revalidate();
	    repaint();
	    
	    Idl= new JTextField("Idle Units");
	    Idl.setBounds(0, 0, units.getWidth(), 30);
	    Idl.setPreferredSize(new Dimension(400,30));
	    Idl.setBackground(Color.RED);
	    Idl.setEditable(false);
	    Idl.setFont(Idl.getFont().deriveFont(20f));
	    revalidate();
	    repaint();
	    
	    
	    
	    Res= new JTextField("Responding Units");
	    Res.setBounds(0, 0, units.getWidth(), 30);
	    Res.setPreferredSize(new Dimension(400,30));
	    Res.setBackground(new Color(244, 122, 0));
	    Res.setEditable(false);
	    Res.setFont(Res.getFont().deriveFont(20f));
	    validate();
	    repaint();
	    
	    Tre= new JTextField("Treating Units");
	    Tre.setBounds(0, 0, units.getWidth(), 30);
	    Tre.setPreferredSize(new Dimension(400,30));
	    Tre.setBackground(new Color(0, 147,0));
	    Tre.setFont(Tre.getFont().deriveFont(20f));
	    Tre.setEditable(false);
	    validate();
	    repaint();
	    
	    idle.add(Idl)	;
	    treating.add(Tre);
	    responding.add(Res);
	    validate();
	    repaint();
	    
	    i=new JScrollPane(idle);
	    i.setPreferredSize(new Dimension(400,330));
	    i.setVisible(true);
	    
	    i.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    i.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    
	    
	    r=new JScrollPane(responding);
	    r.setPreferredSize(new Dimension(400,330));
	    r.setVisible(true);
	    
	    r.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    r.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    
	    t=new JScrollPane(treating);
	    t.setPreferredSize(new Dimension(400,330));
	    t.setVisible(true);
	    
	    t.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    t.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    
	    units.add(i);
	    units.add(r);
	    units.add(t);
	  //  units.add(new JScrollPane(idle));
	    
	   // units.add(new JScrollPane(responding));
	   // units.add(new JScrollPane(treating));
	    
	    main.add(units,BorderLayout.EAST);
	    validate();
	    repaint();
	    
	    infopnl=new JPanel();
	    infopnl.setLayout(new BorderLayout());
	    infopnl.setVisible(true);
	    //infopnl.setBounds(0,1150,main.getWidth(),50);
	    infopnl.setPreferredSize(new Dimension(400,970));
	    
	    validate();
	    repaint();
	    
	   infotxt=new JTextArea(400,900);
	   infotxt.setEditable(false);
	   
	   infotxt.setToolTipText("INFO OF RESCUABLES");
		UIManager.put("ToolTip.font",
		           new FontUIResource("SansSerif", Font.BOLD, 28));
	   logtxt=new JTextArea(400,900);
	   logtxt.setEditable(false);
	   infotxt.setFont(infotxt.getFont().deriveFont(18f));
	   logtxt.setFont(logtxt.getFont().deriveFont(18f));
	   infotxt.setPreferredSize(new Dimension(400,900));
	   logtxt.setPreferredSize(new Dimension(400,900));
	   
	   
	   logtxt.setToolTipText("GAME LOG");
		UIManager.put("ToolTip.font",
		           new FontUIResource("SansSerif", Font.BOLD, 28));
	   infotxt.setBackground(new Color(212, 212, 212));
	   logtxt.setBackground(new Color(212, 212, 212));
	   
	   infotxt.setLineWrap(true);
	   infotxt.setWrapStyleWord(true); 
	   
	   logtxt.setLineWrap(true);
	   logtxt.setWrapStyleWord(true); 
	    
	   distxt=new JTextArea(400,900);
	   distxt.setEditable(false);
	   distxt .setFont(distxt.getFont().deriveFont(18f));
	   distxt.setPreferredSize(new Dimension(400,900));
	   distxt.setBackground(new Color(212, 212, 212));
	   distxt.setLineWrap(true);
	   distxt.setWrapStyleWord(true);  //Do it for info and log
	   
	   
	   distxt.setToolTipText("THOSE ARE THE ACTIVE DISASTERS");
		UIManager.put("ToolTip.font",
		           new FontUIResource("SansSerif", Font.BOLD, 28));
	   
	    validate();
	    repaint();
	    info= new JScrollPane(infotxt);
	    info.setPreferredSize(new Dimension(400,317));
	    info.setVisible(true);
	    log=new JScrollPane(logtxt);
	    log.setPreferredSize(new Dimension(400,317));
	    log.setVisible(true);
	     validate();
	     repaint();
	     
	     
	    disaster= new JScrollPane(distxt );
		disaster.setPreferredSize(new Dimension(400,340));
		disaster.setVisible(true);
		
	    info.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    disaster.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    log.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    info.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    disaster.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    log.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    infopnl.add(info,BorderLayout.NORTH);
	    infopnl.add(log,BorderLayout.SOUTH);
	    infopnl.add(disaster,BorderLayout.CENTER);
	    main.add(infopnl,BorderLayout.WEST);
	    validate();
	    repaint();
	    
	   control=new JPanel();
	   control.setPreferredSize(new Dimension(2000,50));
	   control.setVisible(true);
	   control.setLayout(new GridLayout(1,4)); 
	   control.setBackground(new Color(212, 212, 212));
	   control.add(currcycle);
	   control.add(causalities);
	   validate();
	   repaint();
//	   nextcycle=new JButton();
//	   nextcycle.setVisible(true);
//	   nextcycle.setSize(100,100);
//	   nextcycle.setText("NEXT CYCLE");
//	   nextcycle.setName("nextcycle");
//	   nextcycle.setActionCommand("nc");
//	   
//	   help=new JButton();
//	   help.setVisible(true);
//	   help.setSize(100,100);
//	   help.setText("HELP");
//	   help.setName("help");
	   
	   
//	   endcycle=new JButton();
//	   endcycle.setVisible(true);
//	   endcycle.setSize(100,100);
//	   endcycle.setText("END CYCLE");
//	   endcycle.setName("endcycle");
	   
	   //control.add(nextcycle);
	   //control.add(help);
	 //  control.add(endcycle);
	  // control.add(currcycle);
	  // control.add(causalities);
	   
	   main.add(control,BorderLayout.SOUTH);
	    validate();
	    repaint();
	}
	public JPanel getWorld() {
		return world;
	}
	public JPanel getUnits() {
		return units;
	}
	public JPanel getControl() {
		return control;
	}
	public JPanel getInfopnl() {
		return infopnl;
	}
	public JPanel getMain() {
		return main;
	}
	public JPanel getIdle() {
		return idle;
	}
	public JPanel getResponding() {
		return responding;
	}
	public  JPanel getTreating() {
		return treating;
	}
	public JScrollPane getInfo() {
		return info;
	}
	public JScrollPane getLog() {
		return log;
	}
	public JTextArea getInfotxt() {
		return infotxt;
	}
	public JTextArea getLogtxt() {
		return logtxt;
	}

	public JTextField getCausalities() {
		return causalities;
	}
	public JTextField getCurrcycle() {
		return currcycle;
	}
	public JPanel getUp() {
		return up;
	}

	public JButton getEndcycle() {
		return endcycle;
	}
	public JTextField getIdl() {
		return Idl;
	}
	public JTextField getRes() {
		return Res;
	}
	public JTextField getTre() {
		return Tre;
	}
	public JScrollPane getDisaster() {
		return disaster;
	}
	public JTextArea getDistxt() {
		return distxt;
	}
	
	
	

	
	
}
