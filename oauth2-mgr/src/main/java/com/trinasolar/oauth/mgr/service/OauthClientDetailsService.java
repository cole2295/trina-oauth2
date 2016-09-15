package com.trinasolar.oauth.mgr.service;

import com.trinasolar.oauth.mgr.domain.OauthClientDetails;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhm on 16-9-14.
 */
@Mapper
@Service("oauthClientDetailsService")
public interface OauthClientDetailsService {

    @Select("select * from oauth_client_details " +
            "where client_id like #{clientid} limit #{start},#{size}")
    public List<OauthClientDetails> findByCond(@Param("clientid") String clientid,@Param("start") int start,@Param("size") int size);


    @Select("select count(client_id) from oauth_client_details " +
            "where client_id like #{clientid}")
    Long findTotalByCond(@Param("clientid") String clientid);


    @Insert("insert into oauth_client_details(client_id,resource_ids,client_name," +
            "client_secret,scope,authorized_grant_types,web_server_redirect_uri," +
            "authorities,additional_information," +
            "autoapprove,access_token_validity,refresh_token_validity) values(" +
            "#{client_id},#{resource_ids},#{client_name},#{client_secret}," +
            "#{scope},#{authorized_grant_types},#{web_server_redirect_uri},#{authorities},#{additional_information}," +
            "#{autoapprove},#{access_token_validity},#{refresh_token_validity})")
    void saveData(OauthClientDetails data);
}
