import { MemoForm } from "./MemoForm";

export default {
  title: "Components/MemoForm",
  component: MemoForm,
};

const Template = (args) => <MemoForm {...args} />;

export const Default = Template.bind({});
Default.args = {
  submitting: false,
  onSubmit: async (data) => {
    console.log("submit:", data);
  },
};

export const Submitting = Template.bind({});
Submitting.args = {
  submitting: true,
  onSubmit: async () => {},
};
