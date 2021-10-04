package com.personal.multidb.service;

import com.personal.multidb.dto.Account;

import java.util.List;

/**
 * @author bimal on 10/4/21
 * @project multi-db
 */
public interface ClientService {
    List<Account> getAccounts(int clientId);
}
