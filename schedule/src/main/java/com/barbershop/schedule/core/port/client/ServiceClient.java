package com.barbershop.schedule.core.port.client;

import com.barbershop.schedule.core.port.client.response.ServiceResponse;

import java.util.List;

public interface ServiceClient {
    ServiceResponse getServiceById(Integer serviceId);
    boolean servicesExistsById(List<Integer> serviceId);
}
