import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ContentType } from 'src/app/models/app/content-type';
import { ContentsService } from 'src/app/services/app/contents.service';

@Component({
  selector: 'app-content-type-dialog',
  templateUrl: './content-type-dialog.component.html',
  styleUrls: ['./content-type-dialog.component.css'],
})
export class ContentTypeDialogComponent {
  contentType: ContentType;
  contentTypeForm: FormGroup;
  submitButton: string;

  constructor(
    @Inject(MAT_DIALOG_DATA) param: any,
    private contentsService: ContentsService,
    private dialogRef: MatDialogRef<ContentTypeDialogComponent>
  ) {
    this.contentType = param['contentType'];
    this.contentTypeForm = new FormGroup({
      name: new FormControl(this.contentType.name, [
        Validators.required,
        Validators.pattern('[a-zA-Z0-9_ -]*'),
      ]),
      type: new FormControl({ value: this.contentType.type, disabled: true }, [
        Validators.required,
      ]),
    });

    this.contentTypeForm.controls['name'].valueChanges.subscribe((value) => {
      this.contentTypeForm.controls['type'].setValue(
        this.createTypeFromName(value)
      );
    });

    if (
      this.contentType &&
      (!this.contentType.id || this.contentType.id.length == 0)
    ) {
      this.submitButton = 'Save';
    } else {
      this.submitButton = 'Update';
    }
  }

  createTypeFromName(type: string) {
    return type.toLowerCase().trim().replaceAll(' ', '_').replaceAll('-', '_');
  }

  onSubmit() {
    this.contentType.name = this.contentTypeForm.value['name'];
    this.contentType.type = this.createTypeFromName(this.contentType.name);
    if (
      this.contentType &&
      (!this.contentType.id || this.contentType.id.length == 0)
    ) {
      this.contentsService.createContentType(this.contentType).subscribe({
        next: (res) => {
          this.dialogRef.close();
        },
        error: (err) => {
          console.log(err);
        },
      });
    } else {
      this.contentsService.updateContentType(this.contentType).subscribe({
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
