import { createContext, useReducer } from "react";
import axios from "axios";
import { DELETE_PRODUCT, GET_PRODUCTS, PRODUCTS_ERROR } from "../types";
import { productReducer } from "./productReducer";

export const ProductContext = createContext();

export const ProductProvider = ({ children }) => {
  const initialState = {
    products: [],
    error: null,
    loading: true,
  };

  const [state, dispatch] = useReducer(productReducer, initialState);

  const getProducts = async () => {
    try {
      const res = await axios.get("http://localhost:8080/api/v1/products/", {
        auth: {
          username: "user1",
          password: "user1Pass",
        },
      });

      dispatch({
        type: GET_PRODUCTS,
        payload: res.data,
      });
    } catch {
      dispatch({
        type: PRODUCTS_ERROR,
        payload: "Something went wrong while querying the products 😫",
      });
    }
  };

  const deleteProduct = async (productId) => {
    try {
      await axios.delete(`http://localhost:8080/api/v1/products/${productId}`, {
        auth: {
          username: "user1",
          password: "user1Pass",
        },
      });

      dispatch({
        type: DELETE_PRODUCT,
        payload: productId,
      });
    } catch {
      dispatch({
        type: PRODUCTS_ERROR,
        payload:
          "Something went wrong while removing a product from the catalog 😫",
      });
    }
  };

  return (
    <ProductContext.Provider
      value={{
        products: state.products,
        error: state.error,
        loading: state.loading,
        getProducts,
        deleteProduct,
      }}
    >
      {children}
    </ProductContext.Provider>
  );
};
