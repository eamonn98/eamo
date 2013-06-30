package eamo.engine.managers;

import java.util.TreeSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eamo.engine.component.Component;
import eamo.engine.rendering.Layer;
import eamo.engine.sandbox.GameCharacter;
import eamo.engine.stage.Stage;

/**
 * A rendering manager handles general rendering of components on a per-layer
 * basis. Messages are issued to each component managed by the component manager
 * alerting them that a render operation is about to take place. Components are
 * required to respond to that message should they require rendering.
 */
public class RenderingManager extends Manager
{
    private TreeSet< Layer > layers;
    private SpriteBatch spriteBatch;

    /**
     * Create a new rendering manager. An instantiated stage must exist and be
     * supplied to this constructor.
     * 
     * @param stage the parent stage of this rendering manager.
     */
    public RenderingManager( Stage stage )
    {
        super( stage );
        this.layers = new TreeSet< Layer >();
        this.spriteBatch = new SpriteBatch();
    }

    /**
     * Render all components on a layer by layer basis.
     * 
     * @param delta the time delta since the last render operation.
     */
    public void render( float delta )
    {
        Gdx.gl.glClearColor( 0.4f, 0.6f, 1.0f, 1.0f );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );

        for ( Layer layer : layers )
        {
            spriteBatch.begin();
            getStage().getComponentManager().dispatchMessage( new GameCharacter.RenderMessage( spriteBatch, delta ) );
            // TODO callback feature
            layer.getLayerID(); // TODO pointless code
            spriteBatch.end();
        }
    }

    /**
     * Add a component to the layer referred by the supplied ID parameter.
     * Ideally a single component should only be associated with a single layer.
     * 
     * @param layerID the ID of the layer to add the component to.
     * @param component the component to add to the supplied layer.
     */
    public void addToLayer( String layerID, Component component )
    {
        Layer layer = getLayer( layerID );

        if ( layer == null )
        {
            layer = new Layer( layerID, layers.size() );
            layers.add( layer );
        }

        layer.addComponent( component );
    }

    /**
     * Get the layer referenced by the supplied layer ID.
     * @param layerID the id of the layer to retrieve.
     * @return the requested layer, or null if requested layer does not exist.
     */
    public Layer getLayer( String layerID )
    {
        for ( Layer layer : layers )
        {
            if ( layer.getLayerID().equals( layerID ) )
            {
                return layer;
            }
        }

        return null;
    }

    @Override
    public void dispose()
    {
        spriteBatch.dispose();
    }
}
