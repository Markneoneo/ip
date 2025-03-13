# ✨ The Amadeus System ✨

👋 **Greetings, I'm Makise Kurisu, also known as Amadeus.**  
I’m your personal assistant for managing tasks. Whether you’re a busy scientist or just someone trying to stay organized, I’m here to help you keep track of everything. Let’s make this timeline a productive one!

![Amadeus](https://github.com/user-attachments/assets/0e7ecd31-601c-4571-91c4-b1f3c84b11cd)

---

## ✍️ What I Can Do

I’m designed to help you manage your tasks efficiently. Here’s what I can do for you:

- **Add Tasks**: Keep track of your to-dos, deadlines, and events.
- **View Tasks**: See all your tasks at a glance or search for specific ones.
- **Mark Tasks**: Mark tasks as complete or incomplete.
- **Delete Tasks**: Remove tasks you no longer need.
- **Reset Tasks**: Clear your entire task list if you want a fresh start.

Using me is simple! Just type commands to add, view, or manage your tasks.
### To see the full list of commands, check out the [User Guide](https://markneoneo.github.io/ip/).

---

## 🙋‍♀️ Why Choose Me?

- **Flexible Date Formats**: I understand dates and times in many formats, so you don’t have to worry about getting it "just right."
- **Automatic Saving**: Your tasks are saved automatically, so you don’t have to lift a finger.
- **User-Friendly**: I’m designed to be intuitive and easy to use, even if you’re not a tech genius.

---

## 🏁 Getting Started

1. Download the latest version of `Amadeus` from [here](https://github.com/Markneoneo/ip).
2. Open **IntelliJ** and Navigate to the folder where you saved the application and run in **Fullscreen**:
   ```
   java -jar amadeus.jar
   ```
3. I’ll greet you with a welcome message. Start typing commands, and I’ll take care of the rest!

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
  
I hope you enjoy using me to manage your tasks. If you have any questions or feedback, feel free to reach out!
### El Psy Kongroo

![Amadeus 2](https://github.com/user-attachments/assets/b47e66c8-a0e7-49fa-b3de-bd85b138d3a2)

