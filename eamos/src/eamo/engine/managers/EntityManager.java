package eamo.engine.managers;

import java.util.ArrayList;
import java.util.List;

import eamo.engine.component.GameEntity;
import eamo.engine.messaging.MessageDispatcher;
import eamo.engine.stage.Stage;

/**
 * Entity manager subsystem of a stage. Handles dispatching messages to
 * entities TODO change to entitiy manager
 */
public class EntityManager extends Manager
{
    private List< GameEntity > entities;

    /**
     * Construct a entity manager supplying a parent stage object.
     * 
     * @param stage the parent stage to supply to this manager.
     */
    public EntityManager( Stage stage )
    {
        super( stage );
        entities = new ArrayList< GameEntity >();
    }

    /**
     * Add a entity to this manager.
     * 
     * @param entity
     */
    public void addEntity( GameEntity entity )
    {
        entity.init();
        entities.add( entity );
    }

    /**
     * Get the entity referenced by the supplied ID
     * 
     * @param id the ID of the entity to retrieve
     * @return the requested entity.
     */
    public GameEntity getEntity( String id )
    {
        for ( GameEntity entity : entities )
        {
            if ( entity.getID().equals( id ) )
            {
                return entity;
            }
        }
        return null;
    }

    public void removeEntity( String id )
    {
        GameEntity toRemove = getEntity( id );
        if ( toRemove != null )
        {
            entities.remove( toRemove );
            toRemove.dispose();
        }
    }

    public int getEntityCount()
    {
        return entities.size();
    }

    // public void registerForMessages( MessageListener listener )
    // {
    // dispatcher.addListener( listener );
    // }
    //
    // public void registerForMessages( MessageListener listener, String
    // category )
    // {
    // dispatcher.addListener( listener, category );
    // }
    //
    // public void unregisterForMessages( MessageListener listener )
    // {
    // dispatcher.removeListener( listener );
    // }
    //
    // public void dispatchMessage( Message message )
    // {
    // dispatcher.dispatchMessage( message );
    // }

    @Override
    public void dispose()
    {
        for ( GameEntity entity : entities )
        {
            entity.dispose();
        }
        entities.clear();
    }

    public void update( float delta )
    {
        for ( GameEntity entity : entities )
        {
            entity.update( delta );
        }
    }
}
