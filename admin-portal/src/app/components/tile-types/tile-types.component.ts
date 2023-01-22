import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/dialog/common/confirm-dialog/confirm-dialog.component';
import { TileTypeDialogComponent } from 'src/app/dialog/tile-type/tile-type-dialog/tile-type-dialog.component';
import { Tile } from 'src/app/models/app/tile';
import { TileType } from 'src/app/models/app/tile-type';
import { TilesService } from 'src/app/services/app/tiles.service';

@Component({
  selector: 'app-tile-types',
  templateUrl: './tile-types.component.html',
  styleUrls: ['./tile-types.component.css'],
})
export class TileTypesComponent {
  tileTypes: TileType[] = [];

  constructor(
    private tilesService: TilesService,
    private dialogRef: MatDialog
  ) {
    this.getTileTypes();
  }

  getTileTypes() {
    this.tilesService.getTileTypes().subscribe((res) => {
      this.tileTypes = res;
    });
  }

  deleteTileType(id: string) {
    this.tilesService.deleteTileType(id).subscribe((res) => {
      this.getTileTypes();
    });
  }

  getDeleteMessage(tileType: TileType) {
    return (
      "Are you sure you want to delete tile type  '" + tileType.name + "' ?"
    );
  }

  openTileTypeDialog(tileType: TileType) {
    this.dialogRef
      .open(TileTypeDialogComponent, {
        height: '50%',
        width: '55%',
        closeOnNavigation: true,
        data: {
          tileType: tileType,
        },
      })
      .afterClosed()
      .subscribe(() => this.getTileTypes());
  }

  openConfirmDialog(tileType: TileType) {
    this.dialogRef
      .open(ConfirmDialogComponent, {
        height: '300px',
        width: '400px',
        closeOnNavigation: true,
        data: {
          msg: this.getDeleteMessage(tileType),
        },
      })
      .afterClosed()
      .subscribe((response) => {
        if (response && response.data && response.data.confirm) {
          this.deleteTileType(tileType.id);
        }
      });
  }

  createNewTileType() {
    return <TileType>{
      name: '',
      type: '',
      safeDelete: true,
    };
  }
}
