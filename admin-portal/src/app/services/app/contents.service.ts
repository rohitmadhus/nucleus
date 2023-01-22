import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { APIConstants } from 'src/app/constants/api-constants';
import { Content } from 'src/app/models/app/content';
import { ContentType } from 'src/app/models/app/content-type';

@Injectable({
  providedIn: 'root',
})
export class ContentsService {
  constructor(private http: HttpClient) {}

  createContent(content: Content) {
    return this.http.post<Content>(APIConstants.CONTENT_URL, content);
  }

  updateContent(content: Content) {
    return this.http.put<Content>(APIConstants.CONTENT_URL, content);
  }

  deleteContent(id: string) {
    return this.http.delete<Content>(
      APIConstants.CONTENT_BY_ID_URL.replaceAll('{id}', id)
    );
  }

  publishContent(content: Content) {
    return this.http.put<Content>(
      APIConstants.CONTENT_PUBLISH_URL.replaceAll('{id}', content.id),
      content
    );
  }

  paginateContent(lastKey: string, index: number, partition: string) {
    return this.http.get<Content[]>(
      APIConstants.CONTENT_PAGINATE_URL.replace('{key}', lastKey)
        .replaceAll('{size}', APIConstants.PAGE_SIZE.toString())
        .replace('{index}', index.toString())
        .replaceAll('{partition}', partition)
    );
  }

  // paginateContent(
  //   lastKey: string,
  //   published: boolean,
  //   index: number,
  //   partition: string
  // ) {
  //   return this.http.get<Content[]>(
  //     APIConstants.CONTENT_PAGINATE_URL.replace('{key}', lastKey)
  //       .replaceAll('{size}', APIConstants.PAGE_SIZE.toString())
  //       .replace('{index}', index.toString())
  //       .replaceAll('{partition}', partition),
  //     {
  //       params: {
  //         categoryIds: categoryIds.toString(),
  //         published: published,
  //       },
  //     }
  //   );
  // }

  // content types
  getContentTypes() {
    return this.http.get<ContentType[]>(APIConstants.CONTENT_TYPE_GET_ALL_URL);
  }

  createContentType(contentType: ContentType) {
    return this.http.post<ContentType>(
      APIConstants.CONTENT_TYPE_URL,
      contentType
    );
  }

  updateContentType(contentType: ContentType) {
    return this.http.put<ContentType>(
      APIConstants.CONTENT_TYPE_URL,
      contentType
    );
  }

  deleteContentType(id: string) {
    return this.http.delete<ContentType>(
      APIConstants.CONTENT_TYPE_BY_ID_URL.replaceAll('{id}', id)
    );
  }
}
