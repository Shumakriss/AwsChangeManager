package updates;

public class ExampleResource implements Identifiable, Deployable, Validatable {


    @Override
    public void deploy() {

    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void getId() {

    }
}
