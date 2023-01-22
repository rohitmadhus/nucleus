import { Component, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AgeGroup } from 'src/app/models/app/age-group';
import { Category } from 'src/app/models/app/category';
import { Content } from 'src/app/models/app/content';
import { ContentType } from 'src/app/models/app/content-type';
import { ContentsService } from 'src/app/services/app/contents.service';

@Component({
  selector: 'app-content-dialog',
  templateUrl: './content-dialog.component.html',
  styleUrls: ['./content-dialog.component.css'],
})
export class ContentDialogComponent {
  content: Content;
  ageGroups: AgeGroup[] = [];
  contentTypes: ContentType[] = [];
  categories: Category[] = [];
  contentForm: FormGroup;
  submitButton: string;

  constructor(
    @Inject(MAT_DIALOG_DATA) param: any,
    private contentsService: ContentsService,
    private dialogRef: MatDialogRef<ContentDialogComponent>
  ) {
    this.content = param['content'];
    this.ageGroups = param['ageGroups'];
    this.contentTypes = param['contentTypes'];
    this.categories = param['categories'];
    this.contentForm = new FormGroup({
      title: new FormControl(this.content.title, [Validators.required]),
      body: new FormControl(this.content.body, [Validators.required]),
      typeId: new FormControl(this.content.typeId, [Validators.required]),
      author: new FormControl(this.content.author),
      categoryId: new FormControl(this.content.categoryId, [
        Validators.required,
      ]),
      webReference: new FormControl(this.content.webReference.url, [
        Validators.required,
        Validators.pattern(
          '(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?'
        ),
      ]),
      imageUrl: new FormControl(this.content.image.url, [
        Validators.required,
        Validators.pattern(
          '(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?'
        ),
      ]),
      enableGoogleAds: new FormControl(this.content.ads.enableGoogleAdMob),
      shareUrl: new FormControl(this.content.share.url, [
        Validators.pattern(
          '((https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?)|^$'
        ),
      ]),
      allowedAgeGroupId: new FormControl(this.content.allowedAgeGroupId, [
        Validators.required,
      ]),
    });
    if (this.content && (!this.content.id || this.content.id.length == 0)) {
      this.submitButton = 'Save';
    } else {
      this.submitButton = 'Update';
    }
  }

  onSubmit() {
    this.content.title = this.contentForm.value['title'];
    this.content.body = this.contentForm.value['body'];
    this.content.typeId = this.contentForm.value['typeId'];
    this.content.author = this.contentForm.value['author'];
    this.content.categoryId = this.contentForm.value['categoryId'];
    this.content.webReference.url = this.contentForm.value['webReference'];
    this.content.image.url = this.contentForm.value['imageUrl'];
    this.content.ads.enableGoogleAdMob =
      this.contentForm.value['enableGoogleAds'];
    this.content.share.url = this.contentForm.value['shareUrl'];
    this.content.allowedAgeGroupId =
      this.contentForm.value['allowedAgeGroupId'];

    if (this.content && (!this.content.id || this.content.id.length == 0)) {
      this.contentsService.createContent(this.content).subscribe({
        next: (res) => {
          this.dialogRef.close();
        },
        error: () => {
          console.log('error');
        },
      });
    } else {
      this.contentsService.updateContent(this.content).subscribe({
        next: (res) => {
          this.dialogRef.close();
        },
        error: () => {
          console.log('error');
        },
      });
    }
  }
}
