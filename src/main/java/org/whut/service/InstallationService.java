package org.whut.service;

import org.whut.entity.Installation;
import org.whut.mapper.InstallationMapper;

import java.util.List;

/**
 * Created by baisu on 15-5-12.
 */
public class InstallationService {
    private InstallationMapper mapper;

    public Installation findById(long id) {
        return mapper.findById(id);
    }

    public List<Installation> getInstallations() {
        return mapper.getInstallations();
    }
}
