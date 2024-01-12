import { Button, Card, Col, Form, Input, Tooltip, message } from "antd";
import { Comment } from "@ant-design/compatible";
import { axiosInstance } from "../utils/axiosInstance";
import { LikeOutlined, LikeFilled } from "@ant-design/icons";
import { useState } from "react";

export const Review = ({ item, fetchReviews, setReviews }) => {
  const [form] = Form.useForm();
  const [isLiked, setIsLiked] = useState(false);

  const handleComment = ({ content }) => {
    axiosInstance
      .post("/comments", { content, reviewId: item.id })
      .then(() => {
        message.open({
          type: "success",
          content: "Komentar uspješno objavljen!",
        });
        fetchReviews();
        form.resetFields();
      })
      .catch((error) => {
        console.error("Error: ", error);
        message.open({
          type: "error",
          content: "Objava komentara neuspjela!",
        });
      });
  };

  const handleLike = () => {
    axiosInstance
      .put("/reviews/like/" + item.id)
      .then(() => {
        setIsLiked(true);
        item.likeCount++;
      })
      .catch((error) => {
        console.error("Error: ", error);
      });
  };

  const handleDisike = () => {
    axiosInstance
      .put("/reviews/dislike/" + item.id)
      .then(() => {
        setIsLiked(false);
        item.likeCount--;
      })
      .catch((error) => {
        console.error("Error: ", error);
      });
  };

  return (
    <Col key={item.id} xs={24} sm={24} md={12} lg={6} xl={6}>
      <Card title={item.title}>
        <p>{item.description}</p>
        <a onClick={isLiked ? handleDisike : handleLike}>
          <span>{item.likeCount}</span>
          {isLiked ? <LikeFilled /> : <LikeOutlined />}
        </a>
        <h3>Komentari:</h3>
        {item.comments.map((it) => {
          return (
            <Comment
              author={<a>{it.username}</a>}
              content={it.content}
              datetime={
                <Tooltip title={it.timestamp}>
                  <span>{it.timestamp}</span>
                </Tooltip>
              }
            />
          );
        })}
        <Form form={form} onFinish={handleComment}>
          <Form.Item name="content">
            <Input.TextArea required />
          </Form.Item>
          <Button type="primary" htmlType="submit" style={{ float: "right" }}>
            Objavi
          </Button>
        </Form>
      </Card>
    </Col>
  );
};
