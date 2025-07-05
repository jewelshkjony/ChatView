# ChatView Extension for MIT App Inventor / Kodular

**Author**: BBL  
**Version**: 2  
**Status**: Stable  
**Platform**: MIT App Inventor, Kodular, Niotron, etc.

---

## 📚 Overview

`ChatView` is a powerful, easy-to-use extension that enables you to display a modern, scrollable chat UI inside any arrangement in your App Inventor-based project.

It uses Android's native `RecyclerView` under the hood, allowing smooth performance, efficient rendering, and customization support.

---

## 🚀 Features

- Lightweight chat interface with dynamic message addition
- Sender/Receiver message alignment
- Fully customizable bubble colors, corner radius, text style, font size
- Smooth scrolling to the latest message
- Works in both vertical and horizontal arrangements

---

## 🧩 Blocks

### 🔧 Core Functions

```ai2blocks
CreateChatView(arrangement)
```
Creates the chat view inside the given layout.

```ai2blocks
AddMessage(message, isSender)
```
Adds a message to the chat. `isSender` controls alignment (`true = right`, `false = left`).

```ai2blocks
ClearMessages()
```
Removes all chat messages.

---

### 🎨 Customization Properties

| Block | Description |
|-------|-------------|
| `BubbleColorSender(color)` | Set background color of sender messages |
| `BubbleColorReceiver(color)` | Set background color of receiver messages |
| `TextColor(color)` | Set the color of the message text |
| `TextSize(number)` | Set the size of message text |
| `FontBold(boolean)` | Enable or disable bold text |
| `FontItalic(boolean)` | Enable or disable italic text |
| `BubblePadding(pixels)` | Set padding inside bubbles |
| `MaxBubbleWidth(pixels)` | Limit the maximum width of bubbles |
| `BubbleCornerRadius(pixels)` | Set the corner radius for rounded message shapes |

All properties must be set **before** adding messages to take effect.

---

## 🧪 Example

```blocks
ChatView1.CreateChatView(VerticalArrangement1)

ChatView1.BubbleColorSender(MakeColor(0, 150, 255))
ChatView1.BubbleColorReceiver(MakeColor(100, 100, 100))
ChatView1.TextColor(MakeColor(255, 255, 255))
ChatView1.TextSize(16)
ChatView1.FontBold(true)
ChatView1.BubbleCornerRadius(30)

ChatView1.AddMessage("Hi there!", true)
ChatView1.AddMessage("Hello!", false)
```

---

## 📦 Installation

1. Download the latest release from the [Releases](https://github.com/Amanpro83/ChatView/releases) tab.
2. Import the `.aix` file into your App Inventor/Kodular project.
3. Drag the extension into the designer.
4. Use `CreateChatView` inside a layout and start adding messages.

---

## 🛠️ Dependencies

These are bundled within the AIX, so no manual setup is required:

- `androidx.recyclerview:1.3.1`
- `androidx.core:1.10.1`

---

## 📸 Screenshots

*You can include screenshots here showing the ChatView in action.*

---

## ✅ Tested Platforms

- MIT App Inventor (ai2.appinventor.mit.edu)
- Kodular (creator.kodular.io)
- Android 5.0 and above

---


## 📬 Contact

Have questions or want to showcase your project using ChatView?  
Post in the [MIT App Inventor Community](https://community.appinventor.mit.edu/t/free-chatview-open-source-and-made-with-recyclerview-library/151379) or open an issue here on GitHub.
