package Updates;

import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.*;

public class UpdateManager {

    private SsmClient ssmClient;
    private String versionParameterKey;
    private int deployedVersion;

    private int versionIndex = 0;


    public UpdateManager(SsmClient ssmClient, String versionParameterKey) {
        this.ssmClient = ssmClient;
        this.versionParameterKey = versionParameterKey;
        this.deployedVersion = getDeployedVersion();
    }

    private int getDeployedVersion() {

        try {
            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                    .name(versionParameterKey)
                    .build();

            GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
            String version = parameterResponse.parameter().value();
            if (version == null) {
                return 0;
            } else {
                return Integer.parseInt(version);
            }

        } catch (ParameterNotFoundException parameterNotFoundException) {
            return 0;
        }
    }

    private void setVersion() {
        PutParameterRequest putParameterRequest = PutParameterRequest.builder()
                .name(versionParameterKey)
                .type(ParameterType.STRING)
                .value(String.valueOf(versionIndex))
                .overwrite(true)
                .build();
        ssmClient.putParameter(putParameterRequest);
    }

    public void update(Update update) {
        if (deployedVersion == versionIndex) {
            update.apply();
            deployedVersion++;
            versionIndex++;
            setVersion();
        } else {
            versionIndex++;
        }
    }

}
