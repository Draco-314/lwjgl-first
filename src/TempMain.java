
public class TempMain {

	private static Window window = new Window();
	private static final long TICK_MS = 500;
	
	public static void main(String[] args) {
		String osName = System.getProperty("os.name");
		if(osName.contains("Mac")){
			System.out.println("rip in sheep");
			return;
		}else{
			window.initWindow();
		}
		long lastTick = System.currentTimeMillis();
		window.glfwInit();
		while(!window.shouldClose()){
			if(System.currentTimeMillis() - lastTick >= TICK_MS){
				window.updateWindow();
			}else{
				try{
					Thread.sleep(1);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		window.destroyWindow();
	}

}
