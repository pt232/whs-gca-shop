import { GET_SHIPPING_COST, SHIPPING_ERROR } from "../types";

export const shippingReducer = (state, action) => {
  switch (action.type) {
    case GET_SHIPPING_COST:
      return { ...state, shippingCost: action.payload };
    case SHIPPING_ERROR:
      return { ...state, error: action.payload };
    default:
      return state;
  }
};
