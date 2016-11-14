# Android EdgeSwipe
Android EdgeSwipe Transition Library

![SlideshowToolbar Sample Material](https://raw.githubusercontent.com/JuL1205/Android-EdgeSwipe/master/images/essample.gif)

# Usage

For a working implementation of this project see the `app/` folder.

* Include the following dependency in your project `build.gradle` file.
```groovy
compile 'com.funtory.jul:edgeswipe:1.0.1'
```
* Set Transparent Theme
```xml
<activity
 ...
 android:theme="@style/AppCompatTransparent">
 ...
</activity>
```

There is two way to implement EdgeSwipe.

* Using ESAppCompatActivity
```java
public class SampleActivity extends ESAppCompatActivity {
    ...
}
```
OR

* Using EdgeSwipeDelegate
```java
public class SampleActivity2 extends AppCompatActivity {
    private EdgeSwipeDelegate edgeSwipeDelegate = new EdgeSwipeDelegate();
    private ActionMode actionMode;
    ...
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(edgeSwipeDelegate.delegate(this, ev)){
            return true;
        } else{
            return super.dispatchTouchEvent(ev);
        }
    }
    
    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        actionMode = mode;
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        endActionMode();
    }
    
    private void endActionMode() {
        if (actionMode != null) {
            actionMode.finish();
        }
    }
    
    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
        actionMode = null;
    }
    ...
}
```


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
