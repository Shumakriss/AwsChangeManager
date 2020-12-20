package org.shumakriss.updates.examples.resources;

import org.shumakriss.updates.Deployable;
import org.shumakriss.updates.Identifiable;
import org.shumakriss.updates.Reversible;
import org.shumakriss.updates.Validatable;

import java.util.Map;

public class ExampleResource implements Identifiable, Deployable, Validatable, Reversible {


    @Override
    public void deploy(Map<String, Object> manifest) {

    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getId() {
        return "foo";
    }


    @Override
    public void reverse() {

    }
}
