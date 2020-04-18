package edu.aeon.sim;

import edu.cmu.lti.ws4j.WS4J;
import edu.illinois.cs.cogcomp.sim.MetricResponse;
import edu.illinois.cs.cogcomp.sim.MetricWord;
import edu.illinois.cs.cogcomp.sim.WordSim;
import edu.illinois.cs.cogcomp.wordnet.ImprovedWN;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.IOException;

public class UberWordSim extends WordSim {

    private static final String NAME = UberWordSim.class.getCanonicalName();
    private static final String IWN_FIELD_NAME = "iwn";

    public Measure measure;

    public UberWordSim(Measure measure, String wnPath) throws IOException {
        super(measure.toString(), ResourceManagerFactory.get(wnPath));
        this.measure = measure;
    }

    public UberWordSim(String wnPath) throws IOException {
        super(ResourceManagerFactory.get(wnPath));
    }

    public MetricResponse compare(String small, String big) {
        double score = 0.;
        String measureName = "NONE";

        // Dirty hack to retrieve iwn private field
        ImprovedWN iwn = null;
        try {
            iwn = (ImprovedWN) FieldUtils.readField(this, IWN_FIELD_NAME, true);
        } catch (IllegalAccessException ignored) {
        }

        if ((iwn == null) || (iwn.getAllSynset(small).size() == 0) || (iwn.getAllSynset(big).size() == 0)) {
            return new MetricResponse(0, NAME + ":NONE");
        }
        switch (this.measure) {
            case Bal:
                measureName = "Bal";
                // TODO
                score = 0.;
                break;
            case Cos:
                measureName = "Cos";
                // TODO
                score = 0.;
                break;
            case LeacockChodorow:
                measureName = "LeacockChodorow";
                score = WS4J.calcSimilarityByLeacockChodorow(small, big);
                break;
            case Lesk:
                measureName = "Lesk";
                score = WS4J.calcSimilarityByLesk(small, big);
                break;
            case Lin:
                measureName = "Lin";
                score = WS4J.calcSimilarityByLin(small, big);
                break;
            case Path:
                measureName = "Path";
                score = WS4J.calcSimilarityByPath(small, big);
                break;
            case Resnik:
                measureName = "Resnik";
                score = WS4J.calcSimilarityByResnik(small, big);
                break;
            case WuPalmer:
                measureName = "WuPalmer";
                score = WS4J.calcSimilarityByWuPalmer(small, big);
                break;
        }
        return new MetricResponse(score, NAME + ":" + measureName);
    }

    public MetricResponse compare(MetricWord first, MetricWord second) throws IllegalArgumentException {
        return compare(first.word, second.word);
    }

    @Override
    public String getName() {
        return NAME;
    }

}
