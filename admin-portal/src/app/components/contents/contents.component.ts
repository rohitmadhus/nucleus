import { Component, OnInit } from '@angular/core';
import { Content } from 'src/app/models/app/content';
import { ContentsService } from 'src/app/services/app/contents.service';
import { MatDialog } from '@angular/material/dialog';
import { ContentDialogComponent } from 'src/app/dialog/contents/content-dialog/content-dialog.component';
import { AgeGroup } from 'src/app/models/app/age-group';
import { ContentType } from 'src/app/models/app/content-type';
import { Category } from 'src/app/models/app/category';
import { AppConfigurationService } from 'src/app/services/app/app-configuration.service';
import { ConfirmDialogComponent } from 'src/app/dialog/common/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-contents',
  templateUrl: './contents.component.html',
  styleUrls: ['./contents.component.css'],
})
export class ContentsComponent {
  contents: Content[] = [];
  ageGroups: AgeGroup[] = [];
  contentTypes: ContentType[] = [];
  categories: Category[] = [];

  constructor(
    private contentsService: ContentsService,
    private appConfigService: AppConfigurationService,
    private dialogRef: MatDialog
  ) {
    this.getAgeGroups();
    this.getContentTypes();
    this.getCategories();
    this.getContents();
  }

  getContents(content?: Content) {
    this.contentsService
      .paginateContent(
        content ? content.id : 'initial',
        0,
        content ? content.createdQuarter : 'unknown'
      )
      .subscribe((res) => {
        if (content) {
          this.contents = [...this.contents, ...res];
        } else {
          this.contents = res;
        }
      });
  }

  getAgeGroups() {
    this.appConfigService.getAgeGroups().subscribe((res) => {
      this.ageGroups = res;
    });
  }

  findAgeGroup(id: string) {
    function match(ageGroup: AgeGroup, index: any, array: any) {
      return id == ageGroup.id;
    }
    return this.ageGroups.filter(match)[0].group;
  }

  getCategories() {
    this.appConfigService.getAllCategories().subscribe((res) => {
      this.categories = res;
    });
  }

  findCategory(id: string) {
    let categoryName;
    this.categories.forEach((category) => {
      if (category.id == id) {
        categoryName = category.name;
      }
    });
    return categoryName ? categoryName : 'unknown';
  }

  getContentTypes() {
    this.contentsService.getContentTypes().subscribe((res) => {
      this.contentTypes = res;
    });
  }

  findContentType(id: string) {
    function match(contentType: ContentType, index: any, array: any) {
      return id == contentType.id;
    }
    return this.contentTypes.filter(match)[0].name;
  }

  openContentDialog(content: Content) {
    this.dialogRef
      .open(ContentDialogComponent, {
        height: '80%',
        width: '75%',
        closeOnNavigation: true,
        data: {
          content: content,
          ageGroups: this.ageGroups,
          categories: this.categories,
          contentTypes: this.contentTypes,
        },
      })
      .afterClosed()
      .subscribe(() => this.getContents());
  }

  getDeleteMessage(content: Content) {
    return "Are you sure you want to delete content '" + content.title + "' ?";
  }

  getPublishMessage(content: Content) {
    return "Are you sure you want to publish content '" + content.title + "' ?";
  }

  deleteContent(content: Content, component: this) {
    this.contentsService.deleteContent(content.id).subscribe((res) => {
      component.contents = this.contents.filter((item) => item !== content);
    });
  }

  publishContent(content: Content, component: this) {
    component.contentsService.publishContent(content).subscribe((res) => {});
  }

  openConfirmDialog(content: Content, msg: String, confirmCallback: Function) {
    this.dialogRef
      .open(ConfirmDialogComponent, {
        height: '300px',
        width: '400px',
        closeOnNavigation: true,
        data: {
          msg: msg,
        },
      })
      .afterClosed()
      .subscribe((response) => {
        if (response && response.data && response.data.confirm) {
          confirmCallback(content, this);
        }
      });
  }

  createNewContent() {
    return <Content>{
      title: '',
      subTitle: '',
      body: '',
      image: { url: '' },
      ads: {
        enableGoogleAdMob: false,
      },
      share: {
        url: '',
      },
      webReference: {
        url: '',
      },
      author: '',
      published: false,
      typeId: '',
      categoryId: '',
      likes: 0,
      saves: 0,
      allowedAgeGroupId: '',
    };
  }
}
