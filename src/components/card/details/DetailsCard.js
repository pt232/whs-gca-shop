import React from "react";
import "./DetailsCard.css";

const DetailsCard = () => {
  return (
    <div className="details-card">
      <h3 className="details-card__title">Order Details:</h3>
      <div className="details-card__item">
        <p className="details-card__information">Order Number:</p>
        <p className="details-card__content">0-1337-4711</p>
      </div>
      <div className="details-card__item">
        <p className="details-card__information">Tracking Number:</p>
        <p className="details-card__content">
          c234a3-f124a-4124-asf124-124325afda
        </p>
      </div>
    </div>
  );
};

export default DetailsCard;
