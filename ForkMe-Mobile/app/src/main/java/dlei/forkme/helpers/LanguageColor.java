package dlei.forkme.helpers;

import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Helper class to assign a color to a programming language.
 */
public class LanguageColor {
    // Color Json from https://github.com/ozh/github-colors/blob/master/colors.json
    private static final String colorJsonString = (
            "{\n" +
            "\"1C Enterprise\": {\n" +
            "\"color\": \"#814CCC\",\n" +
            "\"url\": \"https://github.com/trending?l=1C-Enterprise\"\n" +
            "},\n" +
            "\"ABAP\": {\n" +
            "\"color\": \"#E8274B\",\n" +
            "\"url\": \"https://github.com/trending?l=ABAP\"\n" +
            "},\n" +
            "\"ActionScript\": {\n" +
            "\"color\": \"#882B0F\",\n" +
            "\"url\": \"https://github.com/trending?l=ActionScript\"\n" +
            "},\n" +
            "\"Ada\": {\n" +
            "\"color\": \"#02f88c\",\n" +
            "\"url\": \"https://github.com/trending?l=Ada\"\n" +
            "},\n" +
            "\"Agda\": {\n" +
            "\"color\": \"#315665\",\n" +
            "\"url\": \"https://github.com/trending?l=Agda\"\n" +
            "},\n" +
            "\"AGS Script\": {\n" +
            "\"color\": \"#B9D9FF\",\n" +
            "\"url\": \"https://github.com/trending?l=AGS-Script\"\n" +
            "},\n" +
            "\"Alloy\": {\n" +
            "\"color\": \"#64C800\",\n" +
            "\"url\": \"https://github.com/trending?l=Alloy\"\n" +
            "},\n" +
            "\"Alpine Abuild\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Alpine-Abuild\"\n" +
            "},\n" +
            "\"AMPL\": {\n" +
            "\"color\": \"#E6EFBB\",\n" +
            "\"url\": \"https://github.com/trending?l=AMPL\"\n" +
            "},\n" +
            "\"ANTLR\": {\n" +
            "\"color\": \"#9DC3FF\",\n" +
            "\"url\": \"https://github.com/trending?l=ANTLR\"\n" +
            "},\n" +
            "\"Apex\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Apex\"\n" +
            "},\n" +
            "\"API Blueprint\": {\n" +
            "\"color\": \"#2ACCA8\",\n" +
            "\"url\": \"https://github.com/trending?l=API-Blueprint\"\n" +
            "},\n" +
            "\"APL\": {\n" +
            "\"color\": \"#5A8164\",\n" +
            "\"url\": \"https://github.com/trending?l=APL\"\n" +
            "},\n" +
            "\"Apollo Guidance Computer\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Apollo-Guidance-Computer\"\n" +
            "},\n" +
            "\"AppleScript\": {\n" +
            "\"color\": \"#101F1F\",\n" +
            "\"url\": \"https://github.com/trending?l=AppleScript\"\n" +
            "},\n" +
            "\"Arc\": {\n" +
            "\"color\": \"#aa2afe\",\n" +
            "\"url\": \"https://github.com/trending?l=Arc\"\n" +
            "},\n" +
            "\"Arduino\": {\n" +
            "\"color\": \"#bd79d1\",\n" +
            "\"url\": \"https://github.com/trending?l=Arduino\"\n" +
            "},\n" +
            "\"ASP\": {\n" +
            "\"color\": \"#6a40fd\",\n" +
            "\"url\": \"https://github.com/trending?l=ASP\"\n" +
            "},\n" +
            "\"AspectJ\": {\n" +
            "\"color\": \"#a957b0\",\n" +
            "\"url\": \"https://github.com/trending?l=AspectJ\"\n" +
            "},\n" +
            "\"Assembly\": {\n" +
            "\"color\": \"#6E4C13\",\n" +
            "\"url\": \"https://github.com/trending?l=Assembly\"\n" +
            "},\n" +
            "\"ATS\": {\n" +
            "\"color\": \"#1ac620\",\n" +
            "\"url\": \"https://github.com/trending?l=ATS\"\n" +
            "},\n" +
            "\"Augeas\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Augeas\"\n" +
            "},\n" +
            "\"AutoHotkey\": {\n" +
            "\"color\": \"#6594b9\",\n" +
            "\"url\": \"https://github.com/trending?l=AutoHotkey\"\n" +
            "},\n" +
            "\"AutoIt\": {\n" +
            "\"color\": \"#1C3552\",\n" +
            "\"url\": \"https://github.com/trending?l=AutoIt\"\n" +
            "},\n" +
            "\"Awk\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Awk\"\n" +
            "},\n" +
            "\"Batchfile\": {\n" +
            "\"color\": \"#C1F12E\",\n" +
            "\"url\": \"https://github.com/trending?l=Batchfile\"\n" +
            "},\n" +
            "\"Befunge\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Befunge\"\n" +
            "},\n" +
            "\"Bison\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Bison\"\n" +
            "},\n" +
            "\"BitBake\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=BitBake\"\n" +
            "},\n" +
            "\"BlitzBasic\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=BlitzBasic\"\n" +
            "},\n" +
            "\"BlitzMax\": {\n" +
            "\"color\": \"#cd6400\",\n" +
            "\"url\": \"https://github.com/trending?l=BlitzMax\"\n" +
            "},\n" +
            "\"Bluespec\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Bluespec\"\n" +
            "},\n" +
            "\"Boo\": {\n" +
            "\"color\": \"#d4bec1\",\n" +
            "\"url\": \"https://github.com/trending?l=Boo\"\n" +
            "},\n" +
            "\"Brainfuck\": {\n" +
            "\"color\": \"#2F2530\",\n" +
            "\"url\": \"https://github.com/trending?l=Brainfuck\"\n" +
            "},\n" +
            "\"Brightscript\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Brightscript\"\n" +
            "},\n" +
            "\"Bro\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Bro\"\n" +
            "},\n" +
            "\"C\": {\n" +
            "\"color\": \"#555555\",\n" +
            "\"url\": \"https://github.com/trending?l=C\"\n" +
            "},\n" +
            "\"C#\": {\n" +
            "\"color\": \"#178600\",\n" +
            "\"url\": \"https://github.com/trending?l=C#\"\n" +
            "},\n" +
            "\"C++\": {\n" +
            "\"color\": \"#f34b7d\",\n" +
            "\"url\": \"https://github.com/trending?l=C++\"\n" +
            "},\n" +
            "\"C2hs Haskell\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=C2hs-Haskell\"\n" +
            "},\n" +
            "\"Cap'n Proto\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Cap'n-Proto\"\n" +
            "},\n" +
            "\"CartoCSS\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=CartoCSS\"\n" +
            "},\n" +
            "\"Ceylon\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Ceylon\"\n" +
            "},\n" +
            "\"Chapel\": {\n" +
            "\"color\": \"#8dc63f\",\n" +
            "\"url\": \"https://github.com/trending?l=Chapel\"\n" +
            "},\n" +
            "\"Charity\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Charity\"\n" +
            "},\n" +
            "\"ChucK\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=ChucK\"\n" +
            "},\n" +
            "\"Cirru\": {\n" +
            "\"color\": \"#ccccff\",\n" +
            "\"url\": \"https://github.com/trending?l=Cirru\"\n" +
            "},\n" +
            "\"Clarion\": {\n" +
            "\"color\": \"#db901e\",\n" +
            "\"url\": \"https://github.com/trending?l=Clarion\"\n" +
            "},\n" +
            "\"Clean\": {\n" +
            "\"color\": \"#3F85AF\",\n" +
            "\"url\": \"https://github.com/trending?l=Clean\"\n" +
            "},\n" +
            "\"Click\": {\n" +
            "\"color\": \"#E4E6F3\",\n" +
            "\"url\": \"https://github.com/trending?l=Click\"\n" +
            "},\n" +
            "\"CLIPS\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=CLIPS\"\n" +
            "},\n" +
            "\"Clojure\": {\n" +
            "\"color\": \"#db5855\",\n" +
            "\"url\": \"https://github.com/trending?l=Clojure\"\n" +
            "},\n" +
            "\"CMake\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=CMake\"\n" +
            "},\n" +
            "\"COBOL\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=COBOL\"\n" +
            "},\n" +
            "\"CoffeeScript\": {\n" +
            "\"color\": \"#244776\",\n" +
            "\"url\": \"https://github.com/trending?l=CoffeeScript\"\n" +
            "},\n" +
            "\"ColdFusion\": {\n" +
            "\"color\": \"#ed2cd6\",\n" +
            "\"url\": \"https://github.com/trending?l=ColdFusion\"\n" +
            "},\n" +
            "\"ColdFusion CFC\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=ColdFusion-CFC\"\n" +
            "},\n" +
            "\"Common Lisp\": {\n" +
            "\"color\": \"#3fb68b\",\n" +
            "\"url\": \"https://github.com/trending?l=Common-Lisp\"\n" +
            "},\n" +
            "\"Component Pascal\": {\n" +
            "\"color\": \"#B0CE4E\",\n" +
            "\"url\": \"https://github.com/trending?l=Component-Pascal\"\n" +
            "},\n" +
            "\"Cool\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Cool\"\n" +
            "},\n" +
            "\"Coq\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Coq\"\n" +
            "},\n" +
            "\"Crystal\": {\n" +
            "\"color\": \"#776791\",\n" +
            "\"url\": \"https://github.com/trending?l=Crystal\"\n" +
            "},\n" +
            "\"Csound\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Csound\"\n" +
            "},\n" +
            "\"Csound Document\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Csound-Document\"\n" +
            "},\n" +
            "\"Csound Score\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Csound-Score\"\n" +
            "},\n" +
            "\"CSS\": {\n" +
            "\"color\": \"#563d7c\",\n" +
            "\"url\": \"https://github.com/trending?l=CSS\"\n" +
            "},\n" +
            "\"Cuda\": {\n" +
            "\"color\": \"#3A4E3A\",\n" +
            "\"url\": \"https://github.com/trending?l=Cuda\"\n" +
            "},\n" +
            "\"Cycript\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Cycript\"\n" +
            "},\n" +
            "\"Cython\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Cython\"\n" +
            "},\n" +
            "\"D\": {\n" +
            "\"color\": \"#ba595e\",\n" +
            "\"url\": \"https://github.com/trending?l=D\"\n" +
            "},\n" +
            "\"Dart\": {\n" +
            "\"color\": \"#00B4AB\",\n" +
            "\"url\": \"https://github.com/trending?l=Dart\"\n" +
            "},\n" +
            "\"DIGITAL Command Language\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=DIGITAL-Command-Language\"\n" +
            "},\n" +
            "\"DM\": {\n" +
            "\"color\": \"#447265\",\n" +
            "\"url\": \"https://github.com/trending?l=DM\"\n" +
            "},\n" +
            "\"Dogescript\": {\n" +
            "\"color\": \"#cca760\",\n" +
            "\"url\": \"https://github.com/trending?l=Dogescript\"\n" +
            "},\n" +
            "\"DTrace\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=DTrace\"\n" +
            "},\n" +
            "\"Dylan\": {\n" +
            "\"color\": \"#6c616e\",\n" +
            "\"url\": \"https://github.com/trending?l=Dylan\"\n" +
            "},\n" +
            "\"E\": {\n" +
            "\"color\": \"#ccce35\",\n" +
            "\"url\": \"https://github.com/trending?l=E\"\n" +
            "},\n" +
            "\"Eagle\": {\n" +
            "\"color\": \"#814C05\",\n" +
            "\"url\": \"https://github.com/trending?l=Eagle\"\n" +
            "},\n" +
            "\"eC\": {\n" +
            "\"color\": \"#913960\",\n" +
            "\"url\": \"https://github.com/trending?l=eC\"\n" +
            "},\n" +
            "\"ECL\": {\n" +
            "\"color\": \"#8a1267\",\n" +
            "\"url\": \"https://github.com/trending?l=ECL\"\n" +
            "},\n" +
            "\"ECLiPSe\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=ECLiPSe\"\n" +
            "},\n" +
            "\"Eiffel\": {\n" +
            "\"color\": \"#946d57\",\n" +
            "\"url\": \"https://github.com/trending?l=Eiffel\"\n" +
            "},\n" +
            "\"Elixir\": {\n" +
            "\"color\": \"#6e4a7e\",\n" +
            "\"url\": \"https://github.com/trending?l=Elixir\"\n" +
            "},\n" +
            "\"Elm\": {\n" +
            "\"color\": \"#60B5CC\",\n" +
            "\"url\": \"https://github.com/trending?l=Elm\"\n" +
            "},\n" +
            "\"Emacs Lisp\": {\n" +
            "\"color\": \"#c065db\",\n" +
            "\"url\": \"https://github.com/trending?l=Emacs-Lisp\"\n" +
            "},\n" +
            "\"EmberScript\": {\n" +
            "\"color\": \"#FFF4F3\",\n" +
            "\"url\": \"https://github.com/trending?l=EmberScript\"\n" +
            "},\n" +
            "\"EQ\": {\n" +
            "\"color\": \"#a78649\",\n" +
            "\"url\": \"https://github.com/trending?l=EQ\"\n" +
            "},\n" +
            "\"Erlang\": {\n" +
            "\"color\": \"#B83998\",\n" +
            "\"url\": \"https://github.com/trending?l=Erlang\"\n" +
            "},\n" +
            "\"F#\": {\n" +
            "\"color\": \"#b845fc\",\n" +
            "\"url\": \"https://github.com/trending?l=F#\"\n" +
            "},\n" +
            "\"Factor\": {\n" +
            "\"color\": \"#636746\",\n" +
            "\"url\": \"https://github.com/trending?l=Factor\"\n" +
            "},\n" +
            "\"Fancy\": {\n" +
            "\"color\": \"#7b9db4\",\n" +
            "\"url\": \"https://github.com/trending?l=Fancy\"\n" +
            "},\n" +
            "\"Fantom\": {\n" +
            "\"color\": \"#dbded5\",\n" +
            "\"url\": \"https://github.com/trending?l=Fantom\"\n" +
            "},\n" +
            "\"Filebench WML\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Filebench-WML\"\n" +
            "},\n" +
            "\"Filterscript\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Filterscript\"\n" +
            "},\n" +
            "\"fish\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=fish\"\n" +
            "},\n" +
            "\"FLUX\": {\n" +
            "\"color\": \"#88ccff\",\n" +
            "\"url\": \"https://github.com/trending?l=FLUX\"\n" +
            "},\n" +
            "\"Forth\": {\n" +
            "\"color\": \"#341708\",\n" +
            "\"url\": \"https://github.com/trending?l=Forth\"\n" +
            "},\n" +
            "\"Fortran\": {\n" +
            "\"color\": \"#4d41b1\",\n" +
            "\"url\": \"https://github.com/trending?l=Fortran\"\n" +
            "},\n" +
            "\"FreeMarker\": {\n" +
            "\"color\": \"#0050b2\",\n" +
            "\"url\": \"https://github.com/trending?l=FreeMarker\"\n" +
            "},\n" +
            "\"Frege\": {\n" +
            "\"color\": \"#00cafe\",\n" +
            "\"url\": \"https://github.com/trending?l=Frege\"\n" +
            "},\n" +
            "\"Game Maker Language\": {\n" +
            "\"color\": \"#8fb200\",\n" +
            "\"url\": \"https://github.com/trending?l=Game-Maker-Language\"\n" +
            "},\n" +
            "\"GAMS\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=GAMS\"\n" +
            "},\n" +
            "\"GAP\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=GAP\"\n" +
            "},\n" +
            "\"GCC Machine Description\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=GCC-Machine-Description\"\n" +
            "},\n" +
            "\"GDB\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=GDB\"\n" +
            "},\n" +
            "\"GDScript\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=GDScript\"\n" +
            "},\n" +
            "\"Genie\": {\n" +
            "\"color\": \"#fb855d\",\n" +
            "\"url\": \"https://github.com/trending?l=Genie\"\n" +
            "},\n" +
            "\"Genshi\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Genshi\"\n" +
            "},\n" +
            "\"Gentoo Ebuild\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Gentoo-Ebuild\"\n" +
            "},\n" +
            "\"Gentoo Eclass\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Gentoo-Eclass\"\n" +
            "},\n" +
            "\"Gherkin\": {\n" +
            "\"color\": \"#5B2063\",\n" +
            "\"url\": \"https://github.com/trending?l=Gherkin\"\n" +
            "},\n" +
            "\"GLSL\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=GLSL\"\n" +
            "},\n" +
            "\"Glyph\": {\n" +
            "\"color\": \"#e4cc98\",\n" +
            "\"url\": \"https://github.com/trending?l=Glyph\"\n" +
            "},\n" +
            "\"Gnuplot\": {\n" +
            "\"color\": \"#f0a9f0\",\n" +
            "\"url\": \"https://github.com/trending?l=Gnuplot\"\n" +
            "},\n" +
            "\"Go\": {\n" +
            "\"color\": \"#375eab\",\n" +
            "\"url\": \"https://github.com/trending?l=Go\"\n" +
            "},\n" +
            "\"Golo\": {\n" +
            "\"color\": \"#88562A\",\n" +
            "\"url\": \"https://github.com/trending?l=Golo\"\n" +
            "},\n" +
            "\"Gosu\": {\n" +
            "\"color\": \"#82937f\",\n" +
            "\"url\": \"https://github.com/trending?l=Gosu\"\n" +
            "},\n" +
            "\"Grace\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Grace\"\n" +
            "},\n" +
            "\"Grammatical Framework\": {\n" +
            "\"color\": \"#79aa7a\",\n" +
            "\"url\": \"https://github.com/trending?l=Grammatical-Framework\"\n" +
            "},\n" +
            "\"Groovy\": {\n" +
            "\"color\": \"#e69f56\",\n" +
            "\"url\": \"https://github.com/trending?l=Groovy\"\n" +
            "},\n" +
            "\"Groovy Server Pages\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Groovy-Server-Pages\"\n" +
            "},\n" +
            "\"Hack\": {\n" +
            "\"color\": \"#878787\",\n" +
            "\"url\": \"https://github.com/trending?l=Hack\"\n" +
            "},\n" +
            "\"Harbour\": {\n" +
            "\"color\": \"#0e60e3\",\n" +
            "\"url\": \"https://github.com/trending?l=Harbour\"\n" +
            "},\n" +
            "\"Haskell\": {\n" +
            "\"color\": \"#29b544\",\n" +
            "\"url\": \"https://github.com/trending?l=Haskell\"\n" +
            "},\n" +
            "\"Haxe\": {\n" +
            "\"color\": \"#df7900\",\n" +
            "\"url\": \"https://github.com/trending?l=Haxe\"\n" +
            "},\n" +
            "\"HCL\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=HCL\"\n" +
            "},\n" +
            "\"HLSL\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=HLSL\"\n" +
            "},\n" +
            "\"HTML\": {\n" +
            "\"color\": \"#e34c26\",\n" +
            "\"url\": \"https://github.com/trending?l=HTML\"\n" +
            "},\n" +
            "\"Hy\": {\n" +
            "\"color\": \"#7790B2\",\n" +
            "\"url\": \"https://github.com/trending?l=Hy\"\n" +
            "},\n" +
            "\"HyPhy\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=HyPhy\"\n" +
            "},\n" +
            "\"IDL\": {\n" +
            "\"color\": \"#a3522f\",\n" +
            "\"url\": \"https://github.com/trending?l=IDL\"\n" +
            "},\n" +
            "\"Idris\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Idris\"\n" +
            "},\n" +
            "\"IGOR Pro\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=IGOR-Pro\"\n" +
            "},\n" +
            "\"Inform 7\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Inform-7\"\n" +
            "},\n" +
            "\"Inno Setup\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Inno-Setup\"\n" +
            "},\n" +
            "\"Io\": {\n" +
            "\"color\": \"#a9188d\",\n" +
            "\"url\": \"https://github.com/trending?l=Io\"\n" +
            "},\n" +
            "\"Ioke\": {\n" +
            "\"color\": \"#078193\",\n" +
            "\"url\": \"https://github.com/trending?l=Ioke\"\n" +
            "},\n" +
            "\"Isabelle\": {\n" +
            "\"color\": \"#FEFE00\",\n" +
            "\"url\": \"https://github.com/trending?l=Isabelle\"\n" +
            "},\n" +
            "\"Isabelle ROOT\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Isabelle-ROOT\"\n" +
            "},\n" +
            "\"J\": {\n" +
            "\"color\": \"#9EEDFF\",\n" +
            "\"url\": \"https://github.com/trending?l=J\"\n" +
            "},\n" +
            "\"Jasmin\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Jasmin\"\n" +
            "},\n" +
            "\"Java\": {\n" +
            "\"color\": \"#b07219\",\n" +
            "\"url\": \"https://github.com/trending?l=Java\"\n" +
            "},\n" +
            "\"Java Server Pages\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Java-Server-Pages\"\n" +
            "},\n" +
            "\"JavaScript\": {\n" +
            "\"color\": \"#f1e05a\",\n" +
            "\"url\": \"https://github.com/trending?l=JavaScript\"\n" +
            "},\n" +
            "\"JFlex\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=JFlex\"\n" +
            "},\n" +
            "\"Jison\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Jison\"\n" +
            "},\n" +
            "\"Jison Lex\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Jison-Lex\"\n" +
            "},\n" +
            "\"Jolie\": {\n" +
            "\"color\": \"#843179\",\n" +
            "\"url\": \"https://github.com/trending?l=Jolie\"\n" +
            "},\n" +
            "\"JSONiq\": {\n" +
            "\"color\": \"#40d47e\",\n" +
            "\"url\": \"https://github.com/trending?l=JSONiq\"\n" +
            "},\n" +
            "\"JSX\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=JSX\"\n" +
            "},\n" +
            "\"Julia\": {\n" +
            "\"color\": \"#a270ba\",\n" +
            "\"url\": \"https://github.com/trending?l=Julia\"\n" +
            "},\n" +
            "\"Jupyter Notebook\": {\n" +
            "\"color\": \"#DA5B0B\",\n" +
            "\"url\": \"https://github.com/trending?l=Jupyter-Notebook\"\n" +
            "},\n" +
            "\"KiCad\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=KiCad\"\n" +
            "},\n" +
            "\"Kotlin\": {\n" +
            "\"color\": \"#F18E33\",\n" +
            "\"url\": \"https://github.com/trending?l=Kotlin\"\n" +
            "},\n" +
            "\"KRL\": {\n" +
            "\"color\": \"#28431f\",\n" +
            "\"url\": \"https://github.com/trending?l=KRL\"\n" +
            "},\n" +
            "\"LabVIEW\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=LabVIEW\"\n" +
            "},\n" +
            "\"Lasso\": {\n" +
            "\"color\": \"#999999\",\n" +
            "\"url\": \"https://github.com/trending?l=Lasso\"\n" +
            "},\n" +
            "\"Lean\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Lean\"\n" +
            "},\n" +
            "\"Lex\": {\n" +
            "\"color\": \"#DBCA00\",\n" +
            "\"url\": \"https://github.com/trending?l=Lex\"\n" +
            "},\n" +
            "\"LFE\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=LFE\"\n" +
            "},\n" +
            "\"LilyPond\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=LilyPond\"\n" +
            "},\n" +
            "\"Limbo\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Limbo\"\n" +
            "},\n" +
            "\"Literate Agda\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Literate-Agda\"\n" +
            "},\n" +
            "\"Literate CoffeeScript\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Literate-CoffeeScript\"\n" +
            "},\n" +
            "\"Literate Haskell\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Literate-Haskell\"\n" +
            "},\n" +
            "\"LiveScript\": {\n" +
            "\"color\": \"#499886\",\n" +
            "\"url\": \"https://github.com/trending?l=LiveScript\"\n" +
            "},\n" +
            "\"LLVM\": {\n" +
            "\"color\": \"#185619\",\n" +
            "\"url\": \"https://github.com/trending?l=LLVM\"\n" +
            "},\n" +
            "\"Logos\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Logos\"\n" +
            "},\n" +
            "\"Logtalk\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Logtalk\"\n" +
            "},\n" +
            "\"LOLCODE\": {\n" +
            "\"color\": \"#cc9900\",\n" +
            "\"url\": \"https://github.com/trending?l=LOLCODE\"\n" +
            "},\n" +
            "\"LookML\": {\n" +
            "\"color\": \"#652B81\",\n" +
            "\"url\": \"https://github.com/trending?l=LookML\"\n" +
            "},\n" +
            "\"LoomScript\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=LoomScript\"\n" +
            "},\n" +
            "\"LSL\": {\n" +
            "\"color\": \"#3d9970\",\n" +
            "\"url\": \"https://github.com/trending?l=LSL\"\n" +
            "},\n" +
            "\"Lua\": {\n" +
            "\"color\": \"#000080\",\n" +
            "\"url\": \"https://github.com/trending?l=Lua\"\n" +
            "},\n" +
            "\"M\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=M\"\n" +
            "},\n" +
            "\"M4\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=M4\"\n" +
            "},\n" +
            "\"M4Sugar\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=M4Sugar\"\n" +
            "},\n" +
            "\"Makefile\": {\n" +
            "\"color\": \"#427819\",\n" +
            "\"url\": \"https://github.com/trending?l=Makefile\"\n" +
            "},\n" +
            "\"Mako\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Mako\"\n" +
            "},\n" +
            "\"Mask\": {\n" +
            "\"color\": \"#f97732\",\n" +
            "\"url\": \"https://github.com/trending?l=Mask\"\n" +
            "},\n" +
            "\"Mathematica\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Mathematica\"\n" +
            "},\n" +
            "\"Matlab\": {\n" +
            "\"color\": \"#bb92ac\",\n" +
            "\"url\": \"https://github.com/trending?l=Matlab\"\n" +
            "},\n" +
            "\"Max\": {\n" +
            "\"color\": \"#c4a79c\",\n" +
            "\"url\": \"https://github.com/trending?l=Max\"\n" +
            "},\n" +
            "\"MAXScript\": {\n" +
            "\"color\": \"#00a6a6\",\n" +
            "\"url\": \"https://github.com/trending?l=MAXScript\"\n" +
            "},\n" +
            "\"Mercury\": {\n" +
            "\"color\": \"#ff2b2b\",\n" +
            "\"url\": \"https://github.com/trending?l=Mercury\"\n" +
            "},\n" +
            "\"Meson\": {\n" +
            "\"color\": \"#007800\",\n" +
            "\"url\": \"https://github.com/trending?l=Meson\"\n" +
            "},\n" +
            "\"Metal\": {\n" +
            "\"color\": \"#8f14e9\",\n" +
            "\"url\": \"https://github.com/trending?l=Metal\"\n" +
            "},\n" +
            "\"MiniD\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=MiniD\"\n" +
            "},\n" +
            "\"Mirah\": {\n" +
            "\"color\": \"#c7a938\",\n" +
            "\"url\": \"https://github.com/trending?l=Mirah\"\n" +
            "},\n" +
            "\"Modelica\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Modelica\"\n" +
            "},\n" +
            "\"Modula-2\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Modula-2\"\n" +
            "},\n" +
            "\"Module Management System\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Module-Management-System\"\n" +
            "},\n" +
            "\"Monkey\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Monkey\"\n" +
            "},\n" +
            "\"Moocode\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Moocode\"\n" +
            "},\n" +
            "\"MoonScript\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=MoonScript\"\n" +
            "},\n" +
            "\"MQL4\": {\n" +
            "\"color\": \"#62A8D6\",\n" +
            "\"url\": \"https://github.com/trending?l=MQL4\"\n" +
            "},\n" +
            "\"MQL5\": {\n" +
            "\"color\": \"#4A76B8\",\n" +
            "\"url\": \"https://github.com/trending?l=MQL5\"\n" +
            "},\n" +
            "\"MTML\": {\n" +
            "\"color\": \"#b7e1f4\",\n" +
            "\"url\": \"https://github.com/trending?l=MTML\"\n" +
            "},\n" +
            "\"MUF\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=MUF\"\n" +
            "},\n" +
            "\"mupad\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=mupad\"\n" +
            "},\n" +
            "\"Myghty\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Myghty\"\n" +
            "},\n" +
            "\"NCL\": {\n" +
            "\"color\": \"#28431f\",\n" +
            "\"url\": \"https://github.com/trending?l=NCL\"\n" +
            "},\n" +
            "\"Nemerle\": {\n" +
            "\"color\": \"#3d3c6e\",\n" +
            "\"url\": \"https://github.com/trending?l=Nemerle\"\n" +
            "},\n" +
            "\"nesC\": {\n" +
            "\"color\": \"#94B0C7\",\n" +
            "\"url\": \"https://github.com/trending?l=nesC\"\n" +
            "},\n" +
            "\"NetLinx\": {\n" +
            "\"color\": \"#0aa0ff\",\n" +
            "\"url\": \"https://github.com/trending?l=NetLinx\"\n" +
            "},\n" +
            "\"NetLinx+ERB\": {\n" +
            "\"color\": \"#747faa\",\n" +
            "\"url\": \"https://github.com/trending?l=NetLinx+ERB\"\n" +
            "},\n" +
            "\"NetLogo\": {\n" +
            "\"color\": \"#ff6375\",\n" +
            "\"url\": \"https://github.com/trending?l=NetLogo\"\n" +
            "},\n" +
            "\"NewLisp\": {\n" +
            "\"color\": \"#87AED7\",\n" +
            "\"url\": \"https://github.com/trending?l=NewLisp\"\n" +
            "},\n" +
            "\"Nginx\": {\n" +
            "\"color\": \"#9469E9\",\n" +
            "\"url\": \"https://github.com/trending?l=Nginx\"\n" +
            "},\n" +
            "\"Nim\": {\n" +
            "\"color\": \"#37775b\",\n" +
            "\"url\": \"https://github.com/trending?l=Nim\"\n" +
            "},\n" +
            "\"Nit\": {\n" +
            "\"color\": \"#009917\",\n" +
            "\"url\": \"https://github.com/trending?l=Nit\"\n" +
            "},\n" +
            "\"Nix\": {\n" +
            "\"color\": \"#7e7eff\",\n" +
            "\"url\": \"https://github.com/trending?l=Nix\"\n" +
            "},\n" +
            "\"NSIS\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=NSIS\"\n" +
            "},\n" +
            "\"Nu\": {\n" +
            "\"color\": \"#c9df40\",\n" +
            "\"url\": \"https://github.com/trending?l=Nu\"\n" +
            "},\n" +
            "\"NumPy\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=NumPy\"\n" +
            "},\n" +
            "\"Objective-C\": {\n" +
            "\"color\": \"#438eff\",\n" +
            "\"url\": \"https://github.com/trending?l=Objective-C\"\n" +
            "},\n" +
            "\"Objective-C++\": {\n" +
            "\"color\": \"#6866fb\",\n" +
            "\"url\": \"https://github.com/trending?l=Objective-C++\"\n" +
            "},\n" +
            "\"Objective-J\": {\n" +
            "\"color\": \"#ff0c5a\",\n" +
            "\"url\": \"https://github.com/trending?l=Objective-J\"\n" +
            "},\n" +
            "\"OCaml\": {\n" +
            "\"color\": \"#3be133\",\n" +
            "\"url\": \"https://github.com/trending?l=OCaml\"\n" +
            "},\n" +
            "\"Omgrofl\": {\n" +
            "\"color\": \"#cabbff\",\n" +
            "\"url\": \"https://github.com/trending?l=Omgrofl\"\n" +
            "},\n" +
            "\"ooc\": {\n" +
            "\"color\": \"#b0b77e\",\n" +
            "\"url\": \"https://github.com/trending?l=ooc\"\n" +
            "},\n" +
            "\"Opa\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Opa\"\n" +
            "},\n" +
            "\"Opal\": {\n" +
            "\"color\": \"#f7ede0\",\n" +
            "\"url\": \"https://github.com/trending?l=Opal\"\n" +
            "},\n" +
            "\"OpenCL\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=OpenCL\"\n" +
            "},\n" +
            "\"OpenEdge ABL\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=OpenEdge-ABL\"\n" +
            "},\n" +
            "\"OpenRC runscript\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=OpenRC-runscript\"\n" +
            "},\n" +
            "\"OpenSCAD\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=OpenSCAD\"\n" +
            "},\n" +
            "\"Ox\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Ox\"\n" +
            "},\n" +
            "\"Oxygene\": {\n" +
            "\"color\": \"#cdd0e3\",\n" +
            "\"url\": \"https://github.com/trending?l=Oxygene\"\n" +
            "},\n" +
            "\"Oz\": {\n" +
            "\"color\": \"#fab738\",\n" +
            "\"url\": \"https://github.com/trending?l=Oz\"\n" +
            "},\n" +
            "\"P4\": {\n" +
            "\"color\": \"#7055b5\",\n" +
            "\"url\": \"https://github.com/trending?l=P4\"\n" +
            "},\n" +
            "\"Pan\": {\n" +
            "\"color\": \"#cc0000\",\n" +
            "\"url\": \"https://github.com/trending?l=Pan\"\n" +
            "},\n" +
            "\"Papyrus\": {\n" +
            "\"color\": \"#6600cc\",\n" +
            "\"url\": \"https://github.com/trending?l=Papyrus\"\n" +
            "},\n" +
            "\"Parrot\": {\n" +
            "\"color\": \"#f3ca0a\",\n" +
            "\"url\": \"https://github.com/trending?l=Parrot\"\n" +
            "},\n" +
            "\"Parrot Assembly\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Parrot-Assembly\"\n" +
            "},\n" +
            "\"Parrot Internal Representation\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Parrot-Internal-Representation\"\n" +
            "},\n" +
            "\"Pascal\": {\n" +
            "\"color\": \"#E3F171\",\n" +
            "\"url\": \"https://github.com/trending?l=Pascal\"\n" +
            "},\n" +
            "\"PAWN\": {\n" +
            "\"color\": \"#dbb284\",\n" +
            "\"url\": \"https://github.com/trending?l=PAWN\"\n" +
            "},\n" +
            "\"Perl\": {\n" +
            "\"color\": \"#0298c3\",\n" +
            "\"url\": \"https://github.com/trending?l=Perl\"\n" +
            "},\n" +
            "\"Perl6\": {\n" +
            "\"color\": \"#0000fb\",\n" +
            "\"url\": \"https://github.com/trending?l=Perl6\"\n" +
            "},\n" +
            "\"PHP\": {\n" +
            "\"color\": \"#4F5D95\",\n" +
            "\"url\": \"https://github.com/trending?l=PHP\"\n" +
            "},\n" +
            "\"PicoLisp\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=PicoLisp\"\n" +
            "},\n" +
            "\"PigLatin\": {\n" +
            "\"color\": \"#fcd7de\",\n" +
            "\"url\": \"https://github.com/trending?l=PigLatin\"\n" +
            "},\n" +
            "\"Pike\": {\n" +
            "\"color\": \"#005390\",\n" +
            "\"url\": \"https://github.com/trending?l=Pike\"\n" +
            "},\n" +
            "\"PLpgSQL\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=PLpgSQL\"\n" +
            "},\n" +
            "\"PLSQL\": {\n" +
            "\"color\": \"#dad8d8\",\n" +
            "\"url\": \"https://github.com/trending?l=PLSQL\"\n" +
            "},\n" +
            "\"PogoScript\": {\n" +
            "\"color\": \"#d80074\",\n" +
            "\"url\": \"https://github.com/trending?l=PogoScript\"\n" +
            "},\n" +
            "\"Pony\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Pony\"\n" +
            "},\n" +
            "\"PostScript\": {\n" +
            "\"color\": \"#da291c\",\n" +
            "\"url\": \"https://github.com/trending?l=PostScript\"\n" +
            "},\n" +
            "\"POV-Ray SDL\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=POV-Ray-SDL\"\n" +
            "},\n" +
            "\"PowerBuilder\": {\n" +
            "\"color\": \"#8f0f8d\",\n" +
            "\"url\": \"https://github.com/trending?l=PowerBuilder\"\n" +
            "},\n" +
            "\"PowerShell\": {\n" +
            "\"color\": \"#012456\",\n" +
            "\"url\": \"https://github.com/trending?l=PowerShell\"\n" +
            "},\n" +
            "\"Processing\": {\n" +
            "\"color\": \"#0096D8\",\n" +
            "\"url\": \"https://github.com/trending?l=Processing\"\n" +
            "},\n" +
            "\"Prolog\": {\n" +
            "\"color\": \"#74283c\",\n" +
            "\"url\": \"https://github.com/trending?l=Prolog\"\n" +
            "},\n" +
            "\"Propeller Spin\": {\n" +
            "\"color\": \"#7fa2a7\",\n" +
            "\"url\": \"https://github.com/trending?l=Propeller-Spin\"\n" +
            "},\n" +
            "\"Puppet\": {\n" +
            "\"color\": \"#302B6D\",\n" +
            "\"url\": \"https://github.com/trending?l=Puppet\"\n" +
            "},\n" +
            "\"Pure Data\": {\n" +
            "\"color\": \"#91de79\",\n" +
            "\"url\": \"https://github.com/trending?l=Pure-Data\"\n" +
            "},\n" +
            "\"PureBasic\": {\n" +
            "\"color\": \"#5a6986\",\n" +
            "\"url\": \"https://github.com/trending?l=PureBasic\"\n" +
            "},\n" +
            "\"PureScript\": {\n" +
            "\"color\": \"#1D222D\",\n" +
            "\"url\": \"https://github.com/trending?l=PureScript\"\n" +
            "},\n" +
            "\"Python\": {\n" +
            "\"color\": \"#3572A5\",\n" +
            "\"url\": \"https://github.com/trending?l=Python\"\n" +
            "},\n" +
            "\"Python console\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Python-console\"\n" +
            "},\n" +
            "\"QMake\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=QMake\"\n" +
            "},\n" +
            "\"QML\": {\n" +
            "\"color\": \"#44a51c\",\n" +
            "\"url\": \"https://github.com/trending?l=QML\"\n" +
            "},\n" +
            "\"R\": {\n" +
            "\"color\": \"#198CE7\",\n" +
            "\"url\": \"https://github.com/trending?l=R\"\n" +
            "},\n" +
            "\"Racket\": {\n" +
            "\"color\": \"#22228f\",\n" +
            "\"url\": \"https://github.com/trending?l=Racket\"\n" +
            "},\n" +
            "\"Ragel\": {\n" +
            "\"color\": \"#9d5200\",\n" +
            "\"url\": \"https://github.com/trending?l=Ragel\"\n" +
            "},\n" +
            "\"RAML\": {\n" +
            "\"color\": \"#77d9fb\",\n" +
            "\"url\": \"https://github.com/trending?l=RAML\"\n" +
            "},\n" +
            "\"Rascal\": {\n" +
            "\"color\": \"#fffaa0\",\n" +
            "\"url\": \"https://github.com/trending?l=Rascal\"\n" +
            "},\n" +
            "\"REALbasic\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=REALbasic\"\n" +
            "},\n" +
            "\"Reason\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Reason\"\n" +
            "},\n" +
            "\"Rebol\": {\n" +
            "\"color\": \"#358a5b\",\n" +
            "\"url\": \"https://github.com/trending?l=Rebol\"\n" +
            "},\n" +
            "\"Red\": {\n" +
            "\"color\": \"#f50000\",\n" +
            "\"url\": \"https://github.com/trending?l=Red\"\n" +
            "},\n" +
            "\"Redcode\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Redcode\"\n" +
            "},\n" +
            "\"Ren'Py\": {\n" +
            "\"color\": \"#ff7f7f\",\n" +
            "\"url\": \"https://github.com/trending?l=Ren'Py\"\n" +
            "},\n" +
            "\"RenderScript\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=RenderScript\"\n" +
            "},\n" +
            "\"REXX\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=REXX\"\n" +
            "},\n" +
            "\"RobotFramework\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=RobotFramework\"\n" +
            "},\n" +
            "\"Roff\": {\n" +
            "\"color\": \"#ecdebe\",\n" +
            "\"url\": \"https://github.com/trending?l=Roff\"\n" +
            "},\n" +
            "\"Rouge\": {\n" +
            "\"color\": \"#cc0088\",\n" +
            "\"url\": \"https://github.com/trending?l=Rouge\"\n" +
            "},\n" +
            "\"Ruby\": {\n" +
            "\"color\": \"#701516\",\n" +
            "\"url\": \"https://github.com/trending?l=Ruby\"\n" +
            "},\n" +
            "\"RUNOFF\": {\n" +
            "\"color\": \"#665a4e\",\n" +
            "\"url\": \"https://github.com/trending?l=RUNOFF\"\n" +
            "},\n" +
            "\"Rust\": {\n" +
            "\"color\": \"#dea584\",\n" +
            "\"url\": \"https://github.com/trending?l=Rust\"\n" +
            "},\n" +
            "\"Sage\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Sage\"\n" +
            "},\n" +
            "\"SaltStack\": {\n" +
            "\"color\": \"#646464\",\n" +
            "\"url\": \"https://github.com/trending?l=SaltStack\"\n" +
            "},\n" +
            "\"SAS\": {\n" +
            "\"color\": \"#B34936\",\n" +
            "\"url\": \"https://github.com/trending?l=SAS\"\n" +
            "},\n" +
            "\"Scala\": {\n" +
            "\"color\": \"#c22d40\",\n" +
            "\"url\": \"https://github.com/trending?l=Scala\"\n" +
            "},\n" +
            "\"Scheme\": {\n" +
            "\"color\": \"#1e4aec\",\n" +
            "\"url\": \"https://github.com/trending?l=Scheme\"\n" +
            "},\n" +
            "\"Scilab\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Scilab\"\n" +
            "},\n" +
            "\"Self\": {\n" +
            "\"color\": \"#0579aa\",\n" +
            "\"url\": \"https://github.com/trending?l=Self\"\n" +
            "},\n" +
            "\"ShaderLab\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=ShaderLab\"\n" +
            "},\n" +
            "\"Shell\": {\n" +
            "\"color\": \"#89e051\",\n" +
            "\"url\": \"https://github.com/trending?l=Shell\"\n" +
            "},\n" +
            "\"ShellSession\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=ShellSession\"\n" +
            "},\n" +
            "\"Shen\": {\n" +
            "\"color\": \"#120F14\",\n" +
            "\"url\": \"https://github.com/trending?l=Shen\"\n" +
            "},\n" +
            "\"Slash\": {\n" +
            "\"color\": \"#007eff\",\n" +
            "\"url\": \"https://github.com/trending?l=Slash\"\n" +
            "},\n" +
            "\"Smali\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Smali\"\n" +
            "},\n" +
            "\"Smalltalk\": {\n" +
            "\"color\": \"#596706\",\n" +
            "\"url\": \"https://github.com/trending?l=Smalltalk\"\n" +
            "},\n" +
            "\"Smarty\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Smarty\"\n" +
            "},\n" +
            "\"SMT\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=SMT\"\n" +
            "},\n" +
            "\"SourcePawn\": {\n" +
            "\"color\": \"#5c7611\",\n" +
            "\"url\": \"https://github.com/trending?l=SourcePawn\"\n" +
            "},\n" +
            "\"SQF\": {\n" +
            "\"color\": \"#3F3F3F\",\n" +
            "\"url\": \"https://github.com/trending?l=SQF\"\n" +
            "},\n" +
            "\"SQLPL\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=SQLPL\"\n" +
            "},\n" +
            "\"Squirrel\": {\n" +
            "\"color\": \"#800000\",\n" +
            "\"url\": \"https://github.com/trending?l=Squirrel\"\n" +
            "},\n" +
            "\"SRecode Template\": {\n" +
            "\"color\": \"#348a34\",\n" +
            "\"url\": \"https://github.com/trending?l=SRecode-Template\"\n" +
            "},\n" +
            "\"Stan\": {\n" +
            "\"color\": \"#b2011d\",\n" +
            "\"url\": \"https://github.com/trending?l=Stan\"\n" +
            "},\n" +
            "\"Standard ML\": {\n" +
            "\"color\": \"#dc566d\",\n" +
            "\"url\": \"https://github.com/trending?l=Standard-ML\"\n" +
            "},\n" +
            "\"Stata\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Stata\"\n" +
            "},\n" +
            "\"SuperCollider\": {\n" +
            "\"color\": \"#46390b\",\n" +
            "\"url\": \"https://github.com/trending?l=SuperCollider\"\n" +
            "},\n" +
            "\"Swift\": {\n" +
            "\"color\": \"#ffac45\",\n" +
            "\"url\": \"https://github.com/trending?l=Swift\"\n" +
            "},\n" +
            "\"SystemVerilog\": {\n" +
            "\"color\": \"#DAE1C2\",\n" +
            "\"url\": \"https://github.com/trending?l=SystemVerilog\"\n" +
            "},\n" +
            "\"Tcl\": {\n" +
            "\"color\": \"#e4cc98\",\n" +
            "\"url\": \"https://github.com/trending?l=Tcl\"\n" +
            "},\n" +
            "\"Tcsh\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Tcsh\"\n" +
            "},\n" +
            "\"Terra\": {\n" +
            "\"color\": \"#00004c\",\n" +
            "\"url\": \"https://github.com/trending?l=Terra\"\n" +
            "},\n" +
            "\"TeX\": {\n" +
            "\"color\": \"#3D6117\",\n" +
            "\"url\": \"https://github.com/trending?l=TeX\"\n" +
            "},\n" +
            "\"Thrift\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Thrift\"\n" +
            "},\n" +
            "\"TI Program\": {\n" +
            "\"color\": \"#A0AA87\",\n" +
            "\"url\": \"https://github.com/trending?l=TI-Program\"\n" +
            "},\n" +
            "\"TLA\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=TLA\"\n" +
            "},\n" +
            "\"Turing\": {\n" +
            "\"color\": \"#cf142b\",\n" +
            "\"url\": \"https://github.com/trending?l=Turing\"\n" +
            "},\n" +
            "\"TXL\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=TXL\"\n" +
            "},\n" +
            "\"TypeScript\": {\n" +
            "\"color\": \"#2b7489\",\n" +
            "\"url\": \"https://github.com/trending?l=TypeScript\"\n" +
            "},\n" +
            "\"Unified Parallel C\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Unified-Parallel-C\"\n" +
            "},\n" +
            "\"Unix Assembly\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Unix-Assembly\"\n" +
            "},\n" +
            "\"Uno\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Uno\"\n" +
            "},\n" +
            "\"UnrealScript\": {\n" +
            "\"color\": \"#a54c4d\",\n" +
            "\"url\": \"https://github.com/trending?l=UnrealScript\"\n" +
            "},\n" +
            "\"UrWeb\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=UrWeb\"\n" +
            "},\n" +
            "\"Vala\": {\n" +
            "\"color\": \"#fbe5cd\",\n" +
            "\"url\": \"https://github.com/trending?l=Vala\"\n" +
            "},\n" +
            "\"VCL\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=VCL\"\n" +
            "},\n" +
            "\"Verilog\": {\n" +
            "\"color\": \"#b2b7f8\",\n" +
            "\"url\": \"https://github.com/trending?l=Verilog\"\n" +
            "},\n" +
            "\"VHDL\": {\n" +
            "\"color\": \"#adb2cb\",\n" +
            "\"url\": \"https://github.com/trending?l=VHDL\"\n" +
            "},\n" +
            "\"Vim script\": {\n" +
            "\"color\": \"#199f4b\",\n" +
            "\"url\": \"https://github.com/trending?l=Vim-script\"\n" +
            "},\n" +
            "\"VimL\": {\n" +
            "\"color\": \"#199f4b\",\n" +
            "\"url\": \"https://github.com/trending?l=VimL\"\n" +
            "},\n" +
            "\"Visual Basic\": {\n" +
            "\"color\": \"#945db7\",\n" +
            "\"url\": \"https://github.com/trending?l=Visual-Basic\"\n" +
            "},\n" +
            "\"Volt\": {\n" +
            "\"color\": \"#1F1F1F\",\n" +
            "\"url\": \"https://github.com/trending?l=Volt\"\n" +
            "},\n" +
            "\"Vue\": {\n" +
            "\"color\": \"#2c3e50\",\n" +
            "\"url\": \"https://github.com/trending?l=Vue\"\n" +
            "},\n" +
            "\"Web Ontology Language\": {\n" +
            "\"color\": \"#9cc9dd\",\n" +
            "\"url\": \"https://github.com/trending?l=Web-Ontology-Language\"\n" +
            "},\n" +
            "\"WebIDL\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=WebIDL\"\n" +
            "},\n" +
            "\"wisp\": {\n" +
            "\"color\": \"#7582D1\",\n" +
            "\"url\": \"https://github.com/trending?l=wisp\"\n" +
            "},\n" +
            "\"X10\": {\n" +
            "\"color\": \"#4B6BEF\",\n" +
            "\"url\": \"https://github.com/trending?l=X10\"\n" +
            "},\n" +
            "\"xBase\": {\n" +
            "\"color\": \"#403a40\",\n" +
            "\"url\": \"https://github.com/trending?l=xBase\"\n" +
            "},\n" +
            "\"XC\": {\n" +
            "\"color\": \"#99DA07\",\n" +
            "\"url\": \"https://github.com/trending?l=XC\"\n" +
            "},\n" +
            "\"Xojo\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Xojo\"\n" +
            "},\n" +
            "\"XPages\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=XPages\"\n" +
            "},\n" +
            "\"XProc\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=XProc\"\n" +
            "},\n" +
            "\"XQuery\": {\n" +
            "\"color\": \"#5232e7\",\n" +
            "\"url\": \"https://github.com/trending?l=XQuery\"\n" +
            "},\n" +
            "\"XS\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=XS\"\n" +
            "},\n" +
            "\"XSLT\": {\n" +
            "\"color\": \"#EB8CEB\",\n" +
            "\"url\": \"https://github.com/trending?l=XSLT\"\n" +
            "},\n" +
            "\"Xtend\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Xtend\"\n" +
            "},\n" +
            "\"Yacc\": {\n" +
            "\"color\": \"#4B6C4B\",\n" +
            "\"url\": \"https://github.com/trending?l=Yacc\"\n" +
            "},\n" +
            "\"Zephir\": {\n" +
            "\"color\": \"#118f9e\",\n" +
            "\"url\": \"https://github.com/trending?l=Zephir\"\n" +
            "},\n" +
            "\"Zimpl\": {\n" +
            "\"color\": null,\n" +
            "\"url\": \"https://github.com/trending?l=Zimpl\"\n" +
            "}\n" +
            "}"
    );

    private static final int noLanguageColor = Color.parseColor("#e5e5e5");
    private static final String noLanguageColorHex = "#e5e5e5";

    private static final JsonObject languageColorLookup = new JsonParser().parse(colorJsonString).getAsJsonObject();

    public LanguageColor() {}

    /**
     * Return color as a hex string of a programming langiuage.
     * @param language programming language to query for.
     * @return hex string representing the color, or #e5e5e5 if not found.
     */
    public static String getColor(String language) {
        JsonElement colorUrl = languageColorLookup.get(language);
        if (colorUrl == null) {
            Log.w("LanguageColor: ", String.format("Language %s not found", language));
            return null;
        }
        JsonObject colorUrlObject = colorUrl.getAsJsonObject();
        String colorHex;
        try {
            colorHex = colorUrlObject.get("color").getAsString();
        } catch (UnsupportedOperationException e) {
            colorHex = noLanguageColorHex;
        }
        return colorHex;
    }

    /**
     * Sets the language text and tints the language circle of a view.
     * @param language programming language to query for.
     * @param languageCircleImage language circle view, will set to a pale grey if not found.
     * @param languageText language text view, will set to None if not found.
     */
    public static void setLanguageOnView(String language, AppCompatImageView languageCircleImage, AppCompatTextView languageText) {
        int languageColorAsInt = noLanguageColor;
        if (language != null) {
            String languageColorAsHex = LanguageColor.getColor(language);
            if (languageColorAsHex != null) {
                languageColorAsInt = Color.parseColor(languageColorAsHex);
            }
        } else {
            language = "None";
        }
        languageCircleImage.setColorFilter(languageColorAsInt);
        languageText.setText(language);
    }
}
