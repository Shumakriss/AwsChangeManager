package org.shumakriss.updates;


import java.util.Map;

public abstract class Update {

    public static boolean validateManifest(Map<String, Object> manifest) {
        for (Object object : manifest.values())
            if (object instanceof Validatable)
                if (!((Validatable) object).isValid())
                    return false;
        return true;
    }

    public abstract boolean preDeploy(Map<String, Object> manifest);

    public abstract void postDeploy(Map<String, Object> manifest);

    public abstract void deploy(Map<String, Object> manifest);

    public abstract Map<String, Object> updateManifest(Map<String, Object> manifest);
}
