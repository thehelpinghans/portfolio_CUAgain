package com.green.api.bus.dto;

import lombok.Data;

import java.util.List;

@Data
public class MsgBody<T> {
	private List<T> itemList;
}
