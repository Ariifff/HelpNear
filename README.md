# 📱 HelpNear

HelpNear is a simple Android app built as part of my internship assignment.  
It allows users to **report nearby issues** (like wire cuts, water leaks, etc.) quickly, with details like contact, location, and description.


---

## ✨ Features

✅ User login & registration (Firebase Authentication)  
✅ Submit new complaints with:
- Issue type (e.g., Wire Cut, Pothole)
- Contact number
- Location
- Description

✅ View submitted complaints  
✅ Clean and intuitive UI

---

## 🛠 Tech Stack

- **Android** (Java)
- **Firebase Authentication** – for user login & signup
- **Firebase Realtime Database** – to store complaints
- **Material Design**

---

## 🚀 Getting Started

To run the app locally:

1. Clone this repository:
   ```bash
   git clone https://github.com/Ariifff/HelpNear.git

2.Open in Android Studio.

3.Make sure to add your own google-services.json (Firebase config) inside the app/ folder.

4.Build & run on an emulator or Android device.

##📂 Project Structure
```
HelpNear/
├── app/
│   ├── java/com/arif/helpnear/
│   │   ├── LoginActivity.java
│   │   ├── RegisterActivity.java
│   │   ├── MainActivity.java
│   │   └── ComplaintActivity.java
│   ├── res/
│   │   ├── layout/
│   │   └── drawable/
│   └── AndroidManifest.xml
├── build.gradle
└── ...
