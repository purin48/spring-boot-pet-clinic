package dev.spring.petclinic.controller;

import dev.spring.petclinic.dto.OwnerRequest;
import dev.spring.petclinic.dto.OwnerResponse;
import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/owners")
@RestController
public class OwnerRestController {
    private final OwnerService ownerService;


    public OwnerRestController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
//    public ResponseEntity<List<Owner>> listOwners() {
    public List<OwnerResponse> listOwners() {
        List<Owner> owners = ownerService.findAll();

        // 정적 팩토리 메서드
        // of(), from() - 정적(static) 팩토리(factory) 메서드
        // 이펙티브 자바 - item 1번 참고하기

        List<OwnerResponse> ownerResponse = owners.stream() // List<Owner> 의 owner 객체들을 풀어해쳐
                .map(OwnerResponse::from) // OwnerResponse.from(Owner owner) 의 인수로 객체를 하나씩 전달해서 처리
                .collect(Collectors.toList()); // 처리가 끝난 개별 OwnerResponse 객체들을 List<OwnerResponse>로 만들어라
        // => Service 단으로 빼도 된다.


        return ownerResponse;
    }


    // @Valid : 유효성 검사 | @RequestBody : json 객체로 받기 위해서
    @PostMapping
    public ResponseEntity<OwnerResponse> addOwner(@Valid @RequestBody OwnerRequest ownerRequest) {
        // DTO로 받아오는 이유 : Entity는 DB와 관련된 친구들 & 요청과 분리하기 위해서
        // ownerRequest 는 여기서 요청을 유효성 검사하는 로직과 분리

        System.out.println(ownerRequest);

        // 유효성을 검증한 ownerRequest 라는 DTO를
        // DTO를 entity로 변환해서 넣는 작업을 해야함
//        ownerService.save();

        return null;
    }
}
