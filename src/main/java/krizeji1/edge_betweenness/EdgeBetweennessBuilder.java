package krizeji1.edge_betweenness;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsBuilder.class)
public class EdgeBetweennessBuilder implements StatisticsBuilder {
    public String getName() {
        return NbBundle.getMessage(EdgeBetweennessBuilder.class, "EdgeBetweennessBuilder.name");
    }

    public Statistics getStatistics() {
        return new EdgeBetweenness();
    }

    public Class<? extends Statistics> getStatisticsClass() {
        return EdgeBetweenness.class;
    }

}
