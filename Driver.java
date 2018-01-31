
public class Driver {

	public static void main(String[] args) {

				PhotoModel model= new PhotoModel();
				Photoframe view= new Photoframe();
				PhotoController cont= new PhotoController();
				cont.setModel(model);
				cont.setView(view);
				view.setController(cont);
	}

}
