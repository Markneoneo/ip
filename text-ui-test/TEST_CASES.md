# **Unit Test Documentation**

This document outlines unit tests for the Amadeus chatbot, ensuring robustness by verifying valid and invalid inputs.

---

## **1. Task List Management**
- **`list`** – Verify if database successfully loads the saved task list from previous run.
- **`reset`** – Ensure all tasks are cleared from the list.
- **`list` (after reset)** – Confirm the list is empty.

---

## **2. Adding Tasks**
### **ToDo Tasks**
- **`todo Buy groceries`** – Add a valid ToDo task.
- **`todo`** – Handle missing descriptions.
- **`todo Read a book`** – Add another task to delete later.

### **Deadline Tasks**
- **`deadline Submit report /by 31/12/2025`** – Add a Deadline task with a valid date (slashes).
- **`deadline Submit report /by 31/12/2025 0900`** – Add a Deadline task with a valid date (slashes) and 24-hour time.
- **`deadline Submit report /by 31-12-2025 9.00AM`** – Add a Deadline task with a valid date (dashes) and 12-hour format (dots).
- **`deadline Submit report /by 31 12 2025 9am`** – Add a Deadline task with a valid date (spaces) and 12-hour format.
- **`deadline`** – Handle missing descriptions.
- **`deadline Submit report`** – Handle missing `/by` clause.
- **`deadline Submit report /by 31/12/2025 12:60`** – Reject invalid time.
- **`deadline leap year /by 29/2/2024`** – Accept valid leap year dates.
- **`deadline non-leap year /by 29/2/2023`** – Reject invalid leap year dates.
- **`deadline midnight /by 31/12/2025 12:00AM`** – Handle midnight time.
- **`deadline noon /by 31/12/2025 12:00PM`** – Handle noon time.

### **Event Tasks**
- **`event Team meeting /from 30/12/2025 /to 31-12-2025`** – Add an event with valid dates (slashes and dashes).
- **`event Team meeting /from 31/12/2025 1400 /to 31 12 2025 16:00`** – Add an event with valid dates (slashes and spaces) and 24-hour time (colons).
- **`event Team meeting /from 31-12-2025 14.00 /to 31/12/2025 4pm`** – Add an event with valid dates (dashes and slashes) and time (24-hour dots and 12-hour).
- **`event`** – Handle missing descriptions.
- **`event Team meeting`** – Handle missing `/from` or `/to` clause.
- **`event Team meeting /from 31/12/2025 /to 30/12/2025`** – Reject invalid time order.
- **`event Team meeting /from 12/30/2025 /to 12/31/2025`** – Reject incorrect date format.
- **`event Team meeting /from 30/12/2025 /to 31/12/2025 12pm`** – Handle mismatched `/from` and `/to` formats.

---

## **3. Marking & Unmarking Tasks**
- **`mark 1` / `unmark 1`** – Mark/unmark task using valid index.
- **`mark nine` / `unmark nine`** – Mark/unmark task using non-numeric index.
- **`mark thirteen`** – Reject out-of-range indices.
- **`mark` / `unmark`** – Handle missing indices.
- **`mark 10 twenty` / `unmark three 100`** – Reject invalid indices.

---

## **4. Deleting Tasks**
- **`delete 14`** – Delete a task with a valid index.
- **`delete`** – Handle missing index.
- **`delete hundred`** – Reject out-of-range indices.

---

## **5. Checking Tasks**
- `check` – Handle missing description.
- `check before` – Handle missing date/time.
- `check this out` – Reject invalid format.
- `check 31 12 2025` – List tasks occurring on valid date (spaces).
- `check 31/12/2025 9:00AM` – List tasks occurring on valid date (slashes) and 12-hour time (colons).
- `check before 31-12-2025` – List tasks occurring before valid date (dashes).
- `check before 31/12/2025 12pm` – List tasks occurring before valid date (slashes) and 12-hour time.
- `check after 31 12 2025` – List tasks occurring after valid date (spaces).
- `check after 31-12-2025 1200` – List tasks occurring after valid date (dashes) and 24-hour time.

---

## **6. Miscellaneous Tests**
- **`Read a book`** – Reject invalid commands.
- **`find meeting`** – Find tasks containing a keyword.
- **`find`** – Handle missing keywords.
- **`bye`** – Ensure chatbot displays a farewell message and exits.

---

This documentation provides a structured and concise overview of chatbot test cases.