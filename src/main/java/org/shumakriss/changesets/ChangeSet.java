package org.shumakriss.changesets;

import java.util.ArrayList;
import java.util.List;

public abstract class ChangeSet {

    List<Trackable> updated = new ArrayList<Trackable>();
    List<Trackable> removed = new ArrayList<Trackable>();

    protected abstract void deploy(Context context);

    protected abstract boolean isDeployed(Context context);
}
