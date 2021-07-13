import React from "react";
import { Link } from "react-router-dom";
import CostCard from "../../components/card/cost/CostCard";
import ProductCard from "../../components/card/product/ProductCard";
import CheckoutForm from "../../components/form/CheckoutForm";
import "./CartPage.css";

const CartPage = () => {
  return (
    <section className="cart">
      <div className="container">
        <div className="cart__header">
          <h1 className="cart__title">1 item in your cart</h1>
          <div className="cart__options">
            <button className="cart__btn btn btn--gray">Empty Cart</button>
            <Link to="/" className="btn btn--cyan">
              Keep Browsing
            </Link>
          </div>
        </div>
        <div className="cart__body">
          <ProductCard orientation="horizontal" />
          <ProductCard orientation="horizontal" />
          <CostCard />
        </div>
        <div className="cart__form">
          <h2 className="cart__title">Checkout</h2>
          <CheckoutForm />
        </div>
      </div>
    </section>
  );
};

export default CartPage;
