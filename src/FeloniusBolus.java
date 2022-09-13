/*@author A. R. Chessor
 * @purpose  Gui Testing. This was inspired off of PilotRedSun's Video "Felonius Bolus", and impact.wav comes directly from the video.
 * @Date: 9.12.2022 04:03
 */
package window;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;


import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class FeloniusBolus extends JPanel implements Runnable, ActionListener  {
	
	int x = 400; // x coordinate for ball
	int y = 300; // y coord for ball
	int vx = 5; // x vector direction for ball
	int vy = -5; // x vector direction for ball. attempts to randomize: 1
	int radius = 25; // radius of ball.
	Color randcolor = new Color(rnumber(0,255),rnumber(0,255),rnumber(0,255)); // new random color.
	public Window() { // constructor
		super(); // ?
		this.setBackground(Color.gray); // test
	}
	public void actionPerformed(ActionEvent evt) { // when an action is done (also this implements ActionListener..? UC)
		x = x + vx;  // add velocity to the position.
		y = y + vy; // add velocity to position,
		if(x >= this.getSize().width - radius ){ // if it is on the right wall
			vx = -5; // reverse x velocity.
			changeColor(); // change color of ball
			this.setBackground(new Color(rnumber(0,255),rnumber(0,255),rnumber(0,255)));
			try { //would not run unless exceptions handled.
				play(); // try to play the sound, if its not there, whatever. 
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}
		if(x <= radius) { // if it is on the left wall
			vx = 5; // maintain x velocity...?    UNCERTAIN COMMENT
			changeColor(); // change color of ball
			this.setBackground(new Color(rnumber(0,255),rnumber(0,255),rnumber(0,255))); // change color of background.
			try {
				play(); // try to play the sound, if its not there, whatever. 
			} catch (LineUnavailableException e) {//would not run unless exceptions handled.
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}
		if(y <= radius) { //  if it is on the top wall
			vy = 5; // reverse y velocity.
			this.setBackground(new Color(rnumber(0,255),rnumber(0,255),rnumber(0,255))); // change color of background
			changeColor(); // change color of ball.
			try {
				play(); // try to play the sound, if its not there, whatever. 
				
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}
		if(y >= this.getSize().height - radius) { // if it is on the  bottom wall
			vy = -5; // maintain y velocity??           UNCERTAIN COMMENT
			changeColor(); // change color of ball
			this.setBackground(new Color(rnumber(0,255),rnumber(0,255),rnumber(0,255)));// change color of background
			try {
				play(); // try to play the sound, if its not there, whatever. make sure java doesn't cry just bc it can't.
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}
		repaint(); // update?
	}
	public void run() { //on run, i presume?
		//this implements runnable..? 
		ImageIcon icon = new ImageIcon(".//res//icon.png"); // initiate icon image for program to felonius bolus (refer to line 100 for completion.)
		new Timer(10,this).start(); //need to keep a sense of time. have a timer going every 10 miliseconds
		JFrame jeff = new JFrame("Felonius Bolus"); // In the beginning, there was Jeff. (define frame window, with name of program.)
		jeff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Jeff has to go to sleep when it is time for bed. (make sure program closes on quit.)
		jeff.setResizable(false); // Jeff also was happy with his body size. (make sure you can not resize. messes with ball physics.)
		jeff.setSize(new Dimension(800,600)); // This is how big Jeff was! A growing  (er. maybe not ) boy.
		jeff.setVisible(true); //Jeff wanted people to see him. (set window to be visible)
		jeff.getGraphics(); //Jeff likes to pick his clothes. (initiate graphics.)
		jeff.getContentPane().add(this); //this = JPanel, I presume.                 UNCERTAIN COMMENT.
		jeff.setIconImage(icon.getImage()); // set icon image for program.
		repaint();
		
	}
	public void paint(Graphics gh) { //In the morning, Jeff has to get dressed for work.
		super.paint(gh); //paint the panel with the graphics.
		gh.setColor(randcolor); //initiate random color for the panel.
		centerTheFreakingStuff(gh,x,y,radius*2); //call function to load the ball in. refer to line 107.
		
	}
	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		System.out.println("RISE AND SHINE, MISTER FREEMAN");//hw
		SwingUtilities.invokeLater(new Window());
		
		
	}
	private void centerTheFreakingStuff(Graphics g,int x, int y, int diameter) { //called on line 107, takes in location pair, and diameter of circle. 
		g.fillOval(x-diameter/2,y-diameter/2,diameter,diameter); //create a ball at x=175,y=125, and with width, height of 50.	
             //    ^400-50=350/2=175  ^300-50=250/2=125.
	}
	private int rnumber(int max, int min) { //random number generator between the range of max & min.
		
		int range = max - min + 1; // the range
		int rand = (int)(Math.random()*range)+min; // generate random number
		return rand; // RANDOM INTEGER?!  RNUMBER!!!!!! THROW IT BACK!
	}
	private void changeColor() { //changes color to a new random color.
		//called  4 different times at line 33, 47, 62, 76
		
		randcolor = new Color(rnumber(0,255),rnumber(0,255),rnumber(0,255)); // generate a random color with random numbers for RGB values.
	}
	private static void play() throws LineUnavailableException, IOException, UnsupportedAudioFileException { // plays the impact sound whenever called. 
		//called 4 different times at line 36, 50, 64, and 79.
		
		File file = new File(".//res//impact.wav"); //load the (sound) file.
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file); //i dont know what this does honestly. i think it  loads the sound. UNCERTAIN COMMENT
		Clip clip = AudioSystem.getClip(); //loads the sound further...? UNCERTAIN COMMENT
		clip.open(audioStream); //open the audio? UNCERTAIN COMMENT
		clip.start(); // start the audio.
	}
	
}
