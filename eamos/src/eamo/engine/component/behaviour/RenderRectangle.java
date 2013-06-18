package eamo.engine.component.behaviour;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import eamo.engine.component.attribute.Attribute;
import eamo.engine.component.attribute.AttributeConstants;

/**
 * TODO javadoc
 */
public class RenderRectangle extends Behaviour implements Updatable
{
    private ShapeRenderer shapeRenderer;

    public RenderRectangle()
    {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update( float delta )
    {
        Attribute< Vector2 > pos = (Attribute< Vector2 >) getParent().getAttribute( AttributeConstants.POSITION_ATTRIBITE_ID );
        Attribute< Vector2 > size = (Attribute< Vector2 >) getParent().getAttribute( AttributeConstants.SIZE_ATTRIBITE_ID );
        Attribute< Color > col = (Attribute< Color >) getParent().getAttribute( AttributeConstants.COLOR_ATTRIBITE_ID );

        shapeRenderer.begin( ShapeRenderer.ShapeType.FilledRectangle );
        shapeRenderer.setColor( col.getAttribute() );
        shapeRenderer.filledRect( pos.getAttribute().x, pos.getAttribute().y, size.getAttribute().x, size.getAttribute().y );
        shapeRenderer.end();

        shapeRenderer.begin( ShapeRenderer.ShapeType.Rectangle );
        shapeRenderer.setColor( Color.WHITE );
        shapeRenderer.rect( pos.getAttribute().x, pos.getAttribute().y, size.getAttribute().x, size.getAttribute().y );
        shapeRenderer.end();
    }
}
