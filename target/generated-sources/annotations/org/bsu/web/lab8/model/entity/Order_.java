package org.bsu.web.lab8.model.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, User> client;
	public static volatile SingularAttribute<Order, User> admin;
	public static volatile ListAttribute<Order, OrderPosition> positions;
	public static volatile SingularAttribute<Order, Boolean> isConfirmed;
	public static volatile SingularAttribute<Order, Integer> id;
	public static volatile SingularAttribute<Order, Date> orderDate;
	public static volatile SingularAttribute<Order, Boolean> isPayed;

	public static final String CLIENT = "client";
	public static final String ADMIN = "admin";
	public static final String POSITIONS = "positions";
	public static final String IS_CONFIRMED = "isConfirmed";
	public static final String ID = "id";
	public static final String ORDER_DATE = "orderDate";
	public static final String IS_PAYED = "isPayed";

}

