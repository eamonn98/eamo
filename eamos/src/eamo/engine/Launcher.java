package eamo.engine;

import com.badlogic.gdx.Game;

import eamo.engine.stage.Stage;

/**
 * Main application entry point
 */
public class Launcher extends Game
{
    @Override
    public void create()
    {
        setScreen( new Stage( ) );
    }
}
