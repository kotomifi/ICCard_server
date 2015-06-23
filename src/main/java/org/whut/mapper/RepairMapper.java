package org.whut.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.entity.Repair;

import java.util.List;

/**
 * Created by baisu on 15-5-12.
 */
public interface RepairMapper {

    public List<Repair> getRepairs();

    public List<Repair> findByUser(@Param("userName") String userName, @Param("isComplete") boolean isComplete);

    public Repair findById(long id);

    public void add(Repair repair);

    public void delete(Repair repair);

    public void update(Repair repair);
}
