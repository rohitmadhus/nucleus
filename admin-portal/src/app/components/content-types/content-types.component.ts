import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/dialog/common/confirm-dialog/confirm-dialog.component';
import { ContentTypeDialogComponent } from 'src/app/dialog/content-type/content-type-dialog/content-type-dialog.component';
import { ContentType } from 'src/app/models/app/content-type';
import { ContentsService } from 'src/app/services/app/contents.service';

@Component({
  selector: 'app-content-types',
  templateUrl: './content-types.component.html',
  styleUrls: ['./content-types.component.css'],
})
export class ContentTypesComponent {
  contentTypes: ContentType[] = [];

  constructor(
    private contentsService: ContentsService,
    private dialogRef: MatDialog
  ) {
    this.getContentTypes();
  }

  getContentTypes() {
    this.contentsService.getContentTypes().subscribe((res) => {
      this.contentTypes = res;
    });
  }

  deleteContentType(id: string) {
    this.contentsService.deleteContentType(id).subscribe((res) => {
      this.getContentTypes();
    });
  }

  getDeleteMessage(contentType: ContentType) {
    return (
      "Are you sure you want to delete content type  '" +
      contentType.name +
      "' ?"
    );
  }

  openContentTypeDialog(contentType: ContentType) {
    this.dialogRef
      .open(ContentTypeDialogComponent, {
        height: '50%',
        width: '55%',
        closeOnNavigation: true,
        data: {
          contentType: contentType,
        },
      })
      .afterClosed()
      .subscribe(() => this.getContentTypes());
  }

  openConfirmDialog(contentType: ContentType) {
    this.dialogRef
      .open(ConfirmDialogComponent, {
        height: '300px',
        width: '400px',
        closeOnNavigation: true,
        data: {
          msg: this.getDeleteMessage(contentType),
        },
      })
      .afterClosed()
      .subscribe((response) => {
        if (response && response.data && response.data.confirm) {
          this.deleteContentType(contentType.id);
        }
      });
  }

  createNewContentType() {
    return <ContentType>{
      name: '',
      type: '',
      safeDelete: true,
    };
  }
}
