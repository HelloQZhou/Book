package com.Qzhou.book.pojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Integer, CartItem> cartItemMap;  //���ﳵ�й��ﳵ��ļ��� key��ʾ book��id
    private Double totalMoney;  //���ﳵ�ܽ��
    private Integer totalCount; //���ﳵ������
    private Integer totalBookCount; // ���ﳵ��book��������

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> cartItemMap, Double totalMoney, Integer totalCount) {
        this.cartItemMap = cartItemMap;
        this.totalMoney = totalMoney;
        this.totalCount = totalCount;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        totalMoney=0.0;
        if(cartItemMap!=null && cartItemMap.size()>0){
            //Collection<CartItem> values = cartItemMap.values();
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer, CartItem> cartItemEntry:entries){
                CartItem cartItem = cartItemEntry.getValue();
                 //totalMoney = cartItem.getBook().getPrice()*cartItem.getBuyCount()+totalMoney;
                BigDecimal bigDecimalPrice = new BigDecimal("" + cartItem.getBook().getPrice());
                BigDecimal bigDecimalByCount = new BigDecimal("" + cartItem.getBuyCount());
                BigDecimal bigDecimalZje = bigDecimalPrice.multiply(bigDecimalByCount);
                totalMoney=bigDecimalZje.doubleValue()+totalMoney;
            }
        }
        return totalMoney;
    }

    public Integer getTotalCount() {
        totalCount=0;
        if(cartItemMap!=null && cartItemMap.size()>0){
            totalCount=cartItemMap.size();
        }
        return totalCount;
    }

    public Integer gettotalBookCount() { //��Ʒ������
        totalBookCount=0;
        if(cartItemMap!=null && cartItemMap.size()>0){
           for(CartItem cartItem:cartItemMap.values()){
             totalBookCount=cartItem.getBuyCount()+totalBookCount;
           }
        }
        return totalBookCount;
    }


}
