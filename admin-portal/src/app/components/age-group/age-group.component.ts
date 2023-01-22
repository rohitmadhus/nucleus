import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AgeGroupDialogComponent } from 'src/app/dialog/age-group/age-group-dialog/age-group-dialog.component';
import { ConfirmDialogComponent } from 'src/app/dialog/common/confirm-dialog/confirm-dialog.component';
import { AgeGroup } from 'src/app/models/app/age-group';
import { AppConfigurationService } from 'src/app/services/app/app-configuration.service';

@Component({
  selector: 'app-age-group',
  templateUrl: './age-group.component.html',
  styleUrls: ['./age-group.component.css'],
})
export class AgeGroupComponent {
  ageGroups: AgeGroup[] = [];

  constructor(
    private appConfigurationService: AppConfigurationService,
    private dialogRef: MatDialog
  ) {
    this.getAgeGroups();
  }

  getAgeGroups() {
    this.appConfigurationService.getAgeGroups().subscribe((res) => {
      this.ageGroups = res;
    });
  }

  deleteAgeGroup(id: string) {
    this.appConfigurationService.deleteAgeGroup(id).subscribe((res) => {
      this.getAgeGroups();
    });
  }

  getDeleteMessage(ageGroup: AgeGroup) {
    return (
      "Are you sure you want to delete content type  '" + ageGroup.group + "' ?"
    );
  }

  openContentTypeDialog(ageGroup: AgeGroup) {
    this.dialogRef
      .open(AgeGroupDialogComponent, {
        height: '50%',
        width: '55%',
        closeOnNavigation: true,
        data: {
          ageGroup: ageGroup,
        },
      })
      .afterClosed()
      .subscribe(() => this.getAgeGroups());
  }

  openConfirmDialog(ageGroup: AgeGroup) {
    this.dialogRef
      .open(ConfirmDialogComponent, {
        height: '300px',
        width: '400px',
        closeOnNavigation: true,
        data: {
          msg: this.getDeleteMessage(ageGroup),
        },
      })
      .afterClosed()
      .subscribe((response) => {
        if (response && response.data && response.data.confirm) {
          this.deleteAgeGroup(ageGroup.id);
        }
      });
  }

  createNewContentType() {
    return <AgeGroup>{
      group: '',
      ageMoreThan: 0,
    };
  }
}
