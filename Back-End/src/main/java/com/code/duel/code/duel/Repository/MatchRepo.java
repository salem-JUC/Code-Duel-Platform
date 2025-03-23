package com.code.duel.code.duel.Repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MatchRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;


}