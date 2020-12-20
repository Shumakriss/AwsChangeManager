package org.shumakriss.updates.resources;

import org.shumakriss.updates.Deployable;
import org.shumakriss.updates.Identifiable;
import org.shumakriss.updates.Validatable;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.*;

import java.util.Map;

public class SsmStringParameterResource implements Identifiable, Deployable, Validatable {
    SsmClient ssmClient;
    String key;
    String value;

    public SsmStringParameterResource(SsmClient ssmClient, String key, String value) {
        this.ssmClient = ssmClient;
        this.key = key;
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getId() {
        return "AWS_SSM_PARAMETER_" + key;
    }

    public static String getId(String key) {
        return "AWS_SSM_PARAMETER_" + key;
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

    public String getDeployedValue() {
        try {
            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                    .name(key)
                    .build();

            GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
            String responseValue = parameterResponse.parameter().value();
            return responseValue;
        } catch (ParameterNotFoundException parameterNotFoundException) {
            return null;
        }
    }

    @Override
    public boolean isValid() {
        String deployedValue = getDeployedValue();
        return deployedValue == value;
    }

}
