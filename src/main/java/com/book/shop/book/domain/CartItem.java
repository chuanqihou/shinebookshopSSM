package com.book.shop.book.domain;


import java.text.DecimalFormat;

/**
* @version 创建时间：2017年10月25日 下午2:36:37 
*/
public class CartItem {
	private ViewBook book;
	private int quantity;//数量
	private double subtotal;//小计
	
	public CartItem() {}
	
	
	
	public CartItem(ViewBook book, int quantity) {
		super();
		this.setBook(book);
		this.setQuantity(quantity);
	}

	public ViewBook getBook() {
		return book;
	}
	public void setBook(ViewBook book) {
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.subtotal = Double.parseDouble(new DecimalFormat("#.00").format(quantity*book.getPrice()));
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", quantity=" + quantity + ", subtotal=" + subtotal + "]";
	}
	
	
	
}
