package com.abc.blogger.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class BuzzWord {

	private Set<String> table;

	public Set<String> getTable() {
		return table;
	}

	public BuzzWord() {
		super();
	this.table=new HashSet<String>();
	this.table.add("ico");
	this.table.add("blockchain");
	this.table.add("currency");
	this.table.add("ledger");
	this.table.add("peer");
	this.table.add("decentralized");
	this.table.add("token");
	this.table.add("digital");
	this.table.add("distributed");
	this.table.add("proof");
	this.table.add("work");
	this.table.add("mining");
	this.table.add("fork");
	this.table.add("ripple");
	this.table.add("bitcoin");
	this.table.add("etherium");
	this.table.add("wallet");
	this.table.add("exchange");
	this.table.add("coinbase");
	this.table.add("launch");
	this.table.add("cryptology");
	this.table.add("coin");
	
	}
	
	
}
