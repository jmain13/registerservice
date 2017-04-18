package edu.uark.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum TransactionClassification {
	NOT_DEFINED(0),
	RETURN(1),
	SALE(2);
	
	public int getValue() {
		return value;
	}

	public static TransactionClassification map(int key) {
		if (valueMap == null) {
			valueMap = new HashMap<Integer, TransactionClassification>();

			for (TransactionClassification status : TransactionClassification.values()) {
				valueMap.put(status.getValue(), status);
			}
		}
		
		return (valueMap.containsKey(key) ? valueMap.get(key) : TransactionClassification.NOT_DEFINED);
	}
	
	private int value;

	private static Map<Integer, TransactionClassification> valueMap = null;

	private TransactionClassification(int value) {
		this.value = value;
	}
}
