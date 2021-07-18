import React from "react";
import "./CostCard.css";

const CostCard = ({ shippingCost, totalCost }) => {
  return (
    <div className="cost-card">
      <p className="cost-card__shipping">
        Shipping Cost: <strong>USD {shippingCost.toFixed(2)}</strong>
      </p>
      <p className="cost-card__total">
        Total Cost: <strong>USD {totalCost.toFixed(2)}</strong>
      </p>
    </div>
  );
};

export default CostCard;
