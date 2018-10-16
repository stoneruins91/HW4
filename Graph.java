import static java.lang.Math.abs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Graph extends JFrame implements MouseListener {
	private JPanel Content;
	private JTextField TextField1;
	private JTextField TextField2;
	private JTextField TextField3;
	private boolean mouse=false;
	
	
	/*Create the frame*/
	public Graph() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		Content = new JPanel();
		Content.setBorder(new EmptyBorder(8, 8, 8, 8));
		setContentPane(Content);
		Content.setLayout(null);
		
		//Label for Red
		JLabel label1 = new JLabel("R");
		label1.setBounds(46, 40, 20, 20);
		Content.add(label1);
		
		//Label for Green
		JLabel label2 = new JLabel("G");
		label2.setBounds(46, 80, 20, 20);
		Content.add(label2);
		
		//Label for blue
		JLabel label3 = new JLabel("B");
		label3.setBounds(46, 120, 20, 20);
		Content.add(label3);
		  
		//Textfield for red input
		TextField1 = new JTextField();
		TextField1.setBounds(86, 40, 90, 20); //x= 46+20+20, y=y
		Content.add(TextField1);
		
		
		//Textfield for green input
		TextField2 = new JTextField();
		TextField2.setBounds(86, 80, 90, 20); //x= 46+20+20, y=y
		Content.add(TextField2);
		
		//Textfield for blue input
		TextField3 = new JTextField();
		TextField3.setBounds(86, 120, 90, 20); //x= 46+20+20, y=y
		Content.add(TextField3);
		
		//Button for graph
		JButton graph = new JButton("Graph");
		graph.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
			mouse=true;
			repaint();
			}
		});
		graph.setBounds(45, 145, 80, 30);
		Content.add(graph);
		addMouseListener(this);
		}
	
	
	//method paint the graph
	public void paint(Graphics graph)
	{
		super.paint(graph);
		if (mouse==true)
		{
			String s1=TextField1.getText();
			String s2=TextField2.getText();
			String s3=TextField3.getText();
			int red=Integer.parseInt(s1);
			int green=Integer.parseInt(s2);
			int blue=Integer.parseInt(s3);
			int max=getmax(red,green,blue);
			
			
			// multiple to constant to make the graph bigger. Divide my max to keep the scale
			int bar1=red*100/max;
			int bar2=green*100/max;
			int bar3=blue*100/max;
			  
			graph.setColor(new Color(255,0,0));
			graph.fillRect(100,250,bar1,40);
			graph.drawString("Red", 10, 270);
			
			graph.setColor(new Color(0,128,0));
			graph.fillRect(100,300,bar2,40);
			graph.drawString("Green", 10, 320);
			
			graph.setColor(new Color(0,0,255));
			graph.fillRect(100,350,bar3,40);
			graph.drawString("Blue", 10, 370);
		}
	}
	
	
	//method find the max int between 3 input red, green and blue
	private int getmax(int red,int green,int blue)
	{
		if (red>green && red>blue)
			return red;
		else
			if (green>blue)
				return green;
			else
				return blue;
	}
	
	
	//method to update the graphbar with new set of input red, green, blue
	private void ChangeBar(int x)
	{
		String s1=TextField1.getText();
		String s2=TextField2.getText();
		String s3=TextField3.getText();
		  
		int red=Integer.parseInt(s1);
		int green=Integer.parseInt(s2);
		int blue=Integer.parseInt(s3);
		int max=getmax(red,green,blue);
		  
		if (max > x)
		{
			int d = abs(max-x-0);
			red -= d;
			green -= d;
			blue -= d;
		}
		else
		{
			int d = abs(max+x+100);
			red += d;
			green += d;
			blue += d;
		}
		TextField1.setText(String.valueOf(red));
		TextField2.setText(String.valueOf(green));
		TextField3.setText(String.valueOf(blue));
		repaint();
		}
	
	
	//Override all methods from mouseclicked interface
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		if (mouse)
			ChangeBar(e.getX());
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
