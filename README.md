# 📱 Eh-Ho Android

[![forthebadge](https://forthebadge.com/images/badges/built-for-android.svg)](https://forthebadge.com)

## 📝 Description

Little Android client for consume `https://mdiscourse.keepcoding.io/` API

> Final Android Practice for KeepCoding Mobile 10

## 📷 Screenshots

![Login](/screenCaptures/login.png)
![Register](/screenCaptures/register.png)

![Topics](/screenCaptures/topics.png)
![Search Topic](/screenCaptures/search_topic.png)
![Create Topic](/screenCaptures/create_topic.png)

![Topic Detial](/screenCaptures/topic_detail.png)
![Reply Topic](/screenCaptures/reply_topic.png)

![Users](/screenCaptures/users.png)
![Search User](/screenCaptures/search_user.png)
![User Detail](/screenCaptures/user_detail.png)

## 🚧 Application Architecture

[Kotlin](https://kotlinlang.org/) app based on ([MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) + [Delegate Pattern](https://en.wikipedia.org/wiki/Delegation_pattern)) as main architecture.

[Retrofit](https://square.github.io/retrofit/) as networking library.

[Glide](https://bumptech.github.io/glide/) as image loading library.

[Room](https://developer.android.com/topic/libraries/architecture/room) for local database.

[LiveData](https://developer.android.com/topic/libraries/architecture/livedata) for observe database changes.

[GitFlow](https://datasift.github.io/gitflow/IntroducingGitFlow.html) as Git methodology.

> Legacy side of the app uses [MVC](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) as architecture and [Volley](https://github.com/google/volley) as networking library.

## ✅ App Features

##### Register

- Register new users

##### Login

- Login for registered users

##### Topics

- Paginated list of recent topic
- Pull to refresh topics
- Search topics
- Create new topic
- Toolbar menu for user logout

##### Posts

- Topic detail with paginated list of posts
- Reply topic using post

##### Users

- List users
- Pull to refresh users
- Search users
- User detail view

## 🛠 Work in progress

- Cache user objects on Room database.
  > Branch ➡️ feature/cacheUsers

## 👨‍💻 Author

> Javier Laguna
