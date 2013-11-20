package eamo.engine.messaging;

import eamo.engine.component.Component;

public abstract class MessageListener
{
    private Component owner;

    public MessageListener( Component owner )
    {
        this.owner = owner;
    }

    public abstract void receiveMessage( Message message );

    public Component getOwner()
    {
        return owner;
    }
}
