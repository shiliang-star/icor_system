package com.shiliang.icor.test.ThreadStudy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author sl
 * @Date 2021/3/20 13:36
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    private int id;
    private String username;
    private int age;
}
