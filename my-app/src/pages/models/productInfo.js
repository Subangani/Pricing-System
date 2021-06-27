import { calculatePrice, getProductList,getProductPriceRange } from '@/services/api';


export default {
  namespace: 'productInfo',

  state: {
    productList: [],
    productOrderList : []
  },

  effects: {
    *fetchInitialData({}, { call, put }) {
      const response = yield call(getProductList);
      yield put({
        type: 'productList',
        payload: Array.isArray(response) ? response : [],
      });

    },
    *fetchData({}, { call, put }) {

      const response = yield call(getProductPriceRange);
      yield put({
        type: 'priceRange',
        payload:  Array.isArray(response) ? response : [],
      });

    },
    *calculatePrice({payload}, { call, put }) {
      const response = yield call(calculatePrice,payload );
      yield put({
        type: 'price',
        payload: response ,
      });
    },

  },

  reducers: {
    productList(state, action) {
      return {
        ...state,
        productList: action.payload,
      };
    },
    price(state, action) {
      return {
        ...state,
        price: action.payload,
      };
    },
    priceRange(state, action) {
      return {
        ...state,
        productOrderList: action.payload,
      };
    },

  },
};
