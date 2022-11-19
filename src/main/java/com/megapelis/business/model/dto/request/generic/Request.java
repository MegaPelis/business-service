package com.megapelis.business.model.dto.request.generic;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * Clase {@link Request}
 * @author yadir.garcia.
 */
@Getter
@Setter
public class Request {
    private String traceId;
    private String dateTime;
    private String service;
    private String operation;
    private List<RequestProperty> properties;
    private Object data;
}
