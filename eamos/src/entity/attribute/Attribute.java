package com.eamo.entity.attribute;

/**
 * Attribute associated with a game entity
 * User: eamonn98
 * Date: 12/05/2013
 * Time: 16:13
 * To change this template use File | Settings | File Templates.
 */
public class Attribute<T>
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
