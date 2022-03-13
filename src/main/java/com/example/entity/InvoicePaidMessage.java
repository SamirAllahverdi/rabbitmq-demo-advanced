package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InvoicePaidMessage extends Invoice{

	private String invoiceNumber;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate paidDate;

	private String paymentNumber;
}
