
import java.awt.BasicStroke; 			
import java.awt.Color; 				
import java.awt.Graphics; 			
import java.awt.Graphics2D; 			
import java.awt.event.ActionEvent; 		
import java.awt.event.ActionListener; 		
import java.awt.event.MouseEvent; 		
import java.awt.event.MouseListener; 		
import java.awt.event.MouseMotionListener;	
import java.awt.image.BufferedImage;
import javax.swing.ButtonGroup; 		
import javax.swing.ImageIcon; 			
import javax.swing.JButton; 			
import javax.swing.JColorChooser;		
import javax.swing.JFrame; 			
import javax.swing.JMenu; 			
import javax.swing.JMenuBar; 		
import javax.swing.JMenuItem; 			
import javax.swing.JPanel;
import javax.swing.JRadioButton; 		

public class MainFile extends JPanel implements MouseListener, ActionListener, MouseMotionListener 				
{
private static final long serialVersionUID = 1L; 		
public static int stroke, eraser = 0; 				
private int xX1, yY1, xX2, yY2, choice;				
private static final Color BACKGROUND_COLOR = Color.WHITE;	
private int eraserW = 50; 					
private int eraserH = 50; 					

public static void main(String[] args) 				
{
new MainFile();
}

MainFile() 							
{

JFrame frame = new JFrame("s1091426_Paint");			
frame.setSize(1600, 1024);					

frame.setBackground(BACKGROUND_COLOR); 				
frame.getContentPane().add(this);

		

JButton b1 = new JButton("Clear Drawing"); 			
b1.addActionListener(this);					
JButton color = new JButton("Color");
color.addActionListener(this);
JButton erase = new JButton("Erase"); 		
erase.addActionListener(this);
JButton b2 = new JButton("Empty Rect"); 		
b2.addActionListener(this);					
JButton b3 = new JButton("Empty oval"); 			
b3.addActionListener(this);					
JButton b4 = new JButton("Line"); 				
b4.addActionListener(this); 					
JRadioButton medium = new JRadioButton("Medium Line"); 		
medium.addActionListener(this);
JRadioButton thick = new JRadioButton("Thick Line"); 		
thick.addActionListener(this);
JButton b5 = new JButton("Rect"); 			
b5.addActionListener(this);	
JButton b6 = new JButton("oval"); 			
b6.addActionListener(this);
JButton b7 = new JButton("Empty RoundRect"); 			
b7.addActionListener(this);
JButton b8 = new JButton("RoundRect"); 			
b8.addActionListener(this);
JButton b9 = new JButton("Pencil"); 			
b9.addActionListener(this);

ButtonGroup lineOption = new ButtonGroup();
lineOption.add(medium); 					
lineOption.add(thick); 						

this.add(b1);
this.add(color);
this.add(erase);
this.add(b2);
this.add(b3);
this.add(b4);
this.add(medium);
this.add(thick);
this.add(b5);
this.add(b6);
this.add(b7);
this.add(b8);
this.add(b9);
addMouseListener(this); 					
frame.setVisible(true);						
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		

}

public void paintComponent(Graphics g)
{
super.paintComponent(g);
Graphics2D g2 = (Graphics2D) g;
if (grid == null)
{
int w = this.getWidth(); 						
int h = this.getHeight(); 						
grid = (BufferedImage) (this.createImage(w, h));
gc = grid.createGraphics();
gc.setColor(Color.BLUE);
}

g2.drawImage(grid, null, 0, 0);
check();
}

BufferedImage grid;
Graphics2D gc;

public void draw()
{
Graphics2D g = (Graphics2D) getGraphics();
int w = xX2 - xX1;
if (w < 0)
w = w * (-1);

int h = yY2 - yY1;
if (h < 0)
h = h * (-1);

if(choice == 1)
{
check();
gc.drawRect(xX1, yY1, w, h);
repaint();
}

else if(choice == 2)
{
check();
gc.drawOval(xX1, yY1, w, h);
repaint();
}

else if(choice == 3)
{
if (stroke == 0)
gc.setStroke(new BasicStroke(3));
if (stroke == 1)
gc.setStroke(new BasicStroke(6));
gc.drawLine(xX1, yY1, xX2, yY2);
repaint();
}

else if(choice == 6)
{
check();
gc.fillRect(xX1, yY1, w, h);
repaint();
}

else if(choice == 7)
{
check();
gc.fillOval(xX1, yY1, w, h);
repaint();
}
else if(choice == 8)
{
check();
gc.drawRoundRect(xX1, yY1, w, h,40,40);
repaint();
}
else if(choice == 9)
{
check();
gc.fillRoundRect(xX1, yY1, w, h,40,40);
repaint();
}
else if(choice == 4)
{
repaint();
Color temp = gc.getColor();
gc.setColor(BACKGROUND_COLOR);
gc.fillRect(0, 0, getWidth(), getHeight());
gc.setColor(temp);
repaint();
}


else
{
if (eraser == 1)
gc.clearRect(xX1, yY1, w, h);
else
{

}
}
}

public void check()
{
if (xX1 > xX2)
{
int z = 0;
z = xX1;
xX1 = xX2;
xX2 = z;
}
if (yY1 > yY2)
{
int z = 0;
z = yY1;
yY1 = yY2;
yY2 = z;
}
}

public void actionPerformed(ActionEvent e)
{

super.removeMouseMotionListener(this);

if (e.getActionCommand().equals("Color")) 				
{
Color bgColor = JColorChooser.showDialog(this, "Choose Background Color", getBackground());
if (bgColor != null)
gc.setColor(bgColor);
}

if (e.getActionCommand().equals("About")) 							
{
System.out.println("About Has Been Pressed");
JFrame about = new JFrame("About");
about.setSize(300, 300);
JButton picture = new JButton(new ImageIcon("C:/Users/TehRobot/Desktop/Logo.png"));
about.add(picture);
about.setVisible(true);
}
if (e.getActionCommand().equals("Rect")) 				
{
choice = 6;
}

if (e.getActionCommand().equals("oval")) 				
{
choice = 7;
}
if (e.getActionCommand().equals("Empty RoundRect")) 				
{
choice = 8;
}
if (e.getActionCommand().equals("RoundRect")) 				
{
choice = 9;
}

if (e.getActionCommand().equals("Empty Rect")) 				
{
choice = 1;
}

if (e.getActionCommand().equals("Empty oval")) 				
{
choice = 2;
}

if (e.getActionCommand().equals("Line")) 				
{
choice = 3;
}

if (e.getActionCommand().equals("Medium Line")) 			
{
stroke = 0;
}

if (e.getActionCommand().equals("Thick Line")) 				
{

stroke = 1;
}

if (e.getActionCommand().equals("Erase")) 				
{
eraser = 1;
choice = 5;
super.addMouseMotionListener(this);
}

if (e.getActionCommand().equals("Clear Drawing"))
{
choice = 4;
draw();
}

}

public void mouseExited(MouseEvent e) 						
{
}

public void mouseEntered(MouseEvent e)						
{
}

public void mouseClicked(MouseEvent e) 						
{
}

public void mousePressed(MouseEvent e) 						
{

xX1 = e.getX();
yY1 = e.getY();

}

public void mouseReleased(MouseEvent e)
{
xX2 = e.getX();
yY2 = e.getY();
draw();
eraser = 0;
}


public void mouseDragged(MouseEvent re)
{
Color c = gc.getColor();
gc.setColor(BACKGROUND_COLOR);
gc.drawRect(re.getX(), re.getY(), eraserW, eraserH);
gc.setColor(c);
repaint();
}

public void mouseMoved(MouseEvent arg0)
{
}
}