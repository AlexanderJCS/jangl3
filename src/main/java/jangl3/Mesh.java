package jangl3;

import static org.lwjgl.opengl.GL41C.*;

public class Mesh {
    private final int vao;
    private final int vbo;
    private final int ebo;

    private final Shader shader;

    public Mesh(float[] vertices, int[] indices) {
        this.vbo = glGenBuffers();
        this.vao = glGenVertexArrays();
        this.ebo = glGenBuffers();
        this.shader = new Shader("shaders/shader.vert", "shaders/shader.frag");

        glBindVertexArray(this.vao);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);
    }

    public void draw() {
        // TODO: use ebo
        this.shader.bind();
        glBindVertexArray(this.vao);
        glDrawArrays(GL_TRIANGLES, 0, 3);
    }

    public void close() {
        glDeleteBuffers(this.vbo);
        glDeleteVertexArrays(this.vao);
    }
}
