package top.alexmmd.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import top.alexmmd.domain.MagazineOrder;

/**
 * @author 汪永晖
 */
public interface MagazineOrderRepository extends PagingAndSortingRepository<MagazineOrder, Long> {
}
