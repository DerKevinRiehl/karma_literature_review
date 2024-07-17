// Part of: "Karma Literature Review", Copyrights Kevin Riehl 2024, <kriehl@ethz.ch>
package tools;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RobotTools 
{
//	Image
	public static Dimension getScreenSize()
	{
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	public static BufferedImage getScreenShot()
	{
        try 
        {
			return new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
        return null;
	}
	public static BufferedImage cutImage(BufferedImage bi, Rectangle r)
	{
		return bi.getSubimage(r.x,r.y,r.width,r.height);
	}
	public static BufferedImage cutImage(BufferedImage bi, int x, int y, int w, int h)
	{
		return bi.getSubimage(x, y, w, h);
	}
	public static Color averageColor(BufferedImage bi)
	{
	    long sumr = 0, sumg = 0, sumb = 0;
	    for (int x = 0; x < bi.getWidth(); x++) 
	    {
	        for (int y = 0; y < bi.getHeight(); y++) 
	        {
	            Color pixel = new Color(bi.getRGB(x, y));
	            sumr += pixel.getRed();
	            sumg += pixel.getGreen();
	            sumb += pixel.getBlue();
	        }
	    }
	    int num = bi.getWidth() * bi.getHeight();
	    int r = (int) (sumr/num);
	    int g = (int) (sumg/num);
	    int b = (int) (sumb/num);
	    return new Color(r,g,b);
	}
	public static void saveImage(BufferedImage bi,String file)
	{
		try
		{
			ImageIO.write(bi, "PNG", new File(file));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static BufferedImage loadImage(String file)
	{
		try
		{
			return ImageIO.read(new File(file));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	public static boolean compareColors(Color a, Color b, int tol)
	{
		if( (a.getRed()<b.getRed()+tol&&a.getRed()>b.getRed()-tol)&&
			(a.getGreen()<b.getGreen()+tol&&a.getGreen()>b.getGreen()-tol)&&
			(a.getBlue()<b.getBlue()+tol&&a.getBlue()>b.getBlue()-tol))
		{
			return true;
		}
		return false;
	}

//	Beep
	public static void beep()
	{
		Toolkit.getDefaultToolkit().beep();
	}
	
//	ToolTip
	public static String getClipBoard()
	{
		try
		{
			return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	public static void saveClipBoard(String text)
	{
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
	}
	
//	Wait
	public static void waitMS(int ms)
	{
		try 
		{
			Thread.sleep(ms);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	
//	Mouse
	public static void moveMouse(double x, double y)
	{
		try 
		{
			new Robot().mouseMove((int)x, (int)y);
		} 
		catch (AWTException e) 
		{
			e.printStackTrace();
		}
	}
	public static void mousePress(int button)
	{
		try 
		{
			int bCode = 0;
			if(button==MouseEvent.BUTTON1)
			{
				bCode = InputEvent.BUTTON1_DOWN_MASK ;
			}
			if(button==MouseEvent.BUTTON2)
			{
				bCode = InputEvent.BUTTON2_DOWN_MASK ;
			}
			if(button==MouseEvent.BUTTON3)
			{
				bCode = InputEvent.BUTTON3_DOWN_MASK ;
			}
			
			new Robot().mousePress(bCode);
		} 
		catch (AWTException e) 
		{
			e.printStackTrace();
		}
	}
	public static void mouseRelease(int button)
	{
		try 
		{
			int bCode = 0;
			if(button==MouseEvent.BUTTON1)
			{
				bCode = InputEvent.BUTTON1_DOWN_MASK ;
			}
			if(button==MouseEvent.BUTTON2)
			{
				bCode = InputEvent.BUTTON2_DOWN_MASK ;
			}
			if(button==MouseEvent.BUTTON3)
			{
				bCode = InputEvent.BUTTON3_DOWN_MASK ;
			}
			
			new Robot().mouseRelease(bCode);
		} 
		catch (AWTException e) 
		{
			e.printStackTrace();
		}
	}
	public static void mouseClick(int button)
	{
		mousePress(button);
		waitMS(100);
		mouseRelease(button);
	}
	public static void mouseDoubleClick(int button)
	{
		mousePress(button);
		waitMS(100);
		mouseRelease(button);
		waitMS(100);
		mousePress(button);
		waitMS(100);
		mouseRelease(button);
	}
	public static void mouseScroll(int notches)
	{
		try 
		{
			new Robot().mouseWheel(notches);
		} 
		catch (AWTException e) 
		{
			e.printStackTrace();
		}
	}
	
//	Keyboard
	public static void keyPress(int button)
	{
		try 
		{
			new Robot().keyPress(button);
		} 
		catch (AWTException e) 
		{
			e.printStackTrace();
		}
	}
	public static void keyRelease(int button)
	{
		try 
		{
			new Robot().keyRelease(button);
		} 
		catch (AWTException e) 
		{
			e.printStackTrace();
		}
	}
	public static void keyClick(int button)
	{
		keyPress(button);
		waitMS(100);
		keyRelease(button);
	}
	public static void keyCTRL_S()
	{
		keyPress(KeyEvent.VK_CONTROL);
		waitMS(100);
		keyClick(KeyEvent.VK_S);
		waitMS(100);
		keyRelease(KeyEvent.VK_CONTROL);
	}
	public static void keyCTRL_F()
	{
		keyPress(KeyEvent.VK_CONTROL);
		waitMS(100);
		keyClick(KeyEvent.VK_F);
		waitMS(100);
		keyRelease(KeyEvent.VK_CONTROL);
	}
	public static void keyCTRL_L()
	{
		keyPress(KeyEvent.VK_CONTROL);
		waitMS(100);
		keyClick(KeyEvent.VK_L);
		waitMS(100);
		keyRelease(KeyEvent.VK_CONTROL);
	}
	public static void keyCTRL_W()
	{
		keyPress(KeyEvent.VK_CONTROL);
		waitMS(100);
		keyClick(KeyEvent.VK_W);
		waitMS(100);
		keyRelease(KeyEvent.VK_CONTROL);
	}
	public static void keyCTRL_T()
	{
		keyPress(KeyEvent.VK_CONTROL);
		waitMS(100);
		keyClick(KeyEvent.VK_T);
		waitMS(100);
		keyRelease(KeyEvent.VK_CONTROL);
	}
	public static void keyCTRL_U()
	{
		keyPress(KeyEvent.VK_CONTROL);
		waitMS(100);
		keyClick(KeyEvent.VK_U);
		waitMS(100);
		keyRelease(KeyEvent.VK_CONTROL);
	}
	public static void keyCTRLCopy()
	{
		keyPress(KeyEvent.VK_CONTROL);
		waitMS(100);
		keyClick(KeyEvent.VK_C);
		waitMS(100);
		keyRelease(KeyEvent.VK_CONTROL);
	}
	public static void keyCTRLPaste()
	{
		keyPress(KeyEvent.VK_CONTROL);
		waitMS(100);
		keyClick(KeyEvent.VK_V);
		waitMS(100);
		keyRelease(KeyEvent.VK_CONTROL);
	}
	public static void keyCTRLAll()
	{
		keyPress(KeyEvent.VK_CONTROL);
		waitMS(100);
		keyClick(KeyEvent.VK_A);
		waitMS(100);
		keyRelease(KeyEvent.VK_CONTROL);
	}
	public static void keyALTF4() {
		keyPress(KeyEvent.VK_ALT);
		keyPress(KeyEvent.VK_F4);
		waitMS(100);
		keyRelease(KeyEvent.VK_F4);
		keyRelease(KeyEvent.VK_ALT);
	}

//	Dialog Tools
	public static String textInputDialog(String msg)
	{
		return JOptionPane.showInputDialog(msg);
	}
	public static int seletionDialog(String msg, String[] options)
	{
		return JOptionPane.showOptionDialog(null, msg, "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,  null,options, options[0]);
	}
	public static Point mousePositionDialog()
	{	
		Point s = new Point(-1,-1);
	//	Give User Time To Prepare
		beep();
		waitMS(2000);
		beep();
	//	Confirm Screenshot
		BufferedImage actualScreen = getScreenShot();
	//	Open Dialog
		JDialog screenWindow = new JDialog();
		screenWindow.setUndecorated(true);
		screenWindow.setSize(RobotTools.getScreenSize().width, RobotTools.getScreenSize().height);
		screenWindow.setTitle("Screenshow Window");
		screenWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screenWindow.setLayout(new BorderLayout());
	//	Add Components
		@SuppressWarnings("serial")
		JPanel draw = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				g.setColor(Color.black);
				g.fillRect(0, 0, 5000, 5000);
				g.drawImage(actualScreen, 0, 0, this);
			}
		};
		screenWindow.add(draw,"Center");
	//	Add Listener
		draw.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) 
			{				
			}
			public void mousePressed(MouseEvent e) 
			{
				s.setLocation(e.getXOnScreen(), e.getYOnScreen());
				screenWindow.setVisible(false);
			}
			public void mouseReleased(MouseEvent e) 
			{
			}
			public void mouseEntered(MouseEvent e) 
			{
			}
			public void mouseExited(MouseEvent e) 
			{
			}
		});
		screenWindow.setModal(true);
		screenWindow.setVisible(true);
		beep();
		return new Point(s);
	}
	public static int keyTypeDialog()
	{			
	//	Give User Time To Prepare
		beep();
	//	Confirm Screenshot
		BufferedImage actualScreen = getScreenShot();
	//	Open Dialog
		JDialog screenWindow = new JDialog();
		screenWindow.setUndecorated(true);
		screenWindow.setSize(RobotTools.getScreenSize().width, RobotTools.getScreenSize().height);
		screenWindow.setTitle("Screenshow Window");
		screenWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screenWindow.setLayout(new BorderLayout());
	//	Add Components
		@SuppressWarnings("serial")
		JPanel draw = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				g.setColor(Color.black);
				g.fillRect(0, 0, 5000, 5000);
				g.drawImage(actualScreen, 0, 0, this);
				
				for(int i = 0;i<100;i++)
				{
					g.drawLine(i*10, 0, i*10, i*10);
				}
			}
		};
		screenWindow.add(draw,"Center");
	//	Add Listener
		IntHelper keyCode = new IntHelper(-1);
		screenWindow.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e) 
			{
			}
			public void keyPressed(KeyEvent e) 
			{
				keyCode.intVal = e.getKeyCode();
				screenWindow.setVisible(false);
			}
			public void keyReleased(KeyEvent e) 
			{
			}
		});
		screenWindow.setModal(true);
		screenWindow.setVisible(true);
		beep();
		return keyCode.intVal;
	}
	public static Rectangle rectangleDialog()
	{	
		beep();
		waitMS(2000);
		beep();
		Dimension d = RobotTools.getScreenSize();
		Rectangle r = new Rectangle(d.width/4,d.height/4,d.width/2,d.height/2);
	//	Giver User Time To Prepare
		beep();
	//	Confirm Screenshot
		BufferedImage actualScreen = getScreenShot();
	//	Open Dialog
		JDialog screenWindow = new JDialog();
		screenWindow.setUndecorated(true);
		screenWindow.setSize(RobotTools.getScreenSize().width, RobotTools.getScreenSize().height);
		screenWindow.setTitle("Screenshow Window");
		screenWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screenWindow.setLayout(new BorderLayout());
	//	Add Components
		@SuppressWarnings("serial")
		JPanel draw = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				g.setColor(Color.black);
				g.fillRect(0, 0, 5000, 5000);
				g.drawImage(actualScreen, 0, 0, this);
				
				Graphics2D g2d = (Graphics2D)g;
				Color color = new Color(1, 0, 0, 0.5f); // Red
				g2d.setPaint(color);
				
				g2d.fillRect(0, 0, d.width, r.y);
				g2d.fillRect(0, r.height, d.width, d.height);
			
				g2d.fillRect(0, r.y, r.x, r.height-r.y);
				g2d.fillRect(r.width, r.y, d.width, r.height-r.y);
			}
		};
		screenWindow.add(draw,"Center");
	//	Add Listener
		screenWindow.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e) 
			{
			}
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					screenWindow.setVisible(false);
				}
			}
			public void keyReleased(KeyEvent e) 
			{
			}
		});
		BolHelper leftOrRight = new BolHelper(true);
		draw.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) 
			{				
			}
			public void mousePressed(MouseEvent e) 
			{
				leftOrRight.bolVar = (leftOrRight.bolVar!=true);
			}
			public void mouseReleased(MouseEvent e) 
			{
			}
			public void mouseEntered(MouseEvent e) 
			{
			}
			public void mouseExited(MouseEvent e) 
			{
			}
		});
		draw.addMouseMotionListener(new MouseMotionListener()
		{
			public void mouseDragged(MouseEvent e)
			{
			}
			public void mouseMoved(MouseEvent e) 
			{
				if(leftOrRight.bolVar)
				{
					r.x	= e.getXOnScreen();
					r.y = e.getYOnScreen();
				}
				else
				{
					r.width	 = e.getXOnScreen();
					r.height = e.getYOnScreen();
				}
				screenWindow.repaint();
			}		
		});
		screenWindow.setModal(true);
		screenWindow.setVisible(true);
		beep();
	//	Determine Rectangle
		Rectangle back = new Rectangle();
		back.x = r.x;
		back.y = r.y;
		back.width = r.width-r.x;
		back.height = r.height-r.y;
		
		if(back.width<0||back.height<0)
		{
			back.width = 0;
			back.height = 0;
		}
		return back;
	}
	private static class IntHelper
	{
		int intVal = 0;
		public IntHelper(int a)
		{
			intVal = a;
		}
	}
	private static class BolHelper
	{
		boolean bolVar = false;
		public BolHelper(boolean a)
		{
			bolVar = a;
		}
	}
	public static void main(String[] args)
	{
		System.out.println(isFirefoxLoaded());
	}
	public static boolean isChromeLoaded()
	{
		BufferedImage screen = RobotTools.getScreenShot();
		screen = screen.getSubimage(82,53,22,17);//(82, 63, 22, 17);
		Color c = RobotTools.averageColor(screen);
		int gray = c.getRed()+c.getGreen()+c.getBlue();
		if(gray!=679)//632)
			return false;
		return true;
	}
	public static boolean isFirefoxLoaded()
	{
		BufferedImage screen = RobotTools.getScreenShot();
		screen = screen.getSubimage(82, 63, 22, 17);
		Color c = RobotTools.averageColor(screen);
		int gray = c.getRed()+c.getGreen()+c.getBlue();
		if(gray!=632)
			return false;
		return true;
	}
}
