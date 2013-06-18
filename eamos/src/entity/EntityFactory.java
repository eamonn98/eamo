package com.eamo.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.eamo.entity.attribute.Attribute;
import com.eamo.entity.attribute.AttributeConstants;
import com.eamo.entity.behaviour.RenderRectangle;

/**
 * Created with IntelliJ IDEA.
 * User: eamonn98
 * Date: 12/05/2013
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
public class EntityFactory
{
    public static Entity createRectangleEntity( float x, float y, float w, float h, Color color )
    {
        Entity rect = new Entity( 1L );
        rect.addAttribute( new Attribute<Vector2>( AttributeConstants.POSITION_ATTRIBITE_ID, new Vector2( x, y ) ) );
        rect.addAttribute( new Attribute<Vector2>( AttributeConstants.SIZE_ATTRIBITE_ID, new Vector2( w, y ) ) );
        rect.addAttribute( new Attribute<Color>( AttributeConstants.COLOR_ATTRIBITE_ID, color ) );
        rect.addBehaviour( new RenderRectangle() );
        return rect;
    }
}
