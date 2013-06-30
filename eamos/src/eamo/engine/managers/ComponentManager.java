package eamo.engine.managers;

import java.util.ArrayList;
import java.util.List;

import eamo.engine.component.Component;
import eamo.engine.component.message.Message;
import eamo.engine.component.message.MessageDispatcher;
import eamo.engine.component.message.MessageListener;
import eamo.engine.stage.Stage;

/**
 * Component manager subsystem of a stage. Handles dispatching messages to components
 */
public class ComponentManager extends Manager
{
    private List< Component > components;
    private MessageDispatcher dispatcher;

    /**
     * Construct a component manager supplying a parent stage object.
     * 
     * @param stage the parent stage to supply to this manager.
     */
    public ComponentManager( Stage stage )
    {
        super( stage );
        components = new ArrayList< Component >();
        dispatcher = new MessageDispatcher();
    }

    /**
     * Add a component to this manager.
     * 
     * @param component
     */
    public void addComponent( Component component )
    {
        component.initialise( this );
        components.add( component );
    }

    /**
     * Get the component referenced by the supplied ID
     * 
     * @param id the ID of the component to retrieve
     * @return the requested component.
     */
    public Component getComponent( long id )
    {
        for ( Component component : components )
        {
            if ( component.getID() == id )
            {
                return component;
            }
        }
        return null;
    }

    public void removeComponent( long id )
    {
        Component toRemove = getComponent( id );
        if ( toRemove != null )
        {
            components.remove( toRemove );
            toRemove.dispose();
        }
    }

    public int getComponentCount()
    {
        return components.size();
    }

    public void registerForMessages( MessageListener listener )
    {
        dispatcher.addListener( listener );
    }

    public void unregisterForMessages( MessageListener listener )
    {
        dispatcher.removeListener( listener );
    }

    public void dispatchMessage( Message message )
    {
        dispatcher.dispatchMessage( message );
    }

    @Override
    public void dispose()
    {
        for ( Component component : components )
        {
            removeComponent( component.getID() );
        }
    }
}
