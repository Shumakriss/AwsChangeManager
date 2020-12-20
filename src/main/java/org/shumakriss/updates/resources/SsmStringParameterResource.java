package org.shumakriss.updates.resources;

import org.shumakriss.updates.Deployable;
import org.shumakriss.updates.Identifiable;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.ParameterType;
import software.amazon.awssdk.services.ssm.model.PutParameterRequest;

import java.util.Map;

public class SsmStringParameterResource implements Identifiable, Deployable {
    SsmClient ssmClient;
    String key;
    String value;

    public SsmStringParameterResource(SsmClient ssmClient, String key, String value) {
        this.ssmClient = ssmClient;
        this.key = key;
        this.value = value;
    }

    @Override
    public String getId() {
        return "AWS_SSM_PARAMETER" + key;
    }

    @Override
    public void deploy(Map<String, Object> manifest) {
        PutParameterRequest putParameterRequest = PutParameterRequest.builder()
                .name(key)
                .type(ParameterType.STRING)
                .value(String.valueOf(value))
                .overwrite(true)
                .build();
        ssmClient.putParameter(putParameterRequest);
    }

//    private int getDeployedVersion() {
//
//        try {
//            GetParameterRequest parameterRequest = GetParameterRequest.builder()
//                    .name(versionParameterKey)
//                    .build();
//
//            GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
//            String version = parameterResponse.parameter().value();
//            if (version == null) {
//                return 0;
//            } else {
//                return Integer.parseInt(version);
//            }
//
//        } catch (ParameterNotFoundException parameterNotFoundException) {
//            return 0;
//        }
//    }
//
//    private void setVersion() {
//        PutParameterRequest putParameterRequest = PutParameterRequest.builder()
//                .name(versionParameterKey)
//                .type(ParameterType.STRING)
//                .value(String.valueOf(versionIndex))
//                .overwrite(true)
//                .build();
//        ssmClient.putParameter(putParameterRequest);
//    }

}
