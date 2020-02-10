package top.alexmmd.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.alexmmd.domain.Magazine;
import top.alexmmd.domain.PagePackage;
import top.alexmmd.domain.ResponseBean;
import top.alexmmd.repository.MagazineRepository;
import top.alexmmd.service.MagazineService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author 汪永晖
 */
@Service
@Slf4j
public class MagazineServiceImpl implements MagazineService {

    private final MagazineRepository magazineRepository;

    public MagazineServiceImpl(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    @Override
    public ResponseBean saveMagazine(String name, BigDecimal price) {

        Magazine newMagazine = magazineRepository.save(Magazine.builder().
                commodityName(name).commodityPrice(price).build());

        log.info("<magazine-commodity>: add magazine -> {}", newMagazine);

        return new ResponseBean(100, "新增杂志成功", newMagazine);
    }

    @Override
    public ResponseBean getAllMagazine(PagePackage pagePackage) {

        Pageable pageable = PageRequest.of(pagePackage.getPage(), pagePackage.getSize());
        Page<Magazine> magazines = magazineRepository.findAll(pageable);

        List<Magazine> magazineList = magazines.getContent();

        log.info("<magazine-commodity>: 查询第{}页的内容是 -> {}",pagePackage.getPage(), magazineList);

        return new ResponseBean(100, "成功查询所有商品", magazineList);
    }

    @Override
    public ResponseBean getMagazine(Long id) {

        Optional<Magazine> magazine = magazineRepository.findByCommodityId(id);

        log.info("<magazine-commodity>: 查询 id = -> {}", id);

        if (!magazine.isPresent()) {
            return new ResponseBean(500, "查询失败");
        }
        return new ResponseBean(100, "查询成功", magazine.get());
    }

    @Override
    public ResponseBean getMagazine(String name) {

        Optional<Magazine> magazine = magazineRepository.findByCommodityName(name);

        log.info("<magazine-commodity>: 查询 name = -> {}", name);

        if (!magazine.isPresent()) {
            return new ResponseBean(500, "查询失败");
        }
        return new ResponseBean(100, "查询成功", magazine.get());
    }

    @Override
    public List<Magazine> getMagazineByName(List<String> names) {
        return magazineRepository.findByCommodityNameInOrderByCommodityId(names);
    }

}
