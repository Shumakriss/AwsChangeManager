package org.shumakriss.changesets;

import java.util.HashMap;
import java.util.Map;

public class Context {
    Map<String, Trackable> trackableMap = new HashMap<String, Trackable>();

    public boolean isDeployed() {
        for (Trackable trackable : trackableMap.values()) {
            if (trackable instanceof Deployable) {
                if (!((Deployable) trackable).isDeployed()) {
                    return false;
                }
            }
        }
        return true;
    }


}
