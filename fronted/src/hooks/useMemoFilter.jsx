import { useMemo, useState } from "react";

export function useMemoFilter(memos) {
  const [keyword, setKeyword] = useState("");

  const filteredMemos = useMemo(() => {
    const normalizedKeyword = keyword.trim().toLowerCase();

    if (!normalizedKeyword) {
      return memos;
    }

    return memos.filter((memo) =>
      memo.tags?.some((tag) =>
        tag.toLowerCase().includes(normalizedKeyword)
      )
    );
  }, [memos, keyword]);

  return {
    keyword,
    setKeyword,
    filteredMemos,
  };
}