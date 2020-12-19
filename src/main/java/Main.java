import updates.*;

public class Main {

    public static void main(String[] args) {
        UpdateManager updateManager = new UpdateManager();
        updateManager.add(new UpdateA());
        updateManager.deploy();
    }
}