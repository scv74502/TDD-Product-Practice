package org.sampletask.tddpractice

import com.google.common.base.CaseFormat
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Table
import jakarta.persistence.metamodel.EntityType
import jakarta.persistence.metamodel.Type
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Component
class DatabaseCleanup : InitializingBean {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    // 원본처럼 선언 후 afterPropertiesSet에서 할당하기 위해 lateinit 사용
    private lateinit var tableNames: MutableList<String>

    override fun afterPropertiesSet() {
        val entities: Set<EntityType<*>> = entityManager.metamodel.entities

        tableNames =
            entities
                .stream()
                .filter { e -> isEntity(e) && hasTableAnnotation(e) }
                .map { e -> e.javaType.getAnnotation(Table::class.java).name }
                .collect(Collectors.toList())

        val entityNames: List<String> =
            entities
                .stream()
                .filter { e -> isEntity(e) && !hasTableAnnotation(e) }
                .map { e ->
                    CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.name)
                }.toList() // 자바 16 이상에서 사용하는 toList()는 코틀린에서도 호출 가능

        // 4. 기존 리스트에 추가
        tableNames.addAll(entityNames)
    }

    private fun isEntity(e: EntityType<*>): Boolean {
        // 해당 타입이 JPA 엔티티인지 확인하는 로직
        return e.persistenceType == Type.PersistenceType.ENTITY
    }

    private fun hasTableAnnotation(e: EntityType<*>): Boolean {
        // 클래스에 @Table 어노테이션이 붙어있는지 확인
        return e.javaType.isAnnotationPresent(Table::class.java)
    }

//    // 코틀린 버전
//    override fun afterPropertiesSet() {
//        val entities = entityManager.metamodel.entities
//
//        // @Table 어노테이션이 있는 엔티티의 테이블명 추출
//        val tableNamesFromAnnotation =
//            entities
//                .filter { e -> isEntity(e) && hasTableAnnotation(e) }
//                .map { e -> e.javaType.getAnnotation(Table::class.java).name }
//
//        // @Table 어노테이션이 없는 엔티티의 클래스명을 snake_case로 변환
//        val entityNames =
//            entities
//                .filter { e -> isEntity(e) && !hasTableAnnotation(e) }
//                .map { e ->
//                    CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.name)
//                }
//
//        // 결과 합치기 (tableNames가 MutableList라고 가정)
//        tableNames.addAll(tableNamesFromAnnotation)
//        tableNames.addAll(entityNames)
//    }

    @Transactional
    fun execute() {
        entityManager.flush()
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate()

        for (tableName in tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate()
            entityManager
                .createNativeQuery(
                    "ALTER TABLE " + tableName + " ALTER  COLUMN ID RESTART  WITH  1",
                ).executeUpdate()
        }

        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate()
    }
}
