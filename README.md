# AR Drill Placement App

An educational Android AR app that lets users select different drills and place their corresponding 3D models in the real world using ARCore and SceneView, built fully with Jetpack Compose and a modern modular architecture.

**🔗 APK Link →**  
[Download APK](https://drive.google.com/file/d/1U1YToWer9phMoiys9o3YUtZwHbhmlEoP/view?usp=drivesdk)

---

## ✨ Features

- **Select and Preview Drills**: Choose from multiple drill models.  
- **AR Placement**: Place the selected 3D model on real surfaces using ARCore.  
- **One-at-a-time Placement**: Only the most recent drill is shown in the scene.  
- **Async Model Loading**: UI/UX remains smooth while models download.  
- **Kotlin + Jetpack Compose**: 100% Compose UI, Material3, and best-practices project structure.  

---

## 📸 Demo

<p align="center">
  <img src="https://github.com/user-attachments/assets/cdd3203c-ad00-4688-8ff7-32cab808af9b" width="30%" />
  <img src="https://github.com/user-attachments/assets/4c6388e4-3dba-4cad-8f0b-df0aeea7ca13" width="30%" />
  <img src="https://github.com/user-attachments/assets/c58e6882-cc96-4fac-bae0-3a4331106c5c" width="30%" />
</p>

---

## 📂 Project Structure


```
app/
├── manifests/
│   └── AndroidManifest.xml
├── kotlin+java/
│   └── com/sachin/arapp/
│       ├── data/
│       │   ├── Drill.kt
│       │   └── DrillRepository.kt
│       ├── di/
│       │   └── AppModule.kt
│       ├── navigation/
│       │   └── AppNavHost.kt
│       ├── ui/
│       │   ├── screens/
│       │   │   ├── ar/ArScreen.kt
│       │   │   └── drill/
│       │   │       ├── DrillDetailScreen.kt
│       │   │       ├── DrillSelectionScreen.kt
│       │   │       └── DrillViewModel.kt
│       │   └── theme/
│       ├── MainActivity.kt
│       └── MyApplication.kt
└── assets/
    ├── drill1.glb
    ├── drill2.glb
    └── drill3.glb
```
---

## 📦 APK Download

Latest APK download via Google Drive.  
Built on Android Studio Hedgehog, minSdk 26, ARCore compatible.

---

## 🚀 How to Run

1. Clone the repo or download and unzip the project.  
2. Open in Android Studio Giraffe/Hedgehog or newer.  
3. Build and run the app on a real ARCore-supported Android device.  
4. Grant the camera permission when asked.  
5. Select a drill model, tap **“Start AR drill.”**  
6. Move your phone slowly over a well-lit, textured surface.  
7. Wait for ARCore to detect a plane (mesh/grid will appear).  
8. Tap on the detected surface to place the selected drill’s 3D model.  
9. Tap again to move it; only one drill is shown at a time.

---

## 🗂️ Model Info

All 3D models (`drill1.glb`, `drill2.glb`, `drill3.glb`) are shipped in `/app/src/main/assets/`.  
Models must have colored materials/textures to be visible in AR (test with [gltf-viewer.donmccurdy.com](https://gltf-viewer.donmccurdy.com)).


