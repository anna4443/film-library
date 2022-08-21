package com.as.bl;

import com.as.dal.IRepository;
import com.as.dal.RepositoryFactory;

public class HandlerBase {
    final IRepository repository;
    public HandlerBase() {
        repository = RepositoryFactory.getRepository();
    }
}
