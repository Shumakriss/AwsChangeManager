package org.shumakriss.changesets;

import java.util.ArrayList;
import java.util.List;

public class App {
    VersionStore versionStore;
    String deployedVersionId;
    List<Version> versionList = new ArrayList<Version>();
    List<ChangeSet> changeSetList = new ArrayList<ChangeSet>();

    public App(VersionStore versionStore) {
        this.versionStore = versionStore;
        this.deployedVersionId = versionStore.getDeployedVersion();
    }

    public void addVersionChangeSet(Version version, ChangeSet changeSet) {
        this.versionList.add(version);
        this.changeSetList.add(changeSet);
    }

    public static Context getContext(List<ChangeSet> changeSetList) {
        ContextManager contextManager = new ContextManager();
        for (ChangeSet changeSet : changeSetList.toArray(new ChangeSet[0]))
            contextManager.addChangeSet(changeSet);

        return contextManager.getContext();
    }

    public void update() {
        boolean firstMatch = true;
        Context deployedContext = null;

        for (int i = 0; i < versionList.size(); i++) {

            if (deployedVersionId != null) {
                if (deployedVersionId.equals(versionList.get(i).getVersionId())) {
                    deployedContext = getContext(changeSetList.subList(0, i));
                    if (firstMatch) {
                        if (!isDeployed(deployedContext, changeSetList.get(i)))
                            return;
                        firstMatch = false;
                    }
                    continue;
                }
            }

            deployedVersionId = versionList.get(i).getVersionId();
            changeSetList.get(i).deploy(deployedContext);
            versionStore.saveVersion(versionList.get(i));

        }

    }

    public static boolean isDeployed(Context context, ChangeSet changeSet) {
        return context != null && context.isDeployed() && changeSet.isDeployed(context);
    }

}
