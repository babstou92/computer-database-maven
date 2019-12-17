package com.excilys.pagination;

public class Page {
	
	private int limite = 10;
	private Page() {};
	private static Page page = null;
	public static Page getPage() {
		if(page == null) {
			page = new Page();
		}
		return page;
	}
	
	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
	
	public int nbPageTotal(int nbComputer) {
		return (int) Math.ceil(((double) nbComputer / (double) this.limite));
	}
}
