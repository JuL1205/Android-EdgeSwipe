# Android EdgeSwipe
Android EdgeSwipe Transition Library

![SlideshowToolbar Sample Material](https://raw.githubusercontent.com/JuL1205/Android-EdgeSwipe/master/images/essample.gif)

# Usage

For a working implementation of this project see the `app/` folder.

* Include the following dependency in your project `build.gradle` file.
```groovy
compile ''
```
* Set Transparent Theme
```xml
<activity
 ...
 android:theme="@style/AppCompatTransparent">
 ...
</activity>
```

* Using Activity
```java
 slideshowImageView.setImages(R.drawable.test1, R.drawable.test2, R.drawable.test3);
```

there is two way to implement EdgeSwipe.

1 For adding images
```java
slideshowImageView.addImages(R.drawable.test3, R.drawable.test4, R.drawable.test5);


# License

    Copyright 2016 JuL

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
