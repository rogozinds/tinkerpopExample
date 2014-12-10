import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.tinkerpop.blueprints.Features;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientTransactionalGraph;
import com.tinkerpop.frames.FramedGraph;
import com.tinkerpop.frames.FramedGraphFactory;


public class MainClass {

    private static final String DB_PATH = "local:c:/tmpMain2";
    private FramedGraphFactory factory;
    private FramedGraph<OrientTransactionalGraph> framedGraph;
    private OrientTransactionalGraph graph;

    public static void main(String[] args) throws IOException {
        MainClass mainClass = new MainClass();
        PersonDAO some = mainClass.addPerson("1", "Bobby", "BobbyAddedByMEthod22");
        mainClass.createVertecies();
        System.out.println(some.getId() + "+++++");
        some.setLastName("!!!!!!!!!!!!!!");
        mainClass.updatePeson(some);
        System.out.println("Db created");
        System.out.println(mainClass.getPersons());
        System.in.read();
    }

    public FramedGraph<OrientTransactionalGraph> getFramedGraphInstance() {
        if (factory == null) {
            factory = new FramedGraphFactory();
        }
        this.graph = new OrientTransactionalGraph(DB_PATH) {
            public Features getFeatures() {
                return null;
            }
        };
        return factory.create(graph);
    }

    public void createVertecies() {
        try {
            framedGraph = getFramedGraphInstance();
            OClass personClass = framedGraph.getBaseGraph().createVertexType("IPerson");
            Vertex vPerson = graph.addVertex("class:IPerson");
            vPerson.setProperty("firstName", "John");
            vPerson.setProperty("lastName", "Smith");
            IPerson person = framedGraph.frame(vPerson, IPerson.class);
            person.setFirstName("Bobby");
            vPerson = graph.addVertex("class:IPerson");
            vPerson.setProperty("firstName", "Mikey");
            vPerson.setProperty("lastName", "Black");
        } finally {
            graph.shutdown();
        }
    }

    //    public IPerson addPerson(PersonDAO p) {
    //        IPerson person;
    //        try {
    //            framedGraph = getFramedGraphInstance();
    //            person = framedGraph.addVertex(null, IPerson.class);
    //            person.setFirstName(p.getFirstName());
    //            person.setLastName(p.getLastName());
    //            framedGraph.getBaseGraph().commit();
    //        } finally {
    //            graph.shutdown();
    //        }
    //        return person;
    //    }

    public void updatePeson(PersonDAO newPerson) {
        try {
            framedGraph = getFramedGraphInstance();
            IPerson pers = framedGraph.getVertex(newPerson.getId(), IPerson.class);
            pers.setFirstName(newPerson.getFirstName());
            pers.setLastName(newPerson.getLastName());
            framedGraph.getBaseGraph().commit();
        } finally {
            graph.shutdown();
        }
    }

    public PersonDAO addPerson(String id, String firstName, String lastName) {
        IPerson person;
        try {
            framedGraph = getFramedGraphInstance();
            person = framedGraph.addVertex(null, IPerson.class);
            person.setFirstName(firstName);
            person.setLastName(lastName);
            framedGraph.getBaseGraph().commit();

        } finally {
            graph.shutdown();
        }

        return new PersonDAO(person.getId().toString(), person.getFirstName(), person.getLastName());
    }

    public List<String> getPersons() {
        List<String> names;
        try {
            framedGraph = getFramedGraphInstance();
            names = new ArrayList<String>();
            Iterator<IPerson> bobies = framedGraph.getVertices("@class", "IPerson", IPerson.class).iterator();
            while (bobies.hasNext()) {
                IPerson p = bobies.next();
                names.add(p.getFirstName() + " " + p.getLastName());
                System.out.println("----" + " V: " + p.getVersion() + " " + p.getFirstName() + " " + p.getLastName());

            }
        } finally {
            graph.shutdown();
        }
        //        framedGraph.
        //        Iterator<Vertex> it = graph.getVertices("firstName", "Mikey").iterator();
        //        
        //        graph.getVerticiesOfclass
        //        while (it.hasNext()) {
        //            Vertex v = it.next();
        //            String name = v.getProperty("lastName");
        //            names.add(name);
        //        }
        return names;
    }
}
