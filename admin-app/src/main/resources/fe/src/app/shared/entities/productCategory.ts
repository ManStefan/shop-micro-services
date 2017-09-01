export class ProductCategory{
  id: number;
  name: string;
  description: string;
  level: number;
  parentCategory: number;
  childCategories: number[];
  parentCategoryName: string;
  childCategoriesName: [number, string][];
}
