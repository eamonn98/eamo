package eamo.engine.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

import eamo.engine.component.GameEntity;
import eamo.engine.managers.AssetManager;
import eamo.engine.managers.EntityManager;
import eamo.engine.managers.InputManager;
import eamo.engine.managers.RenderingManager;
import eamo.engine.managers.UpdateManager;
import eamo.engine.sandbox.GameCharacter;

public class Stage implements Screen
{
    private EntityManager entityManager;
    private RenderingManager renderingManager;
//    private InputManager inputManager;
    private AssetManager assetManager;
    private UpdateManager updateManager;

    private OrthographicCamera camera;

    public Stage()
    {
        entityManager = new EntityManager( this );
        renderingManager = new RenderingManager( this );
//        inputManager = new InputManager( this );
        assetManager = new AssetManager( this );
        updateManager = new UpdateManager( this );

        camera = new OrthographicCamera( Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
        camera.setToOrtho( true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
    }

    public EntityManager getComponentManager()
    {
        return entityManager;
    }

    public RenderingManager getRenderingManager()
    {
        return renderingManager;
    }

    public InputManager getInputManager()
    {
        return null;
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
        entityManager.update( delta );
        camera.update();
        renderingManager.render( camera, delta );
    }

    @Override
    public void resize( int width, int height )
    {

    }

    @Override
    public void show()
    {
        // Create a test character
        for ( float spd = 5.0f; spd > 0.01f; spd -= 0.05f )
        {
            GameCharacter char1 = GameCharacter.createInstance();
            entityManager.addEntity( char1 );
            renderingManager.addToLayer( "TEST", char1 );
            char1.setMoveSpeed( spd );
        }

        System.out.printf( "Created %d components\n", entityManager.getEntityCount() );
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
