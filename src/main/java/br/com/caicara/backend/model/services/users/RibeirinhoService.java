package br.com.caicara.backend.model.services.users;

import br.com.caicara.backend.model.dto.users.RibeirinhoUpdateDto;
import br.com.caicara.backend.model.entities.users.Ribeirinho;
import br.com.caicara.backend.model.repositories.users.RibeirinhoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RibeirinhoService {

    private final RibeirinhoRepository ribeirinhoRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Ribeirinho createRibeirinho(Ribeirinho ribeirinho){
        return ribeirinhoRepository.save(ribeirinho);
    }

    @Transactional(readOnly = true)
    public Ribeirinho getRibeirinhoById(UUID id){
        return ribeirinhoRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("Id= '%s' não encontrado", id)));
    }

    @Transactional(readOnly = true)
    public List<Ribeirinho> getAllRibeirinhos(){
        return ribeirinhoRepository.findAll();
    }

    @Transactional
    public void updateRibeirinho(UUID id, RibeirinhoUpdateDto dto) {
        Ribeirinho rib = ribeirinhoRepository.findById(id).orElseThrow(()-> new RuntimeException("Id não encontrado"));
        modelMapper.map(dto, rib);
        ribeirinhoRepository.save(rib);
    }

    @Transactional(readOnly = true)
    public Ribeirinho getUserById(UUID id) {
        return ribeirinhoRepository.findByUsersId(id);
    }

    @Transactional(readOnly = true)
    public Ribeirinho getUserByCpf(String cpf) {
        return ribeirinhoRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException(String.format("Cliente com cpf '%s' não encontrado", cpf)));
    }
}
