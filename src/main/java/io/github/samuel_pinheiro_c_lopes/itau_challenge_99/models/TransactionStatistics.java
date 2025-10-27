package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.models;

public class TransactionStatistics {
	private Integer count;
	private Long sum;
	private Long avg;
	private Long max;
	private Long min;
	
	public TransactionStatistics() {
		this.count = 0;
		this.min = this.max = this.avg = this.sum = 0l;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getSum() {
		return sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
	}

	public Long getAvg() {
		return avg;
	}

	public void setAvg(Long avg) {
		this.avg = avg;
	}

	public Long getMax() {
		return max;
	}

	public void setMax(Long max) {
		this.max = max;
	}

	public Long getMin() {
		return min;
	}

	public void setMin(Long min) {
		this.min = min;
	}
}
