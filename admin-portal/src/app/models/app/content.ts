import { Ads } from './ads';
import { Image } from './image';

export interface Content {
  id: string;
  createdQuarter: string;
  publishedQuarter: string;
  title: string;
  subTitle: string;
  body: string;
  author: string;
  createdDate: number;
  lastUpdatedDate: number;
  publishedDate: number;
  published: boolean;
  web: Web;
  typeId: string;
  categoryId: string;
  notification: Notification;
  webReference: WebReferenceOrShare;
  image: Image;
  likes: number;
  saves: number;
  share: WebReferenceOrShare;
  ads: Ads;
  allowedAgeGroupId: string;
  subContentIds?: string[] | null;
}
export interface Web {
  published: boolean;
  publishedDate: number;
}
export interface Notification {
  allowed: boolean;
  scheduledDate: number;
}
export interface WebReferenceOrShare {
  url: string;
  label: string;
}
