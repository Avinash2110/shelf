package com.shelf.payloads;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GraphqlRequestBody {

	private String query;
	private String operationName;
	private Map<String, Object> variables;

}
