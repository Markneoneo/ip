---
layout: default
title: Home
---

# User Guide for **Amadeus Task Manager**

Welcome to **Amadeus Task Manager**, your personal assistant for managing tasks efficiently! Amadeus helps you keep track of your to-dos, deadlines, and events, ensuring you stay organized and productive.

# [View Javadocs](javadoc/index.html)

## Table of Contents

- [Features](#features)
  - [Adding Tasks](#1-adding-tasks)
    - [Add a ToDo Task](#add-a-todo-task)
    - [Add a Deadline Task](#add-a-deadline-task)
    - [Add an Event Task](#add-an-event-task)
  - [Viewing Tasks](#2-viewing-tasks)
    - [List All Tasks](#list-all-tasks)
    - [Find Tasks by Keyword](#find-tasks-by-keyword)
    - [Check Tasks by Date](#check-tasks-by-date)
  - [Marking Tasks](#3-marking-tasks)
    - [Mark a Task as Complete](#mark-a-task-as-complete)
    - [Mark a Task as Incomplete](#mark-a-task-as-incomplete)
  - [Deleting Tasks](#4-deleting-tasks)
    - [Delete a Task](#delete-a-task)
    - [Reset All Tasks](#reset-all-tasks)
  - [Exiting the Chatbot](#5-exiting-the-chatbot)
- [Command Summary](#command-summary)
- [Notes](#notes)
- [FAQ](#faq)


---

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest version of `Amadeus` from [here](https://github.com/Markneoneo/ip).
3. Navigate to the folder where you saved the application and run the following command:
   ```
   java -jar amadeus.jar
   ```
4. Amadeus will greet you with a welcome message. You can start using the chatbot immediately!


---

## ⚠️ UI Compatibility

Amadeus uses **ANSI text formatting** and **UTF-8 emojis** for a rich experience. However, not all terminals may be compatible.

- Ensure you run Amadeus in **Fullscreen** to avoid formatting issues.

- **IntelliJ** will be the best Terminal to run Amadeus on as it displays coloured text and emojis.

- If you see `?` appearing, it means the terminal is not able to parse emojis. Try the following commands:
   - Set the code page to UTF-8 before running the JAR:
     ```
     chcp 65001
     java -jar amadeus.jar
     ```
   - Run the JAR with explicit encoding:
     ```
     java -Dfile.encoding=UTF-8 -jar Amadeus.jar
     java "-Dfile.encoding=UTF-8" -jar Amadeus.jar
     ```

   - For compiling:  
     ```
     javac -encoding UTF-8 -d . amadeus/*.java
     java -Dfile.encoding=UTF-8 amadeus.Amadeus
     ```

- **Git Bash, Windows Terminal and MacOS**: These terminals work but may have unintended outputs such as:
   - Uncoloured Emojis
   - Unable to parse certain text colours
   - Messed up text formatting

- **Windows PowerShell and CMD.exe**: These terminals work but **do not show emojis**. 

---

## Features

### 1. **Adding Tasks**
Amadeus supports three types of tasks: **ToDo**, **Deadline**, and **Event**.

#### Add a ToDo Task
Adds a simple task with no specific date or time.

**Format:**
```
todo DESCRIPTION
```

**Example:**
```
todo Buy groceries
```

---

#### Add a Deadline Task
Adds a task with a specific deadline.

**Format:**
```
deadline DESCRIPTION /by DATE_TIME
```

- `DATE_TIME` can be in the following formats:
  - Date only: `2/12/2025`, `2-12-2025`, or `2 12 2025`.
  - Date and time (24-hour): `2/12/2025 1800`, `2/12/2025 18:00`, or `2/12/2025 18.00`.
  - Date and time (12-hour): `2/12/2025 6pm`, `2/12/2025 6:00PM`, or `2/12/2025 6.30AM`.

**Example:**
```
deadline Submit report /by 31/12/2025
```

---

#### Add an Event Task
Adds a task with a start and end time.

**Format:**
```
event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME
```

- `START_DATE_TIME` and `END_DATE_TIME` follow the same format as `DATE_TIME` in the Deadline task.

**Example:**
```
event Team meeting /from 1/1/2025 11:59pm /to 2/1/2025 12pm
```

---

### 2. **Viewing Tasks**
#### List All Tasks
Displays all tasks in your list, organized by type (Deadlines, Events, ToDos).

**Format:**
```
list
```

---

#### Find Tasks by Keyword
Searches for tasks containing a specific keyword.

**Format:**
```
find KEYWORD
```

**Example:**
```
find meeting
```

---

#### Check Tasks by Date
Lists tasks occurring on, before, or after a specific date.

**Format:**
```
check [before/after] DATE_TIME
```

- Omit `before/after` to list tasks on the exact date.

**Examples:**
```
check 2/10/2025
check before 2/10/2025 6am
check after 2/10/2025 2359
```

---

### 3. **Marking Tasks**
#### Mark a Task as Complete
Marks a task as done.

**Format:**
```
mark INDEX
```

- `INDEX` is the task number shown in the task list.

**Example:**
```
mark 1
```

---

#### Mark a Task as Incomplete
Marks a task as not done.

**Format:**
```
unmark INDEX
```

**Example:**
```
unmark 1
```

---

### 4. **Deleting Tasks**
#### Delete a Task
Deletes a task from the list.

**Format:**
```
delete INDEX
```

**Example:**
```
delete 2
```

---

#### Reset All Tasks
Clears all tasks from the list.

**Format:**
```
reset
```

---

### 5. **Exiting the Chatbot**
Exits the chatbot.

**Format:**
```
bye
```

---

## Command Summary

| Command                | Format                                                                 |
|------------------------|------------------------------------------------------------------------|
| **Add ToDo**           | `todo DESCRIPTION`                                                     |
| **Add Deadline**       | `deadline DESCRIPTION /by DATE_TIME`                                   |
| **Add Event**          | `event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME`            |
| **List Tasks**         | `list`                                                                 |
| **Find Tasks**         | `find KEYWORD`                                                         |
| **Check Tasks**        | `check [before/after] DATE_TIME`                                       |
| **Mark Task**          | `mark INDEX`                                                           |
| **Unmark Task**        | `unmark INDEX`                                                         |
| **Delete Task**        | `delete INDEX`                                                         |
| **Reset Tasks**        | `reset`                                                                |
| **Exit**               | `bye`                                                                  |

---

## Notes
- **Date and Time Formats**: Amadeus supports flexible date and time formats. For example:
  - `2/12/2025`, `2-12-2025`, or `2 12 2025` for dates.
  - `6pm`, `6:00PM`, `1800`, or `18:00` for times.
- **Index Numbers**: You can use either numeric indices (e.g., `1`, `2`) or words (e.g., `one`, `two`) for commands like `mark`, `unmark`, and `delete`.
- **Error Handling**: If you enter an invalid command, Amadeus will display an error message with guidance on the correct format.

---

## FAQ

**Q: How do I save my tasks?**  
A: Tasks are automatically saved to a file (`Memory.txt`) in the same directory as the application. You don’t need to manually save anything.

**Q: Can I edit a task after adding it?**  
A: Currently, Amadeus does not support direct editing of tasks. You can delete the task and add a new one with the updated details.

**Q: What happens if I close the application without typing `bye`?**  
A: Your tasks will still be saved, but it’s recommended to use the `bye` command to ensure a clean exit.

---

Enjoy using **Amadeus Task Manager**! If you have any questions or feedback, feel free to reach out. 😊
