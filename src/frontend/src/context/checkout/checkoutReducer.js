import { CHECKOUT_ERROR, GET_CHECKOUT_ORDER } from "../types";

export const checkoutReducer = (state, action) => {
  switch (action.type) {
    case GET_CHECKOUT_ORDER:
      return {
        ...state,
        products: action.payload.products,
        order: action.payload.order,
      };
    case CHECKOUT_ERROR:
      return { ...state, error: action.payload };
    default:
      return state;
  }
};
