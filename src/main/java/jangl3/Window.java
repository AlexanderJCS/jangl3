package jangl3;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL41C.*;

public class Window {
    private final Keyboard keyboard;
    private final long window;

    public Window() {
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        this.window = glfwCreateWindow(800, 600, "Hello World", 0, 0);

        if (this.window == 0) {
            throw new IllegalStateException("Failed to create window");
        }

        glfwMakeContextCurrent(this.window);
        GL.createCapabilities();
        glViewport(0, 0, 800, 600);

        glfwSetFramebufferSizeCallback(this.window, Window::framebufferSizeCallback);

        // initialize member variables
        this.keyboard = new Keyboard(this.window);
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(this.window);
    }

    public void close() {
        glfwDestroyWindow(this.window);
    }

    public static void terminate() {
        glfwTerminate();
    }

    public void update() {
        glfwPollEvents();
        glfwSwapBuffers(this.window);
        this.clear();
    }

    public void setClearColor(float r, float g, float b) {
        glClearColor(r, g, b, 1.0f);
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    private static void framebufferSizeCallback(long window, int width, int height) {
        glViewport(0, 0, width, height);
    }

    public Keyboard keyboard() {
        return this.keyboard;
    }
}
