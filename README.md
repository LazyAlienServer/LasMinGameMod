# LasMinGameMod

> LAS服务器除夕小游戏服务端拓展模组  
> **modName**: LasMinGameMod  
> **modId**: lasmingamemod  
> **namespace**: lasmingamemod

## 1.增加雪球攻击标签
> **效果:**  
> 当添加该标签后，会根据itemStack中custom_data组件中hitDamage中数值给予被击中实体对应伤害
> - 为雪球增加自定义数据组件并增添伤害标签  
> **hitDamage**标签  
> 类型：*float*
> - 获取指令:  
> */give @s minecraft:snowball[minecraft:custom_data={hitDamage:50}]*
> - 召唤指令:  
> */summon minecraft:snowball ~ ~ ~ {"hitDamage":50}*
> - 伤害类型:snowball_hit
>   - id: snowball_hit 
>   - 伤害不随难度提升
>   - 不损耗饥饿值
> - 死亡信息 *(ps:该信息，客户端需增加材质包)* :
>   - zh-cn: %1$s被%2$s的雪球打倒了，真是太菜了！
>   - en-us: %1$s was knocked down by %2$s's snowball. What a loser!
> * **注意:投掷物的受击后伤害免疫被取消**
## 2.修改末影珍珠发射冷却时长
> **效果:**
> 当添加该标签后，会根据itemStack中custom_data组件中CoolDownTick中数值给予对应的发射冷却时长
> - 为末影珍珠增加自定义物品堆叠组件并修改发射冷却时长   
> **CoolDownTick&CoolDownSecond**标签  
> 类型：*int*
> - 获取指令:  
> */give @s minecraft:ender_pearl[minecraft:custom_data={CoolDownTick:20}]*  
> */give @s minecraft:ender_pearl[minecraft:custom_data={CoolDownSecond:1}]*
> - **注意事项**   
> *1.CoolDownTick所对应的数值为游戏刻,**1s=20GameTick**(即为gt或t),切勿填入秒数,否则会大幅减小冷却时长*  
> *2.CoolDownSecond仅支持对应整数数值,若要精确控制请使用CoolDownTick标签*  
> *3.CoolDownTick和CoolDownSecond不可同时使用,若同时使用会导致末影珍珠实际冷却时长为CoolDownTick对应的数值*