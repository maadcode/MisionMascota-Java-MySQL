
package interfaces;

import java.sql.ResultSet;

public interface ICRUD {
    public void create();
    public ResultSet read();
    public void update(String id);
    public void delete(String id);
}
