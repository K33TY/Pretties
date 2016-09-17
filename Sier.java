/**================================================================================================||

	██╗  ██╗ █████╗  █████╗ ████████╗██╗   ██╗
	██║ ██╔╝██╔══██╗██╔══██╗╚══██╔══╝╚██╗ ██╔╝
	█████╔╝ ╚█████╔╝╚█████╔╝   ██║    ╚████╔╝ 
	██╔═██╗ ██╔══██╗██╔══██╗   ██║     ╚██╔╝  
	██║  ██╗╚█████╔╝╚█████╔╝   ██║      ██║   
	╚═╝  ╚═╝ ╚════╝  ╚════╝    ╚═╝      ╚═╝    
  
	https://github.com/K33TY 
	3 February 2015 
	EJP                                                                                              
	
	Description: Just playing around with the Sierpinski Fractal Algorithm
	
|=================================================================================================**/

	import java.util.ArrayList;
	import java.awt.Graphics.*;
	import java.awt.geom.*;
	import javax.swing.*;
	import java.awt.*;

	public class Sier
	{
		public static int frameWidth;
		public static int frameHeight;
	
		public static void main(String[] args)
		{
			SierFrame frame = new SierFrame();		
		}
	}

/**=================================================================================================**/
	
	class SierFrame extends JFrame
	{
		public SierFrame()
		{
			Color purple = new Color(140, 40, 255);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int screenWidth  = ((int)screenSize.getHeight()) - 10;
			int screenHeight = ((int)screenSize.getHeight()) - 10;
			setSize(screenWidth, screenHeight);
			SierPanel panel = new SierPanel();
			setTitle("Fractalicious");
			setBackground(purple);
			add(panel);
			repaint();
			setVisible(true);
		}
	}	
	
	class SierPanel extends JPanel
	{	
		public static Color c = new Color(50, 140, 55, 77);
		public static Color purple = new Color(30, 10, 35);
	
		public void divideSquares(Graphics g, int frameWidth, int frameHeight, int x, int y)
		{	
			int squareDiameter;
			if (frameHeight < frameWidth) 
			{
				squareDiameter = frameHeight;
			}
			else
			{ 
				squareDiameter = frameWidth;
			}
		
			int heightDivision = (squareDiameter)/2;
			int lowerWidthDivision = (squareDiameter)/2;
			int leftWidthDivision = (squareDiameter)/4;
			int rightWidthDivision = (squareDiameter)/2 + leftWidthDivision;
		
			//becomes a subsquare
			squareDiameter = heightDivision;
		
			c = newColor(c);
			g.setColor(c);
			g.drawRect(x, y + heightDivision, squareDiameter, squareDiameter);
			c = newColor(c);
			g.setColor(c);
			g.drawRect(x + lowerWidthDivision, y + heightDivision, squareDiameter, squareDiameter);
			c = newColor(c);
			g.setColor(c);
			g.fillRect(x + leftWidthDivision, y, squareDiameter, squareDiameter);

			if (squareDiameter == 1)
			{	
				c = newColor(c);
				//|    LL    |//
				g.fillRect(x, y + heightDivision, squareDiameter, squareDiameter);
			
				//|    LR    |//
				c = newColor(c);
				g.fillRect(x + lowerWidthDivision, y + heightDivision, squareDiameter, squareDiameter);

				//|    TOP   |//
				c = newColor(c);
				g.fillRect(x + leftWidthDivision, y, squareDiameter, squareDiameter);
			
			}
			else
			{
				c = newColor(c);
				g.setColor(c);
			
				//|    LL    |//
				divideSquares(g, squareDiameter, squareDiameter, x, y + heightDivision);

				//|    LR    |//
				divideSquares(g, squareDiameter, squareDiameter, x + lowerWidthDivision, y + heightDivision);
			
				//|    TOP   |//
				divideSquares(g, squareDiameter, squareDiameter, x + leftWidthDivision, y);	
			}
		
			x += squareDiameter;
			y += squareDiameter;
		}
	
		public Color newColor(Color c)
		{
			int alpha = c.getAlpha();
			Color x = new Color(c.getRed(), (c.getBlue()+15)%256, (c.getGreen()+5)%256, alpha);
			return x;
		}
	
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
		
			int frameWidth = getWidth();
			int frameHeight = getHeight();
		
			g.drawString("Expand and", 10 , 20);
			g.drawString("Minimize this", 10 , 40);
			g.drawString("window :)", 10 , 60);
		
			divideSquares(g, frameWidth, frameHeight, 0, 0);
			setBackground(purple);
		}
	}	
