package com.Qzhou.book.dao;

import com.Qzhou.book.pojo.OrderBean;
import com.Qzhou.book.pojo.User;

import java.util.List;

public interface OrderDAO {
    //��Ӷ���
    void addOrderBean(OrderBean orderBean);
    //��ȡָ���û��Ķ����б�
    List<OrderBean> getOrderList(User user);
    //��ȡbook ������
    Integer getTotalBookCount(OrderBean orderBean);
}
