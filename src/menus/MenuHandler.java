package menus;

import core.GameRunTime;
import javafx.scene.Scene;
import javafx.stage.Stage;
import maps.MapEditor;

/**
 * Handles the menus so that we can easily switch through them
 *
 * @author : First created by Paul Popa with code by Evgeniy Kim, and Paul Popa
 * @date : 09/02/16, last edited by Paul Popa on 12/02/16
 */
public class MenuHandler {

    private static Stage primaryStage;

    private MainMenu mainMenu = new MainMenu();
    private OptionsMenu optionsMenu = new OptionsMenu();
    private PauseMenu pauseMenu = new PauseMenu();

    private MapEditor mapEditor = new MapEditor();

    private static Scene[] sceneList = new Scene[20];

    //Declaring indexes for the menus so that we know how to access them easily in the scene array
    public final static int MAIN_MENU = 0;
    public final static int OPTIONS_MENU = 1;
    public final static int MAIN_GAME = 2;
    public final static int PAUSE_MENU = 3;

    // index for map editor
    public final static int MAP_EDITOR = 4;

    public static int currentScene = MAIN_MENU;
    public static int lastScene = MAIN_MENU;

    /**
     * Initialises the stage to be the same used for all scenes
     *
     * @param primaryStage - our primary stage
     */
    public MenuHandler(Stage primaryStage) {
        MenuHandler.primaryStage = primaryStage;
        setScenes();
    }

    /**
     * Places all the scenes into an array of scenes
     */
    public void setScenes() {
        sceneList[MAIN_MENU] = mainMenu.getScene();
        sceneList[OPTIONS_MENU] = optionsMenu.getScene();
        sceneList[PAUSE_MENU] = pauseMenu.getScene();
        sceneList[MAP_EDITOR] = mapEditor.getScene();
    }

    public static void setMainGameScene() {
        sceneList[MAIN_GAME] = GameRunTime.Instance().getScene();
    }

    /**
     * Switches the current scene with another scene
     *
     * @param scene - the scene that we want to switch to
     */
    public static void switchScene(int scene) {
        sceneList[currentScene].getRoot().setVisible(false);
        sceneList[currentScene].getRoot().setDisable(true);

        sceneList[scene].getRoot().setVisible(true);
        sceneList[scene].getRoot().setDisable(false);

        lastScene = currentScene;
        currentScene = scene;

        primaryStage.setScene(sceneList[scene]);
    }
}