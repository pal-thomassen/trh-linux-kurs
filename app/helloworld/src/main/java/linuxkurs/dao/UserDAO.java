package linuxkurs.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

public interface UserDAO {
    @SqlUpdate("create table if not exists something (id serial primary key, name varchar(100))")
    void createTable();

    @SqlUpdate("insert into something (name) values (:name)")
    void insert(@Bind("name") String name);

    @SqlQuery("select name from something")
    List<String> findNames();
}
