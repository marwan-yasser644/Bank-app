# Smart Bank Setup Guide

## Prerequisites
- Android Studio Giraffe or 2022.2.1+
- JDK 11 or higher
- Gradle 8.0+
- Android SDK min 26, target 34

## Step 1: Clone & Open Project

```bash
git clone <repository-url>
cd mobile-app
```

Open in Android Studio:
- File → Open → Select project root
- Wait for Gradle sync

## Step 2: Configure Local Environment

1. Create `local.properties`:
   ```bash
   cp local.properties.example local.properties
   ```

2. Edit `local.properties`:
   ```properties
   sdk.dir=/path/to/android-sdk
   ```

## Step 3: Firebase Setup

1. Go to Firebase Console: https://console.firebase.google.com
2. Create/select project "Smart Bank"
3. Register Android app:
   - Package name: `com.marwan.smartbank`
   - SHA-1: Get from Android Studio
4. Download `google-services.json`
5. Place in `app/` directory

## Step 4: API Configuration

Edit `app/build.gradle`:

```gradle
buildTypes {
    debug {
        buildConfigField "String", "API_BASE_URL", 
            '"https://staging-api.smartbank.example.com/"'
    }
    release {
        buildConfigField "String", "API_BASE_URL", 
            '"https://api.smartbank.example.com/"'
    }
}
```

## Step 5: Build & Run

```bash
# Clean build
./gradlew clean build

# Run on emulator/device
./gradlew installDebug

# Or use Android Studio: Run → Run 'app'
```

## Step 6: Run Tests

```bash
# Unit tests
./gradlew test

# Instrumented tests (requires emulator)
./gradlew connectedAndroidTest
```

## Troubleshooting

### Gradle Sync Issues
- File → Sync Now
- Invalidate Caches → Restart
- Delete `.gradle` and `build` folders

### Build Fails
```bash
./gradlew clean build --info
```

### Hilt Not Generated
- Rebuild project
- Check `@HiltViewModel`, `@AndroidEntryPoint` annotations

### Firebase Issues
- Confirm `google-services.json` in `app/` directory
- Check Firebase Console configuration

## Development Workflow

1. Create feature branch: `git checkout -b feature/name`
2. Make changes following code style
3. Run tests: `./gradlew test`
4. Commit: `git commit -m 'Add feature'`
5. Push: `git push origin feature/name`
6. Create Pull Request

## Code Style Guidelines

- Follow Google Java Style Guide
- Use ViewBinding instead of findViewById
- Use LiveData/StateFlow for reactive updates
- Proper error handling with try-catch
- Logging with Timber
- No hardcoded strings (use resources)

## Performance Checklist

- [ ] No blocking main thread
- [ ] Proper lifecycle management
- [ ] Database queries optimized
- [ ] Image loading with Glide
- [ ] RecyclerView with DiffUtil
- [ ] No memory leaks

## Security Checklist

- [ ] HTTPS only
- [ ] Sensitive data encrypted
- [ ] No passwords/tokens logged
- [ ] Input validation
- [ ] OWASP compliance

## Release Checklist

- [ ] All tests passing
- [ ] Code review completed
- [ ] ProGuard rules updated
- [ ] Version number updated
- [ ] Release notes prepared
- [ ] Firebase Crashlytics configured
- [ ] Signed APK/AAB generated

## Useful Commands

```bash
# Clean and build
./gradlew clean build

# Generate debug APK
./gradlew assembleDebug

# Generate release AAB
./gradlew bundleRelease

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Generate code coverage
./gradlew jacocoTestReport

# Check for lint issues
./gradlew lint

# Format code
./gradlew spotlessApply
```

## Resources

- [Android Developer Guide](https://developer.android.com)
- [Architecture Samples](https://github.com/android/architecture-samples)
- [Material Design 3](https://material.io/design)
- [Firebase Documentation](https://firebase.google.com/docs)
- [Retrofit Documentation](https://square.github.io/retrofit)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)

## Contact Support

For issues or questions:
- Create GitHub issue
- Email: dev@smartbank.example.com
- Slack: #smartbank-android

---
Last Updated: April 13, 2026
