package components;

import jade.Component;

public class FontRenderer extends Component {

    @Override
    public void start(){
        if (gameObject.getComponet(SpriteRenderer.class) != null){
            System.out.println("Font rendere found");
        }
    }

    @Override
    public void update(float dt){

    }

}
