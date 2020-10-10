package com.bat.springcloud.bussiness.bean.enums;

import com.bat.springcloud.bussiness.bean.annotation.CodeEntity;

/**
 * 通过反射将注解值读出来
 *
 * @author ZhengYu
 * @version 1.0 2019/6/22 16:39
 **/
public interface CodeEnumInterface {

    /**
     * 使用反射获取注解信息
     *
     * @return CodeEntity
     * @author ZhengYu
     */
    default CodeEntity codeEntity() {
        try {
            return this.getClass().getField(this.toString()).getAnnotation(CodeEntity.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 是否成功
     *
     * @return CodeEntity
     * @author ZhengYu
     */
    default boolean success() {
        return this.codeEntity().success();
    }

    /**
     * 状态码
     *
     * @return CodeEntity
     * @author ZhengYu
     */
    default int errCode() {
        return this.codeEntity().errCode();
    }

    /**
     * 提示信息
     *
     * @return CodeEntity
     * @author ZhengYu
     */
    default String msg() {
        return codeEntity().msg();
    }
}
