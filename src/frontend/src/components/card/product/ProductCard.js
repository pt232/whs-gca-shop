import React, { useContext } from "react";
import { CartContext } from "../../../context/cart/CartState";
import { ProductContext } from "../../../context/product/ProductState";
import "./ProductCard.css";

const ProductCard = ({ product, orientation, small }) => {
  const { addCartEntry } = useContext(CartContext);
  const { deleteProduct } = useContext(ProductContext);

  const handleClick = () => {
    if (orientation !== "horizontal") {
      addCartEntry(product.id);
      deleteProduct(product.id);
    }
  };

  return (
    <div
      className={`product-card ${
        orientation === "horizontal" ? "product-card--horizontal" : ""
      }`}
      onClick={() => handleClick()}
    >
      <div
        style={small ? { width: "16rem", height: "16rem" } : {}}
        className="product-card__wrapper"
      >
        <img
          src={product.image}
          alt="Product 1"
          className="product-card__img"
        />
        <div className="product-card__overlay" />
      </div>
      <div
        style={small ? { padding: "3.5rem 4rem 3.5rem 2rem" } : {}}
        className="product-card__body"
      >
        <h3 className="product-card__title">{product.name}</h3>
        <p className="product-card__price">USD {product.price}</p>
      </div>
    </div>
  );
};

export default ProductCard;
