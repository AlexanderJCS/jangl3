package jangl3;

import static org.lwjgl.opengl.GL41C.*;

public class Shader {

    private final int program;

    public Shader(String vertexFile, String fragmentFile) {
        String vertexSource = IOUtils.loadFile(vertexFile);
        String fragmentSource = IOUtils.loadFile(fragmentFile);

        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexSource);
        glCompileShader(vertexShader);

        if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new IllegalStateException("Failed to compile vertex shader: " + glGetShaderInfoLog(vertexShader));
        }

        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentSource);
        glCompileShader(fragmentShader);

        if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new IllegalStateException("Failed to compile fragment shader: " + glGetShaderInfoLog(fragmentShader));
        }

        this.program = glCreateProgram();
        glAttachShader(this.program, vertexShader);
        glAttachShader(this.program, fragmentShader);
        glLinkProgram(this.program);

        if (glGetProgrami(this.program, GL_LINK_STATUS) == GL_FALSE) {
            throw new IllegalStateException("Failed to link program: " + glGetProgramInfoLog(this.program));
        }

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
    }

    public void bind() {
        glUseProgram(this.program);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void close() {
        glDeleteProgram(this.program);
    }
}
