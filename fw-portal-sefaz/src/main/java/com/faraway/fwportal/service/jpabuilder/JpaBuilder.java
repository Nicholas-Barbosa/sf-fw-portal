package com.faraway.fwportal.service.jpabuilder;

public interface JpaBuilder<T, R> {

	T toJpaEntity(R object);

}
