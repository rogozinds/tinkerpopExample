import com.orientechnologies.orient.core.id.ORecordId;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;


public interface IPerson extends VertexFrame {

    @Property("firstName")
    public String getFirstName();

    @Property("firstName")
    public void setFirstName(String name);

    @Property("lastName")
    public String getLastName();

    @Property("lastName")
    public void setLastName(String name);

    @Property("idUCCC")
    public String getidUICC();

    @Property("idUCCC")
    public void setidUICC(String id);

    @Property("@rid")
    public ORecordId getId();

    @Property("@Version")
    public Integer getVersion();

    @Property("@Version")
    public void setVersion(Integer version);
}
