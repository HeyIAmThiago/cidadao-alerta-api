package com.cidadao_alerta.cidadao_alerta.models.mappers;

import java.util.List;

import com.cidadao_alerta.cidadao_alerta.models.dtos.CategoryDTOResponse;
import com.cidadao_alerta.cidadao_alerta.models.entities.CategoryEntity;

public class CategoryMapper {
  
  public static List<CategoryDTOResponse> listEntityToDto(List<CategoryEntity> categories) {
    return categories.stream().map(
      categorie -> CategoryMapper.entityToDto(categorie)
    ).toList();
  }

  public static CategoryDTOResponse entityToDto(CategoryEntity category) {
    return new CategoryDTOResponse(category.getId(), category.getDescription());
  }
}
