package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CategoryListDto;
import guru.springfamework.domain.Category;
import guru.springfamework.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
//        categoryController = new CategoryController(categoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        categoryMapper = CategoryMapper.INSTANCE;
    }

    @Test
    void getAllCategories() throws Exception {

        List<CategoryDTO> list = new ArrayList<>(
                Arrays.asList(Category.builder().id(1L).name("test cat 1").build(),
                        Category.builder().id(2L).name("test cat 2").build(),
                        Category.builder().id(3L).name("test cat 3").build(),
                        Category.builder().id(4L).name("test cat 4").build()
                )).stream().map(categoryMapper::categoryToCategoryDto).collect(Collectors.toList());

        when(categoryService.getAllCategories()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(System.out::println)
                .andExpect(jsonPath("$.categoryDTOList", hasSize(4)));
    }

    @Test
    void getCategoryByName() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("test cat 1").build();
        when(categoryService.findByName(anyString())).thenReturn(categoryDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/test cat 1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("test cat 1")));
    }
}