package experiment.moneyboxtest.model;

public class Transaction {

	private String type;
	private int exchangeRateId;
	private String currencyId;
	private int exchangeUnit;
	private double exchangeRate;
	private int baseUnit;
	
	public Transaction(String type,int exchangeRateId,String currencyId,
			int exchangeUnit,double exchangeRate,int baseUnit){
		setType(type);
		setExchangeRateId(exchangeRateId);
		setCurrencyId(currencyId);
		setExchangeUnit(exchangeUnit);
		setExchangeRate(exchangeRate);
		setBaseUnit(baseUnit);
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getExchangeRateId() {
		return exchangeRateId;
	}
	public void setExchangeRateId(int exchangeRateId) {
		this.exchangeRateId = exchangeRateId;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public int getExchangeUnit() {
		return exchangeUnit;
	}
	public void setExchangeUnit(int exchangeUnit) {
		this.exchangeUnit = exchangeUnit;
	}
	public double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public int getBaseUnit() {
		return baseUnit;
	}
	public void setBaseUnit(int baseUnit) {
		this.baseUnit = baseUnit;
	}

}
