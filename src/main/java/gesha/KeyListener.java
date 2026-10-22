package gesha;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private static KeyListener instance;
    // creates an array containing 350 booleans for all the GLFW's key code for keyboard key
    private boolean keyPressed[] = new boolean[350];


    // Making an empty private constructor so no other class can create it essentially we are trying to make a singleton class
    private KeyListener(){

    }

    public static KeyListener get(){
        if (KeyListener.instance == null){
            KeyListener.instance = new KeyListener();
        }

        return KeyListener.instance;
    }

    // Checks if the key is being current pressed
    public static void keyCallback(long window, int key, int scancode, int action, int mods){
        if (action == GLFW_PRESS){
            get().keyPressed[key]=true;
        } else if (action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }

    // Getter function that returns which function is being pressed
    public static boolean isKeyPressed(int keyCode){
        return get().keyPressed[keyCode];
    }
}
