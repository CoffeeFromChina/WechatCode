package com.xuuuuu.jwtdemo.Util;

import com.xuuuuu.jwtdemo.pojo.Audience;
import com.xuuuuu.jwtdemo.pojo.User;

import org.apache.logging.log4j.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @Description JWT 工具类
 * @ClassName JwtTokenUtil.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2020/6/27 15:45
 **/
@Component
public class JwtTokenUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
	private static Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);
	public static final String AUTH_HEADER_KEY = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";

	@Autowired
	private Audience audience;

	// 生成用户token,设置token超时时间
	public String createToken(User user) {
		try {
			long nowTime = System.currentTimeMillis();
			Date now = new Date(nowTime);
			//过期时间
			Date expireDate = new Date(nowTime + audience.getExpiresSecond());

			Map<String, Object> mapHeader = new HashMap<>();
			mapHeader.put("alg", "HS256");
			mapHeader.put("typ", "JWT");

			Map<String , Object> mapClaims = new HashMap<>();
			mapClaims.put("id", user.getId());
			mapClaims.put("name", user.getName());
			mapClaims.put("userName", user.getUserName());
			mapClaims.put("role", user.getRole());

			JwtBuilder jwtBuilder = Jwts.builder()
					// Header
					.setHeader(mapHeader)               // 头信息
					// Payload
					.setClaims(mapClaims)               // 私有签名
					.setIssuedAt(now)                   // 签发时间
					.setNotBefore(now)                  // 失效时间
					.setExpiration(expireDate)          // 过期时间
					.setSubject(user.getUserName())     // 主体
					.setAudience(audience.getName())    // 受众(这个JWT的接收对象)
					.setIssuer(audience.getClientId())  // 签发人
					// Signature
					.signWith(SignatureAlgorithm.HS256, // 设置密钥
							DatatypeConverter.parseBase64Binary(audience.getBase64Secret()));

			return jwtBuilder.compact();
		}catch (Exception e){
			return "获取签名失败";
		}
	}

	// 解析 Token
	public Claims parseJWT(String token, String base64Security){
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
					.parseClaimsJws(token)
					.getBody();
			return claims;
		} catch (ExpiredJwtException e) {
			log.error("===== Token过期 =====", e);
			return null;
		} catch (Exception e){
			log.error("===== token解析异常 =====", e);
			return null;
		}
	}

	// 获取用户名称
	public String getUsername(String token, String base64Security){
		return parseJWT(token, base64Security).getSubject();
	}

	// 获取用户Id
	public String getUserId(String token, String base64Security){
		String userId = parseJWT(token, base64Security).get("id", String.class);
		return Base64Util.encode(userId);
	}

	// 检验是否过期
	public boolean isExpiration(String token, String base64Security){
		return parseJWT(token, base64Security).getExpiration().before(new Date());
	}
}
