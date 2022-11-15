package guru.springfamework.service;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;

import java.util.List;

public interface CategoryService {
    CategoryDTO findByName(String name);
    List<CategoryDTO> getAllCategories();
}
