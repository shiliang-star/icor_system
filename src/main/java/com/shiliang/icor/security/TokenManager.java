package com.shiliang.icor.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shiliang.icor.pojo.entity.UserEntity;
import com.shiliang.icor.service.UserService;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * token管理
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Component
public class TokenManager {

    @Autowired
    private UserService userService;

    private long tokenExpiration = 24*60*60*1000;
    private String tokenSignKey = "123456";

    public String createToken(String username) {
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    public  String getUserFromToken(String token) {
        String user = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return user;
    }

    public void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }

    public  UserEntity getUserInfoByToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token != null && !"".equals(token.trim())) {
            String userName = getUserFromToken(token);
            return userService.getOne(new QueryWrapper<UserEntity>().eq("username", userName));
        }
        return null;
    }

}
