package com.ssafy.nanumi.db.repository;

import com.ssafy.nanumi.db.entity.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @DisplayName("Category 레코드를 삽입하고 조회한다.")
    @Test
    void givenCategory_whenSaved_thenItCanBeRetrieved() {
        Category category = Category.builder()
                .name("Test Category")
                .build();

        Category createdCategory = categoryRepository.save(category);
        Optional<Category> optionalFoundCategory = categoryRepository.findById(createdCategory.getId());

        assertThat(optionalFoundCategory).isPresent();  // ID로 카테고리를 찾을 수 있는지 확인
        assertThat(optionalFoundCategory.get()).isEqualTo(createdCategory);
    }
}
