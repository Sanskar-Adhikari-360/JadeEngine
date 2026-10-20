package jade;

import components.Sprite;
import components.SpriteRenderer;
import components.Spritesheet;
import org.joml.Vector2f;
import org.joml.Vector4f;
import util.AssetPool;

public class LevelEditorScene extends Scene {

    private GameObject obj1;
    private GameObject obj2;
    private Spritesheet sprites;

    public LevelEditorScene(){

    }

    @Override
    public void init(){
        loadResources();

        this.camera = new Camera(new Vector2f(-250,0));

        sprites = AssetPool.getSpritesheet("assets/images/frog-Sheet.png");


        obj1 = new GameObject("Object 1",
                new Transform(new Vector2f(200,100), new Vector2f(256,256)), 2);
        obj1.addComponent(new SpriteRenderer(new Sprite(
                AssetPool.getTexture("assets/images/blendtest1.png")
        )));
        this.addGameObjectToScene(obj1);


        obj2 = new GameObject("Object 1",
                new Transform(new Vector2f(400,100), new Vector2f(256,256)), 2);
        obj2.addComponent(new SpriteRenderer(new Sprite(
                AssetPool.getTexture("assets/images/blendtest2.png")
        )));        this.addGameObjectToScene(obj2);

    }

    private void loadResources(){
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpritesheet("assets/images/frog-Sheet.png",
                new Spritesheet(AssetPool.getTexture("assets/images/frog-Sheet.png"),
                        50,50,24,0));
    }

    @Override
    public void update(float dt) {


        System.out.println("FPS " + (1.0f/dt));

        for (GameObject go : this.gameObjects){
            go.update(dt);

        }
        this.renderer.render();
    }

}
