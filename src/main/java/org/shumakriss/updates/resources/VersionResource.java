package org.shumakriss.updates.resources;


public class VersionResource {

    SsmStringParameterResource versionParameter;
    String versionKey;
    String versionValue;

    public VersionResource(SsmStringParameterResource versionParameter, String versionKey, String versionValue) {
        this.versionParameter = versionParameter;
        this.versionKey = versionKey;
        this.versionValue = versionValue;
    }

    public boolean isUpgradeable() {
        String deployedValue = versionParameter.getDeployedValue();
        return (deployedValue == null ||
                Integer.parseInt(versionParameter.getDeployedValue()) == (Integer.parseInt(versionValue) - 1));
    }

}
