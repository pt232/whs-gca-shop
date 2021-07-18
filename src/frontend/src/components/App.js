import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { CartProvider } from "../context/cart/CartState";
import { CheckoutProvider } from "../context/checkout/CheckoutState";
import { ProductProvider } from "../context/product/ProductState";
import { ShippingProvider } from "../context/shipping/ShippingState";
import CartPage from "../pages/cart/CartPage";
import ConfirmationPage from "../pages/confirmation/ConfirmationPage";
import HomePage from "../pages/home/HomePage";
import Footer from "./footer/Footer";
import Header from "./header/Header";

const App = () => {
  return (
    <CheckoutProvider>
      <ShippingProvider>
        <CartProvider>
          <ProductProvider>
            <Router>
              <Header />
              <Switch>
                <Route path="/" exact component={HomePage} />
                <Route path="/cart" exact component={CartPage} />
                <Route path="/cart/confirmation" component={ConfirmationPage} />
              </Switch>
              <Footer />
            </Router>
          </ProductProvider>
        </CartProvider>
      </ShippingProvider>
    </CheckoutProvider>
  );
};

export default App;
