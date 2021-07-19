import React, { useEffect, useContext } from "react";
import { CartContext } from "../../context/cart/CartState";
import { Link, useLocation } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faShoppingCart } from "@fortawesome/free-solid-svg-icons";
import logo from "../../images/logo.svg";
import logoHero from "../../images/logo-hero.svg";
import "./Header.css";

const Header = () => {
  const location = useLocation();
  const { cartEntriesAmount, getCartEntriesAmount } = useContext(CartContext);

  useEffect(() => {
    getCartEntriesAmount();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <header>
      <nav className="nav">
        <div className="container">
          <div className="nav__wrapper">
            <div className="nav__logo">
              <Link to="/">
                <img src={logo} alt="Logo" />
              </Link>
            </div>
            <div className="nav__cart">
              <FontAwesomeIcon
                icon={faShoppingCart}
                className="nav__cart-icon"
              />
              <div className="nav__cart-wrapper">
                <Link to="/cart" className="cart-text">
                  Cart
                </Link>
                <span className="cart-amount">
                  {isNaN(cartEntriesAmount) ? 0 : cartEntriesAmount}
                </span>
              </div>
            </div>
          </div>
        </div>
      </nav>
      {location.pathname !== "/cart" ? (
        <div className="hero">
          <img src={logoHero} alt="Logo Hero" className="hero__img" />
        </div>
      ) : null}
    </header>
  );
};

export default Header;
