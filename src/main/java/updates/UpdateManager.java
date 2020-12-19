package updates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateManager {

    List<Update> updateList = new ArrayList<>();
    Map<String, Object> currentManifest = new HashMap<>();

    public void add(Update update) {
        update.initManifest(currentManifest);
        currentManifest = update.updateManifest();
        updateList.add(update);
    }

    public void deploy() {
        if (updateList.size() > 0)
            updateList.get(updateList.size() - 1).deploy(currentManifest);
    }

    public boolean validate() {
        for (Object object : currentManifest.values())
            if (object instanceof Validatable)
                if (!((Validatable) object).isValid())
                    return false;
        return true;
    }

}
