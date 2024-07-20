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
> - 伤害类型:snowball_hit
>   - id: snowball_hit 
>   - 伤害不随难度提升
>   - 不损耗饥饿值
> - 死亡信息 *(ps:该信息，客户端需增加材质包)* :
>   - zh-cn: %1$s被%2$s的雪球打倒了，真是太菜了！
>   - en-us: %1$s was knocked down by %2$s's snowball. What a loser!