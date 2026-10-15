// Abstract classes can not have object of it's own they can only be extended by subclasses
// Abstract classes are also called incomplete blueprints because they contain the method but no the actual code
// In this class update() is an abstract method it has no implementations body here
// Any subclasses extending Scene must provide their own implementations
package jade;

public abstract class Scene {

    protected Camera camera;

    public Scene(){

    }

    public void init(){

    }

    public abstract void update(float dt);


}
