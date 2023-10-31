# Add any ProGuard configurations specific to this
# extension here.

-keep public class me.aemo.palettecolorapi.PaletteColorApi {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'me/aemo/palettecolorapi/repack'
-flattenpackagehierarchy
-dontpreverify
