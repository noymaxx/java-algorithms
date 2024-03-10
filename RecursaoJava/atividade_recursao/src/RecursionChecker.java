import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
    Classe auxiliar. Por favor, não modifique este código.
*/
public class RecursionChecker {

    private static HashMap<String, ArrayList> mapBehaviour = new HashMap<String, ArrayList>();

    public static void register(String method) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int counter = 0;
        for (StackTraceElement element : stackTrace) {
            //System.out.println(element.getMethodName());
            if (method.equals(element.getMethodName()))
                counter++;
        }
        if (mapBehaviour.containsKey(method)) {
            ArrayList arr = mapBehaviour.get(method);
            arr.add(counter);
        } else {
            ArrayList<Integer> arr = new ArrayList();
            arr.add(counter);
            mapBehaviour.put(method, arr);
        }
    }

    public static void unregister(String method) {
        if (mapBehaviour.containsKey(method))
            mapBehaviour.remove(method);
    }

    public static boolean check(String method, int stackSizeMin, int stackSizeMax) {
        if (mapBehaviour.containsKey(method)) {
            ArrayList arr = mapBehaviour.get(method);
            boolean strictlyIncreasing = true && arr.size() >= 2;
            for (int i = 1; i < arr.size(); i++) {
                if ((int) arr.get(i - 1) >= (int) arr.get(i)) {
                    strictlyIncreasing = false;
                    break;
                }
            }
            if (strictlyIncreasing && arr.size() >= stackSizeMin && arr.size() <= stackSizeMax)
                return true;
        }
        return false;
    }

    public static void print() {
        for (Map.Entry<String, ArrayList> pair : mapBehaviour.entrySet()) {
            System.out.println(pair.getKey());
            System.out.println(pair.getValue());
        }

    }

    public static void main(String args[]) {
        System.out.println("Tudo certo com a classe RecursionChecker!");
    }

}
