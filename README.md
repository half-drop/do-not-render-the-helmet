# Do Not Render the Helmet

A tiny client-side Fabric mod that hides the item in every player's helmet slot from the player model, including your own. The helmet remains equipped and keeps all of its gameplay effects; only its rendering is changed.

## Usage

Helmet rendering is disabled by default, so installing the mod is enough to hide player helmets.

To toggle it while playing, open **Options → Controls → Key Binds**, find **Do Not Render the Helmet**, and assign a key to **Disable Helmet Rendering**. The key is intentionally unbound by default. Press it to switch helmet rendering on or off; an action-bar message confirms the new state.

## Supported versions

- Minecraft 1.21.11 with Fabric Loader 0.19.3 or newer, Fabric API, and Java 21+
- Minecraft 26.2 with Fabric Loader 0.19.3 or newer, Fabric API, and Java 25+

Install the jar whose filename matches your Minecraft version. This mod is client-side only and does not need to be installed on the server.

## Building

```text
./gradlew build
```

The two installable jars are created under `mc1.21.11/build/libs` and `mc26.2/build/libs`.

## License

MIT © half_drop

