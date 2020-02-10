package top.alexmmd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.alexmmd.domain.PagePackage;
import top.alexmmd.domain.ResponseBean;
import top.alexmmd.domain.request.NewMagazineRequest;
import top.alexmmd.service.MagazineService;

import javax.validation.Valid;

/**
 * @author 汪永晖
 */
@RestController
@RequestMapping("/magazine")
@Slf4j
public class MagazineController {

    private final top.alexmmd.service.MagazineService magazineService;

    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @PostMapping(path = "/add")
    public ResponseBean addMagazine(@Valid @RequestBody NewMagazineRequest newMagazine) {
        return magazineService.saveMagazine(newMagazine.getName(), newMagazine.getPrice());
    }

    @GetMapping(path = "/select")
    public ResponseBean getAll(@RequestBody PagePackage pagePackage) {
        return magazineService.getAllMagazine(pagePackage);
    }

    @GetMapping("/{id}")
    public ResponseBean getById(@PathVariable Long id) {
        return magazineService.getMagazine(id);
    }

    @GetMapping(path = "/search_name")
    public ResponseBean getByName(@RequestParam String name) {
        return magazineService.getMagazine(name);
    }
}
