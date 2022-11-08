/**
 * The text module creates text as a 2D geometric object, using fonts
 * installed on the local system or provided as separate font file.
 *
 * Example 1:
 *
 *     text("OpenSCAD");
 *
 * Note:
 *     To allow specification of particular Unicode characters you can specify
 *     them in a string with the following escape codes;
 *
 *     \x03 - single hex character (only allowed values are 01h - 7fh)
 *
 *     \u0123 - unicode char with 4 hexadecimal digits (note: Lowercase)
 *
 *     \U012345 - unicode char with 6 hexadecimal digits (note: Uppercase)
 *
 *
 * Example 2:
 *
 *     t="\u20AC10 \u263A"; // 10 euro and a smilie
 *
 * Using Fonts & Styles
 *
 * Fonts are specified by their logical font name; in addition a style parameter
 * can be added to select a specific font style like "bold" or "italic", such as:
 *
 *     font="Liberation Sans:style=Bold Italic"
 *
 * The font list dialog shows the font name and the font style for each available
 * font. For reference, the dialog also displays the location of the font file.
 * You can drag a font in the font list, into the editor window to use in the
 * text() statement.
 *
 * OpenSCAD includes the fonts Liberation Mono, Liberation Sans, Liberation Sans Narrow
 * and Liberation Serif. Hence, as fonts in general differ by platform type, use of
 * these included fonts is likely to be portable across platforms.
 *
 * For common/casual text usage, the specification of one of these fonts is recommended
 * for this reason. Liberation Sans is the default font to encourage this.
 *
 *
 * In addition to the installed fonts, it's possible to add project specific font files.
 * Supported font file formats are TrueType Fonts (*.ttf) and OpenType Fonts (*.otf).
 * The files need to be registered with use<>.
 *
 *     use <ttf/paratype-serif/PTF55F.ttf>
 *
 * After the registration, the font will also be listed in the font list dialog,
 * so in case logical name of a font is unknown, it can be looked up there are it
 * was registered.
 *
 * OpenSCAD uses fontconfig to find and manage fonts, so it's possible to list the
 * system configured fonts on command line using the fontconfig tools in a format
 * similar to the GUI dialog.
 *
 *     $ fc-list -f "%-60{{%{family[0]}%{:style[0]=}}}%{file}\n" | sort
 *
 *     ...
 *     Liberation Mono:style=Bold Italic /usr/share/fonts/truetype/liberation2/LiberationMono-BoldItalic.ttf
 *     Liberation Mono:style=Bold        /usr/share/fonts/truetype/liberation2/LiberationMono-Bold.ttf
 *     Liberation Mono:style=Italic      /usr/share/fonts/truetype/liberation2/LiberationMono-Italic.ttf
 *     Liberation Mono:style=Regular     /usr/share/fonts/truetype/liberation2/LiberationMono-Regular.ttf
 *     ...
 *
 * Example 3:
 *
 *     square(10);
 *
 *     translate([15, 15]) {
 *         text("OpenSCAD", font = "Liberation Sans");
 *     }
 *
 *     translate([15, 0]) {
 *         text("OpenSCAD", font = "Liberation Sans:style=Bold Italic");
 *     }
 *
 * Vertical alignment
 *
 *     top
 *         The text is aligned with the top of the bounding box at the given Y coordinate.
 *
 *     center
 *         The text is aligned with the center of the bounding box at the given Y coordinate.
 *
 *     baseline
 *         The text is aligned with the font baseline at the given Y coordinate. This is the default.
 *
 *     bottom
 *         The text is aligned with the bottom of the bounding box at the given Y coordinate.
 *
 * Example 4 - OpenSCAD vertical text alignment:
 *
 *     text = "Align";
 *     font = "Liberation Sans";
 *
 *     valign = [
 *         [  0, "top"],
 *         [ 40, "center"],
 *         [ 75, "baseline"],
 *         [110, "bottom"]
 *     ];
 *
 *     for (a = valign) {
 *         translate([10, 120 - a[0], 0]) {
 *             color("red") cube([135, 1, 0.1]);
 *             color("blue") cube([1, 20, 0.1]);
 *             linear_extrude(height = 0.5) {
 *                 text(
 *                     text = str(text,"_",a[1]),
 *                     font = font,
 *                     size = 20,
 *                     valign = a[1]
 *                 );
 *             }
 *         }
 *     }
 *
 * Horizontal alignment
 *
 *     left
 *         The text is aligned with the left side of the bounding box at the given X coordinate. This is the default.
 *
 *     center
 *         The text is aligned with the center of the bounding box at the given X coordinate.
 *
 *     right
 *         The text is aligned with the right of the bounding box at the given X coordinate.
 *
 * Example 5 - OpenSCAD horizontal text alignment:
 *
 *     text = "Align";
 *     font = "Liberation Sans";
 *
 *     halign = [
 *         [10, "left"],
 *         [50, "center"],
 *         [90, "right"]
 *     ];
 *
 *     for (a = halign) {
 *         translate([140, a[0], 0]) {
 *             color("red") cube([115, 2,0.1]);
 *             color("blue") cube([2, 20,0.1]);
 *             linear_extrude(height = 0.5) {
 *                 text(
 *                     text = str(text,"_",a[1]),
 *                     font = font,
 *                     size = 20,
 *                     halign = a[1]
 *                 );
 *             }
 *         }
 *     }
 *
 * Renderable 3D text can be easily produced using the linear_extrude(height) operator.
 *
 * @param text String. The text to generate.
 * @param size Decimal. The generated text will have approximately an ascent of the given value (height above the baseline). Default is 10. Note that specific fonts will vary somewhat and may not fill the size specified exactly, usually slightly smaller.
 * @param font String. The name of the font that should be used. This is not the name of the font file, but the logical font name (internally handled by the fontconfig library). This can also include a style parameter, see below. A list of installed fonts & styles can be obtained using the font list dialog (Help -> Font List).
 * @param halign String. The horizontal alignment for the text. Possible values are "left", "center" and "right". Default is "left".
 * @param valign String. The vertical alignment for the text. Possible values are "top", "center", "baseline" and "bottom". Default is "baseline".
 * @param spacing Decimal. Factor to increase/decrease the character spacing. The default value of 1 will result in the normal spacing for the font, giving a value greater than 1 will cause the letters to be spaced further apart.
 * @param direction String. Direction of the text flow. Possible values are "ltr" (left-to-right), "rtl" (right-to-left), "ttb" (top-to-bottom) and "btt" (bottom-to-top). Default is "ltr".
 * @param language String. The language of the text. Default is "en".
 * @param script String. The script of the text. Default is "latin".
 * @param $fn Used for subdividing the curved path segments provided by freetype.
 * @since 2015.03
 */
module text(
    text = undef,
    size = 10,
    font = undef,
    halign = "left",
    valign = "baseline",
    spacing = 1,
    direction = "ltr",
    language = "en",
    script = "latin"
){}