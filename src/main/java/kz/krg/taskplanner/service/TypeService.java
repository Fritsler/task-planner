package kz.krg.taskplanner.service;

import kz.krg.taskplanner.model.Type;

import java.util.List;

public interface TypeService {
    Type getType(Long id);
    List<Type> listTypes();
    Type save(Type type);
}
