package renderer;

import components.SpriteRenderer;
import jade.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private final int MAX_BATCH_SIZE = 1000;
    private List<RenderBatch> batches;

    public Renderer(){
        this.batches = new ArrayList<>();
    }

    public void add(GameObject go){
        SpriteRenderer spr = go.getComponet(SpriteRenderer.class);
        if (spr != null){
            add(spr);
        }
    }

    private void add(SpriteRenderer sprite){
        boolean added = false;
        for (RenderBatch batch : batches){
            if (batch.hasRoom()){
                Texture tex = sprite.getTexture();
                // Check if a sprite has a texture and runs if the batch already has that texture or there is room for more texture it's something like that idk
                if (tex == null || (batch.hasTextureRoom(tex) || batch.hasTextureRoom(tex))) {
                    batch.addSprite(sprite);
                    added = true;
                    break;
                }
            }
        }
        if(!added){
            RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE);
            newBatch.start();
            batches.add(newBatch);
            newBatch.addSprite(sprite);
        }

    }
    public void render(){
        for (RenderBatch batch : batches){
            batch.render();
        }
    }
}
