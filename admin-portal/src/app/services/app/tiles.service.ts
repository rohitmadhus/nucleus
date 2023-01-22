import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { APIConstants } from 'src/app/constants/api-constants';
import { Tile } from 'src/app/models/app/tile';
import { TileType } from 'src/app/models/app/tile-type';

@Injectable({
  providedIn: 'root',
})
export class TilesService {
  constructor(private http: HttpClient) {}

  // Tiles
  getTiles(id: string) {
    return this.http.get<Tile[]>(
      APIConstants.TILE_PAGINATE_URL.replaceAll('{key}', id).replaceAll(
        '{size}',
        APIConstants.PAGE_SIZE.toString()
      )
    );
  }

  createTile(tile: Tile) {
    return this.http.post<Tile>(APIConstants.TILE_URL, tile);
  }

  updateTile(tile: Tile) {
    return this.http.put<Tile>(APIConstants.TILE_URL, tile);
  }

  deleteTile(id: string) {
    return this.http.delete<TileType>(
      APIConstants.TILE_BY_ID_URL.replaceAll('{id}', id)
    );
  }

  // Tile types
  getTileTypes() {
    return this.http.get<TileType[]>(APIConstants.TILE_TYPE_GET_ALL_URL);
  }

  createTileType(tile: TileType) {
    return this.http.post<TileType>(APIConstants.TILE_TYPE_URL, tile);
  }

  updateTileType(tileType: TileType) {
    return this.http.put<TileType>(APIConstants.TILE_TYPE_URL, tileType);
  }

  deleteTileType(id: string) {
    return this.http.delete<TileType>(
      APIConstants.TILE_TYPE_BY_ID_URL.replaceAll('{id}', id)
    );
  }
}
