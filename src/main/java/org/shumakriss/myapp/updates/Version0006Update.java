package org.shumakriss.myapp.updates;

import org.shumakriss.updates.AwsClientContext;
import org.shumakriss.updates.GenericUpdate;
import org.shumakriss.updates.resources.SsmStringParameterResource;

import java.util.Map;

public class Version0006Update extends GenericUpdate {

    AwsClientContext awsClientContext;
    SsmStringParameterResource versionParameter;
    String versionKey = "VersionTest";
    String versionValue = "6";

    public Version0006Update(AwsClientContext awsClientContext) {
        this.awsClientContext = awsClientContext;
        versionParameter = new SsmStringParameterResource(awsClientContext.getSsmClient(), versionKey, versionValue);
        addResource(versionParameter);
    }


    @Override
    public boolean preDeploy(Map<String, Object> manifest) {
        String deployedValue = versionParameter.getDeployedValue();
        return (deployedValue == null ||
                Integer.parseInt(versionParameter.getDeployedValue()) == (Integer.parseInt(versionValue) - 1));
    }

}
