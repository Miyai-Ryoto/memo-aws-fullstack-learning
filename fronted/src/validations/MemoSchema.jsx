import { z } from "zod";

export const memoSchema = z.object({
  title: z.string().trim().min(1, "title は必須です"),
  content: z.string().optional(),
  tags: z.string().optional(), // "a,b,c" のまま受ける
});