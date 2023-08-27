package com.ssafy.nanumi.db.repository;

import com.ssafy.nanumi.db.entity.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @DisplayName("Category 레코드를 삽입한다.")
    @Test
    void insert() {
        Category category = Category.builder()
                .name("Test Category")
                .build();

        Category createdCategory = categoryRepository.save(category);
        Category foundCategory = categoryRepository.findById(createdCategory.getId()).get();

        assertThat(foundCategory).isEqualTo(createdCategory);
    }
}
