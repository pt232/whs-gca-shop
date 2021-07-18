import axios from "axios";
import React, { createContext, useReducer } from "react";
import { GET_SHIPPING_COST, SHIPPING_ERROR } from "../types";
import { shippingReducer } from "./shippingReducer";

export const ShippingContext = createContext();

export const ShippingProvider = ({ children }) => {
  const initialState = {
    shippingCost: 0.0,
    error: null,
  };

  const [state, dispatch] = useReducer(shippingReducer, initialState);

  const getShippingCost = async () => {
    try {
      const res = await axios.get("http://localhost:8082/api/v1/shipping/", {
        auth: {
          username: "user1",
          password: "user1Pass",
        },
      });

      dispatch({
        type: GET_SHIPPING_COST,
        payload: res.data,
      });
    } catch {
      dispatch({
        type: SHIPPING_ERROR,
        payload: "Something went wrong while calculating the shipping cost 😫",
      });
    }
  };

  return (
    <ShippingContext.Provider
      value={{ shippingCost: state.shippingCost, getShippingCost }}
    >
      {children}
    </ShippingContext.Provider>
  );
};
