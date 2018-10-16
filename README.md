![banner](https://raw.github.com/PhilJay/MPChart/master/design/feature_graphic_smaller.png)


## 简介

   声明：基于PhilJay MPAndroidChart:v3.0.3版本基础上封装，使用方法不变</br>
  
  
   实现特殊功能：添加不同类，与原框架不冲突（不需要集成原框架）</br>
  
   封装的原因：</br>
   
   1：双或单平滑曲线（双折线图）和MarkView实现   </br>
   2：自定义1MPAndroidChart滑动冲突解决  </br>
   3：饼状图实现和文字重合问题解决  </br>
   4：柱状图实现及X轴文字不显示问题和柱状图上显示文字</br>
   5: [天一方蓝](https://github.com/JinBoy23520/MPAndroidChartDemoByJin) - 天一方蓝 </br>
   6：以上功能封装完毕，1,2条在本项目已经实现，3，4请参考5 点击查看demo</br>
   7：Apk体验请下拉到底</br>

      

## 集成

**Gradle**

- **Project level `build.gradle`**
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
- **App level `build.gradle`**
```gradle
dependencies {
    implementation 'com.github.zhangi789:MPAndroidChart:v3.0.3'
}
```

## 文献

See the [**documentation**](https://github.com/PhilJay/MPAndroidChart/wiki) for examples and general use of MPAndroidChart.

See the [**javadocs**](https://jitpack.io/com/github/PhilJay/MPAndroidChart/v3.0.3/javadoc/) for more advanced documentation.

<br/>


<br/>

## 出现问题 & 解决方式 :思考:

This repository's issue tracker is only for bugs and feature requests. The maintainers ask that you refrain from asking questions about how to use MPAndroidChart through the issue tracker.

Please read the [**documentation**](https://github.com/PhilJay/MPAndroidChart/wiki) first, then ask all your questions on [stackoverflow.com](https://stackoverflow.com/questions/tagged/mpandroidchart) for the fastest answer.

<br/>



## More Examples :+1:

<br/>

**LineChart (with legend, simple design)**

![alt tag](https://raw.github.com/PhilJay/MPChart/master/screenshots/simpledesign_linechart4.png)
<br/><br/>

**LineChart (with legend, simple design)**

![alt tag](https://raw.github.com/PhilJay/MPChart/master/screenshots/simpledesign_linechart3.png)
<br/><br/>

**LineChart (cubic lines)**

![alt tag](https://raw.github.com/PhilJay/MPChart/master/screenshots/cubiclinechart.png)
<br/><br/>

**LineChart (gradient fill)**

![alt tag](https://raw.github.com/PhilJay/MPAndroidChart/master/screenshots/line_chart_gradient.png)
<br/><br/>

**BarChart (with legend, simple design)**

![alt tag](https://raw.github.com/PhilJay/MPChart/master/screenshots/simpledesign_barchart3.png)
<br/><br/>

**BarChart (grouped DataSets)**

![alt tag](https://raw.github.com/PhilJay/MPChart/master/screenshots/groupedbarchart.png)
<br/><br/>

**Horizontal-BarChart**

![alt tag](https://raw.github.com/PhilJay/MPChart/master/screenshots/horizontal_barchart.png)
<br/><br/>

**Combined-Chart (bar- and linechart in this case)**

![alt tag](https://raw.github.com/PhilJay/MPChart/master/screenshots/combined_chart.png)
<br/><br/>

**PieChart (with selection, ...)**

![alt tag](https://raw.github.com/PhilJay/MPAndroidChart/master/screenshots/simpledesign_piechart1.png)
<br/><br/>

**ScatterChart** (with squares, triangles, circles, ... and more)

![alt tag](https://raw.github.com/PhilJay/MPAndroidChart/master/screenshots/scatterchart.png)
<br/><br/>

**CandleStickChart** (for financial data)

![alt tag](https://raw.github.com/PhilJay/MPAndroidChart/master/screenshots/candlestickchart.png)
<br/><br/>

**BubbleChart** (area covered by bubbles indicates the yValue)

![alt tag](https://raw.github.com/PhilJay/MPAndroidChart/master/screenshots/bubblechart.png)
<br/><br/>

**RadarChart** (spider web chart)

![alt tag](https://raw.github.com/PhilJay/MPAndroidChart/master/screenshots/radarchart.png)

<br/>

# License :page_facing_up:

Copyright 2018 Philipp Jahoda

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

<br/>

## 特别感谢:心:

These people rock!

- [天一方蓝](https://blog.csdn.net/dt235201314/article/details/54135182) - 天一方蓝
- [PhilJay](https://github.com/PhilJay/MPAndroidChart) - PhilJay

- [Apk下载](http://app-global.pgyer.com/d41085d908b67fc04f54f2bef33110db.apk?attname=MpChart.apk&sign=aab429f2339612af6144304f298c1695&t=5bbef2ad)
<img src="https://github.com/zhangi789/MPAndroidChart/blob/master/screenshot/chart.png" width="40%" height="40%" div align=center>
