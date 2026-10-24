// Abstract classes can not have object of it's own they can only be extended by subclasses
// Abstract classes are also called incomplete blueprints because they contain the method but no the actual code
// In this class update() is an abstract method it has no implementations body here
// Any subclasses extending Scene must provide their own implementations
package gesha;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import imgui.ImGui;
import renderer.Renderer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    protected Renderer renderer = new Renderer();
    protected Camera camera;
    private boolean isRunning = false;
    protected List<GameObject> gameObjects = new ArrayList<>();
    protected GameObject activeGameObject = null;
    protected boolean levelLoaded = false;

    public Scene(){

    }

    public void init(){

    }

    public void start(){
        for (GameObject go : gameObjects){
            go.start();
            this.renderer.add(go);
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
            this.renderer.add(go);
        }
    }

    public abstract void update(float dt);

    public Camera camera(){
        return this.camera;
    }

    public void sceneImgui(){
        if (activeGameObject != null){
            ImGui.begin("Inspector");
            activeGameObject.imgui();
            ImGui.end();
        }

        imgui();
    }

    public void imgui(){

    }

    public void saveExit(){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Component.class, new ComponentDeserializer())
                .registerTypeAdapter(GameObject.class, new GameObjectDeserializer())
                .create();

        try {
            FileWriter writer = new FileWriter("level.txt");
            writer.write(gson.toJson(this.gameObjects));
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void load(){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Component.class, new ComponentDeserializer())
                .registerTypeAdapter(GameObject.class, new GameObjectDeserializer())
                .create();
        String inFile ="";
        try {
            inFile = new String(Files.readAllBytes(Paths.get("level.txt")));
        }catch (IOException e){
            e.printStackTrace();
        }

        if (!inFile.equals("")){
            GameObject[] objs = gson.fromJson(inFile,GameObject[].class);
            for (int i =0;i<objs.length;i++){
                addGameObjectToScene(objs[i]);
            }
            // flag used to prevents loading the same level multiple time
            this.levelLoaded = true;
        }
    }

}
