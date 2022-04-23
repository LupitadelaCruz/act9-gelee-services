package com.gelee.api.dto.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase dto  MessageResponse
 * 
 * @author Guadalupe de la Cruz Xalocan
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {

	  private String message;
}
