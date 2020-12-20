package updates;

import java.util.Map;
import java.util.jar.Manifest;

public interface Deployable {
    void deploy(Map<String, Object> manifest);
}
