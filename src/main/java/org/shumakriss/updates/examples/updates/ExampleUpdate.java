package org.shumakriss.updates.examples.updates;

import org.shumakriss.updates.GenericUpdate;
import org.shumakriss.updates.examples.resources.ExampleResource;

public class ExampleUpdate extends GenericUpdate {

    public ExampleUpdate() {
        addResource(new ExampleResource());
    }
}
