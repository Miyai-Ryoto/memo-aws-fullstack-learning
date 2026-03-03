import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { memoSchema } from "../validations/MemoSchema";

export function MemoForm({ onSubmit, submitting }) {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors, isSubmitting: rhfSubmitting },
  } = useForm({
    resolver: zodResolver(memoSchema),
    defaultValues: {
      title: "",
      content: "",
      tags: "",
    },
  });

  const submit = async (values) => {
    try {
      await onSubmit(values);
      reset();
    } catch (e) {
      // 失敗時は入力を残す（ユーザーが修正できる）
      console.error(e);
    }
  };

  // 親から来る submitting を優先（今の設計に合わせる）
  const disabled = rhfSubmitting || !!submitting;

  return (
    <form onSubmit={handleSubmit(submit)}>
      <div>
        <label>title *</label>
        <input {...register("title")} disabled={disabled} />
        {errors.title && <p style={{ color: "red" }}>{errors.title.message}</p>}
      </div>

      <div>
        <label>content</label>
        <textarea {...register("content")} disabled={disabled} />
        {errors.content && (
          <p style={{ color: "red" }}>{errors.content.message}</p>
        )}
      </div>

      <div>
        <label>tags（カンマ区切り）</label>
        <input
          {...register("tags")}
          disabled={disabled}
          placeholder="react,api"
        />
        {errors.tags && <p style={{ color: "red" }}>{errors.tags.message}</p>}
      </div>

      <button type="submit" disabled={disabled}>
        {disabled ? "Saving..." : "Add Memo"}
      </button>
    </form>
  );
}