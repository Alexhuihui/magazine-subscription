package top.alexmmd.service;

import top.alexmmd.domain.Magazine;
import top.alexmmd.domain.PagePackage;
import top.alexmmd.domain.ResponseBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 汪永晖
 */
public interface MagazineService {

    ResponseBean saveMagazine(String name, BigDecimal price);

    ResponseBean getAllMagazine(PagePackage pagePackage);

    ResponseBean getMagazine(Long id);

    ResponseBean getMagazine(String name);

    List<Magazine> getMagazineByName(List<String> names);
}
