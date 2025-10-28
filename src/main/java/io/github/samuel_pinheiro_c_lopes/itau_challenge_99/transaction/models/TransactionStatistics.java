package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.models;

public class TransactionStatistics {
	private Integer count;
	private Double sum;
	private Double avg;
	private Double max;
	private Double min;
	
	public TransactionStatistics() {
		this.count = 0;
		this.min = Double.POSITIVE_INFINITY;
		this.max = Double.NEGATIVE_INFINITY;
		this.avg = 0d;
		this.sum = 0d;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSum() {
		return this.sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Double getAvg() {
		return this.avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}

	public Double getMax() {
		return this.max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Double getMin() {
		return this.min;
	}

	public void setMin(Double min) {
		this.min = min;
	}
}
