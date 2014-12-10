import com.orientechnologies.orient.core.id.ORecordId;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;


public interface IProject extends VertexFrame {

    @Property("name")
    String getName();

    @Property("name")
    void getName(String name);

    @Property("budget")
    Integer getBudget();

    @Property("budget")
    void setBudget(Integer budget);

    @Property("@rid")
    public ORecordId getId();

    @Property("@Version")
    public Integer getVersion();

    @Property("@Version")
    public void setVersion(Integer version);
}
