package jade;

import org.lwjgl.system.windows.WINDOWPLACEMENT;

public class LevelScene extends Scene {
    public LevelScene(){
        System.out.println("Inside level scene");
        // resets the background to white
        Window.get().r = 1;
        Window.get().g = 1;
        Window.get().b = 1;


    }

    @Override
    public void update(float dt) {

    }
}
