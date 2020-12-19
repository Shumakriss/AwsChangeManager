package updates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateA extends Update {

    List<Object> resources = new ArrayList<>();

    public UpdateA(){
        resources.add(new ExampleResource());
    }

    @Override
    Map<String, Object> updateManifest() {
        for(Object resource : resources)
            if (resource instanceof Identifiable)
                manifest.put(((Identifiable) resource).getId(), resource);
        return manifest;
    }

    @Override
    void deploy(Map<String, Object> manifest) {
        for(Object resource : resources)
            if (resource instanceof Deployable)
                ((Deployable) resource).deploy();
    }
}
