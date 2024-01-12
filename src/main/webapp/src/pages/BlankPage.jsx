import Image from "./404.jpg";

export const BlankPage = () => {
  return (
    // <div
    //   style={{
    //     backgroundImage: `url(${Image})`,
    //     height: "80vh",
    //     backgroundSize: "cover",
    //     objectFit: "contain",
    //   }}
    // >
    <div style={{ textAlign: "center" }}>
      <img
        src={Image}
        alt="404_image"
        style={{ height: "50%", width: "50%", objectFit: "contain" }}
      />
      <h1>You are not supoosed to be here...</h1>
    </div>
  );
};
