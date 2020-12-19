import Updates.*;
import software.amazon.awssdk.services.ssm.SsmClient;

public class Main {

    static SsmClient ssmClient = SsmClient.builder().build();
    static String versionParameterKey = "version-number";

    public static void main(String[] args) {
        UpdateManager updateManager = new UpdateManager(ssmClient, versionParameterKey);

        updateManager.update(new Update1());
        updateManager.update(new Update2());
    }
}