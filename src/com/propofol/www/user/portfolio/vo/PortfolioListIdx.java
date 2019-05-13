package com.propofol.www.user.portfolio.vo;

public class PortfolioListIdx {
	private int startIdx, endIdx;

	public PortfolioListIdx() {
		
	} // PortfolioListIdx

	public PortfolioListIdx(int startIdx, int endIdx) {
		this.startIdx = startIdx;
		this.endIdx = endIdx;
	} // PortfolioListIdx

	public int getStartIdx() {
		return startIdx;
	}

	public void setStartIdx(int startIdx) {
		this.startIdx = startIdx;
	}

	public int getEndIdx() {
		return endIdx;
	}

	public void setEndIdx(int endIdx) {
		this.endIdx = endIdx;
	}

	@Override
	public String toString() {
		return "PortfolioListIdx [startIdx=" + startIdx + ", endIdx=" + endIdx + "]";
	} // toString
	
} // class
