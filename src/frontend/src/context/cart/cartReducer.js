import {
  ADD_CART_ENTRY,
  CART_ERROR,
  DELETE_CART_ENTRIES,
  GET_CART_ENTRIES,
  GET_CART_ENTRIES_AMOUNT,
  GET_TOTAL_COST,
} from "../types";

export const cartReducer = (state, action) => {
  switch (action.type) {
    case GET_CART_ENTRIES_AMOUNT:
      return { ...state, cartEntriesAmount: action.payload };
    case GET_TOTAL_COST:
      return { ...state, totalCost: action.payload };
    case GET_CART_ENTRIES:
      return { ...state, cartEntries: action.payload, loading: false };
    case ADD_CART_ENTRY:
      return { ...state, cartEntriesAmount: action.payload + 1 };
    case DELETE_CART_ENTRIES:
      return { ...state, cartEntries: [], cartEntriesAmount: 0 };
    case CART_ERROR:
      return { ...state, error: action.payload };
    default:
      return state;
  }
};
