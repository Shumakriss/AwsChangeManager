package updates;

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
