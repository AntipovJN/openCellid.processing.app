package service;

import dao.OpenCellIdDao;
import entity.OpenCellId;
import factory.DataBasePropertiesReaderFactory;
import factory.OpenCellIdDaoFactory;
import org.apache.log4j.Logger;
import pool.BasicConnectionPool;
import util.DataBasePropertiesReader;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OpenCellIdServiceImpl implements OpenCellIdService {

    private final Logger LOGGER = Logger.getLogger(OpenCellIdServiceImpl.class);

    private BasicConnectionPool basicConnectionPool;
    private OpenCellIdDao openCellIdDao;

    public static OpenCellIdService build() throws SQLException {
        DataBasePropertiesReader propertiesReader = DataBasePropertiesReaderFactory.getInstance();
        BasicConnectionPool basicConnectionPool = BasicConnectionPool.create(
                propertiesReader.getUrl(),
                propertiesReader.getUsername(),
                propertiesReader.getPassword());
        Connection connection = basicConnectionPool.getConnection();
        OpenCellIdDao openCellIdDao = OpenCellIdDaoFactory.getInstance(connection);
        basicConnectionPool.releaseConnection(connection);
        return new OpenCellIdServiceImpl(basicConnectionPool, openCellIdDao);
    }

    public void loadToDb(List<OpenCellId> openCellIdList, String[] filterQueries) {
        Thread thread = new Thread(() -> {
            try {
                long timer = System.currentTimeMillis();
                Connection connection = basicConnectionPool.getConnection();
                List<OpenCellId> openCellIds = enableFilters(openCellIdList.stream(), filterQueries);
                int count = openCellIdDao.addAll(openCellIds, connection);
                basicConnectionPool.releaseConnection(connection);
                LOGGER.info(String.format("Done, %s lines was write.", count));
                if (openCellIds.size() - count > 0) {
                    LOGGER.warn(String.format("%s lines was failed", openCellIds.size() - count));
                }
                LOGGER.info(String.format("%.1g operations/sec",
                        (count / (double) (System.currentTimeMillis() - timer) / 1000)));
            } catch (IndexOutOfBoundsException e) {
                LOGGER.warn("All connections are busy. Try again later");
            }
        });
        thread.start();
    }

    private List<OpenCellId> enableFilters(Stream<OpenCellId> stream, String[] filterQueries) {
        if (!filterQueries[0].isEmpty()) {
            List<Integer> mccList = Arrays.stream(filterQueries[0].split(";"))
                    .map(Integer::valueOf).collect(Collectors.toList());
            stream = mccFilter(stream, mccList);
        }
        if (!filterQueries[1].isEmpty()) {
            List<String> radioList = Arrays.asList(filterQueries[1].split(";"));
            stream = radioFilter(stream, radioList);
        }
        if (!filterQueries[2].isEmpty()) {
            String[] minMax = filterQueries[2].split(";");
            stream = areaFilter(stream, Integer.valueOf(minMax[0]), Integer.valueOf(minMax[1]));
        }
        if (!filterQueries[3].isEmpty()) {
            String[] minMax = filterQueries[3].split(";");
            stream = cellFilter(stream, Integer.valueOf(minMax[0]), Integer.valueOf(minMax[1]));
        }
        if (!filterQueries[4].isEmpty()) {
            stream = rangeFilter(stream, Integer.valueOf(filterQueries[4]));
        }
        if (!filterQueries[5].isEmpty()) {
            String[] fromTo = filterQueries[5].split(";");
            stream = createDateFilter(stream, fromTo[0], fromTo[1]);
        }
        if (!filterQueries[6].isEmpty()) {
            String[] fromTo = filterQueries[6].split(";");
            stream = updateDateFilter(stream, fromTo[0], fromTo[1]);
        }
        return stream.collect(Collectors.toList());
    }

    private Stream<OpenCellId> mccFilter(Stream<OpenCellId> stream, List<Integer> predicates) {
        return stream.filter(a -> predicates.contains(a.getMcc()));
    }

    private Stream<OpenCellId> radioFilter(Stream<OpenCellId> stream, List<String> predicates) {
        return stream.filter(a -> predicates.contains(a.getRadio()));
    }

    private Stream<OpenCellId> areaFilter(Stream<OpenCellId> stream, int min, int max) {
        return stream.filter(a -> min < a.getArea() && a.getArea() < max);
    }

    private Stream<OpenCellId> cellFilter(Stream<OpenCellId> stream, int min, int max) {
        return stream.filter(a -> min < a.getCell() && a.getCell() < max);
    }

    private Stream<OpenCellId> rangeFilter(Stream<OpenCellId> stream, int max) {
        return stream.filter(a -> a.getRange() < max);
    }

    private Stream<OpenCellId> createDateFilter(Stream<OpenCellId> stream, String from, String to) {
        return stream.filter(
                a -> a.getCreated().getTime() * 1000 > Timestamp.valueOf(from).getTime() &&
                        a.getCreated().getTime() * 1000 < Timestamp.valueOf(to).getTime());
    }

    private Stream<OpenCellId> updateDateFilter(Stream<OpenCellId> stream, String from, String to) {
        return stream.filter(
                a -> a.getUpdated().getTime() * 1000 > Timestamp.valueOf(from).getTime() &&
                        a.getUpdated().getTime() * 1000 < Timestamp.valueOf(to).getTime());
    }

    private OpenCellIdServiceImpl(BasicConnectionPool basicConnectionPool,
                                  OpenCellIdDao openCellIdDao) {
        this.basicConnectionPool = basicConnectionPool;
        this.openCellIdDao = openCellIdDao;
    }
}
