package com.bat.springcloud.datasource.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Caffeine 本地缓存, 详情参见:
 * https://blog.csdn.net/qq_26680031/article/details/84952060?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
 *
 * @author ZhengYu
 * @version 1.0 2020/6/20 12:12
 **/
public class CaffeineApplication {
    public static void main(String[] args) {
        Cache<String, User> cache = Caffeine.newBuilder().build();

        // 检索一个entry，如果没有则为null
        User zhangsan = cache.getIfPresent("zhangsan");
        System.out.println(zhangsan);

        // 检索一个entry，如果entry为null，则通过key创建一个entry并加入缓存
        User zhangsan1 = cache.get("zhangsan", key -> new User(1L, "zhangsan" + key));
        System.out.println(zhangsan1.toString());

    }

    @Data
    @AllArgsConstructor
    private static class User {

        private Long id;

        private String name;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
