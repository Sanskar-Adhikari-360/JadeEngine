package jade;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
    //The view matrix moves the world relative to the camera.
    //The projection matrix converts that camera-space world into something that can become screen coordinates.
    private Matrix4f projectionMatrix,viewMatrix;
    // Position of the camera in the game world
    public Vector2f position;

    public Camera(Vector2f position){
        this.position = position;
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        adjustProjection();
    }

    public void adjustProjection(){
        // calling .identity() resets the matrix basically so when calling adjustProjection() multiple times it doesn't stack up
        projectionMatrix.identity();
        // ortho projection keeps the object at same size no matter how far they are as
        // The arguments according to claude:-
        // Argument	        Value	    Meaning
        // left / right	    0 / 1280	visible x range
        // bottom / top	    0 / 672	    visible y range
        // near / far	    0 / 100	    visible depth range
        projectionMatrix.ortho(0.0f,32.0f * 40.0f,0.0f,32.0f * 21.0f,0.0f,100.0f);
    }

    public Matrix4f getViewMatrix(){
        Vector3f cameraFront = new Vector3f(0.0f,0.0f,-1.0f);
        Vector3f cameraUp = new Vector3f(0.0f,1.0f,0.0f);
        this.viewMatrix.identity();
        // .lookAt() builds a matrix for the camera based on where the camera is the center of the camera and what direction is considered up
        viewMatrix.lookAt(new Vector3f(position.x,position.y,20.0f),
                                       cameraFront.add(position.x,position.y,0.0f),
                                       cameraUp);
        return this.viewMatrix;
    }
    public Matrix4f getProjectionMatrix(){
        return this.projectionMatrix;
    }
}
