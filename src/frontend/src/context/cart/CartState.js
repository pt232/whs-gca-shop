import axios from "axios";
import { createContext, useReducer } from "react";
import {
  ADD_CART_ENTRY,
  CART_ERROR,
  DELETE_CART_ENTRIES,
  GET_CART_ENTRIES,
  GET_CART_ENTRIES_AMOUNT,
  GET_TOTAL_COST,
} from "../types";
import { cartReducer } from "./cartReducer";

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const initialState = {
    cartEntries: [],
    cartEntriesAmount: 0,
    totalCost: 0,
    error: null,
    loading: true,
  };

  const [state, dispatch] = useReducer(cartReducer, initialState);

  const getCartEntriesAmount = async () => {
    try {
      const res = await axios.get("http://localhost:8081/api/v1/cart/amount/", {
        auth: {
          username: "user1",
          password: "user1Pass",
        },
      });

      dispatch({
        type: GET_CART_ENTRIES_AMOUNT,
        payload: res.data,
      });
    } catch {
      dispatch({
        type: CART_ERROR,
        payload:
          "Something went wrong while querying the number of shopping cart entries 😫",
      });
    }
  };

  const getTotalCost = async () => {
    try {
      const res = await axios.get("http://localhost:8081/api/v1/cart/cost/", {
        auth: {
          username: "user1",
          password: "user1Pass",
        },
      });

      dispatch({
        type: GET_TOTAL_COST,
        payload: res.data,
      });
    } catch {
      dispatch({
        type: CART_ERROR,
        payload:
          "Something went wrong while querying the total cost of shopping cart entries 😫",
      });
    }
  };

  const getCartEntries = async () => {
    try {
      const res = await axios.get("http://localhost:8081/api/v1/cart/", {
        auth: {
          username: "user1",
          password: "user1Pass",
        },
      });

      dispatch({
        type: GET_CART_ENTRIES,
        payload: res.data,
      });
    } catch {
      dispatch({
        type: CART_ERROR,
        payload:
          "Something went wrong while querying the shopping cart entries 😫",
      });
    }
  };

  const addCartEntry = async (productId) => {
    try {
      await axios.post(
        "http://localhost:8081/api/v1/cart/",
        {
          productId: productId,
        },
        {
          auth: {
            username: "user1",
            password: "user1Pass",
          },
        }
      );

      dispatch({
        type: ADD_CART_ENTRY,
        payload: state.cartEntriesAmount,
      });
    } catch {
      dispatch({
        type: CART_ERROR,
        payload: "Something went wrong while adding a shopping cart entry 😫",
      });
    }
  };

  const deleteCartEntries = async () => {
    try {
      await axios.delete("http://localhost:8081/api/v1/cart/", {
        auth: {
          username: "user1",
          password: "user1Pass",
        },
      });

      dispatch({
        type: DELETE_CART_ENTRIES,
      });
    } catch {
      dispatch({
        type: CART_ERROR,
        payload:
          "Something went wrong while removing the shopping cart entries 😫",
      });
    }
  };

  return (
    <CartContext.Provider
      value={{
        cartEntries: state.cartEntries,
        cartEntriesAmount: state.cartEntriesAmount,
        totalCost: state.totalCost,
        error: state.error,
        loading: state.loading,
        getCartEntriesAmount,
        getTotalCost,
        getCartEntries,
        addCartEntry,
        deleteCartEntries,
      }}
    >
      {children}
    </CartContext.Provider>
  );
};
