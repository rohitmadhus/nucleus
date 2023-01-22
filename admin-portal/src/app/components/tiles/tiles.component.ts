import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/dialog/common/confirm-dialog/confirm-dialog.component';
import { TileDialogComponent } from 'src/app/dialog/tiles/tile-dialog/tile-dialog.component';
import { AgeGroup } from 'src/app/models/app/age-group';
import { Category } from 'src/app/models/app/category';
import { Tile } from 'src/app/models/app/tile';
import { TileType } from 'src/app/models/app/tile-type';
import { AppConfigurationService } from 'src/app/services/app/app-configuration.service';
import { TilesService } from 'src/app/services/app/tiles.service';

@Component({
  selector: 'app-tiles',
  templateUrl: './tiles.component.html',
  styleUrls: ['./tiles.component.css'],
})
export class TilesComponent {
  tiles: Tile[] = [];
  ageGroups: AgeGroup[] = [];
  tileTypes: TileType[] = [];
  categories: Category[] = [];

  constructor(
    private tilesService: TilesService,
    private appConfigService: AppConfigurationService,
    private dialogRef: MatDialog
  ) {
    this.getAgeGroups();
    this.getTileTypes();
    this.getCategories();
    this.getTiles();
  }

  getTiles(tile?: Tile) {
    this.tilesService.getTiles(tile ? tile.id : 'initial').subscribe((res) => {
      if (res) {
        this.tiles = [...this.tiles, ...res];
      } else {
        this.tiles = res;
      }
    });
  }

  loadMore() {
    if (this.tiles && this.tiles.length > 0) {
      this.getTiles(this.tiles[this.tiles.length - 1]);
    }
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

  findCategories(ids: string[]) {
    function match(category: Category, index: any, array: any) {
      return ids.includes(category.id);
    }
    let categoryNames = new Array() as Array<String>;
    this.categories
      .filter(match)
      .forEach((category) => categoryNames.push(category.name));
    return categoryNames;
  }

  getTileTypes() {
    this.tilesService.getTileTypes().subscribe((res) => {
      this.tileTypes = res;
    });
  }

  findTileType(id: string) {
    function match(tileType: TileType, index: any, array: any) {
      return id == tileType.id;
    }
    return this.tileTypes.filter(match)[0].name;
  }

  openContentDialog(tile: Tile) {
    this.dialogRef
      .open(TileDialogComponent, {
        height: '80%',
        width: '75%',
        closeOnNavigation: true,
        data: {
          tile: tile,
          ageGroups: this.ageGroups,
          categories: this.categories,
          tileTypes: this.tileTypes,
        },
      })
      .afterClosed()
      .subscribe(() => this.getTiles());
  }

  getDeleteMessage(tile: Tile) {
    return "Are you sure you want to delete tile '" + tile.title + "' ?";
  }

  getPublishMessage(tile: Tile) {
    return "Are you sure you want to publish content '" + tile.title + "' ?";
  }

  openConfirmDialog(tile: Tile, msg: String) {
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
        }
      });
  }

  createNewTile() {
    return <Tile>{
      title: '',
      published: false,
      image: {},
      ads: {},
      customization: {},
      typeId: '',
      categoryIds: new Array<string>(),
      allowedAgeGroupId: '1',
    };
  }
}
