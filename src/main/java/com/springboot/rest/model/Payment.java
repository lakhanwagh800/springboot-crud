package com.springboot.rest.model;



public class Payment {
	public enum paymentCodes {
		
		AL("0", "On time payment"),
		DELAYED("1-89","Delayed"),
		OVERDUE("90+","Overdue Additional Defination"),
		ON_TIME_PAYMENT("000","on time payment (it means clear track record, no delay in payment of EMI)"),
		DPD_PAYMENT("039","DPD (It means borrower made late payment by 39 days, consider number as late payment for howmany days)"),
		NO_RECORD("XXX","No record (Show as blank)"),
		SMA("SMA","Special mention account (Consider as 89) SUB - Sub standard (Consider as 90+)"),
		STD_NINTYDYAS("STD","within 90 days (Consider as 0 )"),
		DBT("DBT","doubtful (Consider as 90+)"),
		LSS("LSS","Loss(Consider as 90+)");
		
		private String id;
		private String value;


		
		private paymentCodes(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public static paymentCodes fromValue(String v) {
			for (paymentCodes c : paymentCodes.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static paymentCodes fromId(Long v) {
			for (paymentCodes c : paymentCodes.values()) {
				if (c.id.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v != null ? v.toString() : null);
		}
	}
}
