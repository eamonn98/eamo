package eamo.engine.sandbox;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import eamo.engine.component.Component;
import eamo.engine.managers.EntityManager;
import eamo.engine.rendering.messages.RenderMessage;

/**
 * TODO javadoc
 * @deprecated
 */
public class Splash implements Screen
{

    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;

    private Texture splashTex;
    private ArrayList< Sprite > splashSprites;
    private SpriteBatch sprBatch;
    private Game parent;
    private BitmapFont font;
    private Skin skin;
    private Sound clickSound;

    Rectangle clicked;

    // TODO refactor to some form of root component management system that
    // utilises cross-component messaging features.
    private List< Component > entities;
    private EntityManager manager;

    private class InputListener implements InputProcessor
    {

        @Override
        public boolean keyDown( int keycode )
        {
            return false; // To change body of implemented methods use File |
                          // Settings | File Templates.
        }

        @Override
        public boolean keyUp( int keycode )
        {
            return false; // To change body of implemented methods use File |
                          // Settings | File Templates.
        }

        @Override
        public boolean keyTyped( char character )
        {
            return false; // To change body of implemented methods use File |
                          // Settings | File Templates.
        }

        @Override
        public boolean touchDown( int screenX, int screenY, int pointer, int button )
        {
            return false;
        }

        @Override
        public boolean touchUp( int screenX, int screenY, int pointer, int button )
        {
            // int actY = Gdx.graphics.getHeight() - screenY;
            // clicked = new Rectangle( screenX-5, actY-5, 10, 10 );
            //
            // for ( int i = 0; i < rectangles.size(); i++ )
            // {
            // Rectangle rect = rectangles.get( i );
            // rectColors.get( i ).set( 0.0f, 0, 0, 1.0f );
            //
            // boolean inWidth = rect.x < screenX && ( rect.x + rect.width ) >
            // screenX;
            // boolean inHeight = rect.y < actY && ( rect.y + rect.height ) >
            // actY;
            // if ( inWidth && inHeight )
            // {
            // rectColors.get( i ).set( 1.0f, 0, 0, 1.0f );
            // }
            // }
            //
            // clickSound.play();
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
            return false; // To change body of implemented methods use File |
                          // Settings | File Templates.
        }

        @Override
        public boolean scrolled( int amount )
        {
            return false; // To change body of implemented methods use File |
                          // Settings | File Templates.
        }
    }

    public Splash( Game parent )
    {
        this.parent = parent;
        manager = new EntityManager( null );
    }

    @Override
    public void render( float delta )
    {
        Gdx.gl.glClearColor( 0.4f, 0.6f, 1.0f, 1.0f );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );

        sprBatch.begin();
        manager.dispatchMessage( new RenderMessage( sprBatch, delta ) );
        sprBatch.end();
    }

    @Override
    public void resize( int width, int height )
    {
        Gdx.input.setInputProcessor( new InputListener() );
        // TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
    }

    @Override
    public void show()
    {

        sprBatch = new SpriteBatch();
        skin = new Skin();
        font = new BitmapFont( Gdx.files.internal( "fonts/font.fnt" ), false );
        clickSound = Gdx.audio.newSound( Gdx.files.internal( "data/snd.wav" ) );

        float posX = Gdx.graphics.getWidth() / 2f;
        float posY = Gdx.graphics.getHeight() / 2f;

        manager.addEntity( GameCharacter.createInstance() );
    }

    @Override
    public void hide()
    {
        // To change body of implemented methods use File | Settings | File
        // Templates.
    }

    @Override
    public void pause()
    {
        // To change body of implemented methods use File | Settings | File
        // Templates.
    }

    @Override
    public void resume()
    {
        // To change body of implemented methods use File | Settings | File
        // Templates.
    }

    @Override
    public void dispose()
    {
        sprBatch.dispose();
        skin.dispose();
        font.dispose();
    }
}
