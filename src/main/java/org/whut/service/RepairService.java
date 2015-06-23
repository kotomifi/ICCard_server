package org.whut.service;

import org.whut.entity.Repair;
import org.whut.mapper.RepairMapper;

import java.util.List;

/**
 * Created by baisu on 15-5-12.
 */
public class RepairService {
    private RepairMapper mapper;

    public Repair findById(long id) {
        return mapper.findById(id);
    }

    public List<Repair> getRepairs() {
        return mapper.getRepairs();
    }
}
