package util;

public class Time {
    // Stores the time when the game started in nanosecond
    public static float timeStarted = System.nanoTime();

    // Gives you the number of time that has passed since the game started in seconds
    public static float getTime(){
        return (float)((System.nanoTime() - timeStarted) * 1E-9);
    }
}
