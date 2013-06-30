package eamo.engine.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;

import eamo.engine.component.attribute.Attribute;
import eamo.engine.component.behaviour.Behaviour;
import eamo.engine.component.message.Message;
import eamo.engine.component.message.MessageDispatcher;
import eamo.engine.component.message.MessageListener;
import eamo.engine.managers.ComponentManager;

/**
 * Game component class TODO javadoc
 */
public class Component implements Disposable
{
    private long id;

    private final HashMap< String, Attribute< ? > > attributes = new HashMap< String, Attribute< ? > >();
    private final List< Behaviour > behaviours = new ArrayList< Behaviour >();
    private final List< Component > subComponents = new ArrayList< Component >();

    private Component parent;
    private Component rootComponent;
    private ComponentManager manager;
    private MessageDispatcher dispatcher;
    private ComponentMessageListener listener;

    /**
     * Message listener that exists in a root component and dispatches messages
     * received from a component manager via it's own child hierarchy.
     */
    private final class ComponentMessageListener extends MessageListener
    {
        public ComponentMessageListener()
        {
            super( Component.this );
        }

        @Override
        public void receiveMessage( Message message )
        {
            dispatcher.dispatchMessage( message );
        }
    }

    /**
     * Create a component object and supply it's parent and unique identifier
     * attributes.
     * 
     * @param id the id of the component.
     * @param parent this component's parent.
     */
    public Component( long id, Component parent )
    {
        this.id = id;
        this.parent = parent;

        if ( parent.getParentComponent() == null )
        {
            setRootComponent( parent );
        }
        else
        {
            setRootComponent( parent.getRootComponent() );
        }
    }

    /**
     * Create a new component supplying only an ID. Notice that a component
     * without any parent component is considered to be a root level component.
     * A root level component is a component that exists at the top of a
     * component-subcomponent hierarchy.
     * <p>
     * A root level component has it's own message dispatcher in which sub
     * components may register for events with.
     * 
     * @param id the component unique identifier.
     */
    public Component( long id )
    {
        this.id = id;
    }

    /**
     * Initialise this component and assign it's component manager.
     * 
     * @param manager the component manager to assign.
     */
    public void initialise( ComponentManager manager )
    {
        this.manager = manager;

        if ( isRootComponent() )
        {
            dispatcher = new MessageDispatcher();
            listener = new ComponentMessageListener();
            manager.registerForMessages( listener );
        }

        for ( Component subComponent : subComponents )
        {
            subComponent.initialise( manager );
        }
    }

    /**
     * Destroy the component and all sub-components of this component. Causes
     * any event listeners registered with the component manager to be
     * invalidated and removed.
     */
    @Override
    public void dispose()
    {
        for ( Component subComponent : subComponents )
        {
            subComponent.dispose();
        }

        // If is root component, invalidate with component manager.
        if ( isRootComponent() )
        {
            manager.unregisterForMessages( listener );
        }
    }

    /**
     * Get the component manager managing this component.
     * @return the component manager managing this component.
     */
    public ComponentManager getComponentManager()
    {
        return manager;
    }
    
    /**
     * Retrieve the message dispatcher object belonging to this component.
     * 
     * @return this component's message dispatcher.
     */
    public MessageDispatcher getDispatcher()
    {
        return dispatcher;
    }

    /**
     * Get this component's unique identifier value.
     * 
     * @return the unique identifier of this component.
     */
    public long getID()
    {
        return id;
    }

    /**
     * Add an attribute to this component.
     * 
     * @param attribute the attribute to add.
     */
    public void addAttribute( Attribute< ? > attribute )
    {
        this.attributes.put( attribute.getAttributeID(), attribute );
    }

    /**
     * Add a behaviour to this component and initialise the behaviour.
     * 
     * @param behaviour the behaviour to initialise.
     */
    public void addBehaviour( Behaviour behaviour )
    {
        behaviour.init( behaviours.size(), this );
        behaviours.add( behaviour );
    }

    /**
     * Get the attribute from this component whose id matches the supplied
     * value.
     * 
     * @param attributeID the unique identifier of the attribute to retrieve.
     * @return the attribute matching the supplied id value, or null if not
     *         found.
     */
    public Attribute< ? > getAttribute( String attributeID )
    {
        return attributes.get( attributeID );
    }

    /**
     * Iteratively search through this components child hierarchy and return a
     * component object whose unique identifier matches the supplied id value.
     * This method operates it's search mechanism in a depth-first fashion.
     * 
     * @param id the unique id of the component to search for.
     * @return the component (if found), or null (if not found).
     */
    public Component getSubComponent( long id )
    {
        for ( Component component : subComponents )
        {
            if ( component.getID() == id )
            {
                return component;
            }

            // Search depth first through the sub component's hierarchy.
            Component sub = component.getSubComponent( id );
            if ( sub != null )
            {
                return sub;
            }
        }
        return null;
    }

    /**
     * Return true if the <b>child</b> component hierarchy of this component
     * contains a component whose unique identifier matches the supplied id
     * value.
     * 
     * @param id the id of the component to search for.
     * @return true if the component lying below this component contains a
     *         matching value.
     */
    public boolean containsSubComponent( long id )
    {
        return getSubComponent( id ) != null;
    }

    /**
     * Add the supplied sub component to this component's component hierarchy.
     * Initialise the sub component if this component has already been
     * initialised previously.
     * 
     * @param subComponent the subcomponent to add.
     */
    public void addSubComponent( Component subComponent )
    {
        subComponents.add( subComponent );

        if ( isInitialised() )
        {
            subComponent.initialise( manager );
        }
    }

    /**
     * Retrieve the amount of subcomponents immediately below this component in
     * the component hierarchy.
     * 
     * @return the amount of sub components this component has.
     */
    public int getSubComponentCount()
    {
        return subComponents.size();
    }

    /**
     * Get the parent component of this component. If this component is a root
     * component, a null value is returned due to the fact that root components
     * exist at the top of a component's hierarchy.
     * 
     * @return this component's parent component.
     */
    public Component getParentComponent()
    {
        return parent;
    }

    /**
     * Set the parent component of this component to a different value. Note
     * that a component's new parent must share the same root component. It is
     * not possible to move components between two different component
     * hierarchies.
     * 
     * @param parent the component to assign to this component as a parent.
     */
    public void setParent( Component parent )
    {
        if ( !parent.getRootComponent().equals( getRootComponent() ) )
        {
            // TODO unsure about libgdx logger system
            new Logger( "Component cannot be moved to a different hierarchy.", Logger.ERROR );
            return;
        }
        this.parent = parent;
    }

    /**
     * Get the root component that sits atop the component hierarchy of this
     * component. If this component is the root component then this component is
     * returned.
     * 
     * @return the root component of this component.
     */
    public Component getRootComponent()
    {
        return rootComponent;
    }

    /**
     * Determine if this component is a root level component.
     * 
     * @return true if this component is at root level.
     */
    public boolean isRootComponent()
    {
        return getParentComponent() == null;
    }

    /**
     * Set the root component of this component. TODO not sure if this method is
     * such a good idea. Unit testing required.
     * 
     * @param rootComponent the new root component to set.
     */
    public void setRootComponent( Component rootComponent )
    {
        this.rootComponent = rootComponent;
    }

    /**
     * Determine whether this component has been initialised. This basically
     * evaluates to true if the component manager has been assigned to this
     * component.
     * 
     * @return true if this component has been initialised.
     */
    public boolean isInitialised()
    {
        return manager != null;
    }
}
