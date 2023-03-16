package com.Qzhou.book.service.Impl;

import com.Qzhou.book.dao.CartItemDAO;
import com.Qzhou.book.dao.OrderDAO;
import com.Qzhou.book.dao.OrderItemDAO;
import com.Qzhou.book.pojo.CartItem;
import com.Qzhou.book.pojo.OrderBean;
import com.Qzhou.book.pojo.OrderItem;
import com.Qzhou.book.pojo.User;
import com.Qzhou.book.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private CartItemDAO cartItemDAO;
    private Integer getOrderTotalBookCount;
    @Override
    public void addOrderBean(OrderBean orderBean) {
        /*
        * 1�����������һ����¼ t_order
        * 2��������������һ����¼ t_order_item
        * 3�����ﳵ���ɾ����Ӧ��N����¼ t_cart_item
        * */
        //1
        //ִ��Ϊ��һ��orderBean �е� id ����ֵ��
        orderDAO.addOrderBean(orderBean);

        //2
        //��ʱOrderBean �е� orderItemList �ǿյ� �˴�����Ӧ�ø����û����ﳵ�еĹ��ﳵ��ȥת����һ��һ���Ķ�����
        User currUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = currUser.getCart().getCartItemMap();
        for (CartItem cartItem:cartItemMap.values()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }

        //3
        for (CartItem cartItem:cartItemMap.values()){
            cartItemDAO.delCartItem(cartItem);
        }

    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderList = orderDAO.getOrderList(user);
        for(OrderBean orderBean: orderList){
            Integer totalBookCount = orderDAO.getTotalBookCount(orderBean);
            orderBean.setTotalCount(totalBookCount);
        }
        return orderList;
    }
}
