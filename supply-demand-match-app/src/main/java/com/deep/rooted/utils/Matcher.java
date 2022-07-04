package com.deep.rooted.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import com.deep.rooted.beans.SupplyAndDemand;
import com.deep.rooted.beans.Trade;

/**
 * The Mather class matches the supply and demand and generates the matching trade
 * @author Dhamoiran D
 *
 */
public class Matcher {

	List<SupplyAndDemand> supplyList = new LinkedList<>();
	List<SupplyAndDemand> demandList = new LinkedList<>();
	List<Trade> tradeList = new ArrayList<>();

	public List<Trade> matchingTrade(Set<SupplyAndDemand> all, Map<SupplyAndDemand, Integer> ledger) {

		// create supply list from the entire supply demand set and sorts time and lower supply price
		supplyList = all.stream().filter(s -> s.getOrerId().matches("^s[0-9]*"))
				.sorted(Comparator.comparing(SupplyAndDemand::getTime).thenComparing(SupplyAndDemand::getPrice))
				.collect(Collectors.toList());
		
		// create demand list from the entire supply demand set and sorts time and higher demand price
		demandList = all.stream().filter(s -> s.getOrerId().matches("^d[0-9]*"))
				.sorted(Comparator.comparing(SupplyAndDemand::getTime)).collect(Collectors.toList());
		
		// Compare demand and supply and generate trade list
		for (SupplyAndDemand demand : demandList) {
			int requiredQty = 0;
			int availableQty = 0;
			
			for (SupplyAndDemand supply : supplyList) {
				int supplyQty = ledger.get(supply);
				int demandQty = ledger.get(demand);
				
				//checks lower supply price than demand price and product wise compare
				if(supply.getPrice() <= demand.getPrice() && supply.getProduct().equals(demand.getProduct()) && supplyQty !=0) {
					
					int tradeQty =0;
					
					if(supplyQty >= demandQty) {
						tradeQty = demandQty;
						availableQty = supplyQty - demandQty; // remaining qty available on supply
						requiredQty = 0; // Demand is fulfilled hence requiredQty is zero;
						ledger.put(supply, availableQty); //ledger update
						ledger.put(demand, requiredQty);  //ledger update
					}else {
						
						tradeQty = supplyQty;
						availableQty = 0;  // supplied all qty to demand hence available qty is zero;
						requiredQty = demandQty - supplyQty; // further required qty on demand
						ledger.put(demand, requiredQty); //ledger update
						ledger.put(supply, availableQty); //ledger update				
						
					}
					
					// create matching trade list
					Trade trade = new Trade(demand.getOrerId(), supply.getOrerId(), supply.getProduct(), supply.getPrice().toString(),String.valueOf(tradeQty));
					tradeList.add(trade);
					
					// skip compare once demand fulfilled
					if(requiredQty==0) {
						break;
					}
				}
				
			}
			
		}
		
		
		return tradeList;
	}

}
