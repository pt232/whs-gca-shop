import React from "react";
import { Link } from "react-router-dom";
import "./EmptyCard.css";

const EmptyCard = () => {
  return (
    <div className="empty-card">
      <h1 className="empty-card__title">Your shopping cart is empty!</h1>
      <p className="empty-card__text">
        Items you add to your shopping cart will appear here.
      </p>
      <Link to="/" className="empty-card__btn btn btn--cyan">
        Browse Products →
      </Link>
    </div>
  );
};

export default EmptyCard;
