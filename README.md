# ArieraFireFighers
Custom Bukkit plugin requested by BananaBiscuits

### Commands and Permissions
|Commands      |Aliases      |Description      |Permission     |
|:------------:|:-----------:|:---------------:|:-------------:|
|`/gascan`|`(gc)`|Gives the player a gas can|`aff.arsonist`|
|`/lighter`|`(lt)`|Gives the player a lighter|`aff.arsonist`|
|`/extinguisher`|N/A|Gives the player an extinguisher|`aff.firefighter`|

Players with the `aff.firefighter` and `aff.police` will receive notifications about fires starting.

Players can only have one of each of the tools in their inventories at a time.

The gas can has 50 uses before it "runs out of gas".

The lighter will light the gas you click and all the gass in a 3x3 (horizontally) around the clicked block.

### Configuration
* `gas-uses`: Number of gas can uses before it runs out
* `gas-time-before-vanish`: Time (in seconds) before the gas will disappear.
