import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import "./CheckoutForm.css";

const CheckoutForm = () => {
  const history = useHistory();

  const [email, setEmail] = useState();
  const [address, setAddress] = useState();
  const [zipCode, setZipCode] = useState();
  const [city, setCity] = useState();
  const [state, setState] = useState();
  const [country, setCountry] = useState();
  const [cardNumber, setCardNumber] = useState("0000-0000-0000-0000");
  const [month, setMonth] = useState([
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
  ]);
  const [year, setYear] = useState(["2021", "2022", "2023", "2024", "2025"]);
  const [cvv, setCvv] = useState("123");

  const handleSubmit = (e) => {
    e.preventDefault();
    history.push("/cart/confirmation");
  };

  return (
    <form className="checkout-form" onSubmit={(e) => handleSubmit(e)}>
      <div className="checkout-form__row">
        <div className="checkout-form__field">
          <label className="checkout-form__label" for="coMail">
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
          <label className="checkout-form__label" for="coAddress">
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
          <label className="checkout-form__label" for="coCode">
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
          <label className="checkout-form__label" for="coCity">
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
          <label className="checkout-form__label" for="coState">
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
          <label className="checkout-form__label" for="coCountry">
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
          <label className="checkout-form__label" for="coCard">
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
          <label className="checkout-form__label" for="coMonth">
            Month
          </label>
          <select
            type="text"
            className="checkout-form__input"
            id="coMonth"
            onChange={(e) => setMonth(e.target.value)}
            required
          >
            {month.map((item, index) => {
              return (
                <option key={index} value={item}>
                  {item}
                </option>
              );
            })}
          </select>
        </div>
        <div className="checkout-form__field">
          <label className="checkout-form__label" for="coYear">
            Year
          </label>
          <select
            type="text"
            className="checkout-form__input"
            id="coYear"
            onChange={(e) => setYear(e.target.value)}
            required
          >
            {year.map((item, index) => {
              return (
                <option key={index} value={item}>
                  {item}
                </option>
              );
            })}
          </select>
        </div>
        <div className="checkout-form__field">
          <label className="checkout-form__label" for="coCVV">
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
      <button type="submit" className="checkout-form__btn btn btn--cyan">
        Place Order
      </button>
    </form>
  );
};

export default CheckoutForm;
