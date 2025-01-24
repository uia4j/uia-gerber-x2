UIA Gerber X2
================

The implementation of this API is based on __Gerber Layer Format Specification - Revision 2024.05__.

## Current Progress

* __Gerber Layout File__ - All commands except `AM` could be written and read.

* __Text__ - `Arial` and `Dialog` under testing.

* __GerberX2FileReader__ - Redesign using streaming pattern.

## Build

The project uses the ANTLR V4 plugin to generate java files automatically, you need to add `target/generated-sources/antlr4` in IDE __build path list__ before building the source code.  

## Examples

1. Reader

    ```java
    GerberX2FileReader reader = new GerberX2FileReader(new GerberX2Visitor.Console());
    reader.run(Paths.get("samples/gerber1.gbr"));
    ```

2. Writer

    ```java
    // create a writer.
    GerberX2FileWriter writer = new GerberX2FileWriter(System.out)
            .fs(4, 6)
            .description("TEST1 - Region");

    // start to write (streaming)
    writer.start();

    // get the graphics object.
    CommonGraphics x2g = writer.getGraphics();

    // create a region at (0.1mm, 0.1mm)
    x2g.createRegion(writer.xy(0.1), writer.y(0.1)) 
            .lineTo(writer.xy(0.1), writer.xy(0.5))   
            .lineTo(writer.xy(0.4), null)
            .cwTo(writer.xy(0.5), writer.xy(0.4), null, writer.xy(-0.1))
            .lineTo(writer.xy(0.5), writer.xy(0.1))
            .lineTo(writer.xy(0.1), null)
            .close();

    // change the polarity
    x2g.loadPolarity(false);

    // create a region  at (0.3mm, 0.3mm)
    x2g.createRegion(writer.xy(0.3), writer.xy(0.3))
            .lineTo(null, writer.xy(0.4))
            .lineTo(writer.xy(0.4), null)
            .lineTo(writer.xy(0.4), writer.xy(0.3))
            .lineTo(writer.xy(0.3), null)
            .close();

    // end writing.
    writer.close();
    ```

3. Write text
    ```java
    // create a writer.
    GerberX2FileWriter writer = new GerberX2FileWriter(System.out)
            .fs(4, 6);

    // start to write (streaming)
    writer.start();

    // create a text graphics and draw 
    writer.getGraphics()
            .createText("Arial")
            .text("ABCDEFG", writer.xy(0), writer.xy(10), writer.xy(100), writer.xy(10))
            .text("1234567890!@#$%^&*()_+", writer.xy(0), writer.xy(0), writer.xy(1000), writer.xy(10))
            .close();

    // end writing.
    writer.close();
    ```

    ![Sample](sample1.png)

## Writer API

The value of coordination needs to meet the FS(Format Specification). `GerberX2FileWriter` provides a method `xy` to convert the value from mm/inch to FS format. 

For example, move the cursor to new position xy(10.2mm, 5.61mm)

```java
double mmX = 10.2;
double mmY = 5.61;

GerberX2FileWriter writer = new GerberX2FileWriter(System.out)
        .fs(4, 6)                       // intDigits = 4, decimalDigits = 6
        .unit(UnitType.MM);             // unit = mm
writer.start();
writer.getGraphics()
        .move(writer.xy(mmX), writer.xy(mmY));  // use xy() to convert mm to FS  format
writer.close();
```

* writer.xy(mmX) - convert 10.2(mm) to 10200000(FS)
* writer.xy(mmY) - convert 5.61(mm) to 5610000(FS)


### Workflow

1. create a writer
   ```java
   GerberX2FileWriter writer = new GerberX2FileWriter(System.out);
   ```

2. start writing 
   ```java
   writer.start();
   ```

3. Get the graphics
   ```java
   CommonGraphics cg = writer.getGraphics();
   ```

4. Define some ADs(Aperture Definition)
   ```java
   cg.defineCircle(11, BigDecimal.valueOf(1.2));
   cg.defineRectangle(12, new BigDecimal(10), new BigDecimal(20));
   cg.defineSquare(13, BigDecimal.valueOf(15.7));
   ```

5. Define a block
   ```java
   BlockDefineGraphics bg = writer.getGraphics().defineBlock(21);
   // draw something
   bg.close();
   ```

6. Use AD 11 
   ```java
   cg.dnn(11);
   ```

7. Move to xy(3, 4) and draw a line to xy(20, 19)
   ```java
   cg.move(writer.xy(3), writer.xy(4));
   cg.lineTo(writer.xy(20), writer.xy(19));
   ```

8. Create a region graphics at xy(-20, -40)
   ```java
   RegionGraphics rg = cg.createRegion(writer.xy(-20), writer.xy(-40));
   // draw something
   rg.close();
   ```

9. Create a text graphics using 'Arial'
   ```java
   TextGraphics tg = cg.createText("Arial");
   // draw something
   tg.close();
   ```

10. End writing
    ```java
    writer.close();
    ```


## References

* [Ucamco Gerber Format](https://www.ucamco.com/en/gerber)

* [The Gerber Layout Format Specification](https://www.ucamco.com/files/downloads/file_en/456/gerber-layer-format-specification-revision-2024-05_en.pdf?0a46cf1c17c7347e12872141102ea536)

* [The Gerber Job Format Specification](https://www.ucamco.com/files/downloads/file_en/435/gerber-job-format-specification-revision-2020-08_en.pdf?81fa8076a5520c3ef5eb2f76d06a6f47)

* [ANTLR V4](https://www.antlr.org/)


## Copyright and License

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
