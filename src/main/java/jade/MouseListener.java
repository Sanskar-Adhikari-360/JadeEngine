package jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener{
    private static MouseListener instance;
    private double scrollX,scrollY;
    private double xPos,yPos,lastY,lastX;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener(){
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastY = 0.0;
        this.lastX = 0.0;
    }
    public static MouseListener get(){
        if (MouseListener.instance == null){
            MouseListener.instance = new MouseListener();
        }
        return MouseListener.instance;
    }
    public static void mousePosCallback(long window,double xpos, double ypos){
        // Changing the last position to current position i think
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        // i think this might be assigning our current position to xpos and ypos
        get().xPos = xpos;
        get().yPos = ypos;
        // if the user is pressing a button while also moving the mouse then the user is obviously dragging something
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }
    public static void mouseButtonCallback(long window, int button, int action, int mods){
        // Checks if the mouse button is pressed or released
        if(action == GLFW_PRESS){
            // Checks if the received mouse id is within the range of declared mouse buttons
            // If the mouse id is not within the declared range it ignores it to avoid array index errors
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }
    // For mouse scroll callback
    public static void mouseScrollCallback(long window, double xOffset, double yOffset){
        // checks how much the mouse scrolled
        // xOffset is the horizontal scrolling and yOffset is the vertical scrolling
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    // Called at the end of each frame
    public static void endFrame(){
        // resets the value
        get().scrollX = 0;
        get().scrollY = 0;
        // Saves the current mouse position as the previous position
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }
    // Getter functions

    // returns the current x position
    public static float getX(){
        return (float)get().xPos;
    }

    // return the current y position
    public static float getY(){
        return (float)get().yPos;
    }

    // returns you the change in x position
    // lastX is the x position of the mouse from the previous position
    public static float getDx(){
        return (float)(get().lastX - get().xPos);
    }

    // returns the change in y position
    // lastY is y position of the mouse from the previous position
    public static float getDy(){
        return (float)(get().lastY - get().yPos);
    }

    // returns the amount the mouse has scrolled horizontally in the current frame
    public static float getScrollX(){
        return (float)get().scrollX;
    }

    // returns the amount the mouse has scrolled vertically in the current frame
    public static float getScrollY(){
        return (float)get().scrollY;
    }

    // returns true or false based on if the mouse is considered dragging
    // mouse is considered dragging when a mouse button is being pressed while the mouse moves
    public static boolean isDragging(){
        return get().isDragging;
    }

    // returns true of false based on if the mouse button is down
    // the button parameter is the mouse button id
    public static boolean mouseButtonDown(int button){
        // Checks if the received mouse id is within the range of declared mouse buttons
        // If the mouse id is not within the declared range it ignores it to avoid array index errors
        if (button < get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        }else {
            return false;
        }
    }
}
