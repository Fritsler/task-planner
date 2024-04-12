package kz.krg.taskplanner.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.krg.taskplanner.model.Type;
import kz.krg.taskplanner.repository.TypeRepository;
import kz.krg.taskplanner.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    @Override
    public Type getType(Long id) {
        return typeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Type> listTypes() {
        return typeRepository.findAll();
    }

    @Override
    public Type save(Type type) {
        Type typeToSave;
        if (type.getId() == null) {
            typeToSave = new Type();
        } else {
            typeToSave = typeRepository.findById(type.getId())
                    .orElseThrow(() -> new EntityNotFoundException("type with id " +
                            type.getId() + " not found"));
        }
        typeToSave.setType(type.getType());
        return typeRepository.save(typeToSave);
    }
}
