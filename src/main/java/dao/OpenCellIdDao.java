package dao;

import entity.OpenCellId;

import java.sql.Connection;
import java.util.List;

public interface OpenCellIdDao {

    List<OpenCellId> getAll(Connection connection);

    int addAll(List<OpenCellId> list, Connection connection);

}
