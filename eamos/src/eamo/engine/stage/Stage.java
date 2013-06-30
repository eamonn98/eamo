package eamo.engine.stage;

import com.badlogic.gdx.Screen;

import eamo.engine.managers.AssetManager;
import eamo.engine.managers.ComponentManager;
import eamo.engine.managers.InputManager;
import eamo.engine.managers.RenderingManager;
import eamo.engine.managers.UpdateManager;
import eamo.engine.sandbox.GameCharacter;

public class Stage implements Screen
{
    private ComponentManager componentManager;
    private RenderingManager renderingManager;
    private InputManager inputManager;
    private AssetManager assetManager;
    private UpdateManager updateManager;

    public Stage()
    {
        componentManager = new ComponentManager( this );
        renderingManager = new RenderingManager( this );
        inputManager = new InputManager( this );
        assetManager = new AssetManager( this );
        updateManager = new UpdateManager( this );
    }

    public ComponentManager getComponentManager()
    {
        return componentManager;
    }

    public RenderingManager getRenderingManager()
    {
        return renderingManager;
    }
    
    public InputManager getInputManager()
    {
        return inputManager;
    }
    
    public AssetManager getAssetManager()
    {
        return assetManager;
    }
    
    public UpdateManager getUpdateManager()
    {
        return updateManager;
    }

    @Override
    public void render( float delta )
    {
        renderingManager.render( delta );
    }

    @Override
    public void resize( int width, int height )
    {

    }

    @Override
    public void show()
    {
        // Create a test character
        componentManager.addComponent( GameCharacter.createInstance() );
    }

    @Override
    public void hide()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }
}
