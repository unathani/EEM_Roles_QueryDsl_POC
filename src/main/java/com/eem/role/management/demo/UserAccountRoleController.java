package com.eem.role.management.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.BooleanBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/api/useraccounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserAccountRoleController {
    @Autowired
    UserAccountRoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("roleId")
    public Page<UserRolesTest> getByRole(@RequestParam(required = false) String role,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<UserRolesTest> pagedAccounts;

            List<String> roles = Arrays.asList("bop.admin.%", "em.global.%");
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            roles.forEach(r -> booleanBuilder.or(QUserRolesTest.userRolesTest.namespace.eq(r.split("\\.")[0]).and(QUserRolesTest.userRolesTest.context.eq("global"))));

            String whereClause = "bop.global.admin";
            return roleRepository.findAll(booleanBuilder.getValue(), paging);

           //return pagedAccounts.map(this::convertToDto);
        } catch (Exception e) {
           throw e;
        }
    }

//    public List<UserAccountRolesDto> getByRole(@RequestParam(required = false) RequestObject request,
//                                               @RequestParam(defaultValue = "0") int page,
//                                               @RequestParam(defaultValue = "3") int size) {
//
//    }

//    @GetMapping
//    public List<UserAccountRolesDto> get(@RequestParam(required = false, defaultValue = "") String namespace,
//                                               @RequestParam(required = false, defaultValue = "") String context,
//                                               @RequestParam(required = false, defaultValue = "") String roleId,
//                                               @RequestParam(defaultValue = "0") int page,
//                                               @RequestParam(defaultValue = "3") int size) {
//        try {
//            Pageable paging = PageRequest.of(page, size);
//            Page<UserAccount> pagedAccounts;
//            pagedAccounts = roleRepository.find(namespace, context, roleId, paging);
//
//            return
//                    pagedAccounts.stream().distinct()
//                            .map(this::convertToDto)
//                            .collect(Collectors.toList());
//        } catch (Exception e) {
//            throw e;
//        }
//    }

    private UserAccountRolesDto convertToDto(UserAccount account) {
        UserAccountRolesDto dto = modelMapper.map(account, UserAccountRolesDto.class);
        dto.setAdminRoles(account.getUserRoles().stream()
                .map(UserRolesTest::getAdminRoles)
                .collect(Collectors.toList()).stream().map(UserRoleKey::getUserRole).collect(Collectors.toSet()));

        dto.setAccountType(account.getSsoUserAccounts().stream().map(SsoUserAccount -> {
            if (SsoUserAccount.getSsoType() == null)
                SsoUserAccount.setSsoType("ebsco_pua");
            return SsoUserAccount.getSsoType();
        }).collect(Collectors.joining()));
        return dto;
    }
}
