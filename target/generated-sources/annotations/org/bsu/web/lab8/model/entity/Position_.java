package org.bsu.web.lab8.model.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Position.class)
public abstract class Position_ {

	public static volatile SingularAttribute<Position, String> itemName;
	public static volatile SingularAttribute<Position, BigDecimal> cost;
	public static volatile SingularAttribute<Position, Integer> id;

	public static final String ITEM_NAME = "itemName";
	public static final String COST = "cost";
	public static final String ID = "id";

}

