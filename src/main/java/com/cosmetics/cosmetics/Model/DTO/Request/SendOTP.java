package com.cosmetics.cosmetics.Model.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SendOTP {

	private String email;
}
