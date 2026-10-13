package jade;

import java.awt.event.KeyEvent;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

public class LevelEditorScene extends Scene {

    // this boolean is a flag that tells us whether the scene change has started
    private boolean changingScene = false;
    // Declares the time before the scene is changed
    private float timeToChangeScene = 2.0f;

    public LevelEditorScene(){
        System.out.println("Inside level editor scene");
    }

    @Override
    public void update(float dt) {

        // Prints the Game FPS
        System.out.println((1/dt) + " FPS");

        if (!changingScene && KeyListener.isKeyPressed(GLFW_KEY_SPACE)){
            // Sets the changingScene flag as true to tell us the scene change has started
            changingScene = true;
        }

        // checks if the scene change has started but still has remaining time
        if (changingScene && timeToChangeScene > 0){
            // if there is time still remaining we reduce it based on real elapsed time
            timeToChangeScene -= dt;
            // Fade out the screen gradually based on dt
            Window.get().r -= dt * 5.0f;
            Window.get().g -= dt * 5.0f;
            Window.get().b -= dt * 5.0f;
        } else if (changingScene) { // After the timeToChangeScene has reached 0 we change the scene
            Window.changeScene(1);
        }
    }

}
