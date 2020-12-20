package updates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateA extends Update implements Deployable, Reversible, Validatable{

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
    public void deploy(Map<String, Object> manifest) {
        for(Object resource : resources)
            if (resource instanceof Deployable)
                ((Deployable) resource).deploy(manifest);
    }

    @Override
    public void reverse() {
        for(Object resource : resources)
            if (resource instanceof Reversible)
                ((Reversible) resource).reverse();
        // TODO: reset manifest?
    }

    @Override
    public boolean isValid() {
        // TODO: Do deep validation
        return false;
    }
}
