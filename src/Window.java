import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
	public long windowHandle;
//	public static int width, height;
	
	public void initWindow(){		
		if(!GLFW.glfwInit()){
			throw new IllegalStateException("GLFW failed to init");
		}
		
		GLFWErrorCallback.createPrint(System.err).set();
		
		windowHandle = glfwCreateWindow(600, 600, "Window", NULL, NULL);
		if(windowHandle == NULL){
			throw new RuntimeException("window creation failed");
		}
		
		glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true);//maybe replace???
		});
		
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(windowHandle, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				windowHandle,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		GLFW.glfwSetWindowSizeCallback(windowHandle, (windowHandle, width, height) -> {
			glfwSetWindowSize(windowHandle, width, height);
		});
		// Make the OpenGL context current
		glfwMakeContextCurrent(windowHandle);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(windowHandle);
	}
	
	public void destroyWindow(){
		glfwFreeCallbacks(windowHandle);
		glfwDestroyWindow(windowHandle);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public boolean shouldClose(){
		return glfwWindowShouldClose(windowHandle);
	}
	
	public void updateWindow(){		
		glfwSwapBuffers(windowHandle);
		glfwPollEvents();
	}
	
	public void glfwInit(){
		GL.createCapabilities();
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClear(GL_COLOR_BUFFER_BIT);
	}
}
