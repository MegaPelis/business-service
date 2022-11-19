package com.megapelis.business.model.dto.request.generic;

import lombok.*;

/**
 * Clase {@link RequestProperty}
 *
 * @author yadir.garcia.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestProperty {
    private String name;
    private String value;
}
