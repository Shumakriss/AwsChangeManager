package org.shumakriss.changesets;

public interface VersionStore {

    String getDeployedVersion();

    void saveVersion(Version version);
}
