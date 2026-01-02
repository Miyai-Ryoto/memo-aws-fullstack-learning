import { MemoList } from "./MemoList";

export default {
  title: "Memo/MemoList",
  component: MemoList,
};

export const Empty = {
  args: {
    memos: [],
  },
};

export const Single = {
  args: {
    memos: [
      {
        id: 1,
        title: "メモ1",
        content: "内容",
        tags: ["aws"],
      },
    ],
  },
};

export const Multiple = {
  args: {
    memos: [
      {
        id: 1,
        title: "メモ1",
        content: "内容",
        tags: ["aws"],
      },
      {
        id: 2,
        title: "メモ2",
        content: "内容",
        tags: ["react"],
      },
    ],
  },
};
