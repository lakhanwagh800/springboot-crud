package com.springboot.rest.model;

public class CibilConfigUtils {
	public enum AccountCodes {
		AL("01", "Auto Loan (Personal)"), 
		HL("02", "Housing Loan"),
		PL("03","Property Loan"),
		LAS("04","Loan Against Shares/Securities"),
		PEL("05","Personal Loan"),
		CS("06","Consumer Loan"),
		GL("07","Gold Loan"),
		EL("08","Education Loan"),
		LP("09","Loan to Professional"),
		CC("10","Credit Card"),
		L("11","Leasing"),
		OD("12","Overdraft"),
		TWL("13","Two-wheeler Loan"),
		NFCF("14","(NFCF) Non-Funded Credit Facility"),
		CABD("15","(LABD) Current Loan Against Bank Deposits"),
		FC("16","Fleet Card"),
		CVL("17","Commercial Vehicle Loan"),
		TW("18","Telco – Wireless"),
		TB("19","Telco – Broadband"),
		TL("20","Telco – Landline"),
		SCC("31","Secured Credit Card"),
		UCL("32","Used Car Loan"),
		CEL("33","Construction Equipment Loan"),
		TRL("34","Tractor Loan"),
		CCC("35","Corporate Credit Card"),
		KCC("36","Kisan Credit Card"),
		LCC("37","Loan on Credit Card"),
		PMJDYO("38","Prime Minister Jaan Dhan Yojana - Overdraft"),
		MLSKT("39","Mudra Loans – Shishu / Kishor / Tarun"),
		MBL("40","Microfinance – Business Loan"),
		MPL("41","Microfinance – Personal Loan"),
		MHL("42","Microfinance – Housing Loan"),
		MO("43","Microfinance – Other"),
		PMAY_CLSS("44","Pradhan Mantri Awas Yojana - Credit Link Subsidy Scheme MAY CLSS"),
		BLS("50","Business Loan - Secured"),
		BLG("51","Business Loan - General"),
		BLPS_SB("52","(BLPS-SB) Business Loan – Priority Sector – Small Business"),
		BLPS_AGR("53","(BLPS-AGR) Business Loan – Priority Sector – Agriculture"),
		BLPS_OTH("54","(BLPS-OTH) Business Loan – Priority Sector – Others"),
		BNFCF_GEN("55","(BNFCF-GEN) Business Non-Funded Credit Facility – General"),
		BNFCF_PS_SB("56","(BNFCF-PS-SB) Business Non-Funded Credit Facility – Priority Sector – Small Business"),
		BNFCF_PS_AGR("57","(BNFCF-PS-AGR)  Business Non-Funded Credit Facility – Priority Sector – Agriculture"),
		BNFCF_PS_OTH("58","(BNFCF-PS-OTH)  Business Non-Funded Credit Facility – Priority Sector-Others"),
		BLABD("59","(BLABD) Current Business Loan Against Bank Deposits"),
		BL_U("61","Business Loan - Unsecured"),
		MDR("80","Microfinance Detailed Report (Applicable to Enquiry Purpose only)"),
		SR("81","Summary Report (Applicable to Enquiry Purpose only)"),
		LPFI("88","Locate Plus for Insurance (Applicable to Enquiry Purpose only)"),
		AR("90","Account Review (Applicable to Enquiry Purpose only)"),
		RE("91","Retro Enquiry (Applicable to Enquiry Purpose only)"),
		LPLUS("92","Locate Plus (Applicable to Enquiry Purpose only)"),
		ADL("97","Adviser Liability (Applicable to Enquiry Purpose only)"),
		SECURED("98","Secured (Account Group for Portfolio Review response)"),
		UNSECURED("99","Unsecured (Account Group for Portfolio Review response)"),
		OTHER("00","Other");
		// fs_application_status_master
		private String id;
		private String value;

		private AccountCodes(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getId() {
			return id;
		}	

		public String getValue() {
			return value;
		}

		public static AccountCodes fromValue(String v) {
			for (AccountCodes c : AccountCodes.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static AccountCodes fromId(Long v) {
			for (AccountCodes c : AccountCodes.values()) {
				if (c.id.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v != null ? v.toString() : null);
		}
	}
}
