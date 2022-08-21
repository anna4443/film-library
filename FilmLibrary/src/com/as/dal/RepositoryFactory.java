package com.as.dal;

public final class RepositoryFactory {
    public static IRepository getRepository() {
        return new SqlRepository();
    }
}
