package com.eamo.entity;

import com.eamo.entity.Message.Message;
import com.eamo.entity.attribute.Attribute;
import com.eamo.entity.behaviour.Behaviour;
import com.eamo.entity.behaviour.Updatable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Game entity class. Instantiate using an EntityFactory.
 * User: eamonn98
 * Date: 12/05/2013
 * Time: 16:09
 */
public class Entity
{
    private long entityID;
    private HashMap<String, Attribute> attributes;
    private List<Behaviour> behaviours;

    public Entity( long entityID )
    {
        this.entityID = entityID;
        attributes = new HashMap<String, Attribute>();
        behaviours = new ArrayList<Behaviour>();
    }

    public void update( float delta )
    {
        for ( Behaviour b : behaviours )
        {
            if ( b instanceof Updatable )
            {
                ( ( Updatable ) b ).update( delta );
            }
        }
    }

    public void dispatchMessage( Message message )
    {

    }

    public void addAttribute( Attribute attribute )
    {
        this.attributes.put( attribute.getAttributeID(), attribute );
    }

    public void addBehaviour( Behaviour behaviour )
    {
        behaviour.init( behaviours.size(), this );
        behaviours.add( behaviour );
    }

    public Attribute getAttribute( String attributeID )
    {
        return attributes.get( attributeID );
    }

}