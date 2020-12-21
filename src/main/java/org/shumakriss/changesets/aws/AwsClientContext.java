package org.shumakriss.changesets.aws;

import software.amazon.awssdk.services.ssm.SsmClient;

public class AwsClientContext {
    SsmClient ssmClient;

    public void add(Object object) {
        if (object instanceof SsmClient) {
            this.ssmClient = (SsmClient) object;
        }
    }

    public SsmClient getSsmClient() {
        return ssmClient;
    }
}
