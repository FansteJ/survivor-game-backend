INSERT INTO enemy_types (id, name, base_gold, base_xp, base_gems) VALUES ('zombie', 'Zombie', 10, 10, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO enemy_types (id, name, base_gold, base_xp, base_gems) VALUES ('skeleton', 'Skeleton', 5, 5, 0.5) ON CONFLICT (id) DO NOTHING;
INSERT INTO enemy_types (id, name, base_gold, base_xp, base_gems) VALUES ('orc', 'Orc', 25, 25, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO enemy_types (id, name, base_gold, base_xp, base_gems) VALUES ('ghost', 'Ghost', 1, 1, 0) ON CONFLICT (id) DO NOTHING;
INSERT INTO enemy_types (id, name, base_gold, base_xp, base_gems) VALUES ('orc_small', 'Small Orc', 2, 2, 0) ON CONFLICT (id) DO NOTHING;
INSERT INTO enemy_types (id, name, base_gold, base_xp, base_gems) VALUES ('ghost_boss', 'Ghost Boss', 100, 100, 10) ON CONFLICT (id) DO NOTHING;

INSERT INTO quest_type (id, name, description, goal_type, goal, reward_gems)
VALUES (gen_random_uuid(), 'Ghost Hunter', 'Kill 100 Ghosts', 'KILL_GHOSTS', 100, 50) ON CONFLICT DO NOTHING;

INSERT INTO quest_type (id, name, description, goal_type, goal, reward_gems)
VALUES (gen_random_uuid(), 'Survivor', 'Survive for 300 seconds in one run', 'SURVIVE_TIME', 300, 20) ON CONFLICT DO NOTHING;

INSERT INTO quest_type (id, name, description, goal_type, goal, reward_gems)
VALUES ( gen_random_uuid(), 'Gold Digger', 'Earn 1000 gold', 'COLLECT_GOLD', 1000, 10) ON CONFLICT DO NOTHING;