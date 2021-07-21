import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import axios from "axios";
import "./CheckoutForm.css";

const CheckoutForm = () => {
  const history = useHistory();

  const monthList = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "Oktober",
    "November",
    "December",
  ];
  const yearList = ["2021", "2022", "2023", "2024", "2025"];

  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [zipCode, setZipCode] = useState("");
  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [country, setCountry] = useState("");
  const [cardNumber, setCardNumber] = useState("0000-0000-0000-0000");
  const [month, setMonth] = useState("January");
  const [year, setYear] = useState(2021);
  const [cvv, setCvv] = useState("123");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    setLoading(true);

    const res = await axios.post(
      process.env.REACT_APP_CHECKOUT_SERVICE,
      {
        email: email,
        street: address,
        zipcode: zipCode,
        city: city,
        state: state,
        country: country,
        creditCardNumber: cardNumber,
        month: month,
        year: year,
        cvv: cvv,
      },
      {
        auth: {
          username: process.env.REACT_APP_CHECKOUT_USERNAME,
          password: process.env.REACT_APP_CHECKOUT_PASSWORD,
        },
      }
    );

    if (res.data.success) {
      setError("");
      setLoading(false);
      history.push("/cart/confirmation");
    } else {
      setLoading(false);
      if (res.data === "The checkout could not be processed correctly.")
        setError("An error occured. Please try again.");
      else setError(res.data.message);
    }
  };

  return (
    <>
      {error ? (
        <div
          style={{
            marginBottom: "3.5rem",
            padding: "1.2rem 2.4rem",
            color: "#D8000C",
            backgroundColor: "#FFBABA",
          }}
        >
          {error}
        </div>
      ) : null}
      <form
        className="checkout-form"
        onSubmit={(e) => handleSubmit(e)}
        autoComplete="off"
      >
        <div className="checkout-form__row">
          <div className="checkout-form__field">
            <label className="checkout-form__label" htmlFor="coMail">
              E-mail Address
            </label>
            <input
              type="email"
              className="checkout-form__input"
              value={email}
              placeholder="someone@example.com"
              pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
              id="coMail"
              onChange={(e) => {
                setEmail(e.target.value);
              }}
              required
            />
          </div>
          <div className="checkout-form__field">
            <label className="checkout-form__label" htmlFor="coAddress">
              Street Address
            </label>
            <input
              type="text"
              className="checkout-form__input"
              value={address}
              placeholder="251 Bassel Street"
              id="coAddress"
              onChange={(e) => setAddress(e.target.value)}
              required
            />
          </div>
          <div className="checkout-form__field">
            <label className="checkout-form__label" htmlFor="coCode">
              Zip Code
            </label>
            <input
              type="text"
              className="checkout-form__input"
              value={zipCode}
              placeholder="94043"
              id="coCode"
              pattern="\d{4,5}"
              onChange={(e) => setZipCode(e.target.value)}
              required
            />
          </div>
        </div>
        <div className="checkout-form__row">
          <div className="checkout-form__field">
            <label className="checkout-form__label" htmlFor="coCity">
              City
            </label>
            <input
              type="text"
              className="checkout-form__input"
              value={city}
              placeholder="Mountain View"
              id="coCity"
              onChange={(e) => setCity(e.target.value)}
              required
            />
          </div>
          <div className="checkout-form__field">
            <label className="checkout-form__label" htmlFor="coState">
              State
            </label>
            <input
              type="text"
              className="checkout-form__input"
              value={state}
              placeholder="CA"
              id="coState"
              onChange={(e) => setState(e.target.value)}
              required
            />
          </div>
          <div className="checkout-form__field">
            <label className="checkout-form__label" htmlFor="coCountry">
              Country
            </label>
            <input
              type="text"
              className="checkout-form__input"
              value={country}
              placeholder="United States"
              id="coCountry"
              onChange={(e) => setCountry(e.target.value)}
              required
            />
          </div>
        </div>
        <div className="checkout-form__row">
          <div className="checkout-form__field">
            <label className="checkout-form__label" htmlFor="coCard">
              Credit Card Number
            </label>
            <input
              type="text"
              className="checkout-form__input"
              id="coCard"
              value={cardNumber}
              pattern="\d{4}-\d{4}-\d{4}-\d{4}"
              onChange={(e) => setCardNumber(e.target.value)}
              required
            />
          </div>
          <div className="checkout-form__field">
            <label className="checkout-form__label" htmlFor="coMonth">
              Month
            </label>
            <select
              type="text"
              className="checkout-form__input"
              id="coMonth"
              onChange={(e) => setMonth(e.target.value)}
              required
            >
              {monthList.map((item, index) => {
                return (
                  <option key={index} value={item}>
                    {item}
                  </option>
                );
              })}
            </select>
          </div>
          <div className="checkout-form__field">
            <label className="checkout-form__label" htmlFor="coYear">
              Year
            </label>
            <select
              type="text"
              className="checkout-form__input"
              id="coYear"
              onChange={(e) => setYear(e.target.value)}
              required
            >
              {yearList.map((item, index) => {
                return (
                  <option key={index} value={item}>
                    {item}
                  </option>
                );
              })}
            </select>
          </div>
          <div className="checkout-form__field">
            <label className="checkout-form__label" htmlFor="coCVV">
              CVV
            </label>
            <input
              type="password"
              className="checkout-form__input"
              value={cvv}
              id="coCVV"
              pattern="\d{3}"
              onChange={(e) => setCvv(e.target.value)}
              required
            />
          </div>
        </div>
        <button
          type="submit"
          style={
            loading
              ? { backgroundColor: "#4bc7c787", borderColor: "#4bc7c787" }
              : {}
          }
          className="checkout-form__btn btn btn--cyan"
        >
          {loading ? "Place Order..." : "Place Order"}
        </button>
      </form>
    </>
  );
};

export default CheckoutForm;
