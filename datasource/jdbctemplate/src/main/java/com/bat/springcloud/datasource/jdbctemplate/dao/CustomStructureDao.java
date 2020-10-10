package com.bat.springcloud.datasource.jdbctemplate.dao;

import com.bat.springcloud.bussiness.bean.po.CustomStructurePO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link com.bat.springcloud.bussiness.bean.dto.CustomStructureDTO} 持久层
 *
 * @author ZhengYu
 * @version 1.0 2020/8/19 9:51
 **/
public class CustomStructureDao {

    private JdbcTemplate jdbcTemplate;

    private TransactionTemplate transactionTemplate;

    public CustomStructureDao(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    public Integer insertCustomStructure() {
        List<Object[]> batchArgs = new ArrayList<Object[]>(2) {{
            add(new Object[]{100L, "user_100", 100});
            add(new Object[]{200L, "user_200", 200});
        }};

        return transactionTemplate.execute(status -> {
            Object savepoint = status.createSavepoint();
            try {
                int[] result = jdbcTemplate.batchUpdate("INSERT INTO `t_custom_structure` (`id`, `name`, `age`) VALUES (?, ?, ?)", batchArgs);
                return result.length;
            } catch (Exception e) {
                status.rollbackToSavepoint(savepoint);
                e.printStackTrace();
            }
            return -1;
        });
    }

    /**
     * 根据ID查询
     *
     * @param id id
     * @author ZhengYu
     */
    public CustomStructurePO queryCustomStructureById(Long id) {
        String sql = "SELECT id,`name`,age FROM t_custom_structure WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            CustomStructurePO customStructurePO = new CustomStructurePO();
            customStructurePO.setId(rs.getLong("id"));
            customStructurePO.setName(rs.getString("name"));
            customStructurePO.setAge(rs.getInt("age"));
            return customStructurePO;
        }, id);
    }
}
