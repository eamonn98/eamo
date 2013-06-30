package eamo.engine.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eamo.engine.component.Component;
import eamo.engine.component.message.Message;
import eamo.engine.component.message.MessageListener;
import eamo.engine.managers.ComponentManager;
import eamo.engine.sandbox.GameCharacter.RenderMessage;

/**
 * The abstract renderer serves as a base class for implementing renderer
 * objects. It simply receives a render message which would contain a reference
 * to the {@link SpriteBatch} and time delta in which to render at.
 */
public abstract class AbstractRenderer extends Component
{
    private String layerID;

    private class RenderMessageListener extends MessageListener
    {
        public RenderMessageListener( Component owner )
        {
            super( owner );
        }

        @Override
        public void receiveMessage( Message message )
        {
            if ( message.getMessageBody().equals( RenderMessage.MESSAGE_ID ) )
            {
                RenderMessage msg = (RenderMessage) message;
                render( msg.getBatch(), msg.getDelta() );
            }
        }
    }

    /**
     * Create a new abstract renderer. Implementing classes must make a call to
     * this constructor via <code>super( id, parent );</code>.
     * 
     * @param id the ID of this renderer component.
     * @param parent the parent component of this renderer.
     */
    public AbstractRenderer( long id, Component parent, String layerID )
    {
        super( id, parent );
        this.layerID = layerID;
    }

    @Override
    public void initialise( ComponentManager manager )
    {
        super.initialise( manager );
        getRootComponent().getDispatcher().addListener( new RenderMessageListener( this ) );
        manager.getStage().getRenderingManager().addToLayer( layerID, this );
    }

    /**
     * Renderer a set of graphics to the screen via the supplied
     * {@link SpriteBatch}
     * 
     * @param batch the batch of sprite objects to render to.
     * @param timeDelta the time since the last update operation.
     */
    protected abstract void render( SpriteBatch batch, float timeDelta );
}
