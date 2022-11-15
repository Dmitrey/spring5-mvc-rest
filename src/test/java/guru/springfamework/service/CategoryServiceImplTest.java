package guru.springfamework.service;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;

    CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryMapper = CategoryMapper.INSTANCE;
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void findByName() {
        Category category = Category.builder().id(1L).name("test cat 1").build();
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDto(category);
        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDTO byName = categoryService.findByName("test cat 1");

        verify(categoryRepository, times(1)).findByName(anyString());
        assertEquals(categoryDTO.getId(), byName.getId());
        assertEquals(categoryDTO.getName(), byName.getName());
    }

    @Test
    void getAllCategories() {
        List<Category> list = new ArrayList<>(
                Arrays.asList(Category.builder().id(1L).name("test cat 1").build(),
                        Category.builder().id(2L).name("test cat 2").build(),
                        Category.builder().id(3L).name("test cat 3").build(),
                        Category.builder().id(4L).name("test cat 4").build()
                ));

        when(categoryRepository.findAll()).thenReturn(list);

        List<CategoryDTO> allCategories = categoryService.getAllCategories();

        verify(categoryRepository, times(1)).findAll();

        assertEquals(list.size(), allCategories.size());
    }
}