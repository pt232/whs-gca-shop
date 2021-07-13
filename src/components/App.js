import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import CartPage from "../pages/cart/CartPage";
import ConfirmationPage from "../pages/confirmation/ConfirmationPage";
import HomePage from "../pages/home/HomePage";
import Footer from "./footer/Footer";
import Header from "./header/Header";

const App = () => {
  return (
    <Router>
      <Header />
      <Switch>
        <Route path="/" exact component={HomePage} />
        <Route path="/cart" exact component={CartPage} />
        <Route path="/cart/confirmation" component={ConfirmationPage} />
      </Switch>
      <Footer />
    </Router>
  );
};

export default App;
