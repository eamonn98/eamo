package eamo.engine.component.message;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 */
public class MessageDispatcher
{
    private Map< String, Set< MessageListener > > categoryToListenerPairs;

    /**
     * 
     */
    public MessageDispatcher()
    {
        categoryToListenerPairs = new HashMap< String, Set< MessageListener > >();
    }

    /**
     * 
     * @param listener
     */
    public void addListener( MessageListener listener )
    {
        this.addListener( listener, Message.CATEGORY_ALL );
    }

    /**
     * 
     * @param listener
     * @param category
     */
    public void addListener( MessageListener listener, String category )
    {
        Set< MessageListener > listeners = categoryToListenerPairs.get( category );

        if ( listeners == null )
        {
            listeners = new HashSet< MessageListener >();
            categoryToListenerPairs.put( category, listeners );
        }

        listeners.add( listener );

        if ( !category.equals( Message.CATEGORY_ALL ) )
        {
            // Ensure is added to CATEGORY_ALL
            listeners = categoryToListenerPairs.get( Message.CATEGORY_ALL );
            if ( listeners == null )
            {
                listeners = new HashSet< MessageListener >();
                categoryToListenerPairs.put( category, listeners );
            }
            listeners.add( listener );
        }
    }

    /**
     * 
     * @param listener
     */
    public void removeListener( MessageListener listener )
    {
        for ( Entry< String, Set< MessageListener > > entry : categoryToListenerPairs.entrySet() )
        {
            entry.getValue().remove( listener );
        }
    }

    /**
     * 
     * @param listener
     * @param categories
     */
    public void removeListener( MessageListener listener, String... categories )
    {
        for ( Entry< String, Set< MessageListener > > entry : categoryToListenerPairs.entrySet() )
        {
            for ( String s : categories )
            {
                if ( entry.getKey().equals( s ) )
                {

                    entry.getValue().remove( listener );
                }
            }
        }
    }

    /**
     * 
     * @param message
     */
    public void dispatchMessage( Message message )
    {
        if ( message.getCategory() == null || message.getCategory().equals( Message.CATEGORY_ALL ) )
        {
            for ( Entry< String, Set< MessageListener > > entry : categoryToListenerPairs.entrySet() )
            {
                for ( MessageListener listener : entry.getValue() )
                {
                    if ( listener.getOwner().isInitialised() )
                    {
                        if ( message.getRecipient() != null )
                        {
                            if ( message.getRecipient().equals( listener.getOwner() ) )
                            {
                                listener.receiveMessage( message );
                            }
                        }
                        else
                        {
                            listener.receiveMessage( message );
                        }
                    }
                }
            }
        }
        else
        {
            for ( Entry< String, Set< MessageListener > > entry : categoryToListenerPairs.entrySet() )
            {
                if ( entry.getKey().equals( message.getCategory() ) )
                {
                    for ( MessageListener listener : entry.getValue() )
                    {
                        if ( listener.getOwner().isInitialised() )
                        {
                            if ( message.getRecipient() != null )
                            {
                                if ( message.getRecipient().equals( listener.getOwner() ) )
                                {
                                    listener.receiveMessage( message );
                                }
                            }
                            else
                            {
                                listener.receiveMessage( message );
                            }
                        }
                    }
                }
            }
        }
    }
}
