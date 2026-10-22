package renderer;

import components.SpriteRenderer;
import gesha.GameObject;

import java.util.ArrayList;
import java.util.Collections;
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
            // check if we have room and only adds sprite of the same index onto the same batch
            if (batch.hasRoom() && batch.getzIndex() == sprite.gameObject.getzIndex()){
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
            RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE, sprite.gameObject.getzIndex());
            newBatch.start();
            batches.add(newBatch);
            newBatch.addSprite(sprite);
            // sorts the batches every time a new one is added
            Collections.sort(batches);
        }

    }
    public void render(){
        for (RenderBatch batch : batches){
            batch.render();
        }
    }
}
