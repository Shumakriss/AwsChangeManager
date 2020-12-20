package org.shumakriss.updates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class GenericUpdate extends Update implements Deployable, Reversible {

    List<Object> additionalResources = new ArrayList<>();
    List<Object> removedResources = new ArrayList<>();


    @Override
    public void deploy(Map<String, Object> manifest) {
        for (Object resource : additionalResources)
            if (resource instanceof Deployable)
                ((Deployable) resource).deploy(manifest);
        for (Object resource : removedResources)
            if (resource instanceof Reversible)
                ((Reversible) resource).reverse();
    }

    @Override
    public Map<String, Object> updateManifest(Map<String, Object> manifest) {
        for (Object resource : additionalResources)
            if (resource instanceof Identifiable)
                manifest.put(((Identifiable) resource).getId(), resource);
        for (Object resource : removedResources)
            if (resource instanceof Identifiable)
                manifest.remove(((Identifiable) resource).getId());
        return manifest;
    }

    @Override
    public void reverse() {
        for (Object resource : additionalResources)
            if (resource instanceof Reversible)
                ((Reversible) resource).reverse();
        // TODO: reset manifest?
    }

    public void addResource(Identifiable resource) {
        this.additionalResources.add(resource);
    }

    public void removeResource(Identifiable resource) {
        this.removedResources.add(resource);
    }

    public void postDeploy(Map<String, Object> manifest) {
        validateManifest(manifest);
    }
}
