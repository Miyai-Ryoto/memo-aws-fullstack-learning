import { MemoItem } from "./MemoItem";

export default {
  title: "Memo/MemoItem",
  component: MemoItem,
};

export const Default = {
  args: {
    memo: {
      id: 1,
      title: "メモ1",
      content: "内容",
      tags: ["aws", "react"],
    },
  },
};

export const NoTags = {
  args: {
    memo: {
      id: 2,
      title: "タグなしメモ",
      content: "内容",
      tags: [],
    },
  },
};
