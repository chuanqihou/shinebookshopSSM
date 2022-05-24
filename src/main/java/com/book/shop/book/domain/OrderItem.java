package com.book.shop.book.domain;

import java.util.Map;

/**
 * 订单项
 *
 * @author
 * @create 2017-09-23 1:53
 */
public class OrderItem {

    private int itemId;          //订单项编号
    private int bookId;         //图书编号
    private int orderId;        //订单编号
    private int quantity;       //数量
    
    private ViewBook book;
    
    public OrderItem() {
    }

   
    public OrderItem(Map<String, Object> map) {
		this.setOrderId((int) map.get("orderId"));
        this.setBookId((int) map.get("bookId"));
		this.setItemId((int) map.get("itemId"));
		this.setQuantity((int) map.get("quantity"));
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


	public ViewBook getBook() {
		return book;
	}


	public void setBook(ViewBook book) {
		this.book = book;
	}
    
}
