package org.jfrog.build.extractor.clientConfiguration.client.distribution.services;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.jfrog.build.api.util.Log;
import org.jfrog.build.extractor.clientConfiguration.client.distribution.request.CreateReleaseBundleRequest;

import java.io.IOException;

import static org.jfrog.build.extractor.clientConfiguration.util.JsonUtils.toJsonString;

public class CreateReleaseBundle extends VoidDistributionService {
    private static final String CREAT_RELEASE_BUNDLE_ENDPOINT = "api/v1/release_bundle";
    private CreateReleaseBundleRequest createReleaseBundleRequest;
    private String gpgPassphrase;

    public CreateReleaseBundle(CreateReleaseBundleRequest createReleaseBundleRequest, String gpgPassphrase,Log logger) {
        super(logger);
        this.createReleaseBundleRequest = createReleaseBundleRequest;
        this.gpgPassphrase = gpgPassphrase;
    }

    @Override
    public HttpRequestBase createRequest() throws IOException {
        HttpPost request = new HttpPost(CREAT_RELEASE_BUNDLE_ENDPOINT);
        request.setHeader("Accept"," application/json");
        request.setHeader("X-GPG-PASSPHRASE",gpgPassphrase);
        StringEntity stringEntity = new StringEntity(toJsonString(createReleaseBundleRequest));
        stringEntity.setContentType("application/json");
        request.setEntity(stringEntity);
        return request;
    }
}
