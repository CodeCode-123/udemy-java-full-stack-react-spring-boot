import React from "react";
import { toast } from "react-toastify";
import { Form } from "react-router-dom";
import Header from "./Header";
import Footer from "./footer/Footer";
import { useActionData, useNavigation, useSubmit } from "react-router-dom";
import { useEffect, useRef } from "react";
import apiClient from "../api/apiClient";
import "react-toastify/dist/ReactToastify.css";
import PageTitle from "./PageTitle";
import { redirect } from "react-router-dom";

export default function Contact() {
  const actionData = useActionData();
  const formRef = useRef(null);
  // try to avoid submit multiple times
  const navigation = useNavigation();
  const submit = useSubmit();
  // when the state value is submitting, indicating the saving operation is in progress
  const isSubmitting = navigation.state === "submitting";
  useEffect(() => {
    if (actionData?.success) {
      formRef.current?.reset();
      toast.success("Your message has been submitted successfully!");
    }
  }, [actionData]);
  
  // use useSubmit if you'd like to submit the form manually
  const handleSubmit = (event) => {
    event.preventDefault();
    const userConfirmed = window.confirm(
      "Are you sure you want to submit the form?"
    );

    if (userConfirmed) {
      const formData = new FormData(formRef.current); // Get form data
      submit(formData, { method: "post" }); // Proceed with form submission
    } else {
      toast.info("Form submission cancelled.");
    }
  };

  const labelStyle =
    "block text-lg font-semibold text-primary dark:text-light mb-2";
  const textFieldStyle =
    "w-full px-4 py-2 text-base border rounded-md transition border-primary dark:border-light focus:ring focus:ring-dark dark:focus:ring-lighter focus:outline-none text-gray-800 dark:text-lighter bg-white dark:bg-gray-600 placeholder-gray-400 dark:placeholder-gray-300";
  return (
    <div className="max-w-[1152px] min-h-[852px] mx-auto px-6 py-8 font-primary bg-normalbg dark:bg-darkbg">
      {/* Page Title */}
      <PageTitle title="Contact Us" />
      {/* Contact Info */}
      <p className="max-w-[768px] mx-auto mt-8 text-gray-600 dark:text-lighter mb-8 text-center">
        We’d love to hear from you! If you have any questions, feedback, or
        suggestions, please don’t hesitate to reach out.
      </p>

      {/* Contact Form */}
      <Form
        method="POST"
        ref={formRef} /*This entire form will be assigned as the current attribute under these formRef */
        onSubmit={handleSubmit}
        className="space-y-6 max-w-[768px] mx-auto"
      >
        {/* Name Field */}
        <div>
          <label htmlFor="name" className={labelStyle}>
            Name
          </label>
          <input
            id="name"
            name="name"
            type="text"
            placeholder="Your Name"
            className={textFieldStyle}
            required
            minLength={5}
            maxLength={30}
          />
          {actionData?.errors?.name && (
            <p className="text-red-500 text-sm mt-1">
              {actionData.errors.name}
            </p>
          )}
        </div>

        {/* Email and mobile Row */}
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
          {/* Email Field */}
          <div>
            <label htmlFor="email" className={labelStyle}>
              Email
            </label>
            <input
              id="email"
              name="email"
              type="email"
              placeholder="Your Email"
              className={textFieldStyle}
              required
            />
            {actionData?.errors?.email && (
              <p className="text-red-500 text-sm mt-1">
                {actionData.errors.email}
              </p>
            )}
          </div>

          {/* Mobile Field */}
          <div>
            <label htmlFor="mobileNumber" className={labelStyle}>
              Mobile Number
            </label>
            <input
              id="mobileNumber"
              name="mobileNumber"
              type="tel"
              required
              pattern="^\d{10}$"
              title="Mobile number must be exactly 10 digits"
              placeholder="Your Mobile Number"
              className={textFieldStyle}
            />
            {actionData?.errors?.mobileNumber && (
            <p className="text-red-500 text-sm mt-1">
              {actionData.errors.mobileNumber}
            </p>
          )}
          </div>
        </div>

        {/* Message Field */}
        <div>
          <label htmlFor="message" className={labelStyle}>
            Message
          </label>
          <textarea
            id="message"
            name="message"
            rows="4"
            placeholder="Your Message"
            className={textFieldStyle}
            required
            minLength={5}
            maxLength={500}
          ></textarea>
          {actionData?.errors?.message && (
            <p className="text-red-500 text-sm mt-1">
              {actionData.errors.message}
            </p>
          )}
        </div>

        {/* Submit Button */}
        <div className="text-center">
          <button
            type="submit"
            disabled={isSubmitting}
            className="px-6 py-2 text-white dark:text-black text-xl rounded-md transition duration-200 bg-primary dark:bg-light hover:bg-dark dark:hover:bg-lighter"
          >
            {isSubmitting ? "Submitting..." : "Submit"}
          </button>
        </div>
      </Form>
    </div>
  );
}

export async function contactAction({ request, params }) {
  // populate the request data from the Form
  const data = await request.formData();

  const contactData = {
    name: data.get("name"),
    email: data.get("email"),
    mobileNumber: data.get("mobileNumber"),
    message: data.get("message"),
  };
  try {
    // send the data to the backend, the POST should be mentioned in the backend
    // @PostMapping, in the html/jsx <Form method="POST" .../>, and apiClient.post();
    await apiClient.post("/contacts", contactData);
    return { success: true };
    //return redirect("/home"); // if use redirect() to home page after submission
  } catch (error) {
    if (error.response?.status === 400) {
      return { success: false, errors: error.response?.data};
    }
    throw new Response(
      // error.response?.data.errorMessage, if there is a ResponseEntity with errorMessage
      // get the errorMessage from the backend
      error.response?.data?.errorMessage ||
      error.message || 
      "Failed to submit your message. Please try again.",
      { status: error.status || 500 }
    );
  }
}