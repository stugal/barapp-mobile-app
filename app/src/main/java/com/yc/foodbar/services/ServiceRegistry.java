package com.yc.foodbar.services;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {
    /**
     * Service registry
     */
    private static final Map<Class, AbstractFoodBarService> serviceRegistry = new HashMap<Class, AbstractFoodBarService>();

    private ServiceRegistry(){}

    /**
     * Instantiates the registry, initializes and registers the configuration service if not already initialized/registered
     * @param ctx
     */
    public static void INSTANTIATE(Context ctx) {
        serviceRegistry.put(RemotingService.class, new RemotingService());
        serviceRegistry.put(SessionService.class, new SessionService());
    }

    public static <T> T getService(Class serviceClass) {
        return (T) serviceRegistry.get(serviceClass);
    }
}
