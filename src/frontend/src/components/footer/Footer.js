import React from "react";
import "./Footer.css";

const Footer = () => {
  return (
    <footer className="footer">
      <div className="container">
        <p className="footer__text">
          This website is hosted for demo purposes only. It is not an actual
          shop. This is definitely 1000% a Google product.
        </p>
        <p className="footer__text">&copy; 2021 WHS GCA Inc.</p>
      </div>
    </footer>
  );
};

export default Footer;
