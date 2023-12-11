package com.leikooo.design.adapter;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public interface Login3rdTarget {

    String loginByGitee(String code, String state);

    String loginByGithub(String... parameters);

    String loginByWeChat(String... parameters);

}
