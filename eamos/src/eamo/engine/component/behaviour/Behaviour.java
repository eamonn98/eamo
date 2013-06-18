package eamo.engine.component.behaviour;

import eamo.engine.component.Component;
import eamo.engine.component.message.Message;

/**
 * Abstract base class for behaviours. TODO javadoc
 */
public abstract class Behaviour
{
    private int behaviourID;
    private Component parent;

    public void init( int behaviourID, Component parent )
    {
        this.behaviourID = behaviourID;
        this.parent = parent;
    }

    public int getBehaviourID()
    {
        return behaviourID;
    }

    protected Component getParent()
    {
        return parent;
    }

    public void issueMessage( Message message )
    {
        // TODO stub. Implement listener interface.
    }
}
