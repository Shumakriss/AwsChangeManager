package org.shumakriss.updates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateManager {

    List<Update> updateList = new ArrayList<>();
    Map<String, Object> currentManifest = new HashMap<>();


    public void add(Update update) {
        currentManifest = update.updateManifest(currentManifest);
        updateList.add(update);
    }

    public void deploy() {
        if (updateList.size() == 0)
            return;

        Update currentUpdate = updateList.get(updateList.size() - 1);
        currentUpdate.preDeploy(currentManifest);
        currentUpdate.deploy(currentManifest);
        currentUpdate.postDeploy(currentManifest);
    }

}
