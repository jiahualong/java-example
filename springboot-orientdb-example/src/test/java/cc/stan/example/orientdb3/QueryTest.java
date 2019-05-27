package cc.stan.example.orientdb3;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.ODatabaseType;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class QueryTest {


    /**
     * OrientDBConfig defaultConfig username and pass word is admin
     * ref https://orientdb.com/docs/3.0.x/java/Document-API-Database.html
     */
    @Test
    public void queryInEmbedded() {

        OrientDB orientDB = new OrientDB("embedded:", OrientDBConfig.defaultConfig());
        orientDB.create("test", ODatabaseType.MEMORY);
        ODatabaseSession session = orientDB.open("test", "admin", "admin");
        session.save(session.newVertex("v"));
        OResultSet res = session.execute("gremlin", "g.V()", new HashMap<>());
        assertTrue(res.hasNext());
        session.close();
        orientDB.close();

    }


    /**
     * Remote OrientDB
     * --------------------------
     * version is 3.0.18
     * ip is 127.0.0.1
     * port is 2424
     * database is demodb
     * username&password is root
     * --------------------------
     */
    @Test
    public void queryRemote() {

        /* Open Remote OrientDB3 */
        OrientDB orientDB = new OrientDB("remote:127.0.0.1:2424", OrientDBConfig.defaultConfig());
        ODatabaseSession session = orientDB.open("demodb", "root", "root");

        /* execute Gremlin Language */
        OResultSet gremlinResult = session.execute("gremlin", "g.V()", Collections.emptyMap());
        assertTrue(gremlinResult.stream().count() > 0);

        /* execute SQL Language */
        OResultSet sqlResult = session.execute("sql", "select from v", Collections.emptyMap());
        assertTrue(sqlResult.stream().count() > 0);

        /* Close DB */
        session.close();
        orientDB.close();

    }

    @Test
    public void queryByGermlin() {


    }
}
