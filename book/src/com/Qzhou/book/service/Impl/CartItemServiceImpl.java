package com.Qzhou.book.service.Impl;

import com.Qzhou.book.dao.CartItemDAO;
import com.Qzhou.book.pojo.Book;
import com.Qzhou.book.pojo.Cart;
import com.Qzhou.book.pojo.CartItem;
import com.Qzhou.book.pojo.User;
import com.Qzhou.book.service.BookService;
import com.Qzhou.book.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {
    private CartItemDAO cartItemDAO;
    private BookService bookService;
    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartIem(CartItem cartItem, Cart cart) {
        //�жϵ�ǰ�û��Ĺ��ﳵ���Ƿ����Ȿ���CartItem �����1 �������
        //1�������ǰ�û��Ĺ��ﳵ�Ѵ������ͼ�� ��������һ
        //2���������ҵĹ��ﳵ������һ���Ȿͼ���CartItem ������1
        if(cart!=null){
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if(cartItemMap==null){
                cartItemMap=new HashMap<>();
            }
            if(cartItemMap.containsKey(cartItem.getBook().getId())){
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount()+1);
                updateCartItem(cartItemTemp);
            }else{
                addCartItem(cartItem);
            }
        }else { //û�й��ﳵ�����
            addCartItem(cartItem);
        }
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        //��ʱ ��ѯ���� cartItem ������ֻ��book id�� ��Ҫbook ������Ϣ
        List<CartItem> cartItemList = cartItemDAO.getCartItemList(user);
        for(CartItem cartItem:cartItemList){
           Book book= bookService.getBookById(cartItem.getBook().getId());
           cartItem.setBook(book);
           //�˴�����getXj() Ŀ���Ǽ���� Xj ��ֵ����Xj
           cartItem.getXj();
        }
        return cartItemList;
    }

    @Override
    public Cart getCart(User user) {
        //��ʱcartItemList ��book����ֵ��
        List<CartItem> cartItemList = getCartItemList(user);
        Map<Integer,CartItem> cartItemMap=new HashMap<>();
        for (CartItem cartItem : cartItemList){
            cartItemMap.put(cartItem.getBook().getId(),cartItem);
        }
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
    }
}
