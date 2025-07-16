export type Pagination = {
  size: number;
  page: number;
}

export default Pagination;

export const defaultPagination: Pagination = {
  size: 20,
  page: 0,
}