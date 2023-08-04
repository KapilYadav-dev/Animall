# Animall

## Screenshots

| Empty State            | Home 1    | Home 2            | Add Sale        | Splash         |
|---------------------|---------------------|---------------------|---------------------|---------------------|
| ![Image 1](https://github.com/KapilYadav-dev/Animall/assets/69911517/06b60411-ebf3-4d45-817c-38c05043a083) | ![Image 2](https://github.com/KapilYadav-dev/Animall/assets/69911517/7f76ae96-5330-4347-8fcc-4193232d4f6e) | ![Image 3](https://github.com/KapilYadav-dev/Animall/assets/69911517/e90ec0d4-8dda-410f-8543-6734ad0c7ef0) | ![Image 4](https://github.com/KapilYadav-dev/Animall/assets/69911517/0c43ed8a-58e8-431e-8d4c-4fa598de1c96) | ![Image 5](https://github.com/KapilYadav-dev/Animall/assets/69911517/107ba4a7-5a69-492f-9976-d76a7d6b85f1) |

## Demo Video

https://github.com/KapilYadav-dev/Animall/assets/69911517/8a2d005a-8ed9-4b9a-89a3-2f84d2c8ade7

## Download Apks

https://github.com/KapilYadav-dev/Animall/raw/main/apks/app-release.apk

https://github.com/KapilYadav-dev/Animall/raw/main/apks/app-debug.apk

## Tech and libraries used

1. Compose framework
2. Room db
3. Flows
4. Coroutines
5. Material UI 3

## Getting Started

1. Clone this repository.
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or device.

## MVVM Architecture

The app is structured using the MVVM architecture pattern, which separates the app into three main components:

- **Model**: Represents the data and business logic of the app. It abstracts the data sources and provides data to the ViewModel.

- **View**: Represents the UI components of the app. It observes the changes in the ViewModel and updates the UI accordingly.

- **ViewModel**: Acts as an intermediary between the Model and View. It retrieves data from the Model and prepares it for the View. It also handles user interactions and updates the Model as needed.

This separation of concerns makes the codebase more maintainable, testable, and scalable.

