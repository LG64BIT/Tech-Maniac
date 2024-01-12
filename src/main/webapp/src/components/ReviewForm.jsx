import { Button, Form, Input, message } from "antd";
import { axiosInstance } from "../utils/axiosInstance";

export const ReviewForm = ({ closeModal, fetchReviews }) => {
  const [form] = Form.useForm();

  const insertReview = (formData) => {
    axiosInstance
      .post("/reviews", formData)
      .then(() => {
        message.open({
          type: "success",
          content: "Uspješno objavljena recenzija",
        });
        fetchReviews();
        closeModal();
        form.resetFields();
      })
      .catch((error) => {
        message.open({
          type: "error",
          content: "Pogreška prilikom objave recenzije!",
        });
        console.error("Error: ", error);
      });
  };

  return (
    <Form form={form} onFinish={insertReview}>
      <Form.Item label="Naslov" name="title">
        <Input />
      </Form.Item>
      <Form.Item label="Recenzija" name="description">
        <Input.TextArea />
      </Form.Item>
      <Button type="primary" htmlType="submit">
        Kreiraj
      </Button>
      <Button onClick={closeModal}>Odustani</Button>
    </Form>
  );
};
