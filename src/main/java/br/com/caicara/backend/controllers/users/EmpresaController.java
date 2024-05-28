package br.com.caicara.backend.controllers.users;

import br.com.caicara.backend.model.dto.users.EmpresaCreateDto;
import br.com.caicara.backend.model.dto.users.EmpresaResponseDto;
import br.com.caicara.backend.model.entities.mapper.EmpresaMapper;
import br.com.caicara.backend.model.entities.users.Empresa;
import br.com.caicara.backend.model.jwt.JwtUserDetails;
import br.com.caicara.backend.model.services.users.EmpresaService;
import br.com.caicara.backend.model.services.users.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/empresa")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('EMPRESA')")
    public ResponseEntity<EmpresaResponseDto> createEmpresa(@RequestBody @Valid EmpresaCreateDto dto, @AuthenticationPrincipal JwtUserDetails userDetails){
        Empresa emp = EmpresaMapper.toEmpresa(dto);
        emp.setUsers(userService.findUserById(userDetails.getId()));
        empresaService.createEmpresa(emp);
        return ResponseEntity.status(HttpStatus.CREATED).body(EmpresaMapper.toDto(emp));
    }

    @GetMapping("/detalhes")
    @PreAuthorize("hasRole('EMPRESA')")
    public ResponseEntity<EmpresaResponseDto> getDetalhes(@AuthenticationPrincipal JwtUserDetails userDetails){
        Empresa emp = empresaService.getUserById(userDetails.getId());
        return ResponseEntity.ok(EmpresaMapper.toDto(emp));
    }
}