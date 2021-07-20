import React from "react";
import "./CostCard.css";

const CostCard = ({ shippingCost, totalCost }) => {
  return (
    <div className="cost-card">
      <p className="cost-card__shipping">
        Shipping Cost:{" "}
        <strong>
          USD{" "}
          {!isNaN(shippingCost)
            ? shippingCost.toFixed(2)
            : "Please Reload this Page, and try again!"}
        </strong>
      </p>
      <p className="cost-card__total">
        Total Cost:{" "}
        <strong>
          USD{" "}
          {!isNaN(totalCost)
            ? totalCost.toFixed(2)
            : "Please Reload this Page, and try again!"}
        </strong>
      </p>
    </div>
  );
};

export default CostCard;
