import org.shumakriss.myapp.updates.Version_0_0_1_Update;
import org.shumakriss.myapp.updates.Version_0_0_2_Update;
import org.shumakriss.updates.UpdateManager;
import software.amazon.awssdk.services.ssm.SsmClient;

public class Main {

    public static void main(String[] args) {
        SsmClient ssmClient = SsmClient.builder().build();

        UpdateManager updateManager = new UpdateManager();
        updateManager.add(new Version_0_0_1_Update(ssmClient));
        updateManager.add(new Version_0_0_2_Update(ssmClient));
        updateManager.deploy();
    }
}