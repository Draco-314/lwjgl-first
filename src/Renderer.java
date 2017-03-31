public class Renderer {
	
	Utils utils = new Utils();
	
	public void init() throws Exception{
		ShaderProgram shaderProgram = new ShaderProgram();
		shaderProgram.createVertexShader(utils.loadResource("/vertex.vs"));
		shaderProgram.createFragmentShader(utils.loadResource("fragment.fs"));
		shaderProgram.link();
	}
}
