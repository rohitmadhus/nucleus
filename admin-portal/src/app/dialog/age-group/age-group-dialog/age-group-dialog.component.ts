import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AgeGroup } from 'src/app/models/app/age-group';
import { AppConfigurationService } from 'src/app/services/app/app-configuration.service';

@Component({
  selector: 'app-age-group-dialog',
  templateUrl: './age-group-dialog.component.html',
  styleUrls: ['./age-group-dialog.component.css'],
})
export class AgeGroupDialogComponent {
  agegroup: AgeGroup;
  ageGroupForm: FormGroup;
  submitButton: string;

  constructor(
    @Inject(MAT_DIALOG_DATA) param: any,
    private appConfigurationService: AppConfigurationService,
    private dialogRef: MatDialogRef<AgeGroupDialogComponent>
  ) {
    this.agegroup = param['ageGroup'];
    this.ageGroupForm = new FormGroup({
      group: new FormControl(this.agegroup.group, [Validators.required]),
      ageMoreThan: new FormControl(this.agegroup.ageMoreThan, [
        Validators.required,
      ]),
    });

    if (this.agegroup && (!this.agegroup.id || this.agegroup.id.length == 0)) {
      this.submitButton = 'Save';
    } else {
      this.submitButton = 'Update';
    }
  }

  onSubmit() {
    this.agegroup.group = this.ageGroupForm.value['group'];
    this.agegroup.ageMoreThan = this.ageGroupForm.value['ageMoreThan'];
    if (this.agegroup && (!this.agegroup.id || this.agegroup.id.length == 0)) {
      this.appConfigurationService.createAgeGroup(this.agegroup).subscribe({
        next: (res) => {
          this.dialogRef.close();
        },
        error: (err) => {
          console.log(err);
        },
      });
    } else {
      this.appConfigurationService.updateAgeGroup(this.agegroup).subscribe({
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
