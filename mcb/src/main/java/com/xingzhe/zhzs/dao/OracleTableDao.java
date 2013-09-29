package com.xingzhe.zhzs.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OracleTableDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<String> getList(){
        return jdbcTemplate.queryForList("select table_name from user_tables", String.class);
    }
    
    public int getCount(String name){
        return (int) jdbcTemplate.queryForObject("select count(*) from "+name+" where rownum=1", Integer.class);
    }
    
    public  void del(String name){
        jdbcTemplate.execute("DROP TABLE "+name+" CASCADE constraints purge");
    }
}
