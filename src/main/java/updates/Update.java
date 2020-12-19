package updates;


import java.util.Map;

public abstract class Update {

    Map<String, Object> manifest;

    void initManifest(Map<String, Object> manifest) {
        this.manifest = manifest;
    }

    // Add or remove resources from the manifest based on what you'll be deploying
    abstract Map<String, Object> updateManifest();

    // Perform actual changes listed in updateManifest and whatever else you want to do
    abstract void deploy(Map<String, Object> manifest);

}
