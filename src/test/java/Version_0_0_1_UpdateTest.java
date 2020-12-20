import org.junit.jupiter.api.Test;
import org.shumakriss.myapp.updates.Version0001Update;
import org.shumakriss.updates.AwsClientContext;
import org.shumakriss.updates.Update;

public class Version_0_0_1_UpdateTest {

    @Test
    public void testDeploy() {
        AwsClientContext awsClientContext = null; // TODO: Mock this
        Update udpate = new Version0001Update(awsClientContext);
    }

}
