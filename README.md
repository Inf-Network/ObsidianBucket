# 桶装黑曜石（ObsidianBucket）

面向 Paper 1.20+ 的轻量插件：玩家手持熔岩桶右键黑曜石时，黑曜石会变成熔岩，非创造模式会消耗熔岩桶并得到空桶。

## 保护区域兼容

插件会调用服务器标准的 `BlockBreakEvent` 保护流程来判断权限。只要玩家不能正常挖掉该黑曜石，桶装也会被拒绝，因此可兼容 WorldGuard、Residence、SuperiorSkyblock 2 等通过取消方块破坏事件实现保护的插件。插件只创建检测事件，不会真的破坏方块。

默认不要求额外权限；可在 `plugins/ObsidianBucket/config.yml` 中设置 `require-permission: true`，再授予 `obsidianbucket.use`。

## 构建

需要 Java 21 和 Gradle：

```bash
./gradlew build
```

构建产物位于 `build/libs/ObsidianBucket-1.0.0.jar`。

## 开源协议

本项目使用 MIT License，详见 [LICENSE](LICENSE)。
