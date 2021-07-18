import React, { useEffect, useContext } from "react";
import { ProductContext } from "../../context/product/ProductState";
import ProductCard from "../../components/card/product/ProductCard";
import hotProducts from "../../images/hot-products.svg";
import "./HomePage.css";

const HomePage = () => {
  const { products, loading, getProducts } = useContext(ProductContext);

  useEffect(() => {
    getProducts();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <section className="home">
      <div className="container">
        <div className="home__wrapper">
          <img src={hotProducts} alt="Hot Products" className="home__img" />
          {loading ? (
            <div style={{ display: "flex", justifyContent: "center" }}>
              Loading...
            </div>
          ) : (
            <div className="home__products">
              {products.length === 0 ? (
                <div
                  style={{
                    display: "flex",
                    justifyContent: "center",
                  }}
                >
                  Hier gibt es leider keine Produkte mehr.
                </div>
              ) : null}
              {products.map((product) => {
                return <ProductCard key={product.id} product={product} />;
              })}
            </div>
          )}
        </div>
      </div>
    </section>
  );
};

export default HomePage;
