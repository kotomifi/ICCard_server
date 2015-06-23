package org.whut.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.entity.Installation;

import java.util.List;

/**
 * Created by baisu on 15-5-12.
 */
public interface InstallationMapper {

    public List<Installation> getInstallations();

    public List<Installation> findByUser();

    public Installation findById(long id);

    public void add(Installation installation);

    public void delete();

    public void update(Installation installation);

    public List<Installation> findByUserName(
            @Param("userName")String userName,
            @Param("postDate")String postDate,
            @Param("isComplete")boolean isComplete);

}
