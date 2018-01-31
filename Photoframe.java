import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Photoframe extends JFrame{

	private PhotoModel model;
	private PhotoController controller;
	public int count=0;
	public int total=0;
	public JFrame frame= new JFrame();
	public JButton prev= new JButton("Previous");
	public JButton next= new JButton("Next");
	public JButton okay= new JButton("Okay");
	public JMenuBar menuBar= new JMenuBar();
	public JMenu menu= new JMenu("Menu");
	public JMenu subMenu= new JMenu("List of Photos");
	public JMenuItem menu1= new JMenuItem();
	public JMenuItem add= new JMenuItem("Add photos");
	public JMenuItem next1=new JMenuItem("Next");
	public JMenuItem prev1=new JMenuItem("Previous");
	public JPanel photoPanel=new JPanel();
	public JPanel panel= new JPanel();
	public JLabel picture= new JLabel();
	private GridBagConstraints layoutConst;
	public JComboBox combo= new JComboBox();
	public Icon pic;
	private InputMap input= photoPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
	private ActionMap action= photoPanel.getActionMap();

	
	
	public Photoframe(){
		menu.add(add);
		menu.add(next1);
		menu.add(prev1);
		prev.setSize(500,600);
		next.setSize(500,600);
		panel.add(prev);
		panel.add(next);
		menuBar.add(menu);
		photoPanel.add(picture);
		frame.add(panel, BorderLayout.SOUTH);
		frame.setJMenuBar(menuBar);
		frame.add(photoPanel,BorderLayout.CENTER);
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RightArrow");
		input.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "LeftArrow");
		
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth()/3;
		double height = screenSize.getHeight()/2;
		
		frame.setSize((int) width,(int) height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagConstraints layoutConst = new GridBagConstraints();

		frame.setVisible(true);
		
		JOptionPane.showMessageDialog(frame, "Select Menu and add photos to get started. ");
		

		
	}
	
	public void setPic(File photo){
		Image temppic;
		try {
			temppic = ImageIO.read(photo);
			pic=new ImageIcon(temppic.getScaledInstance(photoPanel.getWidth(), photoPanel.getHeight(), Image.SCALE_SMOOTH));
			//Dimension totalSize= new Dimension(this.getWidth(),this.getHeight());
			
			
			picture.setIcon(pic);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	
	}
	
	public void setCombo(){
		ArrayList<String> options= new ArrayList<String>();
		for(int i=0; i< controller.getCount(); i++){
			options.add(Integer.toString(i));
			combo.addItem(options.get(i));
		}
		
		menuBar.add(combo);
		menuBar.add(okay);
		count=controller.getTCount();
		total=controller.getCount();
		MouseController mouse= new MouseController();
		photoPanel.addMouseListener(mouse);
		action.put("RightArrow", new KeyAction("RightArrow"));
		action.put("LeftArrow", new KeyAction("LeftArrow"));
		

	}
	public void setController(PhotoController controller){
		this.controller=controller;
	}
	
	
	public void actionPerformed(ActionEvent e){
		picture.setIcon( pic);
	}
	public void addAddPhotoListener(ActionListener AddAddListener ){
		add.addActionListener(AddAddListener);
	}
	public void addNumListener(ActionListener NumListener){
		okay.addActionListener(NumListener);
	}
	

	public void addPrevListener(ActionListener AddPrevListener){
		prev.addActionListener(AddPrevListener);
		prev1.addActionListener(AddPrevListener);
	}
	public void addNextListener(ActionListener AddNextListener){
		next.addActionListener(AddNextListener);
		next1.addActionListener(AddNextListener);
	}
	
	
	private class KeyAction extends AbstractAction{

		private String command;
		
		public KeyAction (String command){
			this.command=command;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			if(command.equalsIgnoreCase("RightArrow")){
				if(count==total){
					count=0;
					controller.setCount(0);
					setPic(controller.getPhotos().get(count));

				}
				else{
					count++;
					controller.setCount(count);

					setPic(controller.getPhotos().get(count));

				}
			}
			else if(command.equalsIgnoreCase("LeftArrow")){
				if(count==0){
					count=total;
					controller.setCount(total);
					setPic(controller.getPhotos().get(count));

				}
				else{
					count--;
					controller.setCount(count);

					setPic(controller.getPhotos().get(count));

				}
			}
			
		}
		
	}
	

	

	private class MouseController implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
				if(count==total){
					count=0;
					controller.setCount(0);
					setPic(controller.getPhotos().get(count));

				}
				else{
					count++;
					controller.setCount(count);
					setPic(controller.getPhotos().get(count));

				}
			}
			
		

		@Override
		public void mouseEntered(MouseEvent e) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
				
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	
}





