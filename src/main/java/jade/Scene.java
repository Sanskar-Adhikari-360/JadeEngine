// Abstract classes can not have object of it's own they can only be extended by subclasses
// Abstract classes are also called incomplete blueprints because they contain the method but no the actual code
// In this class update() is an abstract method it has no implementations body here
// Any subclasses extending Scene must provide their own implementations
package jade;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    protected Camera camera;
    private boolean isRunning = false;
    protected List<GameObject> gameObjects = new ArrayList<>();

    public Scene(){

    }

    public void init(){

    }

    public void start(){
        for (GameObject go : gameObjects){
            go.start();
        }
        isRunning = true;
    }

    public void addGameObjectToScene(GameObject go){
        if(!isRunning){
            gameObjects.add(go);
        }else{
            // If running add it to the list and run it immediately
            gameObjects.add(go);
            go.start();
        }
    }

    public abstract void update(float dt);


}
