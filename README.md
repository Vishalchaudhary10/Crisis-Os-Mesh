# Crisis OS

Crisis OS is an offline-first, decentralized mesh communication network and emergency coordination platform designed for disaster scenarios where traditional infrastructure (cell towers, internet) has failed.

## 🚀 Features
* **Decentralized Mesh Networking**: Google Nearby Connections API powering a peer-to-peer (P2P) auto-healing communication backbone.
* **Offline Messaging**: Send and receive encrypted messages securely without an internet connection.
* **Emergency Broadcasting (SOS)**: Dedicated critical channels to coordinate disaster aid, supplies, and real-time alerts.
* **Location Mapping**: Offline maps with Danger Zones and Checkpoint sharing.
* **Distributed Contact Discovery**: Alias-based connection handshakes and proximity discovery.

## 🛠 Tech Stack
* **Language:** Kotlin
* **Architecture:** Clean Architecture (UI -> Domain/UseCases -> Data -> Core)
* **UI:** Jetpack Compose (Material 3)
* **Dependency Injection:** Dagger Hilt
* **Local Database:** Room Database
* **Network Engine:** Customized Google Nearby Connections & Bluetooth LE
* **Concurrency:** Kotlin Coroutines & Flows

## 🧬 Architecture Overview
Crisis OS strictly adheres to Clean Architecture:
* `core/`: EventBus, Permissions, Device APIs.
* `data/`: Room DAOs, Mesh Handlers, DTOs, Repository Implementations.
* `domain/`: UseCases, Domain Data Models, Repository Interfaces.
* `ui/`: Compose Screens, ViewModels strictly driven by UseCases.

## 📷 Screenshots
*(Add screenshots here)*

## 📦 Getting Started
1. Clone the repository.
2. Open in Android Studio.
3. Sync Gradle and build the project.
4. Run on a physical Android device (Emulators do not fully support Nearby Connections mesh behavior).

### Permissions Required
- `BLUETOOTH_ADVERTISE`, `BLUETOOTH_CONNECT`, `BLUETOOTH_SCAN`
- `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`
- `NEARBY_WIFI_DEVICES`
- `POST_NOTIFICATIONS`

## 🛡 License
*(Add license details here)*