package org.jfrog.build.extractor.usageReport;

import org.jfrog.build.api.util.Log;
import org.jfrog.build.client.ProxyConfiguration;
import org.jfrog.build.extractor.clientConfiguration.client.artifactory.ArtifactoryManager;

import java.io.IOException;

/**
 * Created by Bar Belity on 20/11/2019.
 */
public class UsageReporter {
    private String productId;
    private FeatureId[] features;

    public UsageReporter(String productId, String[] featureIds) {
        this.productId = productId;
        setFeatures(featureIds);
    }

    public void reportUsage(String artifactoryUrl, String username, String password, String accessToken, ProxyConfiguration proxyConfiguration, Log log) throws IOException {
        ArtifactoryManager artifactoryManager = new ArtifactoryManager(artifactoryUrl, username, password, accessToken, log);
        if (proxyConfiguration != null) {
            artifactoryManager.setProxyConfiguration(proxyConfiguration);
        }
        artifactoryManager.reportUsage(this);
    }

    public String getProductId() {
        return productId;
    }

    public FeatureId[] getFeatures() {
        return features;
    }

    private void setFeatures(String[] featureIds) {
        features = new FeatureId[featureIds.length];
        int featureIndex = 0;
        for (String featureId : featureIds) {
            features[featureIndex] = new FeatureId(featureId);
            featureIndex++;
        }
    }

    public static class FeatureId {
        private final String featureId;

        public FeatureId(String featureId) {
            this.featureId = featureId;
        }

        public String getFeatureId() {
            return featureId;
        }
    }
}
