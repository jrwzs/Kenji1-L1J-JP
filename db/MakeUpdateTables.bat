set driver=mysql
set locale=ja
type .\schema\%driver%\armor.sql > update_tables.sql
type .\schema\%driver%\armor_set.sql >> update_tables.sql
type .\schema\%driver%\beginner_items.sql >> update_tables.sql
type .\schema\%driver%\commands.sql >> update_tables.sql
type .\schema\%driver%\cooking_ingredients.sql >> update_tables.sql
type .\schema\%driver%\cooking_recipes.sql >> update_tables.sql
type .\schema\%driver%\door_gfxs.sql >> update_tables.sql
type .\schema\%driver%\drop_item.sql >> update_tables.sql
type .\schema\%driver%\droplist.sql >> update_tables.sql
type .\schema\%driver%\dungeon.sql >> update_tables.sql
type .\schema\%driver%\dungeon_random.sql >> update_tables.sql
type .\schema\%driver%\etcitem.sql >> update_tables.sql
type .\schema\%driver%\getback.sql >> update_tables.sql
type .\schema\%driver%\getback_restart.sql >> update_tables.sql
type .\schema\%driver%\magic_doll.sql >> update_tables.sql
type .\schema\%driver%\mapids.sql >> update_tables.sql
type .\schema\%driver%\mobgroup.sql >> update_tables.sql
type .\schema\%driver%\mobskill.sql >> update_tables.sql
type .\schema\%driver%\npc.sql >> update_tables.sql
type .\schema\%driver%\npcaction.sql >> update_tables.sql
type .\schema\%driver%\npcchat.sql >> update_tables.sql
type .\schema\%driver%\petitem.sql >> update_tables.sql
type .\schema\%driver%\pettypes.sql >> update_tables.sql
type .\schema\%driver%\polymorphs.sql >> update_tables.sql
type .\schema\%driver%\resolvent.sql >> update_tables.sql
type .\schema\%driver%\shop.sql >> update_tables.sql
type .\schema\%driver%\skills.sql >> update_tables.sql
type .\schema\%driver%\spawnlist.sql >> update_tables.sql
type .\schema\%driver%\spawnlist_boss.sql >> update_tables.sql
type .\schema\%driver%\spawnlist_door.sql >> update_tables.sql
type .\schema\%driver%\spawnlist_furniture.sql >> update_tables.sql
type .\schema\%driver%\spawnlist_light.sql >> update_tables.sql
type .\schema\%driver%\spawnlist_npc.sql >> update_tables.sql
type .\schema\%driver%\spawnlist_time.sql >> update_tables.sql
type .\schema\%driver%\spawnlist_trap.sql >> update_tables.sql
type .\schema\%driver%\spawnlist_ub.sql >> update_tables.sql
type .\schema\%driver%\spr_action.sql >> update_tables.sql
type .\schema\%driver%\trap.sql >> update_tables.sql
type .\schema\%driver%\ub_managers.sql >> update_tables.sql
type .\schema\%driver%\ub_settings.sql >> update_tables.sql
type .\schema\%driver%\ub_times.sql >> update_tables.sql
type .\schema\%driver%\weapon.sql >> update_tables.sql
type .\schema\%driver%\weapon_skill.sql >> update_tables.sql
type .\records\%locale%\armor.sql >> update_tables.sql
type .\records\%locale%\armor_set.sql >> update_tables.sql
type .\records\%locale%\beginner_items.sql >> update_tables.sql
type .\records\%locale%\commands.sql >> update_tables.sql
type .\records\%locale%\cooking_ingredients.sql >> update_tables.sql
type .\records\%locale%\cooking_recipes.sql >> update_tables.sql
type .\records\%locale%\door_gfxs.sql >> update_tables.sql
type .\records\%locale%\drop_item.sql >> update_tables.sql
type .\records\%locale%\droplist.sql >> update_tables.sql
type .\records\%locale%\dungeon.sql >> update_tables.sql
type .\records\%locale%\dungeon_random.sql >> update_tables.sql
type .\records\%locale%\etcitem.sql >> update_tables.sql
type .\records\%locale%\getback.sql >> update_tables.sql
type .\records\%locale%\getback_restart.sql >> update_tables.sql
type .\records\%locale%\magic_doll.sql >> update_tables.sql
type .\records\%locale%\mapids.sql >> update_tables.sql
type .\records\%locale%\mobgroup.sql >> update_tables.sql
type .\records\%locale%\mobskill.sql >> update_tables.sql
type .\records\%locale%\npc.sql >> update_tables.sql
type .\records\%locale%\npcaction.sql >> update_tables.sql
type .\records\%locale%\npcchat.sql >> update_tables.sql
type .\records\%locale%\petitem.sql >> update_tables.sql
type .\records\%locale%\pettypes.sql >> update_tables.sql
type .\records\%locale%\polymorphs.sql >> update_tables.sql
type .\records\%locale%\resolvent.sql >> update_tables.sql
type .\records\%locale%\shop.sql >> update_tables.sql
type .\records\%locale%\skills.sql >> update_tables.sql
type .\records\%locale%\spawnlist.sql >> update_tables.sql
type .\records\%locale%\spawnlist_boss.sql >> update_tables.sql
type .\records\%locale%\spawnlist_door.sql >> update_tables.sql
type .\records\%locale%\spawnlist_furniture.sql >> update_tables.sql
type .\records\%locale%\spawnlist_light.sql >> update_tables.sql
type .\records\%locale%\spawnlist_npc.sql >> update_tables.sql
type .\records\%locale%\spawnlist_time.sql >> update_tables.sql
type .\records\%locale%\spawnlist_trap.sql >> update_tables.sql
type .\records\%locale%\spawnlist_ub.sql >> update_tables.sql
type .\records\%locale%\spr_action.sql >> update_tables.sql
type .\records\%locale%\trap.sql >> update_tables.sql
type .\records\%locale%\ub_managers.sql >> update_tables.sql
type .\records\%locale%\ub_settings.sql >> update_tables.sql
type .\records\%locale%\ub_times.sql >> update_tables.sql
type .\records\%locale%\weapon.sql >> update_tables.sql
type .\records\%locale%\weapon_skill.sql >> update_tables.sql
