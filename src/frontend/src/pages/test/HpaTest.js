import React, { useEffect } from "react";

const HpaTest = () => {
  useEffect(() => {
    console.log("Starting Load Test");
    let x = 0.0001;
    for (let i = 0; i <= 1000000000; i++) {
      x += Math.sqrt(x);
    }
    console.log("Finished Load Test");
  }, []);

  return <div>Load Test</div>;
};

export default HpaTest;
