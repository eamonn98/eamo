package eamo.engine.rendering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import eamo.engine.component.GameEntity;

public class Layer implements Comparable< Layer >
{
    private String layerID;
    private int zIndex;
    private List< GameEntity > components;
    private final ZindexComparator zIndexComparator = new ZindexComparator();

    private class ZindexComparator implements Comparator< GameEntity >
    {
        @Override
        public int compare( GameEntity a, GameEntity b )
        {
            return ( (Float) a.calculateZIndex() ).compareTo( b.calculateZIndex() );
        }
    }

    public Layer( String layerID, int zIndex )
    {
        this.layerID = layerID;
        this.components = new ArrayList< GameEntity >();
    }

    public String getLayerID()
    {
        return layerID;
    }

    public void addEntity( GameEntity component )
    {
        if ( !components.contains( component ) )
        {
            components.add( component );
        }
    }

    public List< GameEntity > getEntities()
    {
        return components;
    }

    public void sortLayers()
    {
        Collections.sort( getEntities(), zIndexComparator );
    }

    @Override
    public int compareTo( Layer o )
    {
        return ( (Integer) zIndex ).compareTo( (Integer) o.zIndex );
    }
}
