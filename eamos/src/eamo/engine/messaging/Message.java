package eamo.engine.messaging;

import eamo.engine.component.Component;

/**
 * Messages form the primary basis of communication between components.
 * Typically a message is sent to one component from another, within a single
 * component hierarchy. A component "listens" for messages by requesting, via
 * the root component in it's hierarchy, registration for messages.
 * <p>
 * A component may wish to target another component, all components, a range of
 * components utilising a category feature with the message it's sending.
 */
public class Message
{
    public static final String CATEGORY_ALL = "ALL_CATEGORIES";

    private String messageBody;
    private String category;
    private Component sender;
    private Component recipient;

    /**
     * Create a message with a supplied message body. This constructor does not
     * assign a specific category of message and thus dispatches this message to
     * all recipients of the component it is sent to.
     * 
     * @param messageBody the message body portion.
     */
    public Message( String messageBody )
    {
        this.messageBody = messageBody;
    }

    /**
     * Create a message specifying the message body and a category identifier. A
     * message category identifier cannot match the value "CATEGORY_ALL".
     * 
     * @param messageBody the message body portion.
     * @param category the category this message falls under.
     */
    public Message( String messageBody, String category )
    {
        super();
        this.messageBody = messageBody;
        this.category = category;
    }

    /**
     * Create a message supplying the message body and a reference to the
     * component that originally dispatched this message.
     * 
     * @param messageBody the message body portion.
     * @param sender the component that originally sent this message.
     */
    public Message( String messageBody, Component sender )
    {
        super();
        this.messageBody = messageBody;
        this.sender = sender;
    }

    /**
     * Create a message supplying the message body, a category and a reference
     * to the component that initially created this message. A message category
     * identifier cannot match the value "CATEGORY_ALL".
     * 
     * @param messageBody the message body portion.
     * @param category the category this message falls under.
     * @param sender the component that originally sent this message.
     */
    public Message( String messageBody, String category, Component sender )
    {
        super();
        this.messageBody = messageBody;
        this.category = category;
        this.sender = sender;
    }

    /**
     * Create a message supplying the message body, the sending component and a
     * reference to the intended recipient component.
     * 
     * @param messageBody the message body portion.
     * @param sender the component that originally sent this message.
     * @param recipient the component that this message may be received by.
     */
    public Message( String messageBody, Component sender, Component recipient )
    {
        super();
        this.messageBody = messageBody;
        this.sender = sender;
        this.recipient = recipient;
    }

    /**
     * Create a message supplying a message body, a category this message should
     * fall under, the sending component and the intended receiving component. A
     * message category identifier cannot match the value "CATEGORY_ALL".
     * 
     * @param messageBody the message body portion.
     * @param category the category this message falls under.
     * @param sender the component that originally sent this message.
     * @param recipient the component that this message may be received by.
     */
    public Message( String messageBody, String category, Component sender, Component recipient )
    {
        super();
        this.messageBody = messageBody;
        this.category = category;
        this.sender = sender;
        this.recipient = recipient;
    }

    /**
     * Get a String containing the body portion of this message.
     * 
     * @return the message body portion.
     */
    public String getMessageBody()
    {
        return messageBody;
    }

    /**
     * Get the component from which this message originated. This is useful
     * should a callback or response be required after a message is received.
     * 
     * @return the component that originally sent this message.
     */
    public Component getSender()
    {
        return sender;
    }

    /**
     * Get a reference to the component that is supposed to have received this
     * message.
     * 
     * @return the receiving component.
     */
    public Component getRecipient()
    {
        return recipient;
    }

    /**
     * Retrieve the category that this message may be placed under. Performance
     * may be improved through utilisation of message categories. The number of
     * listeners may be restricted for a particular category, resulting in fewer
     * listeners requiring notification of a dispatched message.
     * 
     * @return the category this message falls under.
     */
    public String getCategory()
    {
        return category;
    }
}
