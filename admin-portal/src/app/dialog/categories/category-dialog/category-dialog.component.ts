import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from 'src/app/models/app/category';
import { TileType } from 'src/app/models/app/tile-type';
import { AppConfigurationService } from 'src/app/services/app/app-configuration.service';

@Component({
  selector: 'app-category-dialog',
  templateUrl: './category-dialog.component.html',
  styleUrls: ['./category-dialog.component.css'],
})
export class CategoryDialogComponent {
  category: Category;
  categoryForm: FormGroup;
  submitButton: string;

  constructor(
    @Inject(MAT_DIALOG_DATA) param: any,
    private appConfigurationService: AppConfigurationService,
    private dialogRef: MatDialogRef<CategoryDialogComponent>
  ) {
    this.category = param['category'];
    this.categoryForm = new FormGroup({
      name: new FormControl(this.category.name, [
        Validators.required,
        Validators.pattern('[a-zA-Z0-9_ -]*'),
      ]),
      type: new FormControl({ value: this.category.type, disabled: true }, [
        Validators.required,
      ]),
    });

    this.categoryForm.controls['name'].valueChanges.subscribe((value) => {
      this.categoryForm.controls['type'].setValue(
        this.createTypeFromName(value)
      );
    });

    if (this.category && (!this.category.id || this.category.id.length == 0)) {
      this.submitButton = 'Save';
    } else {
      this.submitButton = 'Update';
    }
  }

  createTypeFromName(type: string) {
    return type.toLowerCase().trim().replaceAll(' ', '_').replaceAll('-', '_');
  }

  onSubmit() {
    this.category.name = this.categoryForm.value['name'];
    this.category.type = this.createTypeFromName(this.category.name);
    if (this.category && (!this.category.id || this.category.id.length == 0)) {
      this.appConfigurationService.createCategory(this.category).subscribe({
        next: (res) => {
          this.dialogRef.close();
        },
        error: (err) => {
          console.log(err);
        },
      });
    } else {
      this.appConfigurationService.updateCategory(this.category).subscribe({
        next: (res) => {
          this.dialogRef.close();
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }
}
