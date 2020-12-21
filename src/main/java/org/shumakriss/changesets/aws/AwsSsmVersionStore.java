package org.shumakriss.changesets.aws;

import org.shumakriss.changesets.Version;
import org.shumakriss.changesets.VersionStore;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.*;

public class AwsSsmVersionStore implements VersionStore {

    SsmClient ssmClient;
    String versionKey;

    public AwsSsmVersionStore(SsmClient ssmClient, String versionKey) {
        this.ssmClient = ssmClient;
        this.versionKey = versionKey;
    }

    @Override
    public String getDeployedVersion() {
        try {
            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                    .name(versionKey)
                    .build();

            GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
            String responseValue = parameterResponse.parameter().value();
            return responseValue;
        } catch (ParameterNotFoundException parameterNotFoundException) {
            return null;
        }
    }

    @Override
    public void saveVersion(Version version) {
        PutParameterRequest putParameterRequest = PutParameterRequest.builder()
                .name(versionKey)
                .type(ParameterType.STRING)
                .value(String.valueOf(version.getVersionId()))
                .overwrite(true)
                .build();
        ssmClient.putParameter(putParameterRequest);
    }
}
