import React from "react";
import "./CostCard.css";

const CostCard = () => {
  return (
    <div className="cost-card">
      <p className="cost-card__shipping">
        Shipping Cost: <strong>USD 4.72</strong>
      </p>
      <p className="cost-card__total">
        Total Cost: <strong>USD 72.71</strong>
      </p>
    </div>
  );
};

export default CostCard;
