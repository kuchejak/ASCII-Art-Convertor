# ASCII Art Convertor

## Description
The idea of this project is to load images, transform them into ASCII ART images, optionally apply filters, and save them. This project was created as a semestral work for the Object Oriented Programming at Czech Technical University in Prague.

## Usage
```
-----COMMANDS-----
help
exit
run <image> [filter1] [filter2] ... <output>

-----IMAGE (use exactly one of the following)-----
--image "relative or absolute path to image"
--image-random

-----FILTERS-----
--invert
--flip x
--flip y
--brightness <amount>

-----OUTPUT (use at least one of the following)-----
--output-console
--output-file "relative or absolute path to output file"
```

Note: Every pixel gets converted into an ASCII character → Using smaller images is generally recommended.

## Example
### Input
![](example/avatar_small.png)

### Output
`run --image "example/avatar_small.png" --output-file "example/output.txt"`
```
,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,,,,~[))!,,,,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,:/q****ht,,,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,IO********X:,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,n**********x,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,;hadpkohkqLphl,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,[a}________[o/,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,nm__________Z0,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,XL__________co;,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,Xz__________1*i,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,zn___________w;,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,xf___________r,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,(/___________}!,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,~[____________>,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,i_________-__-!,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,;_____________;,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,i___________I,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,~_________~,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,!_________i,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,I________+:,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,,!-_______<,,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,,xQ--____--[I,,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,,,nhX--------rUI,,,,,,,,,,,,,,,,
,,,,,,,,,,,,,,:vhhZ?-------\hq?,,,,,,,,,,,,,,,
,,,,,,,,,,,,>nmhhhhc------?QhhhYl,,,,,,,,,,,,,
,,,,,,,,,!/udhhhhhhk/?---?Qhhhhhpz{:,,,,,,,,,,
,,,,,,,>Cdhhhhhhhhhhp[-?-YhhhhhhhhhO|I,,,,,,,,
,,,,,,-whhhhhhhhhhhhhC?]JhhhhhhhhhhhhQl,,,,,,,
,,,,,[khhhhhhhhhhhhhhh0qhhhhhhhhhhhhhhL;,,,,,,
,,,,,mhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhX:,,,,,
,,,,_hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh\,,,,,
,,,,thhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhpI,,,,
,,,,chhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh[,,,,
,,,,Yhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh\,,,,
,,,,Ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhf,,,,
,,,,bhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhu,,,,
,,,ihhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhJ,,,,
,,,{hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhZ,,,,
```

`run --image "example/avatar_small.png" --output-file "example/output_inv.txt"`

```
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$$$$#dmm8$$$$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$$Q{~~~~-L$$$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$%\~~~~~~~~n@$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$X~~~~~~~~~~Y$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$B-_}}?+-?{t}-8$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$d_paaaaaaaad+Q$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$X)aaaaaaaaaa|/$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$nfaaaaaaaaaav+B$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$nuaaaaaaaaaaw+W$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$vXaaaaaaaaaaa1B$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$YCaaaaaaaaaaaU$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$Z0aaaaaaaaaaap8$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$#daaaaaaaaaaaaM$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$Waaaaaaaaahaah&$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$@aaaaaaaaaaaaa@$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$Waaaaaaaaaaa%$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$*aaaaaaaaa*$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$&aaaaaaaaaW$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$%aaaaaaaa*$$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$$8haaaaaaa#$$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$$Y/hhaaaahhd%$$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$$$X-nhhhhhhhhUr%$$$$$$$$$$$$$$$$
$$$$$$$$$$$$$$@c--|khhhhhhh0-{k$$$$$$$$$$$$$$$
$$$$$$$$$$$$WX(----vhhhhhhk/---x8$$$$$$$$$$$$$
$$$$$$$$$&Qz[------?Qkhhhkt-----}vq@$$$$$$$$$$
$$$$$$$Wf[----------}dhkhx---------|Z%$$$$$$$$
$$$$$$h1-------------fkbj------------/8$$$$$$$
$$$$$d?---------------/{--------------t@$$$$$$
$$$$$)---------------------------------n$$$$$$
$$$$a-----------------------------------O$$$$$
$$$$Q-----------------------------------}B$$$$
$$$$c------------------------------------d$$$$
$$$$x------------------------------------0$$$$
$$$$|------------------------------------C$$$$
$$$$]------------------------------------z$$$$
$$$W-------------------------------------j$$$$
$$$q-------------------------------------|$$$$
```

## Build & Run
1. Run `sbt` in the project root directory
2. Compile & Run the program using `run` (sbt command)
3. Use `help` for information about the program, then run it using `run <image> [filter1] [filter2] ... <output>` (ASCII Art Convertor command)
