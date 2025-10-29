# ViewModel Practice App – Demo

This is a **simple Android Compose app** demonstrating the use of **Jetpack ViewModel** with **state management**, **Compose UI**, and **interactive components**.

> **Package:** `com.celalalbayrak.viewmodelcalismasi`  
> **Developed by:** Celalce  
> **Purpose:** Learning & showcasing ViewModel + Compose architecture

---

## Features

### 1. **Counter Screen (`SayacEkrani`)**
- Displays a number (`sayi`) managed by `SayacViewModel`
- Buttons to:
  - **Increase** (`+1`)
  - **Decrease** (`-1`)
  - **Reset** to `0`
  - **Multiply by 2**
- Includes:
  - A **Card** with custom text
  - A **Switch** (toggle)

### 2. **Color Changer Screen (`RenkEkrani`)**
- Background color changes between **Red** and **Blue**
- Controlled by `RenkViewModel`
- Buttons change color and update dynamically

### 3. **State Preservation**
- Thanks to **ViewModel**, the counter and color state **survive configuration changes** (e.g. screen rotation)

---

## Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVVM (Model-View-ViewModel)
- **State Management:** `mutableStateOf`, `remember`
- **Components:** `LazyVerticalGrid`, `Button`, `Card`, `Switch`, `Column`

---

## How to Run

1. Open in **Android Studio**
2. Build and run on an emulator or device (API 21+)
3. Interact with buttons to see ViewModel in action

---

## Screenshots (Preview)

```plaintext
[Preview shows full screen with yellow counter section and red/blue background section]



See @Preview function: TumEkranPreview()


Code Highlights
ViewModel Example
kotlinclass SayacViewModel : ViewModel() {
    var sayi by mutableStateOf(0)
        private set

    fun arttir() { sayi++ }
    fun azalt() { sayi-- }
    fun sifirla() { sayi = 0 }
    fun ikiyecarp() { sayi *= 2 }
}
Compose UI with ViewModel
kotlin@Composable
fun SayacEkrani(viewModel: SayacViewModel = viewModel()) {
    Column(modifier = Modifier.background(Color.Yellow)) {
        Text("Sayı: ${viewModel.sayi}")
        // Buttons call viewModel functions
    }
}

Learning Goals Achieved

 Use ViewModel to manage UI state
 Connect Compose UI with ViewModel
 Preserve state across configuration changes
 Use LazyVerticalGrid, Card, Switch
 Clean separation of UI and logic


About Celalce

Celalce brings joy and excitement to every player with fun, immersive, and unforgettable gaming experiences.


Happy coding!
Made with ❤️ by Celalce
