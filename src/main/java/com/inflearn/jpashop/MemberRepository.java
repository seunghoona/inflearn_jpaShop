package com.inflearn.jpashop;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // Member를 반환하면 되는데 왜 이렇게하지 하는데 스타일
    // 커맨드와 쿼리를 분리해라
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    public Long findMemberId(String userId){

        Long findUserId = em.createQuery("SELECT m.id FROM Member  m where  m.userId=:userId", Long.class)
                .setParameter("userId", userId).getSingleResult();

        return findUserId;
    }





}
