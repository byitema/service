package org.bsu.web.lab8.model.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderPosition.class)
public abstract class OrderPosition_ {

	public static volatile SingularAttribute<OrderPosition, Integer> amount;
	public static volatile SingularAttribute<OrderPosition, Integer> positionID;
	public static volatile SingularAttribute<OrderPosition, Integer> orderID;
	public static volatile SingularAttribute<OrderPosition, Position> position;
	public static volatile SingularAttribute<OrderPosition, Order> order;

	public static final String AMOUNT = "amount";
	public static final String POSITION_ID = "positionID";
	public static final String ORDER_ID = "orderID";
	public static final String POSITION = "position";
	public static final String ORDER = "order";

}

