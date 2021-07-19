import React, { useContext, useEffect } from "react";
import { Link } from "react-router-dom";
import { CartContext } from "../../context/cart/CartState";
import { ShippingContext } from "../../context/shipping/ShippingState";
import CostCard from "../../components/card/cost/CostCard";
import ProductCard from "../../components/card/product/ProductCard";
import CheckoutForm from "../../components/form/CheckoutForm";
import "./CartPage.css";
import EmptyCard from "../../components/card/empty/EmptyCard";

const CartPage = () => {
  const {
    cartEntriesAmount,
    cartEntries,
    loading,
    getCartEntriesAmount,
    getCartEntries,
    deleteCartEntries,
  } = useContext(CartContext);
  const { shippingCost, getShippingCost } = useContext(ShippingContext);
  const { totalCost, getTotalCost } = useContext(CartContext);

  useEffect(() => {
    getShippingCost();
    getTotalCost();
    getCartEntriesAmount();
    getCartEntries();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  if (loading)
    return (
      <div style={{ display: "flex", justifyContent: "center" }}>
        Loading...
      </div>
    );

  return (
    <section className="cart">
      <div className="container">
        <>
          {!Array.isArray(cartEntries) || cartEntries.length === 0 ? (
            <EmptyCard />
          ) : (
            <>
              <div className="cart__header">
                <h1 className="cart__title">
                  {cartEntriesAmount} item in your cart
                </h1>
                <div className="cart__options">
                  <button
                    className="cart__btn btn btn--gray"
                    onClick={() => deleteCartEntries()}
                  >
                    Empty Cart
                  </button>
                  <Link to="/" className="btn btn--cyan">
                    Keep Browsing
                  </Link>
                </div>
              </div>
              <div className="cart__body">
                {Array.isArray(cartEntries) &&
                  cartEntries.map((product) => {
                    return (
                      <ProductCard
                        key={product.id}
                        orientation="horizontal"
                        product={product}
                      />
                    );
                  })}
                <CostCard shippingCost={shippingCost} totalCost={totalCost} />
              </div>
              <div className="cart__form">
                <h2 className="cart__title">Checkout</h2>
                <CheckoutForm />
              </div>
            </>
          )}
        </>
      </div>
    </section>
  );
};

export default CartPage;
