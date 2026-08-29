# BoxVideo

[Русская версия](README_RU.md)

BoxVideo is a native Android video catalog and streaming client. It connects to a backend API, authenticates users with bearer tokens, keeps catalog data in a local Room database, and plays remote video sources through Media3 ExoPlayer.

The interface is built entirely with Jetpack Compose and follows a state-driven MVVM structure. Once signed in, a user can browse the synchronized catalog, open a title’s details, read its full description, and start playback in a dedicated landscape player. Playback can continue in Android Picture-in-Picture mode when the user leaves the app.

## Screenshots

<p align="center">
  <img src="docs/screenshots/catalog.jpg" width="30%" alt="BoxVideo catalog">
  <img src="docs/screenshots/catalog-scroll.jpg" width="30%" alt="Scrollable video catalog">
  <img src="docs/screenshots/profile.jpg" width="30%" alt="User profile panel">
</p>

<p align="center">
  <img src="docs/screenshots/video-details.jpg" width="32%" alt="Video details screen">
</p>

<p align="center">
  <img src="docs/screenshots/player.jpg" width="90%" alt="Landscape video player">
</p>

## Features

- User registration and sign-in
- Session restoration by validating the saved access token on launch
- Secure API requests using bearer-token authorization
- Video catalog synchronized from a remote backend
- Local Room cache exposed as reactive Kotlin `Flow` data
- Movie detail screen with artwork, title, and expandable description
- Remote video playback with Media3 ExoPlayer
- Seekable playback progress bar
- Landscape-oriented player screen
- Picture-in-Picture playback on Android 8.0 and newer
- User profile information and sign-out

## Application flow

1. On startup, BoxVideo reads the locally stored token and verifies it through `GET /auth/me`.
2. An unauthenticated user is directed to the registration/sign-in flow.
3. After successful authentication, the returned token is stored in Preferences DataStore.
4. The app requests the latest catalog from `GET /videos` and writes the response to Room in a transaction.
5. Compose screens observe the database, so catalog changes are reflected automatically.
6. Selecting a title opens its detail screen; pressing **Play** starts the first available video source in ExoPlayer.
7. Leaving the player places it into Picture-in-Picture mode on supported Android versions.

## Tech stack

| Area | Technology |
| --- | --- |
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Architecture | MVVM, Repository pattern, unidirectional state flow |
| Navigation | AndroidX Navigation 3 |
| Dependency injection | Dagger Hilt |
| Networking | Ktor Client with OkHttp and Kotlinx Serialization |
| Local database | Room |
| Preferences | DataStore |
| Async/state | Kotlin Coroutines and Flow |
| Video playback | AndroidX Media3 ExoPlayer |
| Image loading | Coil |
| Build system | Gradle Kotlin DSL, Version Catalog, KSP |

## Architecture

The code is divided into UI, domain, repository, and data responsibilities:

```text
app/src/main/java/com/example/boxvideo/
├── app/                  # Application class and Hilt initialization
├── data/
│   ├── local/
│   │   ├── datastore/    # Access token and authorization state
│   │   ├── db/           # Room entities, DAO, database, and mappers
│   │   └── mockvideo/    # Local video data source implementation
│   └── remote/
│       ├── authorization/# Authentication transport models
│       └── video/        # Video API sources, DTOs, and mappers
├── di/                   # Hilt modules
├── domain/               # Application models and mapper contract
├── repository/           # Authorization and video repositories
└── ui/
    ├── authorization/    # Sign-in and registration screens
    ├── main/             # Catalog, details, and navigation
    ├── player/           # ExoPlayer screen and playback state
    └── theme/            # Compose theme
```

The video repository uses the local database as the UI’s source of truth. Remote DTOs are mapped into domain and Room models, then saved transactionally. The UI observes Room through `Flow`, avoiding a direct dependency between Compose screens and network responses.

## Requirements

- Android Studio with support for Android Gradle Plugin 8.13.x
- JDK 11 or newer
- Android SDK 36
- An emulator or physical device running Android 7.0 (API 24) or newer
- A reachable BoxVideo-compatible backend API

Picture-in-Picture requires Android 8.0 (API 26) or newer.

## Backend configuration

The API base URL is defined as the `BASE_URL` build config field in [`app/build.gradle.kts`](app/build.gradle.kts):

```kotlin
buildConfigField(
    "String",
    "BASE_URL",
    "\"http://192.168.0.106:8080\""
)
```

Replace the address with the URL of your backend before building.

Common development addresses:

- Android Emulator to a server running on the same computer: `http://10.0.2.2:8080`
- Physical device: use the computer’s LAN IP, for example `http://192.168.1.10:8080`
- Hosted backend: use its full HTTPS origin, for example `https://api.example.com`

The current manifest permits cleartext HTTP traffic for local-network development. HTTPS should be used for production deployments.

## Expected API

BoxVideo currently communicates with these endpoints:

| Method | Endpoint | Purpose | Authorization |
| --- | --- | --- | --- |
| `POST` | `/auth/register` | Create an account and return a token | No |
| `POST` | `/auth/login` | Authenticate and return a token | No |
| `GET` | `/auth/me` | Validate the session and load user data | Bearer token |
| `GET` | `/videos` | Load the video catalog | Bearer token |
| `GET` | `/videos/{id}` | Load one video | Bearer token |
| `GET` | `/videos/search` | Search videos | Bearer token |
| `POST` | `/admin/videos` | Add a catalog item | Bearer token |
| `PUT` | `/admin/videos/{id}` | Update a catalog item | Bearer token |
| `DELETE` | `/admin/videos/{id}` | Delete a catalog item | Bearer token |

Authentication responses must contain a token. Video objects are expected to provide an ID, title, description, thumbnail URL, and one or more source entries containing a URL and quality value. The Android client sends the token as:

```http
Authorization: Bearer <token>
```

## Build and run

1. Clone the repository and open it in Android Studio.
2. Set the correct `BASE_URL` in `app/build.gradle.kts`.
3. Make sure the configured backend is running and reachable from the device.
4. Let Gradle synchronize the project.
5. Select a device with API 24 or newer and run the `app` configuration.

You can also build from the command line:

```bash
./gradlew assembleDebug
```

The debug APK is generated at:

```text
app/build/outputs/apk/debug/app-debug.apk
```

Run unit and instrumentation checks with:

```bash
./gradlew test
./gradlew connectedAndroidTest
```

The second command requires a connected device or running emulator.

## Notes

- Video and thumbnail URLs must be reachable directly from the Android device.
- The player currently starts the first source returned for a video.
- Catalog synchronization replaces local entries with the latest server response; an empty response clears the cached catalog.
- Access tokens and authorization state are stored in Preferences DataStore; catalog content is stored in Room.
- The player activity is locked to landscape orientation and declares Picture-in-Picture support in the manifest.
