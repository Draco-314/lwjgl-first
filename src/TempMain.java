
public class TempMain {

	private static Window window = new Window();
	
	public static void main(String[] args) {
		window.initWindow();
		while(!window.shouldClose()){
			window.clearBuffer();
			window.updateWindow();
		}
		window.destroyWindow();
	}

}
