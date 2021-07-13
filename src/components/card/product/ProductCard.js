import React from "react";
import productImage from "../../../images/product-1.jpg";
import "./ProductCard.css";

const ProductCard = ({ orientation }) => {
  return (
    <div
      className={`product-card ${
        orientation === "horizontal" ? "product-card--horizontal" : ""
      }`}
    >
      <div className="product-card__wrapper">
        <img src={productImage} alt="Product 1" className="product-card__img" />
        <div className="product-card__overlay" />
      </div>
      <div className="product-card__body">
        <h3 className="product-card__title">Vintage Typewriter</h3>
        <p className="product-card__price">USD 67.98</p>
      </div>
    </div>
  );
};

export default ProductCard;
