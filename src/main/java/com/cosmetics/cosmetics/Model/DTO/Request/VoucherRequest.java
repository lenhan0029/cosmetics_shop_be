package com.cosmetics.cosmetics.Model.DTO.Request;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import com.cosmetics.cosmetics.Model.Entity.Vourcher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherRequest {

    private Integer id;
    private String name;
    private String code;
    private String image;
    private Date startedDate;
    private Date expiredDate;
    private Integer percentage;
	private Integer quantity;
	private Boolean status;
	private Integer condition;

    public Vourcher toVoucher() {
        Vourcher voucher = new Vourcher();
        voucher.setName(this.getName());
        voucher.setCode(this.getCode());
        voucher.setImage(this.getImage());
        voucher.setStartedDate(this.getStartedDate());
        voucher.setExpiredDate(this.getExpiredDate());
        voucher.setPercentage(this.getPercentage());
        voucher.setQuantity(this.getQuantity());
        voucher.setStatus(this.getStatus());
        voucher.setCondition(this.getCondition());

        return voucher;
    }
}