####ionic
ionic是一个用来开发混合手机应用的，开源的，免费的代码库。专注于用Web开发技术，基于H5创建类似与手机平台原生应用的开发框架，绑定了AJ(Angular JS)和Sass。
这个框架的目的是从web的角度开发手机应用，基于PhoneGap的编译平台，可以实现编译各个应用平台的应用程序。
####PhoneGap
PhoneGap是一个基于HTML，CSS和javaScript的，创建移动平台应用程序的快速开发平台，能够使开发者利用ios，android和WP等智能手机的部分功能，例如定位、加速器，
联系人、声音和振动等功能，此外PhoneGap还有丰富的插件可以调用。业界很多主流的移动开发框架均源于PhoneGap。较著名的有Worklight、appMobi、WeX5等；其中WeX5
为国内打造，完全Apache开源，在融合Phonegap的基础上，做了深度优化，具备接近Native app的性能，同时开发便捷性也较好。原本由Nitobi公司开发，现在由Adobe拥有。
####Cordova
Cordova提供了一组设备相关的API，通过这组API，移动应用可以通过JavaScript来访问原生设备的功能，如摄像头，麦克风等。是从PhoneGap的核心引擎，类似于 Webkit
和Chrome的关系。
####node.js
Node.js是一个javascript运行环境，实际上是对Google V8引擎做了封装，V8引擎执行js的速度非常快，性能非常好，Node.js对一些特殊用例进行了优化，提供了替代的
API，使得V8在非浏览器环境下运行的更好。
####hybrid app
hybrid app指的是介于native app与web app之间的app，Native App良好用户交互体验的优势”和“Web App跨平台开发的优势。这也是h5出现之后，很多产品为节省成本
和快速开发等种种原因采取的“混开”方式。
####移动端web app
hybrid app中既有原生SDK的实现，又有Html的嵌套；移动端web app采用全部为Html，CSS与JS等语言来实现的客户端，就是移动端的Web App。
####native app
native app就是彻头彻尾的原生SDK开发的应用，例如Android应用是采用Java语言基于Android SDK来开发，IOS采用oc或swift在苹果API的基础上进行的开发。

####采用ionic框架开发移动端web app的环境搭建
#####安装jdk，且配置环境变量
#####install node js
下载安装，并输入node -v验证是否安装成功。
#####install cordova
```javascript
npm install -g cordova
```
#####install ionic
```javascript
npm install -g ionic
```
#####创建Hello World验证是否安装完成
```javascript
ionic start myApp blank
```
#####安装sdk并编译（Gradle）
