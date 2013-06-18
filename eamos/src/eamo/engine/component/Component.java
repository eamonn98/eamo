package eamo.engine.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eamo.engine.component.attribute.Attribute;
import eamo.engine.component.behaviour.Behaviour;
import eamo.engine.component.behaviour.Updatable;
import eamo.engine.component.message.Message;

/**
 * Game entity class. Instantiate using an EntityFactory.
 */
public class Component
{
    private long id;
    private HashMap< String, Attribute< ? > > attributes;
    private List< Behaviour > behaviours;
    private List< Component > subComponents;

    public Component( long id )
    {
        this.id = id;
        attributes = new HashMap< String, Attribute< ? > >();
        behaviours = new ArrayList< Behaviour >();
    }

    public void update( float delta )
    {
        for ( Behaviour b : behaviours )
        {
            if ( b instanceof Updatable )
            {
                ( (Updatable) b ).update( delta );
            }
        }
    }

    /**
     * Dispatch the supplied message to all of this component's behaviours and
     * all of this component's added sub-components.
     * 
     * @param message the message to dispatch to behaviours.
     */
    public void dispatchMessage( Message message )
    {
        dispatchMessage( message, true );
    }

    /**
     * Dispatch the supplied message to all of this components behaviours. If
     * the delegate flag evaluates to true, dispatch the same message to all of
     * this components sub-components also.
     * 
     * @param message the message to dispatch to behaviours.
     * @param delegate whether to delegate the message to sub-components.
     */
    public void dispatchMessage( Message message, boolean delegate )
    {
        for ( Behaviour behaviour : behaviours )
        {
            behaviour.issueMessage( message );
        }

        if ( delegate )
        {
            for ( Component component : subComponents )
            {
                component.dispatchMessage( message );
            }
        }
    }

    public long getID()
    {
        return id;
    }

    public void addAttribute( Attribute< ? > attribute )
    {
        this.attributes.put( attribute.getAttributeID(), attribute );
    }

    public void addBehaviour( Behaviour behaviour )
    {
        behaviour.init( behaviours.size(), this );
        behaviours.add( behaviour );
    }

    public Attribute< ? > getAttribute( String attributeID )
    {
        return attributes.get( attributeID );
    }

    public Component getSubComponent( long id )
    {
        for ( Component component : subComponents )
        {
            if ( component.getID() == id )
            {
                return component;
            }
        }
        return null;
    }

    public boolean containsSubComponent( long id )
    {
        return getSubComponent( id ) != null;
    }

    public void addSubComponent( Component subComponent )
    {
        if ( subComponents == null )
        {
            subComponents = new ArrayList< Component >();
        }
        subComponents.add( subComponent );
    }

    public int getSubComponentCount()
    {
        return subComponents.size();
    }
}
