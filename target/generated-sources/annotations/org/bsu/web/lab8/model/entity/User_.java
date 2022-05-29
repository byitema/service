package org.bsu.web.lab8.model.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile ListAttribute<User, Order> clientOrders;
	public static volatile SingularAttribute<User, String> nickname;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, Boolean> isAdmin;
	public static volatile ListAttribute<User, Order> adminOrders;

	public static final String PASSWORD = "password";
	public static final String CLIENT_ORDERS = "clientOrders";
	public static final String NICKNAME = "nickname";
	public static final String ID = "id";
	public static final String IS_ADMIN = "isAdmin";
	public static final String ADMIN_ORDERS = "adminOrders";

}

