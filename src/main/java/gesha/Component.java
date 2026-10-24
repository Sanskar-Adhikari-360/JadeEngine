package gesha;

public abstract class Component {

    // if we don't put transient on GameObject it tries to serialize the list of components in the GameObject which causes stack overflow
    public transient GameObject gameObject = null;

    public void start(){

    }

    public void update(float dt) {

    }

    public void imgui(){

    }

}
