package org.shumakriss.changesets;

public class ContextManager {

    Context context = new Context();

    public void addChangeSet(ChangeSet changeSet) {
        for (Trackable trackable : changeSet.updated)
            context.trackableMap.put(trackable.getId(), trackable);


        for (Trackable trackable : changeSet.removed)
            context.trackableMap.remove(trackable.getId());

    }

    public Context getContext() {
        return context;
    }
}
