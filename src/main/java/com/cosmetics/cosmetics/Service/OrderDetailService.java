package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

public interface OrderDetailService {

	ResponseEntity<?> getByOrder(int id_order);
}
