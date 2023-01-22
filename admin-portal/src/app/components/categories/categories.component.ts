import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { APIConstants } from 'src/app/constants/api-constants';
import { CategoryDialogComponent } from 'src/app/dialog/categories/category-dialog/category-dialog.component';
import { ConfirmDialogComponent } from 'src/app/dialog/common/confirm-dialog/confirm-dialog.component';
import { Category } from 'src/app/models/app/category';
import { AppConfigurationService } from 'src/app/services/app/app-configuration.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css'],
})
export class CategoriesComponent {
  currentPage: number = 0;
  categories: Category[] = [];

  constructor(
    private appConfigurationService: AppConfigurationService,
    private dialogRef: MatDialog
  ) {
    this.getCategories(undefined);
  }

  loadMore() {
    if (this.categories && this.categories.length > 0) {
      this.getCategories(this.categories[this.categories.length - 1]);
    }
  }

  getCategories(category?: Category) {
    this.appConfigurationService
      .getCategories(category ? category.id : 'initial')
      .subscribe((res) => {
        if (category) {
          this.categories = [...this.categories, ...res];
        } else {
          this.categories = res;
        }
      });
  }

  deleteCategory(category: Category) {
    this.appConfigurationService
      .deleteCategory(category.id)
      .subscribe((res) => {
        this.categories = this.categories.filter((item) => item !== category);
      });
  }

  getDeleteMessage(category: Category) {
    return (
      "Are you sure you want to delete content type  '" + category.name + "' ?"
    );
  }

  openCategoryDialog(category: Category) {
    this.dialogRef
      .open(CategoryDialogComponent, {
        height: '50%',
        width: '55%',
        closeOnNavigation: true,
        data: {
          category: category,
        },
      })
      .afterClosed()
      .subscribe(() => this.getCategories());
  }

  openConfirmDialog(category: Category) {
    this.dialogRef
      .open(ConfirmDialogComponent, {
        height: '300px',
        width: '400px',
        closeOnNavigation: true,
        data: {
          msg: this.getDeleteMessage(category),
        },
      })
      .afterClosed()
      .subscribe((response) => {
        if (response && response.data && response.data.confirm) {
          this.deleteCategory(category);
        }
      });
  }

  createNewCategory() {
    return <Category>{
      name: '',
      type: '',
      safeDelete: true,
    };
  }
}
