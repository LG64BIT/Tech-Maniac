import { Button } from "antd";
import { useNavigate } from "react-router-dom";
import { isUserAuthenticated } from "../utils/authUtils";

export const HomePage = () => {
  const navigate = useNavigate();
  return (
    <div style={{ textAlign: "center" }}>
      <h1>Welcome to Tech Maniac! 🚀</h1>
      <h2>
        **Tech Maniac** is your ultimate destination for unbiased,
        comprehensive, and up-to-the-minute hardware reviews. We're dedicated to
        helping you make informed decisions about the tech that powers your
        life.
      </h2>
      <h1>What We Do 🎯</h1>
      <h2>
        We believe in the transformative power of technology. Our mission is to
        bring you the most accurate, insightful, and useful hardware reviews on
        the web. From the latest graphics cards to cutting-edge VR headsets, we
        cover it all.
      </h2>
      <h1>Why Choose Us? 🏆</h1>
      <h2>
        Our reviews are based on rigorous testing and deep-dive analysis. We
        don't just list specs - we put hardware through its paces in real-world
        scenarios. This means you get the full picture, including performance,
        reliability, and value for money.{" "}
      </h2>
      <h1> Join the Maniacs! 🎉</h1>
      <h2>
        {" "}
        Become a part of our vibrant community of tech enthusiasts. Whether
        you're a seasoned pro or just getting started, there's a place for you.
      </h2>
      {isUserAuthenticated() ? (
        ""
      ) : (
        <>
          <p>Welcome aboard, fellow Tech Maniacs!</p>
          <Button type="primary" onClick={() => navigate("/authentication")}>
            Join Us!
          </Button>
        </>
      )}
    </div>
  );
};
