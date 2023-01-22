import { Ads } from './ads';
import { Customization } from './customization';
import { Image } from './image';

export interface Tile {
  id: string;
  title: string;
  viewOrder: number;
  createdDate: number;
  lastUpdatedDate: number;
  publishedDate: number;
  published: boolean;
  typeId: string;
  image: Image;
  customization: Customization;
  url: string;
  fallbackUrl: string;
  notification: Notification;
  ads: Ads;
  allowedAgeGroupId: string;
  categoryIds?: string[] | null;
}
