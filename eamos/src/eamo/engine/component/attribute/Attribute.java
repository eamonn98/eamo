package eamo.engine.component.attribute;

/**
 * Attribute associated with a game entity
 */
public class Attribute< T >
{
    public String attributeID;
    private T attribute;

    public Attribute( String attributeID )
    {
        this.attributeID = attributeID;
    }

    public Attribute( String attributeID, T attribute )
    {
        this.attributeID = attributeID;
        this.attribute = attribute;
    }

    public String getAttributeID()
    {
        return attributeID;
    }

    public void setAttribute( T attribute )
    {
        this.attribute = attribute;
    }

    public T getAttribute()
    {
        return attribute;
    }
}
