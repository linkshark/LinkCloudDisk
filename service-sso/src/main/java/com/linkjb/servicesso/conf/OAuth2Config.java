package com.linkjb.servicesso.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * @ClassName OAuth2Config
 * @Description TODO
 * @Author shark
 * @Data 2020/3/9 13:21
 **/
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsService kiteUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore redisTokenStore;
    @Autowired
    private DataSource dataSource;


    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /**
         * redis token 方式
         */
        endpoints.authenticationManager(authenticationManager)//调用此方法才能支持 password 模式
                .userDetailsService(kiteUserDetailsService)//设置用户验证服务
                .tokenStore(redisTokenStore);//指定 token 的存储方式

    }
//    ClientDetailsServiceConfigurer参数的重写，在这里定义各个端的约束条件。包括
//    ClientId、Client-Secret：这两个参数对应请求端定义的 cleint-id 和 client-secret
//    authorizedGrantTypes 可以包括如下几种设置中的一种或多种：
//    authorization_code：授权码类型。
//    implicit：隐式授权类型。
//    password：资源所有者（即用户）密码类型。
//    client_credentials：客户端凭据（客户端ID以及Key）类型。
//    refresh_token：通过以上授权获得的刷新令牌来获取新的令牌。
//    accessTokenValiditySeconds：token 的有效期
//    scopes：用来限制客户端访问的权限，在换取的 token 的时候会带上 scope 参数，只有在 scopes 定义内的，才可以正常换取 token。
//    上面代码中是使用 inMemory 方式存储的，将配置保存到内存中，相当于硬编码了。正式环境下的做法是持久化到数据库中，比如 mysql 中
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("order-client")
//                .secret(passwordEncoder.encode("order-secret-8888"))
//                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
//                .accessTokenValiditySeconds(3600)
//                .scopes("all")
//                .and()
//                .withClient("user-client")
//                .secret(passwordEncoder.encode("user-secret-8888"))
//                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
//                .accessTokenValiditySeconds(3600)
//                .scopes("all");
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients(); //允许客户端访问 OAuth2 授权接口，否则请求 token 会返回 401
        security.checkTokenAccess("isAuthenticated()");//允许已授权用户访问 checkToken 接口
        security.tokenKeyAccess("isAuthenticated()");//允许已授权用户获取 token 接口
    }
}
