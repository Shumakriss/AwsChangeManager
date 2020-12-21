import myapp.changesets.ChangeSet00000001;
import myapp.changesets.ChangeSet00000002;
import org.shumakriss.changesets.App;
import org.shumakriss.changesets.Version;
import org.shumakriss.changesets.VersionStore;
import org.shumakriss.changesets.aws.AwsClientContext;
import org.shumakriss.changesets.aws.AwsSsmVersionStore;
import software.amazon.awssdk.services.ssm.SsmClient;

public class Main {

    public static void main(String[] args) {
        AwsClientContext awsClientContext = new AwsClientContext();
        awsClientContext.add(SsmClient.builder().build());

        VersionStore versionStore = new AwsSsmVersionStore(awsClientContext.getSsmClient(), "ChangeSetTestKey");
        App app = new App(versionStore);

        app.addVersionChangeSet(new Version("0.0.1"), new ChangeSet00000001());
        app.addVersionChangeSet(new Version("0.0.2"), new ChangeSet00000002());

        app.update();
    }


}