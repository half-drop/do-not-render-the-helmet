# Do Not Render the Helmet

A tiny client-side Fabric mod that hides **any item** in every player's head/helmet slot from the player model, including your own. Armor, carved pumpkins, skulls, regular items, and server resource-pack cosmetics are covered. The item remains equipped and keeps all of its gameplay effects; only its rendering is changed.

## Usage

Head-slot rendering is disabled by default, so installing the mod is enough to hide everything players wear in that slot.

To toggle it while playing, open **Options → Controls → Key Binds**, find **Do Not Render the Helmet**, and assign a key to **Disable Head Slot Rendering**. The key is intentionally unbound by default. Press it to switch head-slot rendering on or off; an action-bar message confirms the new state.

## 中文说明

这是一个纯客户端 Fabric 模组，会隐藏所有玩家（包括你自己）头盔栏中装备的**任何物品**。除了原版头盔，也包括雕刻南瓜、头颅、普通物品以及服务器资源包提供的头部装扮。物品仍然实际装备，护甲值、附魔和其他效果完全不受影响，仅不再显示在玩家模型上。

模组默认禁用头盔栏物品渲染。需要切换时，进入 **选项 → 控制 → 按键绑定**，找到 **Do Not Render the Helmet** 分类，为 **禁用头盔栏物品渲染** 分配快捷键。快捷键默认为空；按下后会切换显示状态，并在屏幕上显示提示。

问题反馈请前往 [GitHub Issues](https://github.com/half-drop/do-not-render-the-helmet/issues)。

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
