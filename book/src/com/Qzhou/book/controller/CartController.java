package com.Qzhou.book.controller;

import com.Qzhou.book.pojo.Book;
import com.Qzhou.book.pojo.Cart;
import com.Qzhou.book.pojo.CartItem;
import com.Qzhou.book.pojo.User;
import com.Qzhou.book.service.CartItemService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;

public class CartController {
    private CartItemService cartItemService;

    public String addCart(Integer bookId, HttpSession session){
        User user = (User) session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId),1,user);
        //��ָ����ͼ��ӵ����ﳵ
        cartItemService.addOrUpdateCartIem(cartItem,user.getCart());
        return "redirect:cart.do";
    }
    //���ص�ǰ���ﳵ��Ϣ
    public String index(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser",user);
        return "cart/cart";
    }
    //���ﳵ���� + -
    public String editCart(Integer cartItemId,Integer buyCount){
        cartItemService.updateCartItem(new CartItem(cartItemId,buyCount));
        return "";
    }

    public String cartInfo(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);

        //����Cart �е���������get������Ŀ���Ǽ���˴��������Ե�ֵ�������������Զ�Ϊnull
        //������ gson ת��ʱ�ᱻ����
        cart.gettotalBookCount();
        cart.getTotalCount();
        cart.getTotalMoney();

        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cart);
        return "json:"+cartJsonStr;

    }
}
