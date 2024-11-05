# Interop scala 3 in scala 2 code with -Ytasty-reader

Source: https://docs.scala-lang.org/scala3/guides/migration/compatibility-classpath.html

Context module scala3module should be consumed by scala2moduleusingscala3module which is in Scala 2.13.* think of an api layer in a real codebased where the module is limited to 2.13.*

Initially I had two types of struggles:

Both modules uses some typelevel libs and I'm getting:

```
[error] Modules were resolved with conflicting cross-version suffixes in ProjectRef(uri("file:/"), "scala2moduleusingscala3module"):
[error]    org.typelevel:cats-free _2.13, _3
[error]    org.scanamo:scanamo _2.13, _3
[error]    org.typelevel:cats-kernel _2.13, _3
[error]    org.typelevel:cats-core _2.13, _3
[error]    org.scala-lang.modules:scala-java8-compat _2.13, _3
```

I have tried to solve it with wither CrossVersion.for3Use2_13 (works with plain cats) and CrossVersion.for2_13Use3 - but with the scanamo lib that uses cats I'm beaten by either

CrossVersion.for3Use2_13 raises:
```
FoobarSemiAutoDerivation.scala:7:4
[error] 7 |    deriveDynamoFormat[FoobarSemiAutoDerivation]
[error]   |    ^
[error]   |Scala 2 macro cannot be used in Dotty. See https://docs.scala-lang.org/scala3/reference/dropped-features/macros.html
```
or with CrossVersion.for2_13Use3 raises:
```
[error] FoobarSemiAutoDerivation.scala:7:5: Unsupported Scala 3 inline method deriveDynamoFormat; found in package org.scanamo.generic.semiauto.package.
[error]     deriveDynamoFormat[FoobarSemiAutoDerivation]
```


Semi auto derivation turned out to work well `.cross(CrossVersion.for2_13Use3)` on all the packages pinned out in use.
