package jangl3;

import java.util.LinkedList;
import java.util.Queue;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard {
    private final long window;
    private final Queue<KeyHit> eventQueue;

    public Keyboard(long window) {
        this.window = window;
        this.eventQueue = new LinkedList<>();

        glfwSetKeyCallback(this.window, this::keyCallback);
    }

    public boolean keyDown(int key) {
        return glfwGetKey(this.window, key) == GLFW_PRESS;
    }

    public KeyHit pollEvent() {
        return this.eventQueue.poll();
    }

    private void keyCallback(long window, int key, int scancode, int action, int mods) {
        this.eventQueue.add(new KeyHit(key, scancode, action, mods));
    }

    public record KeyHit(int key, int scancode, int action, int mods) {
    }
}
