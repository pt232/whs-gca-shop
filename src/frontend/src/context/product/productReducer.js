import { DELETE_PRODUCT, GET_PRODUCTS, PRODUCTS_ERROR } from "../types";

export const productReducer = (state, action) => {
  switch (action.type) {
    case GET_PRODUCTS:
      return { ...state, products: action.payload, loading: false };
    case DELETE_PRODUCT:
      return {
        ...state,
        products: state.products.filter(
          (product) => product.id !== action.payload
        ),
      };
    case PRODUCTS_ERROR:
      return { ...state, error: action.payload, loading: false };
    default:
      return state;
  }
};
