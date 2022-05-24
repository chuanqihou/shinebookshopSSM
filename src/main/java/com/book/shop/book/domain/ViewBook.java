package com.book.shop.book.domain;


public class ViewBook {

  private String catalogName;
  private long bookId;
  private long catalogId;
  private String bookName;
  private double price;
  private String description;
  private long imgId;
  private String imgName;
  private String imgSrc;
  private String imgType;
  private String author;
  private String press;
  private java.sql.Timestamp addTime;


  public String getCatalogName() {
    return catalogName;
  }

  public void setCatalogName(String catalogName) {
    this.catalogName = catalogName;
  }


  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }


  public long getCatalogId() {
    return catalogId;
  }

  public void setCatalogId(long catalogId) {
    this.catalogId = catalogId;
  }


  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public long getImgId() {
    return imgId;
  }

  public void setImgId(long imgId) {
    this.imgId = imgId;
  }


  public String getImgName() {
    return imgName;
  }

  public void setImgName(String imgName) {
    this.imgName = imgName;
  }


  public String getImgSrc() {
    return imgSrc;
  }

  public void setImgSrc(String imgSrc) {
    this.imgSrc = imgSrc;
  }


  public String getImgType() {
    return imgType;
  }

  public void setImgType(String imgType) {
    this.imgType = imgType;
  }


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }


  public String getPress() {
    return press;
  }

  public void setPress(String press) {
    this.press = press;
  }


  public java.sql.Timestamp getAddTime() {
    return addTime;
  }

  public void setAddTime(java.sql.Timestamp addTime) {
    this.addTime = addTime;
  }

}
