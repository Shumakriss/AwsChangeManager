package org.shumakriss.myapp.updates;

import org.shumakriss.updates.GenericUpdate;
import org.shumakriss.updates.resources.SsmStringParameterResource;
import software.amazon.awssdk.services.ssm.SsmClient;

public class Version_0_0_1_Update extends GenericUpdate {

    public Version_0_0_1_Update(SsmClient ssmClient) {
        addResource(new SsmStringParameterResource(ssmClient, "Version", "0.0.1"));
    }
}
