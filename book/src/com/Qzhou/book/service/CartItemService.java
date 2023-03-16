package com.Qzhou.book.service;

import com.Qzhou.book.pojo.Cart;
import com.Qzhou.book.pojo.CartItem;
import com.Qzhou.book.pojo.User;

import java.util.List;

public interface CartItemService {
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void addOrUpdateCartIem(CartItem cartItem, Cart cart);
    //��ȡָ���û������й��ﳵ���б� (��Ҫ����book����ϸ��Ϣ)
    List<CartItem> getCartItemList(User user);
    //����ָ���Ĺ��ﳵ��Ϣ
    Cart getCart(User user);
}
