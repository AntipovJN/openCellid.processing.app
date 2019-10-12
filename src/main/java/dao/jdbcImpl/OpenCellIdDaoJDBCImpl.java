package dao.jdbcImpl;

import dao.OpenCellIdDao;
import entity.OpenCellId;
import org.apache.log4j.Logger;
import service.OpenCellIdServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OpenCellIdDaoJDBCImpl implements OpenCellIdDao {

    private Logger logger = Logger.getLogger(OpenCellIdServiceImpl.class);

    public OpenCellIdDaoJDBCImpl(Connection connection) throws SQLException {
        createTable(connection);
    }

    @Override
    public List<OpenCellId> getAll(Connection connection) {
        List<OpenCellId> openCellIds = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM opencellid");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                getOpenCellIdFromResultSet(resultSet).ifPresent(openCellIds::add);
            }
        } catch (SQLException e) {
            logger.error("Failed SELECT from DB", e);
        }
        return openCellIds;
    }

    @Override
    public synchronized int addAll(List<OpenCellId> list, Connection connection) {
        int successCounter = 0;
        PreparedStatement preparedStatement;
        for (OpenCellId openCellId : list) {
            try {
                preparedStatement = connection.prepareStatement("INSERT INTO opencellid " +
                        "(radio,mcc,net,area,cell,unit,lon,lat,range,samples," +
                        "changeable,created,updated,averagesignal) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, openCellId.getRadio());
                preparedStatement.setInt(2, openCellId.getMcc());
                preparedStatement.setInt(3, openCellId.getNet());
                preparedStatement.setInt(4, openCellId.getArea());
                preparedStatement.setInt(5, openCellId.getCell());
                preparedStatement.setInt(6, openCellId.getUnit());
                preparedStatement.setDouble(7, openCellId.getLon());
                preparedStatement.setDouble(8, openCellId.getLat());
                preparedStatement.setInt(9, openCellId.getRange());
                preparedStatement.setInt(10, openCellId.getSamples());
                preparedStatement.setInt(11, openCellId.getChangeable());
                preparedStatement.setLong(12, openCellId.getCreated().getTime());
                preparedStatement.setLong(13, openCellId.getUpdated().getTime());
                preparedStatement.setInt(14, openCellId.getAverageSignal());
                preparedStatement.execute();
                successCounter++;
            } catch (SQLException e) {
                logger.error(String.format("Failed INSERT into DB values (%s)", openCellId), e);
            }
        }
        return successCounter;
    }

    private Optional<OpenCellId> getOpenCellIdFromResultSet(ResultSet resultSet) throws SQLException {
        return Optional.of(new OpenCellId(
                resultSet.getString("radio"),
                resultSet.getInt("mcc"),
                resultSet.getInt("net"),
                resultSet.getInt("area"),
                resultSet.getInt("cell"),
                resultSet.getInt("unit"),
                resultSet.getDouble("lon"),
                resultSet.getDouble("lat"),
                resultSet.getInt("range"),
                resultSet.getInt("samples"),
                resultSet.getInt("changeable"),
                new Timestamp(resultSet.getLong("created")),
                new Timestamp(resultSet.getLong("updated")),
                resultSet.getInt("averageSignal")));
    }

    private void createTable(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS opencellid (" +
                        "  id  bigserial not null" +
                        "  constraint opencellid_pk " +
                        "  primary key," +
                        "  area          integer," +
                        "  averagesignal integer," +
                        "  cell          integer," +
                        "  changeable    integer," +
                        "  created       bigint," +
                        "  lat           double precision," +
                        "  lon           double precision," +
                        "  mcc           integer," +
                        "  net           integer," +
                        "  radio         varchar," +
                        "  samples       integer," +
                        "  unit          integer," +
                        "  updated       bigint," +
                        "  range         integer);" +
                        "alter table opencellid" +
                        "  owner to postgres;");
        statement.execute();
        statement = connection.prepareStatement(
                "CREATE UNIQUE INDEX IF NOT EXISTS opencellid_id_uindex" +
                        " ON opencellid (id) ;");
        statement = connection.prepareStatement(
                "CREATE INDEX IF NOT EXISTS opencell_locate ON " +
                        " opencellid USING btree(lat,lon) ;");
        statement.execute();
    }
}
