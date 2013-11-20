package eamo.engine.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import eamo.engine.input.Input;
import eamo.engine.stage.Stage;

/**
 * TODO impl
 */
public class InputManager extends Manager implements InputProcessor
{
    public InputManager( Stage stage )
    {
        super( stage );
        Gdx.input.setInputProcessor( this );
    }

    @Override
    public void dispose()
    {

    }

    @Override
    public boolean keyDown( int keycode )
    {
        Input.setKeyDown( keycode );
        return true;
    }

    @Override
    public boolean keyUp( int keycode )
    {
        Input.setKeyUp( keycode );
        return true;
    }

    @Override
    public boolean keyTyped( char character )
    {
        return true;
    }

    @Override
    public boolean touchDown( int screenX, int screenY, int pointer, int button )
    {
        return true;
    }

    @Override
    public boolean touchUp( int screenX, int screenY, int pointer, int button )
    {
        return true;
    }

    @Override
    public boolean touchDragged( int screenX, int screenY, int pointer )
    {
        return true;
    }

    @Override
    public boolean mouseMoved( int screenX, int screenY )
    {
        return true;
    }

    @Override
    public boolean scrolled( int amount )
    {
        return true;
    }

}
