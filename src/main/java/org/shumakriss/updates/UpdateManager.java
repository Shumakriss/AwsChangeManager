package org.shumakriss.updates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateManager {

    List<Update> updateList = new ArrayList<>();
    Map<String, Object> currentManifest = new HashMap<>();


    public void add(Update update) {
        updateList.add(update);
    }

    public void deploy() {
        for (Update update : updateList){
            if (!update.preDeploy(currentManifest)) {
                currentManifest = update.updateManifest(currentManifest);
            } else {
                update.deploy(currentManifest);
                currentManifest = update.updateManifest(currentManifest);
                update.postDeploy(currentManifest);
            }
        }
    }

}
