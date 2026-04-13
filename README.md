"# Smart Bank - Android Mobile Application

## Overview

Smart Bank is a **production-grade digital banking Android application** built with Java, implementing clean architecture principles. The app provides comprehensive banking features including authentication, money transfers, bill payments, transaction history, and secure data management.

**Package Name:** `com.marwan.smartbank`  
**Min SDK:** 26 (Android 8.0)  
**Target SDK:** 34 (Android 14)  
**Language:** Java 11+

---

## Features

### 1. Authentication & Security
- ✅ OTP-based phone authentication
- ✅ JWT token management
- ✅ Biometric authentication support
- ✅ Data encryption (AES-256)
- ✅ Secure token storage

### 2. Banking Operations
- ✅ View account balance and details
- ✅ Send money to other accounts
- ✅ Pay bills via multiple channels
- ✅ View transaction history
- ✅ Manage beneficiaries

### 3. Localization
- ✅ English (LTR) support
- ✅ Arabic (RTL) support
- ✅ Dynamic language switching
- ✅ All 600+ strings translated

### 4. User Experience
- ✅ Material Design 3 UI
- ✅ Dark/Light theme support
- ✅ Intuitive navigation
- ✅ Real-time balance updates
- ✅ Push notifications

### 5. Performance & Reliability
- ✅ Efficient database queries
- ✅ Network error handling
- ✅ Retry logic with exponential backoff
- ✅ Image caching with Glide
- ✅ ProGuard code obfuscation

---

## Architecture

### Design Pattern: MVVM + Clean Architecture

```
┌─────────────────────────────────────┐
│      PRESENTATION LAYER             │
│  (Activities, Fragments, ViewModels)│
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│      DOMAIN LAYER                   │
│  (Entities, UseCases, Repositories) │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│      DATA LAYER                     │
│  (Local DB, Remote API, Repositories)
└─────────────────────────────────────┘
```

---

## Getting Started

### Prerequisites
- Android Studio Giraffe or later
- JDK 11 or higher
- Android SDK API 34

### Installation

1. **Import Project**
   - Open Android Studio
   - File → Open → Select project root
   - Wait for Gradle sync

2. **Configure Firebase**
   - Download `google-services.json` from Firebase Console
   - Place in `app/` directory

3. **Update API Endpoint**
   - Edit `app/build.gradle`
   - Update `API_BASE_URL`:
   ```gradle
   buildConfigField "String", "API_BASE_URL", '"https://your-api.com/"'
   ```

4. **Build & Run**
   ```bash
   ./gradlew assembleDebug
   ```

---

## Project Structure

```
app/src/main/java/com/marwan/smartbank/
├── presentation/       # Activities, Fragments, ViewModels, Adapters
├── domain/            # Entities, Repository interfaces
├── data/              # Database, API services, Repositories impl
├── di/                # Hilt dependency injection modules
├── utils/             # Constants, validators, helpers
├── security/          # Encryption, biometric, token management
└── services/          # Firebase notifications
```

---

## Key Technologies

- **Architecture:** MVVM + Clean Architecture
- **Database:** Room
- **Networking:** Retrofit + OkHttp
- **DI:** Hilt
- **Reactive:** RxJava3
- **Image Loading:** Glide
- **Security:** AndroidX Security Crypto
- **Firebase:** Auth, Messaging, Analytics, Crashlytics
- **Testing:** JUnit, Mockito, Espresso

---

## Build & Deploy

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

### Run Tests
```bash
./gradlew test                    # Unit tests
./gradlew connectedAndroidTest    # Instrumented tests
```

---

## API Endpoints

- `POST /auth/request-otp` - Request OTP
- `POST /auth/verify-otp` - Verify OTP
- `POST /auth/refresh-token` - Refresh token
- `GET  /accounts/{userId}` - Get accounts
- `POST /payments/send-money` - Transfer money
- `POST /payments/bill-payment` - Pay bills
- `GET  /transactions/{userId}` - Get transactions

---

## Security

- ✅ Encrypted SharedPreferences
- ✅ AES-256 encryption for sensitive data
- ✅ Android Keystore for key management
- ✅ HTTPS + Certificate Pinning
- ✅ JWT token refresh mechanism
- ✅ No sensitive data in logs

---

## Performance

- **Cold Start:** < 2 seconds
- **Memory:** < 100MB baseline
- **Database:** < 100ms queries
- **FPS:** 60fps on transaction scroll

---

## Testing Strategy

- Unit tests for ViewModels, Repositories, Utils
- Instrumented tests for UI flows
- Mockito for mocking dependencies
- Espresso for UI testing
- Target: 70%+ code coverage

---

## Version History

**v1.0.0** (April 13, 2026)
- Login & OTP authentication
- Dashboard with balance
- Money transfers
- Bill payments
- Transaction history
- Multi-language (EN, AR)
- Biometric auth
- Push notifications

---

## License

Proprietary - Confidential

---

## Support

For issues or questions, contact the development team.

Generated: April 13, 2026" 
