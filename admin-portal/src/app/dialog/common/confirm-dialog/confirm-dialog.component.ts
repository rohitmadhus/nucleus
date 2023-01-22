import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css'],
})
export class ConfirmDialogComponent {
  msg: string;
  constructor(
    @Inject(MAT_DIALOG_DATA) param: any,
    private dialogRef: MatDialogRef<ConfirmDialogComponent>
  ) {
    this.msg = param.msg;
  }

  onSubmit(confirm: boolean) {
    this.dialogRef.close({
      data: {
        confirm: confirm,
      },
    });
  }
}
