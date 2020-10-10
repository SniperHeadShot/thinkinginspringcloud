package com.bat.springcloud.datasource.mybatis.service.impl;

import com.bat.springcloud.bussiness.bean.po.CustomStructurePO;
import com.bat.springcloud.datasource.mybatis.dao.CustomStructureDao;
import com.bat.springcloud.datasource.mybatis.service.CustomStructureService;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Random;

/**
 * {@link CustomStructurePO} Service
 *
 * @author ZhengYu
 * @version 1.0 2020/8/20 20:03
 **/
public class CustomStructureServiceImpl implements CustomStructureService {

    private CustomStructureDao customStructureDao;

    private PlatformTransactionManager platformTransactionManager;

    public CustomStructureServiceImpl(CustomStructureDao customStructureDao, PlatformTransactionManager platformTransactionManager) {
        this.customStructureDao = customStructureDao;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Override
    public Long insertCustomStructure() {
        long id = new Random().nextInt(100);
        customStructureDao.insertCustomStructure(new CustomStructurePO(id, "user_" + id, 20));
        return id;
    }

    @Override
    public CustomStructurePO queryCustomStructureById(Long id) {
        return customStructureDao.queryCustomStructureById(id);
    }
}
