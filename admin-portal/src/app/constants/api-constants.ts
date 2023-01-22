export class APIConstants {
  static readonly BASE_URL = 'http://localhost:8080';

  static readonly PAGE_SIZE = 5;

  //login
  static readonly LOGIN_URL = APIConstants.BASE_URL + '/auth/login';
  static readonly LOGOUT_URL = APIConstants.BASE_URL + '/auth/logout';

  //Contents API
  static readonly CONTENT_URL = APIConstants.BASE_URL + '/content';

  static readonly CONTENT_BY_ID_URL = APIConstants.CONTENT_URL + '/{id}';
  static readonly CONTENT_PUBLISH_URL =
    APIConstants.CONTENT_URL + '/publish/{id}';
  static readonly CONTENT_PAGINATE_URL =
    APIConstants.CONTENT_URL +
    '/index/{index}/key/{key}/size/{size}/partition/{partition}';

  //Category API
  static readonly CATEGORY_URL = APIConstants.BASE_URL + '/category';
  static readonly CATEGORY_GET_ALL_URL = APIConstants.CATEGORY_URL + '/all';
  static readonly CATEGORY_BY_ID_URL = APIConstants.CATEGORY_URL + '/{id}';
  static readonly CATEGORY_PAGINATE_URL =
    APIConstants.CATEGORY_URL + '/key/{key}/size/{size}';

  // Age group
  static readonly AGE_GROUP_URL = APIConstants.BASE_URL + '/age/group';
  static readonly AGE_GROUP_GET_ALL_URL = APIConstants.AGE_GROUP_URL + '/all';
  static readonly AGE_GROUP_BY_ID_URL = APIConstants.AGE_GROUP_URL + '/{id}';

  // Tile type
  static readonly TILE_TYPE_URL = APIConstants.BASE_URL + '/tile/type';
  static readonly TILE_TYPE_GET_ALL_URL = APIConstants.TILE_TYPE_URL + '/all';
  static readonly TILE_TYPE_BY_ID_URL = APIConstants.TILE_TYPE_URL + '/{id}';

  // Content type
  static readonly CONTENT_TYPE_URL = APIConstants.BASE_URL + '/content/type';
  static readonly CONTENT_TYPE_GET_ALL_URL =
    APIConstants.CONTENT_TYPE_URL + '/all';
  static readonly CONTENT_TYPE_BY_ID_URL =
    APIConstants.CONTENT_TYPE_URL + '/{id}';

  //Tile API
  static readonly TILE_URL = APIConstants.BASE_URL + '/tile';
  static readonly TILE_BY_ID_URL = APIConstants.TILE_URL + '/{id}';
  static readonly TILE_PAGINATE_URL =
    APIConstants.TILE_URL + '/key/{key}/size/{size}';
}
