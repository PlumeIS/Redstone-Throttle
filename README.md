# Aeronautic Throttle

> 为 **Create** / **Create: Aeronautics** 设计的红石转速调节附属模组。
> A redstone-controlled rotational speed modulator addon for **Create** and **Create: Aeronautics**.

[中文](#中文) · [English](#english)

---

## 中文

### 简介

本模组加入了一个方块——**红石转速调节器**（Redstone Speed Modulator）。它放置在两个机械动力网络之间，按红石信号在输入转速基础上累加正/负偏移量，把输出转速平滑、可控地调节到想要的值。

特别适合 Create: Aeronautics 的载具用——平稳起飞、缓慢减速、精确停止；也可以单纯作为"红石远程调速"使用。

### 特性

- **6 向放置**：水平/垂直均可，扳手右键非动力轴面循环切方向
- **红石双向控制**：左侧增加偏移，右侧减少偏移
- **三种模式**（GUI 内切换）：
  - **跟随红石强度**：偏移变化速率 = 信号强度 × 0.5 RPM/步
  - **固定速率**：忽略强度，任意非零信号都按设定的固定 RPM/步 累加
  - **强度倍率**：即时响应，输出 = 输入转速 ± 红石信号强度 × 倍率
- **可调步长**：1-256 RPM/步（滑块）
- **可调更新间隔**：6-200 tick（0.3-10 秒，滑块）。间隔越长变化越缓和，避免传动杆爆裂
- **安全行为**：
  - 输出不会反向旋转——降到 0 自动停下
  - 拆掉输入轴后输出立即归零，再接回去偏移设置不丢
  - 信号方向反转/上电瞬间立即响应，无 tick 延迟
  - 偏移自动钳到对当前输入有效的范围，不会"还债"
- **可视化**：前后两段独立旋转的轴端，前面按输出转速、后面按输入转速旋转
- **护目镜支持**：显示当前模式、输出转速、步长、间隔
- **外观**：基于 Create 链式传动箱 + 可调节齿轮箱风格

### 依赖

| 依赖 | 版本 |
|---|---|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.219 或更高 |
| **Create** | 6.0.10 或更高 |
| Create: Aeronautics | 可选 |

### 安装

1. 安装 NeoForge 21.1.219+
2. 把 Create 6.0.10+ 放到 `mods/` 文件夹
3. 把本模组 jar 放到 `mods/` 文件夹
4. 启动游戏

### 使用

1. **合成**：4×红石 + 2×黄铜锭 + 2×齿轮 + 1×精密机械
2. **放置**：方块前后两面会自动对齐到附近的动力轴方向
3. **接入**：输入动力接到方块**背面**，输出从**前面**接出
4. **红石控制**：
   - **左面**通红石 → 输出转速增加
   - **右面**通红石 → 输出转速减少
5. **配置 GUI**：**Shift + 右键** 打开面板
   - 模式按钮：跟随红石强度 ↔ 固定速率 ↔ 强度倍率
   - 锁定速率滑块：1-256 RPM/步（固定速率模式）
   - 强度倍率滑块：1-256×（强度倍率模式）
   - 更新间隔滑块：6-200 tick（累积模式）

### 调参建议

| 需求 | 推荐设置 |
|---|---|
| 平稳缓慢的变化 | 固定速率，速率 4-16，间隔 100-200 tick |
| 快速响应 | 固定速率，速率 64-256，间隔 6-20 tick（注意传动杆负载）|
| 模拟旋钮 | 跟随强度模式，配合比较器输出强度 |
| 红石精细控制 | 强度倍率模式，倍率 8-32×，即时响应无延迟 |

### 从源码构建

```bash
git clone <repo-url>
cd AeroThrottle
./gradlew build
```

产物路径：`build/libs/aero_throttle-1.21.1-<version>.jar`

需要 Java 21。

### 已知问题 / 待办

- 在某些 Iris shader 组合下可能闪烁

### 致谢

- **simibubi** 和 Create 团队的 API 与代码参考
- **Create: Aeronautics** 团队的飞行机械灵感

### 许可

MIT License — 见 [LICENSE](LICENSE)。

Create 模组本身、其纹理与模型属于 Create 团队，请遵守 [Create 的许可](https://github.com/Creators-of-Create/Create/blob/mc1.21.1/dev/LICENSE.md)。

---

## English

### Overview

This mod adds one block — the **Redstone Speed Modulator**. It sits between two Create kinetic networks and accumulates a positive/negative offset on top of the input speed in response to redstone signals, giving you a smooth, controllable way to dial the output RPM.

Designed to pair with **Create: Aeronautics** vehicles for smooth takeoffs, controlled deceleration, and precise stops — but works as a general-purpose remote speed control too.

### Features

- **6-direction placement**: horizontal or vertical; wrench-right-click any non-shaft face to cycle facing
- **Bidirectional redstone**: left increases offset, right decreases it
- **Three modes** (switchable in GUI):
  - **Strength**: offset change rate = signal × 0.5 RPM/step
  - **Fixed**: any non-zero signal applies the configured RPM/step, ignoring strength
  - **S × Mul**: instantaneous, output = input speed ± signal strength × multiplier
- **Adjustable step**: 1 to 256 RPM per step (slider)
- **Adjustable interval**: 6 to 200 ticks (0.3-10 s, slider) — longer = gentler, avoids snapping shafts
- **Safety**:
  - Output never crosses zero — falls to 0 and stops
  - Disconnecting input immediately zeros output; offset is preserved for reconnect
  - Instant response on direction flip / power-on
  - Offset auto-clamped to useful range — no "debt" accumulates past the cap
- **Visuals**: two independently rotating shaft halves; front spins at output speed, back at input speed
- **Engineer's Goggles** show current mode, output speed, step, interval

### Requirements

| Dependency | Version |
|---|---|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.219+ |
| **Create** | 6.0.10+ |
| Create: Aeronautics | Optional |

### Installation

1. Install NeoForge 21.1.219+
2. Drop Create 6.0.10+ into `mods/`
3. Drop this mod's jar into `mods/`
4. Launch

### Usage

1. **Recipe**: 4× redstone + 2× brass ingots + 2× cogwheels + 1× precision mechanism
2. **Place** the block; the front/back faces auto-align to nearby kinetic shafts
3. **Wire** input shaft into the **back**, take output from the **front**
4. **Redstone**: left face = increase speed; right face = decrease speed
5. **Config GUI**: **Shift + right-click** to open
   - Mode button: Strength ↔ Fixed Rate ↔ S × Mul
   - Locked Rate slider: 1-256 RPM/step (Fixed Rate mode)
   - Strength Mul slider: 1-256× (S × Mul mode)
   - Update Interval slider: 6-200 ticks (accumulating modes)

### Tuning

| Goal | Recommended |
|---|---|
| Smooth slow ramp | Fixed Rate, rate 4-16, interval 100-200 ticks |
| Snappy response | Fixed Rate, rate 64-256, interval 6-20 ticks (mind shaft stress) |
| Analog dial | Strength mode + comparator output |
| Fine redstone control | S × Mul mode, multiplier 8-32×, instant response |

### Building

```bash
git clone <repo-url>
cd AeroThrottle
./gradlew build
```

Output: `build/libs/aero_throttle-1.21.1-<version>.jar`

Requires Java 21.

### Known Issues / TODO

- May flicker under some Iris shader combos

### Credits

- **simibubi** and the Create team for the API and reference code
- **Create: Aeronautics** team for the flight mechanic inspiration

### License

MIT License — see [LICENSE](LICENSE).

Create itself, its textures and models belong to the Create team; please follow [Create's license](https://github.com/Creators-of-Create/Create/blob/mc1.21.1/dev/LICENSE.md).
