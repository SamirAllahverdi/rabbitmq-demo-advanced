package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InvoiceCreatedMessage extends Invoice{

	private double amount;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;

	private String currency;

}
