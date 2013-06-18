package eamo.engine.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import eamo.engine.component.attribute.Attribute;
import eamo.engine.component.attribute.AttributeConstants;
import eamo.engine.component.behaviour.RenderRectangle;

/**
 * TODO javadoc
 */
public class ComponentFactory
{
    public static Component createRectangleEntity( float x, float y, float w, float h, Color color )
    {
        Component rect = new Component( 1L );
        rect.addAttribute( new Attribute< Vector2 >( AttributeConstants.POSITION_ATTRIBITE_ID, new Vector2( x, y ) ) );
        rect.addAttribute( new Attribute< Vector2 >( AttributeConstants.SIZE_ATTRIBITE_ID, new Vector2( w, y ) ) );
        rect.addAttribute( new Attribute< Color >( AttributeConstants.COLOR_ATTRIBITE_ID, color ) );
        rect.addBehaviour( new RenderRectangle() );
        return rect;
    }
}
