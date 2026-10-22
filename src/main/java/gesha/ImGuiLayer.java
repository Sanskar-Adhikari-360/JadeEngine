package gesha;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

public class ImGuiLayer {

    private final long glfwWindow;

    // Platform backend: feeds GLFW input (keys, mouse, scroll, clipboard, cursors,
    // display size, delta time) into ImGui. This replaces ~100 lines of the old code.
    private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();

    // Renderer backend: draws ImGui's output with OpenGL.
    private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

    public ImGuiLayer(long glfwWindow) {
        this.glfwWindow = glfwWindow;
    }

    // Call this AFTER you have set your own GLFW callbacks (MouseListener, KeyListener)
    // and after GL.createCapabilities(). ImGuiImplGlfw will chain onto your callbacks.
    public void initImGui() {
        ImGui.createContext();

        final ImGuiIO io = ImGui.getIO();
        io.setIniFilename(null);                               // don't save imgui.ini
        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard); // keyboard navigation

        // Fonts would be configured here, before the backends are initialized.

        // true = ImGui installs its GLFW callbacks and forwards every event to the
        // callbacks you registered before this call, so your game input keeps working.
        imGuiGlfw.init(glfwWindow, true);
        imGuiGl3.init("#version 330 core");
    }

    // dt is no longer needed (ImGuiImplGlfw computes it), but I kept the parameter
    // so Window.java doesn't need to change.
    public void update(float dt) {
        imGuiGl3.newFrame();
        imGuiGlfw.newFrame();
        ImGui.newFrame();

        ImGui.showDemoWindow();

        ImGui.render();
        imGuiGl3.renderDrawData(ImGui.getDrawData());
    }

    // Call this from Window.run() after loop(), before glfwFreeCallbacks().
    public void destroyImGui() {
        imGuiGl3.shutdown();
        imGuiGlfw.shutdown();
        ImGui.destroyContext();
    }
}