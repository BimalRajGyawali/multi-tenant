package com.personal.multidb.service;

import com.personal.multidb.dto.AccountMapper;

import java.util.List;

/**
 * @author bimal on 10/4/21
 * @project multi-db
 */
public interface ClientService {
    List<AccountMapper> getAccounts(int clientId);
}
