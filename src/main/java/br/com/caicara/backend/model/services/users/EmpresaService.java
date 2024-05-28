package br.com.caicara.backend.model.services.users;

import br.com.caicara.backend.model.entities.users.Empresa;
import br.com.caicara.backend.model.repositories.users.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Transactional
    public Empresa createEmpresa(Empresa emp){
        return empresaRepository.save(emp);
    }

    @Transactional(readOnly = true)
    public Empresa getUserById(UUID id) {
        return empresaRepository.findByUsersId(id);
    }
}
