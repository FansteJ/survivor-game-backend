INSERT INTO enemy_types (id, name, base_gold, base_xp, base_gems) VALUES ('zombie', 'Zombie', 10, 10, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO enemy_types (id, name, base_gold, base_xp, base_gems) VALUES ('skeleton', 'Skeleton', 5, 5, 0.5) ON CONFLICT (id) DO NOTHING;
INSERT INTO enemy_types (id, name, base_gold, base_xp, base_gems) VALUES ('orc', 'Orc', 25, 25, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO enemy_types (id, name, base_gold, base_xp, base_gems) VALUES ('ghost', 'Ghost', 1, 1, 0) ON CONFLICT (id) DO NOTHING;
INSERT INTO enemy_types (id, name, base_gold, base_xp, base_gems) VALUES ('orc_small', 'Small Orc', 2, 2, 0) ON CONFLICT (id) DO NOTHING;
INSERT INTO enemy_types (id, name, base_gold, base_xp, base_gems) VALUES ('ghost_boss', 'Ghost Boss', 100, 100, 10) ON CONFLICT (id) DO NOTHING;

INSERT INTO quest_type (id, name, description, goal_type, goal, reward_gems)
VALUES ('quest_ghost_hunter', 'Ghost Hunter', 'Kill 100 Ghosts', 'KILL_GHOSTS', 100, 50) ON CONFLICT (id) DO NOTHING;

INSERT INTO quest_type (id, name, description, goal_type, goal, reward_gems)
VALUES ('quest_survivor', 'Survivor', 'Survive for 300 seconds in one run', 'SURVIVE_TIME', 300, 20) ON CONFLICT (id) DO NOTHING;

INSERT INTO quest_type (id, name, description, goal_type, goal, reward_gems)
VALUES ('quest_gold_digger', 'Gold Digger', 'Earn 1000 gold', 'COLLECT_GOLD', 1000, 10) ON CONFLICT (id) DO NOTHING;

INSERT INTO upgrade_type (id, name, description, effect_type, scaling_type, scaling_factor, value, base_cost, max_level)
VALUES ('damage_up', 'Brute Force', 'Damage multiplier', 'DAMAGE_MULTIPLIER', 'LINEAR', 100, 0.10, 150, 20) ON CONFLICT (id) DO NOTHING;

INSERT INTO upgrade_type (id, name, description, effect_type, scaling_type, scaling_factor, value, base_cost, max_level)
VALUES ('start_hp_up', 'Vitality', 'Starting HP', 'START_HP', 'EXPONENTIAL', 1.2, 20.0, 50, 50) ON CONFLICT (id) DO NOTHING;

INSERT INTO upgrade_type (id, name, description, effect_type, scaling_type, scaling_factor, value, base_cost, max_level)
VALUES ('gold_up', 'Greed', 'Gold multiplier', 'GOLD_MULTIPLIER', 'LINEAR', 250, 0.1, 500, 10) ON CONFLICT (id) DO NOTHING;

INSERT INTO upgrade_type (id, name, description, effect_type, scaling_type, scaling_factor, value, base_cost, max_level)
VALUES ('xp_up', 'Fast Learner', 'XP multiplier', 'XP_MULTIPLIER', 'LINEAR', 250, 0.10, 500, 10) ON CONFLICT (id) DO NOTHING;

INSERT INTO upgrade_type (id, name, description, effect_type, scaling_type, scaling_factor, value, base_cost, max_level)
VALUES ('revives_up', 'Second Chance', 'Extra revives', 'REVIVE', 'FIXED', 0, 1.0, 5000, 2) ON CONFLICT (id) DO NOTHING;

INSERT INTO upgrade_type (id, name, description, effect_type, scaling_type, scaling_factor, value, base_cost, max_level)
VALUES ('luck_up', 'Lucky Charm', 'Luck multiplier', 'LUCK_MULTIPLIER', 'EXPONENTIAL', 2.0, 1, 1000, 5) ON CONFLICT (id) DO NOTHING;

INSERT INTO upgrade_type (id, name, description, effect_type, scaling_type, scaling_factor, value, base_cost, max_level)
VALUES ('speed_up', 'Swiftness', 'Movement speed', 'START_SPEED', 'LINEAR', 50, 0.02, 100, 15) ON CONFLICT (id) DO NOTHING;