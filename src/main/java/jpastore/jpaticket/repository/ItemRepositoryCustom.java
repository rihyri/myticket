package jpastore.jpaticket.repository;

import jpastore.jpaticket.domain.Item;
import jpastore.jpaticket.dto.ItemSearchDto;
import jpastore.jpaticket.domain.MainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
