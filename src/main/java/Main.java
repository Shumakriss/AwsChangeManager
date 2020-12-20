import org.shumakriss.myapp.updates.*;
import org.shumakriss.updates.AwsClientContext;
import org.shumakriss.updates.UpdateManager;
import software.amazon.awssdk.services.ssm.SsmClient;

public class Main {

    public static void main(String[] args) {
        AwsClientContext awsClientContext = new AwsClientContext();
        awsClientContext.add(SsmClient.builder().build());

        UpdateManager updateManager = new UpdateManager();
        updateManager.add(new Version0001Update(awsClientContext));
        updateManager.add(new Version0002Update(awsClientContext));
        updateManager.add(new Version0003Update(awsClientContext));
        updateManager.add(new Version0004Update(awsClientContext));
        updateManager.add(new Version0005Update(awsClientContext));
        updateManager.add(new Version0006Update(awsClientContext));
        updateManager.add(new Version0007Update(awsClientContext));
        updateManager.add(new Version0008Update(awsClientContext));
        updateManager.deploy();
    }
}