package top.alexmmd.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import top.alexmmd.domain.Magazine;

import java.util.List;
import java.util.Optional;

/**
 * @author 汪永晖
 */
public interface MagazineRepository extends PagingAndSortingRepository<Magazine, Long> {

    Optional<Magazine> findByCommodityName(String name);

    Optional<Magazine> findByCommodityId(Long id);

    List<Magazine> findByCommodityNameInOrderByCommodityId(List<String> names);
}
