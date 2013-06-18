package com.eamo.entity.behaviour;

import com.eamo.entity.Entity;

/**
 * Abstract base class for behaviours.
 * User: eamonn98
 * Date: 12/05/2013
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 */
public abstract class Behaviour
{
    private int behaviourID;
    private Entity parent;

    public void init( int behaviourID, Entity parent )
    {
        this.behaviourID = behaviourID;
        this.parent = parent;
    }

    public int getBehaviourID()
    {
        return behaviourID;
    }

    protected Entity getParent()
    {
        return parent;
    }
}
