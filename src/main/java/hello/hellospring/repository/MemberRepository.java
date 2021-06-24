package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //save information for new members.

    //Once the information has been saved, it can be accessed through these methods.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);

    List<Member> findAll(); //return an information list of all the members.
}
