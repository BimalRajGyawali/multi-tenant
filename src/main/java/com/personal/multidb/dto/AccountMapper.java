package com.personal.multidb.dto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author bimal on 10/5/21
 * @project multi-db
 */
@Mapper
public interface AccountMapper {

    @Select("select id, accnumber as accountNumber from account")
    List<Account> getAccounts();

    List<Account> getAccountsXML();

    @Select("select id, accnumber as accountNumber from account where id=1")
    Account getOne();

}
