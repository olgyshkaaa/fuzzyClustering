package krizeji1.girvan_newman;

import org.gephi.clustering.spi.Clusterer;
import org.gephi.clustering.spi.ClustererBuilder;
import org.gephi.clustering.spi.ClustererUI;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = ClustererBuilder.class)
public class GirvanNewmanClustererBuilder<T> implements ClustererBuilder {


    public Clusterer getClusterer() {
        return new GirvanNewmanClusterer();
    }


    public String getName() {
        return GirvanNewmanClusterer.PLUGIN_NAME;
    }


    public String getDescription() {
        return GirvanNewmanClusterer.PLUGIN_DESCRIPTION;
    }


    public Class getClustererClass() {
        return GirvanNewmanClusterer.class;
    }


    public ClustererUI getUI() {
        return null; // new GirvanNewmanClustererUI();
    }
}
