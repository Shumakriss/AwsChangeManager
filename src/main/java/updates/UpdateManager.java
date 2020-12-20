package updates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateManager {

    List<Update> updateList = new ArrayList<>();
    Map<String, Object> currentManifest = new HashMap<>();
    Map<String, Object> lastManifest = new HashMap<>();

    public void add(Update update) {
        lastManifest = currentManifest;
        update.initManifest(currentManifest);
        currentManifest = update.updateManifest();
        updateList.add(update);
    }

    public void deploy() {
        if (updateList.size() == 0)
            return;

        // Global pre-deployment check
        Update currentUpdate = updateList.get(updateList.size() - 1);
        validate(lastManifest);

        // Per-update pre-deployment check
        if (updateList.size() > 1) {
            Update lastUpdate = updateList.get(updateList.size() - 2);
            validate(lastUpdate);
        }

        // Apply changes
        currentUpdate.deploy(currentManifest);

        // Global post-deployment check
        validate(currentManifest);

        // Per-update post-deployment check
        validate(currentUpdate);
    }

    boolean validate(Update update) {
        if (update instanceof Validatable)
            return ((Validatable) updateList.get(updateList.size() - 2)).isValid();
        return true;
    }

    public boolean validate(Map<String, Object> manifest) {
        for (Object object : manifest.values())
            if (object instanceof Validatable)
                if (!((Validatable) object).isValid())
                    return false;
        return true;
    }

}
