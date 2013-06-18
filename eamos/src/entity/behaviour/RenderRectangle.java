package entity.behaviour;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import entity.attribute.Attribute;
import entity.attribute.AttributeConstants;

/**
 * Created with IntelliJ IDEA.
 * User: eamonn98
 * Date: 12/05/2013
 * Time: 16:34
 * To change this template use File | Settings | File Templates.
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
        Attribute<Vector2> pos = getParent().getAttribute( AttributeConstants.POSITION_ATTRIBITE_ID );
        Attribute<Vector2> size = getParent().getAttribute( AttributeConstants.SIZE_ATTRIBITE_ID );
        Attribute<Color> col = getParent().getAttribute( AttributeConstants.COLOR_ATTRIBITE_ID );

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
