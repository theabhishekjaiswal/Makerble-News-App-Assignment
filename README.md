# Android Developer Assignment - Makerble

## Overview
This project is an Android application that fetches news articles from the [News API](https://newsapi.org/) and displays them in a user-friendly interface. The app includes two main features: a **News** section for browsing the latest articles and a **Bookmarks** section for saving and viewing favorite articles. Users can bookmark articles to view them offline.

## Functional Requirements
### 1. **Bottom Navigation UI**
- **Sections:**
  - **News** (Right Side): Displays the latest news articles.
  - **Bookmarks** (Left Side): Displays all bookmarked articles.
  
### 2. **News Screen**
- Fetches and displays data from the News API.
- Each article card shows:
  - **Source:** Name of the news source.
  - **Title:** Article title.
  - **Description:** Brief description of the article.
  - **Image URL:** Thumbnail image of the article.
- Clicking on an article opens a detailed view, displaying:
  - Full content
  - Author name
  - Publication date
  
### 3. **Bookmarks Screen**
- Users can bookmark any article from both the news list and detailed views.
- All bookmarked articles are displayed in the **Bookmarks** section.
- Bookmarks are stored locally, ensuring offline access.

### 4. **State Management**
- The app handles various states such as loading, error, and empty states to ensure a smooth user experience.

## Tech Stack
- **Language:** Kotlin
- **UI Design:** XML
- **Architecture:** MVVM (Model-View-ViewModel)
- **Networking:** Volley
- **Image Loading:** Glide

## Libraries Used
- **Volley:** For making network requests.
- **Glide:** For loading and displaying images from URLs.
- **View Binding:** For efficient and type-safe view handling.
- **Gson:** For parsing JSON responses from the API.

## Screenshots
Here are some screenshots of the application:

- **[Screenshot 1](https://drive.google.com/file/d/1M-CL7MU8WrqEatHzldbCA9kF_GRH0jwb/view?usp=drive_link)** 
- **[Screenshot 2](https://drive.google.com/file/d/1Ly9ddUn1tKwc06R22np7vOXHln42PAKS/view?usp=drive_link)** 
- **[Screenshot 3](https://drive.google.com/file/d/1LqPy2o71Wm_IxbEMXYexFhUDniNVYVKw/view?usp=drive_link)** 
- **[Screenshot 4](https://drive.google.com/file/d/1LrGTGSdi2o3hoQXC_LmDEec2WiigmL0k/view?usp=drive_link)** 

## Demo Video & APK
- [Demo Video & APK Download](https://drive.google.com/drive/folders/1LbX2npfaeRbeEwUOspI-4X0Vx6rrpCIl?usp=drive_link)

This project follows the best practices for Android development with an intuitive UI, proper state handling, and efficient memory management.
