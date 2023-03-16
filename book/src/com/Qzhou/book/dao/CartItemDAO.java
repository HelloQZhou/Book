package com.Qzhou.book.dao;

import com.Qzhou.book.pojo.CartItem;
import com.Qzhou.book.pojo.User;

import java.util.List;

public interface CartItemDAO {
    //�������ﳵ��
    void addCartItem(CartItem cartItem);
    //�޸�ָ���Ĺ��ﳵ��
    void updateCartItem(CartItem cartItem);
    //��ȡ�ض��û������й��ﳵ��
    List<CartItem> getCartItemList(User user);
    //ɾ��ָ���Ĺ��ﳵ��
    void delCartItem(CartItem cartItem);

}
