package com.basic.springboot.service;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.gson.Gson;

class HoldingResponse {

	int page;
	int recordsPerPage;
	int totalRecords;
	String nextPage;
	Data[] data;

}

class Data {
	String security;
	int quantity;
	double price;
	
	
}

//class HoldingData {
//	// String security;
//	int quantity;
//	double price;
//}

public class HoldingsServiceImple {

	// Fetch List of Securities
	// Rest Client for fetching securities from Holding service
	public void holdingsValue() {
		String json = "";
		Gson gson = new Gson();
		HoldingResponse res = gson.fromJson(json, HoldingResponse.class);
		int totalPages = res.totalRecords;
		//int records = res.recordsPerPage;
		Data[] datas = res.data;
		// Store the stocks in Concurrent Map
		ConcurrentMap<String, Data> holdingsStocks = new ConcurrentHashMap<>();
		
	//	for (int i = 0; i < datas.length; i++) {
		for( Data data: datas) {
			//Data data = datas[i];
			//HoldingData hData = new HoldingData();
			//hData.security =
			holdingsStocks.put(data.security, data);
		}

		// Iterate totalPages times and fetch all holding securities where url =
		// res.nextPage;
		// Store

		
		
		// Fetch Price for each Stock.
		for(Entry<String, Data> entry: holdingsStocks.entrySet()) {
			String security = entry.getKey();
			// Get the price for the security
		//	double price = securityPrice(security);
			Data data = entry.getValue();
			data.price = (Double) null; //price;
		}
//		holdingsStocks.streams(
//				
//				).map(data -> data.quantity * data.price).filter(data -> data.portfolio.equals).
//		
		
	}
	
	public Map<String, Integer> securityPrice(String[] security) {
		// Hit the Price Service and Get all Security process.
		// Lookup for the Price of given security and return. 
		return null;
	}
}
