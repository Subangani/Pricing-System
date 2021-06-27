import request from '@/utils/request';

export async function calculatePrice(params) {
  return request('/api/calculatePrice', {
    method: 'POST',
    data: params,
  });
}
export async function getProductList() {
  return request(`/api/getProductList`);
}

export async function getProductPriceRange() {
  return request(`/api/getProductRangeList`);
}


