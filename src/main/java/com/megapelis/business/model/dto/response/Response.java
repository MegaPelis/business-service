package com.megapelis.business.model.dto.response;

import lombok.*;

/**
 * Clase {@link Response}
 * @author yadir.garcia.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String traceId;
    private String dateTime;
    private String service;
    private String operation;
    private ResponseStatus status;
    private Object data;
}