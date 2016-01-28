package experiment;


public class DealerTransaction {

	private String currencyId;
	private double exchangeUnit;
	private double exchangeRate;
	
	public DealerTransaction(String currencyId,
			int exchangeUnit,double exchangeRate){
		setCurrencyId(currencyId);
		setExchangeUnit(exchangeUnit);
		setExchangeRate(exchangeRate);
	}
	

	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public double getExchangeUnit() {
		return exchangeUnit;
	}
	public void setExchangeUnit(double exchangeUnit) {
		this.exchangeUnit = exchangeUnit;
	}
	public double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

}
