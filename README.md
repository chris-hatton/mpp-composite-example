# Kotlin Multiplatform 
## Composite Build Support Example Project

[KT-52172](https://youtrack.jetbrains.com/issue/KT-52172/Multiplatform-Support-composite-builds)

This project is intended to exercise and test Kotlin Multiplatform's support for Gradle Composite Build ([KT-52172](https://youtrack.jetbrains.com/issue/KT-52172/Multiplatform-Support-composite-builds)).

The file layout of this project:

| Folder                          | Description                                      |
| ------------------------------- | ------------------------------------------------ |
| `/library`         | Hypothetical Library project      |
| `/app` | Hypothetical App project which requires the Library |
| `/composite`      | Gradle Composite workspace for Library & Dev App |
| `/build-system` | Gradle project defining a Gradle Plugin (as a `buildSrc` equivalent) contributing build logic for both `library` and `app`. |

For context; while this repo has been stripped down to a merely hypothetical Library/App; this pattern is used in several  real-world projects of mine including [OpenFoodFacts Kotlin Library](https://github.com/openfoodfacts/openfoodfacts-kotlin) and other projects in development with my current employer.

The stripped down App runs on Desktop, Android and iOS; and using *Compose Multiplatform*, simply displays:

```
Composite Example App
Running on <PlatformName>
```

...where `<PlatformName>` is provided by the `/library` project.

## Running the Apps

### Prerequisites

It's assumed the reader is familiar enough with KMP, and the context around [KT-52172](https://youtrack.jetbrains.com/issue/KT-52172/Multiplatform-Support-composite-builds), to have the requisite software installed.

:warning: Before attempting to use this project; **you must edit the `local.properties` file** at `build-system/local.properties` with the location of your local Android SDK installation.  The Gradle projects at `library`, `app` and `composite` have their own apparent `local.properties` files too; but to avoid duplicated maintenance these are symlinked to `build-system/local.properties`, making this the only necessary edit.

### Running without Composite (Baseline)

This section describes how to run the projects today; in the absense of Gradle Composite support, for any interested developer to establish a baseline:

- Because the Library and App projects are entirely separate Gradle projects; you will not be able to immediately build/run the `/app` project because the `org.example:library:1.0-SNAPSHOT` dependency will not be available.
- First, you must execute the Gradle task `publishToMavenLocal` in the `/library` project.
- After this, trial one or more `/app` projects and observe them running.

This is a functional workflow, but sub-optimal in case we want to develop the Library and App simultaneously.

:warning: Before proceeding to test Composite behaviours; you may need to clean any published `org.example:library:1.0-SNAPSHOT` artifact from within your Maven Local (`~/.m2`) folder.

### Running via Composite Project

An improved workflow should be possible by using the Composite project; this is intended to 'loosely couple' the Library and App projects in the same Gradle and - by extension - IDE workspace.

#### Desired Behaviour

The Jetbrains IDE (IntelliJ/Android Studio) should, through Kotlin Multiplatform, evaluate the Composite project and its included builds consistently, so that:

- The `org.example.Platform` class could be refactored/renamed from the context of either Library/App project, and have the change reflect in the other and vice-versa
- A property could be added to the Library's `org.example.Platform` class, and then be immediately consumed from in the App's `org.example.MainView` class, with fully function code completion/awareness.
- When running/installing the App e.g: via Gradle task `:app:desktop:run` or `:app:android:installDebug` the IDE should be smart enough to recognise that we are in a Composite workspace and invoke any project tasks against  the Composite project behind-the-scenes, thereby enabling **dependency substitution** to happen during builds.

These behaviours should also remain consistent across the three App platform-target source-sets.

:warning: This is a non-exhaustive list of requirements and more test cases should be added.

## Build System Project

To further exercise KMP's integration with Gradle Composite; we are also using a Gradle Plugin defined in an included build. This is a common feature of many larger projects, being the modern recommended way to achieve `buildSrc`-like functionality.

