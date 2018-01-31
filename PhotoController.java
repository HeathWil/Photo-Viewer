import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Utilities;



public class PhotoController{

	private PhotoModel model;
	private Photoframe view;
	private File photo;
	private ArrayList<File> photos= new ArrayList<File>();
	private int count=0;
	private int total=0;



	public PhotoController(){

	}


	public void setModel(PhotoModel m){
		model=m;
	}

	public int getCount(){
		return total;
	}
	public int getTCount(){
		return count;
	}

	public void setCount(int num){
		count=num;
	}
	public PhotoModel getModel(){
		return model;
	}
	public Photoframe getView(){
		return view;
	}

	public ArrayList<File> getPhotos(){
		return photos;
	}

	public void setView(Photoframe view){
		this.view=view;

		this.view.addAddPhotoListener(new AddAddListener());
		this.view.addNextListener(new AddNextListener());
		this.view.addPrevListener(new AddPrevListener());
		this.view.addNumListener(new NumListener());

	}



	public class AddPrevListener implements ActionListener{

		@Override 
		public void actionPerformed(ActionEvent e) {

			if(count==0){
				count=photos.size()-1;
				view.setPic(photos.get(count));

			}
			else{
				count--;

				view.setPic(photos.get(count));
			}
		}
	}

	public class AddNextListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			if(count==photos.size()-1){
				count=0;
				view.setPic(photos.get(count));

			}
			else{
				count++;

				view.setPic(photos.get(count));

			}
		}
	}


	public class NumListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int selected=view.combo.getSelectedIndex();
			view.setPic(photos.get(selected));
		}

	}


	public class AddAddListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){



			JFileChooser chooser= new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			int returnVal=chooser.showOpenDialog(view);


			if (returnVal==chooser.APPROVE_OPTION){

				FileNameExtensionFilter filter= new FileNameExtensionFilter("jpeg", "jpg", "png", "gif");
				chooser.setFileFilter(filter);
				File directory=chooser.getSelectedFile();

				File [] list= directory.listFiles();

				for(int i=0; i< list.length; i++){
					photo=list[i];
					photos.add(photo);
				}

				view.setPic(photos.get(0));
				total=photos.size()-1;
				view.setCombo();

			}


			JOptionPane.showMessageDialog(view, "Use the right and left arrow keys to switch between pictures or click on photo to continue.");



		}




	}



}



