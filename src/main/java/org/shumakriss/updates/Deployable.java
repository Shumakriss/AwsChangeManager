package org.shumakriss.updates;

import java.util.Map;

public interface Deployable {
    void deploy(Map<String, Object> manifest);
}
