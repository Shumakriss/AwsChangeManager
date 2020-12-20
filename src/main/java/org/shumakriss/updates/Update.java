package org.shumakriss.updates;


import java.util.Map;

public abstract class Update {

    static void validateManifest(Map<String, Object> manifest) {
        for (Object object : manifest.values())
            if (object instanceof Validatable)
                if (!((Validatable) object).isValid())
                    return;
        return;
    }

    void preDeploy(Map<String, Object> manifest) {
        validateManifest(manifest);
    }

    void postDeploy(Map<String, Object> manifest) {
        validateManifest(manifest);
    }

    abstract void deploy(Map<String, Object> manifest);

    abstract Map<String, Object> updateManifest(Map<String, Object> manifest);

}
