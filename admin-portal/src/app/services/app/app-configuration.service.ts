import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { APIConstants } from 'src/app/constants/api-constants';
import { AgeGroup } from 'src/app/models/app/age-group';
import { Category } from 'src/app/models/app/category';

@Injectable({
  providedIn: 'root',
})
export class AppConfigurationService {
  constructor(private http: HttpClient) {}

  // Age goups
  getAgeGroups() {
    return this.http.get<AgeGroup[]>(APIConstants.AGE_GROUP_GET_ALL_URL);
  }

  createAgeGroup(ageGroup: AgeGroup) {
    return this.http.post<AgeGroup>(APIConstants.AGE_GROUP_URL, ageGroup);
  }

  updateAgeGroup(ageGroup: AgeGroup) {
    return this.http.put<AgeGroup>(APIConstants.AGE_GROUP_URL, ageGroup);
  }

  deleteAgeGroup(id: string) {
    return this.http.delete<AgeGroup>(
      APIConstants.AGE_GROUP_BY_ID_URL.replaceAll('{id}', id)
    );
  }

  // categories

  getCategories(id: string) {
    return this.http.get<Category[]>(
      APIConstants.CATEGORY_PAGINATE_URL.replaceAll('{key}', id).replaceAll(
        '{size}',
        APIConstants.PAGE_SIZE.toString()
      )
    );
  }

  getAllCategories() {
    return this.http.get<Category[]>(APIConstants.CATEGORY_GET_ALL_URL);
  }

  createCategory(category: Category) {
    return this.http.post<Category>(APIConstants.CATEGORY_URL, category);
  }

  updateCategory(category: Category) {
    return this.http.put<Category>(APIConstants.CATEGORY_URL, category);
  }

  deleteCategory(id: string) {
    return this.http.delete<Category>(
      APIConstants.CATEGORY_BY_ID_URL.replaceAll('{id}', id)
    );
  }
}
