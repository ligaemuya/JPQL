package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //트랜잭션을 받아오지않으면 실제 DB에 저장되지않음
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {


            /*
            //Criteria
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);
            Root<Member> m = query.from(Member.class);
            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            List<Member> resultList = em.createQuery(cq).getResultList();*/
       /* TypedQuery<Member> q = em.createQuery("select m from Member m where m.id = '10'", Member.class);
            Member singleResult = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("singleResult = " + singleResult.getUsername());
//            TypedQuery<String> q2 = em.createQuery("select m.username from Member m", String.class);
//            Query q3 = em.createQuery("select m.username, m.age from Member m");

            Member result = q.getSingleResult();
            //Spring Data JPA ->
            System.out.println("result = " + result);*/


            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();
            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
            List<Team> membersTeam = em.createQuery("select t from Member m join m.team t", Team.class).getResultList();

            Member findMember = members.get(0);
            findMember.setAge(20);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            //code
            em.close();
        }
        emf.close();
    }

    private static void logic(Member m, Member m1) {
        System.out.println("m == m2: " + (m1 instanceof Member));
        System.out.println("m == m2: " + (m instanceof Member));
    }
}
