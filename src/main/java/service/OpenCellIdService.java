package service;

import entity.OpenCellId;

import java.util.List;

public interface OpenCellIdService {

    void loadToDb(List<OpenCellId> openCellIdList, String[] filterQueries);
}
