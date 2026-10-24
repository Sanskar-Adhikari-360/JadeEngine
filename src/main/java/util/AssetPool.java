package util;

import components.SpriteRenderer;
import components.Spritesheet;
import renderer.Shader;
import renderer.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

// This class helps manage resources by providing references to the object when requested instead of providing the object itself
// We use hashmap to implement it
public class AssetPool {
    private static Map<String, Shader> shaders = new HashMap<>();
    private static Map<String, Texture> textures = new HashMap<>();
    private static Map<String, Spritesheet> spritesheets = new HashMap<>();

    public static Shader getShader(String resourceName){
        File file = new File(resourceName);
        if (AssetPool.shaders.containsKey(file.getAbsolutePath())){
            return AssetPool.shaders.get(file.getAbsolutePath());
        }else {
            Shader shader = new Shader(resourceName);
            shader.compile();
            // puts the two arguments into a hashmap called shaders in key-value pair
            AssetPool.shaders.put(file.getAbsolutePath(), shader);
            return shader;
        }
    }

    public static Texture getTexture(String resourceName){
        File file = new File(resourceName);
        if (AssetPool.textures.containsKey(file.getAbsolutePath())){
            return AssetPool.textures.get(file.getAbsolutePath());

        }else {
            Texture texture = new Texture();
            texture.init(resourceName);
            AssetPool.textures.put(file.getAbsolutePath(), texture);
            return texture;
        }
    }

    public static void addSpritesheet(String resourceName, Spritesheet spritesheet){
        File file = new File(resourceName);

        // checks if we don't already have it
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())){
            AssetPool.spritesheets.put(file.getAbsolutePath(), spritesheet);
        }
    }

    public static Spritesheet getSpritesheet(String resourceName){
        File file = new File(resourceName);

        // checks if we don't already have it
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())){
            assert false : "Error: Tried to access spritesheet '" + resourceName + "' and it has not been added to asset pool.";
        }
        // To do: remove the null argument with an empty sprite instead
        return AssetPool.spritesheets.getOrDefault(file.getAbsolutePath(),null);
    }
}
