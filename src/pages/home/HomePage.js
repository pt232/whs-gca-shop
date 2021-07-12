import React from "react";
import ProductCard from "../../components/card/product/ProductCard";
import hotProducts from "../../images/hot-products.svg";
import "./HomePage.css";

const HomePage = () => {
  return (
    <section className="home">
      <div className="container">
        <div className="home__wrapper">
          <img src={hotProducts} alt="Hot Products" className="home__img" />
          <div className="home__products">
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
          </div>
        </div>
      </div>
    </section>
  );
};

export default HomePage;
