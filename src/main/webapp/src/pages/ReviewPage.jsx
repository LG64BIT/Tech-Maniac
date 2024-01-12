import { Button, Col, Modal, Row, Spin, message } from "antd";
import { useEffect, useState } from "react";
import { axiosInstance } from "../utils/axiosInstance";
import { Review } from "../components/Review";
import { ReviewForm } from "../components/ReviewForm";
import { getUserRole } from "../utils/authUtils";

export const ReviewPage = () => {
  const [reviews, setReviews] = useState([]);
  const [reviewsLoading, setReviewsLoading] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [userRole, setUserRole] = useState(null);

  const fetchReviews = () => {
    setReviewsLoading(true);
    axiosInstance
      .get("/reviews")
      .then((response) => {
        setReviews(response.data);
      })
      .catch((error) => {
        message.open({
          type: "error",
          content: "Pogreška prilikom dohvaćanja recenzija!",
        });
        console.error("Error: ", error);
      })
      .finally(() => setReviewsLoading(false));
  };

  useEffect(() => {
    getUserRole(setUserRole);
    fetchReviews();
  }, []);

  if (reviewsLoading) {
    return (
      <Spin tip="Učitavanje..." size="large" fullscreen>
        <div className="content" />
      </Spin>
    );
  }

  return (
    <>
      <Row gutter={[16, 16]} style={{ margin: 16 }}>
        {userRole === "ADMIN" ? (
          <Col
            xs={24}
            sm={24}
            md={24}
            lg={24}
            xl={24}
            style={{ textAlign: "center" }}
          >
            <Button
              type="primary"
              onClick={() => setIsModalOpen((isModalOpen) => !isModalOpen)}
              title="Dodaj recenziju"
            >
              Dodaj recenziju
            </Button>
          </Col>
        ) : (
          ""
        )}
        {reviews.map((review) => {
          return (
            <Review
              key={review.id}
              item={review}
              fetchReviews={fetchReviews}
              setReviews={setReviews}
            />
          );
        })}
      </Row>
      <Modal
        title="Nova recenzija"
        open={isModalOpen}
        onCancel={() => setIsModalOpen(false)}
        footer={[]}
      >
        <ReviewForm
          closeModal={() => setIsModalOpen(false)}
          fetchReviews={fetchReviews}
        />
      </Modal>
    </>
  );
};
