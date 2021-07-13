import React from "react";
import CostCard from "../../components/card/cost/CostCard";
import DetailsCard from "../../components/card/details/DetailsCard";
import ProductCard from "../../components/card/product/ProductCard";
import "./ConfirmationPage.css";

const ConfirmationPage = () => {
  return (
    <section className="confirmation">
      <div className="container">
        <h1 className="confirmation__title">Order Confirmation</h1>
        <div className="confirmation__wrapper">
          <aside className="confirmation__box">
            <ProductCard orientation="horizontal" small={true} />
            <ProductCard orientation="horizontal" small={true} />
            <CostCard />
          </aside>
          <div className="confirmation__details">
            <DetailsCard />
            <DetailsCard />
            <DetailsCard />
          </div>
        </div>
      </div>
    </section>
  );
};

export default ConfirmationPage;
