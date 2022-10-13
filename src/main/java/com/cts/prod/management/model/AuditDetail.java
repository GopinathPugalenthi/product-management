package com.cts.prod.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuditDetail {
	private String action;
	private String AuditMessage;
	private String auctionEntity;
}
