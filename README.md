# Custom Keyboard Compose

This project demonstrates a fully customizable numeric input keyboard using Jetpack Compose. The keyboard is designed for use in scenarios where you need numeric input along with unit selection, such as weight or volume entry. It provides a flexible UI with user-friendly interactions like tap, long-press, and swipe gestures for number input and deletion.

![Screenshot_20241024_125248](https://github.com/user-attachments/assets/da08b376-952a-4621-85c3-5c175ba869d2)


## Features

- **Custom Numeric Keyboard:**
  - Provides a grid of numeric buttons for user input, arranged using `LazyVerticalGrid`.
  - Functional keys such as backspace (delete), space, and done buttons are also included, represented by icons.
  
- **Unit Selection:**
  - Includes a horizontal row of selectable units (`g`, `oz`, `ml`, etc.) using `LazyRow`.
  - Highlights the selected unit and provides a smooth interaction experience.

- **Custom Keyboard Integration:**
  - A `CustomKeybordTextField` composable integrates the custom keyboard into a text field for handling user input.
  - Focus management ensures the custom keyboard is shown only when the text field is active, hiding the system keyboard.

- **User Interaction:**
  - Supports gestures such as tap, long press, and touch events on the buttons.
  - Buttons provide feedback on interaction, changing color and style to reflect their current state (pressed, selected, etc.).
  
- **Custom Styling:**
  - Rounded buttons and unit selection items with configurable background colors and paddings.
  - Responsive layout ensures that the keyboard adjusts based on screen size.

## Usage

### Custom Keyboard

The `CustomKeyBoard` composable can be used to display a fully functional numeric keyboard. It supports input for numbers, deleting characters, inserting spaces, and confirming input with a done button.

Example:
```kotlin
CustomKeyBoard(
    text = inputValue, 
    onClick = { handleClick(it) }, 
    onClickDone = { handleDone() }, 
    onClickDelete = { handleDelete() }, 
    onClickSpace = { handleSpace() },
    selectedUnit = "g",
    onClickRowItem = { unitSelected = it }
)

