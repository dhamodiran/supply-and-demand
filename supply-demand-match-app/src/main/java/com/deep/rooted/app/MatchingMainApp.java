package com.deep.rooted.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.deep.rooted.beans.SupplyAndDemand;
import com.deep.rooted.beans.Trade;
import com.deep.rooted.utils.FileOperations;
import com.deep.rooted.utils.Matcher;

public class MatchingMainApp {

	public static void main(String[] args) {

		System.out.println("Supply and Demand Matching Appliation ");

		// track supply and demand quantities
		Map<SupplyAndDemand, Integer> ledger = new HashMap<>();
		
		//Reading input file
		FileOperations fileOps = new FileOperations();
		Set<SupplyAndDemand> allItems = fileOps.readFile();

		System.out.println("-----------------------input list----------------");
		
		//print input list in console and initialize ledger
		allItems.forEach(a -> {
			ledger.put(a, a.getQuantity());
			System.out.println(a.getOrerId() + " " + a.getTime() + " " + a.getProduct() + " " + a.getPrice() + "/kg "
					+ a.getQuantity() + "kg");

		});
		
		//Matching the supply and demand to create a trade
		Matcher match = new Matcher();
		List<Trade> outputList = match.matchingTrade(allItems, ledger);

		System.out.println("-----------------------output list----------------");
		//print output in Console
		outputList.forEach(t -> System.out.print(t));
		
		//write matched trade in a file
		fileOps.writeFile(outputList);

		System.out.println("-----------------------ledger---------------------");
		//print ledger details 
		ledger.forEach((k, v) -> System.out.println(k.getOrerId() + " " + k.getProduct() + " " + v.toString() + "kg"));

	}

}
