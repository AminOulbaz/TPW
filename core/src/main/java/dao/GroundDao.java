package dao;

import jakarta.annotation.Resource;
import javax.sql.DataSource;

public abstract class GroundDao {
    @Resource(lookup = "java:comp/env/jdbc/MariaDBDS")
    protected DataSource dataSource;
}
