import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PhotoModel {

	
	private ArrayList<ActionListener> actionLL;
	
	public PhotoModel(){
		
	}
	
	
	
	public void setPhotos(ArrayList<Image> photos){
		//do stuff
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Photos Added"));
	}
	
	
	public synchronized void addActionListener(ActionListener l){
		if (actionLL==null) actionLL= new ArrayList<ActionListener>();
		actionLL.add(l);
	}
	
	public synchronized void removeActionListener(ActionListener l){
		if (actionLL != null && actionLL.contains(l)) actionLL.remove(l);
	}

	public synchronized void addKeyboardAction(ActionListener l){
		if (actionLL==null) actionLL= new ArrayList<ActionListener>();
		actionLL.add(l);
	}
	
	private void processEvent(ActionEvent e){
		ArrayList<ActionListener> list;
		
		synchronized (this){
			if (actionLL == null) return;
			list= (ArrayList<ActionListener>)actionLL.clone();
		}
		
		for (int i=0; i< list.size(); i++){
			ActionListener listener = list.get(i);
			listener.actionPerformed(e);
		}
	}
	
	
}
