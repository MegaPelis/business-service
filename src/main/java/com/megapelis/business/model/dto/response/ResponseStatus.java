package com.megapelis.business.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Clase {@link ResponseStatus}
 * @author yadir.garcia.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatus implements Serializable {
    private String code;
    private String messageBackend;
    private String messageFrontend;
}

