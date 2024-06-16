package jangl3;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        window.setClearColor(0.5f, 0.5f, 0.5f);

        Mesh mesh = new Mesh(new float[]{
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.0f, 0.5f, 0.0f
        }, new int[]{0, 1, 2});

        while (!window.shouldClose() && !window.keyboard().keyDown(GLFW_KEY_ESCAPE)) {
            window.update();
            mesh.draw();
        }

        mesh.close();

        window.close();
        Window.terminate();
    }
}