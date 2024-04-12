package kz.krg.taskplanner.rest;

import kz.krg.taskplanner.model.Type;
import kz.krg.taskplanner.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api/type")
@RequiredArgsConstructor
public class TypeRestController {
    private final TypeService typeService;

    @GetMapping("/list")
    public List<Type> getTypes() {
        return typeService.listTypes();
    }

    @PostMapping
    public Type saveType(@RequestBody Type type) {
        return typeService.save(type);
    }
}
