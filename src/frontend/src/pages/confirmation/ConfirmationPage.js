import React, { useContext } from "react";
import { CheckoutContext } from "../../context/checkout/CheckoutState";
import CostCard from "../../components/card/cost/CostCard";
import ProductCard from "../../components/card/product/ProductCard";
import "./ConfirmationPage.css";
import { useEffect } from "react";
import { CartContext } from "../../context/cart/CartState";

const ConfirmationPage = () => {
  const { order, products, getOrderDetails } = useContext(CheckoutContext);
  const { deleteCartEntries } = useContext(CartContext);

  useEffect(() => {
    getOrderDetails();
    deleteCartEntries();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <section className="confirmation">
      <div className="container">
        <h1 className="confirmation__title">Order Confirmation</h1>
        <div className="confirmation__wrapper">
          {!order ? (
            <div style={{ display: "flex", justifyContent: "center" }}>
              Loading...
            </div>
          ) : (
            <>
              <aside className="confirmation__box">
                {products.map((product) => {
                  return (
                    <ProductCard
                      key={product.id}
                      orientation="horizontal"
                      small={true}
                      product={product}
                    />
                  );
                })}
                <CostCard
                  shippingCost={order.shippingCost}
                  totalCost={order.totalCost}
                />
              </aside>
              <div className="confirmation__details">
                <div className="details-card">
                  <h3 className="details-card__title">Order Details:</h3>
                  <div className="details-card__item">
                    <p className="details-card__information">Order Number:</p>
                    <p className="details-card__content">{order.orderNumber}</p>
                  </div>
                  <div className="details-card__item">
                    <p className="details-card__information">
                      Tracking Number:
                    </p>
                    <p className="details-card__content">
                      {order.trackingNumber}
                    </p>
                  </div>
                </div>
                <div className="details-card">
                  <h3 className="details-card__title">Personal Details:</h3>
                  <div className="details-card__item">
                    <p className="details-card__information">E-Mail:</p>
                    <p className="details-card__content">{order.email}</p>
                  </div>
                  <div className="details-card__item">
                    <p className="details-card__information">Street Address:</p>
                    <p className="details-card__content">
                      {order.street +
                        " " +
                        order.zipcode +
                        " " +
                        order.city +
                        " " +
                        order.state +
                        " " +
                        order.country}
                    </p>
                  </div>
                </div>
                <div className="details-card">
                  <h3 className="details-card__title">Payment Details:</h3>
                  <div className="details-card__item">
                    <p className="details-card__information">
                      Credit Card Number:
                    </p>
                    <p className="details-card__content">
                      {order.creditCardNumber}
                    </p>
                  </div>
                  <div className="details-card__item">
                    <p className="details-card__information">Expires:</p>
                    <p className="details-card__content">
                      {order.month + " " + order.year}
                    </p>
                  </div>
                </div>
              </div>
            </>
          )}
        </div>
      </div>
    </section>
  );
};

export default ConfirmationPage;
