import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TileType } from 'src/app/models/app/tile-type';
import { TilesService } from 'src/app/services/app/tiles.service';

@Component({
  selector: 'app-tile-type-dialog',
  templateUrl: './tile-type-dialog.component.html',
  styleUrls: ['./tile-type-dialog.component.css'],
})
export class TileTypeDialogComponent {
  tileType: TileType;
  tileTypeForm: FormGroup;
  submitButton: string;

  constructor(
    @Inject(MAT_DIALOG_DATA) param: any,
    private tilesService: TilesService,
    private dialogRef: MatDialogRef<TileTypeDialogComponent>
  ) {
    this.tileType = param['tileType'];
    this.tileTypeForm = new FormGroup({
      name: new FormControl(this.tileType.name, [
        Validators.required,
        Validators.pattern('[a-zA-Z0-9_ -]*'),
      ]),
      type: new FormControl({ value: this.tileType.type, disabled: true }, [
        Validators.required,
      ]),
    });

    this.tileTypeForm.controls['name'].valueChanges.subscribe((value) => {
      this.tileTypeForm.controls['type'].setValue(
        this.createTypeFromName(value)
      );
    });

    if (this.tileType && (!this.tileType.id || this.tileType.id.length == 0)) {
      this.submitButton = 'Save';
    } else {
      this.submitButton = 'Update';
    }
  }

  createTypeFromName(type: string) {
    return type.toLowerCase().trim().replaceAll(' ', '_').replaceAll('-', '_');
  }

  onSubmit() {
    this.tileType.name = this.tileTypeForm.value['name'];
    this.tileType.type = this.createTypeFromName(this.tileType.name);
    if (this.tileType && (!this.tileType.id || this.tileType.id.length == 0)) {
      this.tilesService.createTileType(this.tileType).subscribe({
        next: (res) => {
          this.dialogRef.close();
        },
        error: (err) => {
          console.log(err);
        },
      });
    } else {
      this.tilesService.updateTileType(this.tileType).subscribe({
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
