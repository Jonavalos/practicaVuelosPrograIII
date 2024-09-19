package pos.logic;
//
import pos.data.Data;
import pos.data.XmlPersister;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    private Data data;

    private Service() {
        try {
            data = XmlPersister.instance().load();
        } catch (Exception e) {
            data = new Data();
        }
    }

    public void stop() {
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}