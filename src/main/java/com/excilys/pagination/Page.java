package com.excilys.pagination;

import org.springframework.stereotype.Component;

@Component
public class Page {
	
	private int limite = 10;
	
	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
	
	public int nbPageTotal(int nbComputer) {
		return (int) Math.ceil(((double) nbComputer / (double) this.limite));
	}
	
	public int calculOffset(int page) {
		return page * this.limite - this.limite;
	}
}
