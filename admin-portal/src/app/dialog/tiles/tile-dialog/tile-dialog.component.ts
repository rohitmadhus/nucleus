import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AgeGroup } from 'src/app/models/app/age-group';
import { Category } from 'src/app/models/app/category';
import { Tile } from 'src/app/models/app/tile';
import { TileType } from 'src/app/models/app/tile-type';
import { TilesService } from 'src/app/services/app/tiles.service';

@Component({
  selector: 'app-tile-dialog',
  templateUrl: './tile-dialog.component.html',
  styleUrls: ['./tile-dialog.component.css'],
})
export class TileDialogComponent {
  tile: Tile;
  ageGroups: AgeGroup[] = [];
  tileTypes: TileType[] = [];
  categories: Category[] = [];
  tileForm: FormGroup;
  submitButton: string;

  constructor(
    @Inject(MAT_DIALOG_DATA) param: any,
    private tilesService: TilesService,
    private dialogRef: MatDialogRef<TileDialogComponent>
  ) {
    this.tile = param['tile'];
    this.ageGroups = param['ageGroups'];
    this.tileTypes = param['tileTypes'];
    this.categories = param['categories'];

    this.tileForm = new FormGroup({
      title: new FormControl(this.tile.title, [Validators.required]),
      typeId: new FormControl(this.tile.typeId, [Validators.required]),
      categoryIds: new FormControl(this.tile.categoryIds, [
        Validators.required,
      ]),
      imageUrl: new FormControl(this.tile.image.url, [
        Validators.pattern(
          '((https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?)|^$'
        ),
      ]),
      url: new FormControl(this.tile.url, [
        Validators.pattern(
          '((https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?)|^$'
        ),
      ]),
      fallbackUrl: new FormControl(this.tile.fallbackUrl, [
        Validators.pattern(
          '((https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?)|^$'
        ),
      ]),
      enableGoogleAds: new FormControl(this.tile.ads.enableGoogleAdMob),
      allowedAgeGroupId: new FormControl(this.tile.allowedAgeGroupId, [
        Validators.required,
      ]),
      bg1ColorHex: new FormControl(this.tile.customization.bg1ColorHex),
      bg2ColorHex: new FormControl(this.tile.customization.bg2ColorHex),
      textColorHex: new FormControl(this.tile.customization.textColorHex),
    });
    if (this.tile && (!this.tile.id || this.tile.id.length == 0)) {
      this.submitButton = 'Save';
    } else {
      this.submitButton = 'Update';
    }
  }

  onSubmit() {
    this.tile.title = this.tileForm.value['title'];
    this.tile.typeId = this.tileForm.value['typeId'];
    this.tile.categoryIds = this.tileForm.value['categoryIds'];
    this.tile.image.url = this.tileForm.value['imageUrl'];
    this.tile.url = this.tileForm.value['url'];
    this.tile.fallbackUrl = this.tileForm.value['fallbackUrl'];
    this.tile.ads.enableGoogleAdMob = this.tileForm.value['enableGoogleAds'];
    this.tile.allowedAgeGroupId = this.tileForm.value['allowedAgeGroupId'];
    this.tile.customization.bg1ColorHex = this.tileForm.value['bg1ColorHex'];
    this.tile.customization.bg2ColorHex = this.tileForm.value['bg2ColorHex'];
    this.tile.customization.textColorHex = this.tileForm.value['textColorHex'];

    if (this.tile && (!this.tile.id || this.tile.id.length == 0)) {
      this.tilesService.createTile(this.tile).subscribe({
        next: (res) => {
          this.dialogRef.close();
        },
        error: () => {
          console.log('error');
        },
      });
    } else {
      this.tilesService.updateTile(this.tile).subscribe({
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
