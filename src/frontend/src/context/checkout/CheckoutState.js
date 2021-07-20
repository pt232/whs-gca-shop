import axios from "axios";
import React, { createContext, useReducer } from "react";
import { CHECKOUT_ERROR, GET_CHECKOUT_ORDER } from "../types";
import { checkoutReducer } from "./checkoutReducer";

export const CheckoutContext = createContext();

export const CheckoutProvider = ({ children }) => {
  const initialState = {
    order: null,
    products: [],
    error: null,
  };

  const [state, dispatch] = useReducer(checkoutReducer, initialState);

  const getOrderDetails = async () => {
    try {
      const res = await axios.get(`${process.env.REACT_APP_CHECKOUT_SERVICE}`, {
        auth: {
          username: process.env.REACT_APP_AUTH_USERNAME,
          password: process.env.REACT_APP_AUTH_PASSWORD,
        },
      });

      dispatch({
        type: GET_CHECKOUT_ORDER,
        payload: res.data,
      });
    } catch {
      dispatch({
        type: CHECKOUT_ERROR,
        payload: "Something went wrong while adding the order details 😫",
      });
    }
  };

  return (
    <CheckoutContext.Provider
      value={{
        order: state.order,
        products: state.products,
        getOrderDetails,
      }}
    >
      {children}
    </CheckoutContext.Provider>
  );
};
