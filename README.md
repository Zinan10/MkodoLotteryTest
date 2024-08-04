
<h1 align="center">Lottery Demo Application</h1>


This project is an Android application for managing and viewing lottery draws, demonstrating the use of modern Android development practices, including the MVVM architecture, Jetpack Compose, and the Repository pattern.

## Features

- View list of lottery draws
- View details of a specific lottery draw
- Navigate between different screens
- Themed UI following Material Design principles

## Project Structure

### `src/main/java/com/example/lotterydemo/`

#### Application Entry Point

- LotteryApp.kt: The main application class that initializes necessary components like dependency injection using Hilt.

#### Data Layer

The data layer is responsible for handling data operations. It includes data models, repository interfaces, and their implementations.

- data/model/
  - LotteryDraw.kt: Data class representing a lottery draw.
  
- data/repo/
  - LotteryRepository.kt: Interface defining the methods for accessing lottery data.
  - impls/LotteryRepositoryImpl.kt: Implementation of `LotteryRepository` that fetches data from a JSON file or an API.

#### Domain Layer

The domain layer contains the business logic of the application, including use case definitions.

- domain/usecase/
  - GetLotteryDrawsUseCase.kt: Use case for fetching the list of lottery draws.
  - GetLotteryDrawDetailsUseCase.kt: Use case for fetching details of a specific lottery draw.

#### Presentation Layer

The presentation layer handles UI-related code, including view models, navigation, and screen components.

- presentation/components/
  - DrawItem.kt: Composable function for displaying a single lottery draw item in a list.
  
- presentation/navigation/
  - MainNavigation.kt: Sets up the navigation graph for the application.
  - NavigationScreens.kt: Defines the different screens and their routes.
  
- presentation/screens/drawlist/
  - LotteryListScreen.kt: Composable function for displaying the list of lottery draws.
  - LotteryListViewModel.kt: ViewModel for managing the state of the lottery list screen.
  - LotteryDrawUiState.kt: Represents the UI state for the lottery list.

- presentation/screens/drawdetails/
  - DrawDetailsScreen.kt: Composable function for displaying the details of a specific lottery draw.
  - DrawDetailsViewModel.kt: ViewModel for managing the state of the draw details screen.
  - DrawDetailsUiState.kt: Represents the UI state for the draw details.

### `src/main/res/`

Contains the resources for the application, including layouts, images, and strings.

- layout/: XML layout files for activities and fragments.
- values/: XML files for defining strings, colors, themes, etc.
- mipmap/: Launcher icons for different screen densities.
- raw/: Contains raw asset files such as `lottery.json`.

### Testing

- androidTest/: Contains instrumented tests that run on an Android device.
  - ExampleInstrumentedTest.kt: Example instrumented test.

- test/: Contains unit tests for the application.
  - LotteryRepositoryImplTest.kt: Unit tests for the repository implementation.
  - ExampleUnitTest.kt: Example unit test.

### Key Components and Code Walkthrough

#### LotteryRepositoryImpl.kt

This class is the core of the data layer, implementing the `LotteryRepository` interface. It fetches lottery data from a local JSON file.

```kotlin
class LotteryRepositoryImpl : LotteryRepository {
    override suspend fun getLotteryDraws(): List<LotteryDraw> {
        // Logic to read from lottery.json and parse it into LotteryDraw objects
    }

    override suspend fun getLotteryDrawDetails(drawId: String): LotteryDraw {
        // Logic to fetch details of a specific lottery draw
    }
}
```

#### LotteryListViewModel.kt

This ViewModel manages the state for the `LotteryListScreen`. It uses `GetLotteryDrawsUseCase` to fetch data and updates the UI state accordingly.

```kotlin
class LotteryListViewModel @ViewModelInject constructor(
    private val getLotteryDrawsUseCase: GetLotteryDrawsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LotteryDrawUiState>(LotteryDrawUiState.Loading)
    val uiState: StateFlow<LotteryDrawUiState> = _uiState

    init {
        fetchLotteryDraws()
    }

    private fun fetchLotteryDraws() {
        viewModelScope.launch {
            _uiState.value = LotteryDrawUiState.Success(getLotteryDrawsUseCase.execute())
        }
    }
}
```

#### LotteryListScreen.kt

This composable function displays a list of lottery draws. It observes the state from `LotteryListViewModel` and updates the UI accordingly.

```kotlin
@Composable
fun LotteryListScreen(viewModel: LotteryListViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is LotteryDrawUiState.Loading -> {
            // Show loading spinner
        }
        is LotteryDrawUiState.Success -> {
            // Display the list of lottery draws
        }
        is LotteryDrawUiState.Error -> {
            // Show error message
        }
    }
}
```

### Tools and Libraries

- **Kotlin**: The programming language used for the project.
- **Jetpack Compose**: For building native UI.
- **Hilt**: For dependency injection.
- **MVVM Architecture**: Model-View-ViewModel architecture.
- **Repository Pattern**: For managing data from different sources.
- **Coroutine**: For asynchronous programming.
- **Flow**: For managing state and data streams.
- **JUnit**: For writing and running tests.
- **Mockk**: Mocking library for Kotlin.

### Screenshots

#### Lottery Draw List Screen

<img width="296" alt="Screenshot 2024-08-04 at 23 03 28" src="https://github.com/user-attachments/assets/eaf78927-103f-4ba0-8a6f-b4d0814254a4">



#### Lottery Draw Details Screen

<img width="306" alt="Screenshot 2024-08-04 at 23 03 21" src="https://github.com/user-attachments/assets/6663df63-72d2-46ef-bc68-5cc19f7b87c3">

### Getting Started

### Prerequisites

- Android Studio Arctic Fox or newer
- Kotlin 1.5 or newer
- Gradle 7.0 or newer

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/Zinan10/lottery-demo.git
   ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the application on an emulator or physical device.

