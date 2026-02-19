# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

### [1.13.1](https://github.com/FansteJ/survivor-game-backend/compare/v1.13.0...v1.13.1) (2026-02-19)


### Bug Fixes

* fix query in UserQuestRepository ([7a0082b](https://github.com/FansteJ/survivor-game-backend/commit/7a0082bdbdee4889c4e729ce1df99b2ef12dd878))

## [1.13.0](https://github.com/FansteJ/survivor-game-backend/compare/v1.12.0...v1.13.0) (2026-02-18)


### Features

* update JwtService and SECRET_KEY ([404a82f](https://github.com/FansteJ/survivor-game-backend/commit/404a82f2adb3f2858cd3e5a822d4c112cc7e9dce))

## [1.12.0](https://github.com/FansteJ/survivor-game-backend/compare/v1.11.0...v1.12.0) (2026-02-18)


### Features

* add claimReward method in QuestService and add QuestController ([ae14b43](https://github.com/FansteJ/survivor-game-backend/commit/ae14b43abc55d9feed1f1cd658f416505086c53c))
* add GameController with startGame and finishGame endpoints ([aaee732](https://github.com/FansteJ/survivor-game-backend/commit/aaee73222f865fad464b4f58353a210f56e3ee80))

## [1.11.0](https://github.com/FansteJ/survivor-game-backend/compare/v1.10.0...v1.11.0) (2026-02-17)


### Features

* add getDailyQuests, generateNewQuests and createQuestFromType methods in QuestService ([28210aa](https://github.com/FansteJ/survivor-game-backend/commit/28210aa5c1d8248619e269dd0782950dfc0b8657))
* add QuestGoalType enum, QuestType and UserQuest entities ([0a52b34](https://github.com/FansteJ/survivor-game-backend/commit/0a52b345273dec09f65a87bb11a674563122fe19))
* add QuestProgressDTO, UserQuestRepository and QuestProgressService with updateQuestProgress method ([906a24c](https://github.com/FansteJ/survivor-game-backend/commit/906a24ceed71c33a244cf1de00de90447a66d4c5))

## [1.10.0](https://github.com/FansteJ/survivor-game-backend/compare/v1.9.0...v1.10.0) (2026-02-16)


### Features

* add LeaderboardEntryDTO and LeaderboardService ([3f603be](https://github.com/FansteJ/survivor-game-backend/commit/3f603bef35a60a378a9325ae5c71e3cc5af142ca))
* implement rank calculation for current profile ([8073ab5](https://github.com/FansteJ/survivor-game-backend/commit/8073ab5898869b83ab569c3c46ed1e32d8cf5b0b))

## [1.9.0](https://github.com/FansteJ/survivor-game-backend/compare/v1.8.0...v1.9.0) (2026-02-15)


### Features

* add buyUpgrade endpoint and modify services to use getCurrentUser method from UserProfileService ([de90a08](https://github.com/FansteJ/survivor-game-backend/commit/de90a08bf47843a946840aa2d0bd6fe1b200f343))
* add getShopItems endpoint ([e8d4774](https://github.com/FansteJ/survivor-game-backend/commit/e8d47743b3c0f74464b637a62ae9f0efab22aaed))
* add PlayerModifiers DTO and UpgradeEffectService ([20e74b5](https://github.com/FansteJ/survivor-game-backend/commit/20e74b5496dc690627315d864dc43b8049999dae))
* modify FinishGameSessionService to use PlayerModifiers and add PlayerController ([54b0308](https://github.com/FansteJ/survivor-game-backend/commit/54b030800d465eb0ae5f3531e9f9cdf5f372b2f7))

## [1.8.0](https://github.com/FansteJ/survivor-game-backend/compare/v1.7.0...v1.8.0) (2026-02-15)


### Features

* add BuyUpgradeResponse DTO and UpgradeService ([e801cb2](https://github.com/FansteJ/survivor-game-backend/commit/e801cb213deb645c75af2c3c040bea840d5135a4))
* add UpgradeShopItemDTO and ShopService ([ea6cea1](https://github.com/FansteJ/survivor-game-backend/commit/ea6cea130fbc1a4da030ff3e606b0400deb95c43))
* add UpgradeTypeRepository and UserUpgradeRepository ([d2d1848](https://github.com/FansteJ/survivor-game-backend/commit/d2d1848f2a04cc72c82b5a5e26ac4f75f2b1b9f7))
* optimize ShopService and add strategies to calculate item cost ([ea3844f](https://github.com/FansteJ/survivor-game-backend/commit/ea3844f49b30f571d46813b33a59642657c0b0f2))

## [1.7.0](https://github.com/FansteJ/survivor-game-backend/compare/v1.6.0...v1.7.0) (2026-02-14)


### Features

* add EffectType enum, UpgradeType and UserUpgrade entities ([f5480ae](https://github.com/FansteJ/survivor-game-backend/commit/f5480aede5cb1c7a269d5f28c616e493fe3834c7))
* add UserProfileController with getMyProfile endpoint ([961eaa1](https://github.com/FansteJ/survivor-game-backend/commit/961eaa1c0c351fb746d95adba73ef0b205f89ba4))

## [1.6.0](https://github.com/FansteJ/survivor-game-backend/compare/v1.5.0...v1.6.0) (2026-02-14)


### Features

* add StartGameSessionService ([f6b0912](https://github.com/FansteJ/survivor-game-backend/commit/f6b09120c731f135ec4c86e63e09f437d84ed3e3))
* add UserProfileService and UserProfileDTO ([53335b2](https://github.com/FansteJ/survivor-game-backend/commit/53335b29263a66f095530f1b3c0f8638279ff004))
* optimize FinishGameSessionService ([246d0de](https://github.com/FansteJ/survivor-game-backend/commit/246d0de4ce74eb425f46422d727eed5eb3a70e6d))

## [1.5.0](https://github.com/FansteJ/survivor-game-backend/compare/v1.4.0...v1.5.0) (2026-02-13)


### Features

* add EnemyTypeRepository, UserProfileRepository, GameSessionEnemyKillRepository and update FinishGameSessionService to calculate reward from game session and save it in database ([390087e](https://github.com/FansteJ/survivor-game-backend/commit/390087e1b385e4d608701c8d77ff26f760c6493f))
* add FinishGameSessionRequest DTO and EnemyKillDTO ([a235938](https://github.com/FansteJ/survivor-game-backend/commit/a2359388fe52f16ec8985968e02881b0ed535a08))
* add GameSessionRepository and FinishGameSessionService ([5eaae85](https://github.com/FansteJ/survivor-game-backend/commit/5eaae85203d9c766aa80635851119ef3f6f54ac6))

## [1.4.0](https://github.com/FansteJ/survivor-game-backend/compare/v1.3.0...v1.4.0) (2026-02-13)


### Features

* add GameSession, EnemyType, GameSessionEnemyKill and modify UserProfile (model classes) ([50bc082](https://github.com/FansteJ/survivor-game-backend/commit/50bc082d087238b78021ae381736a59c5e65c3fe))
* add UserProfile (model) ([f92bf10](https://github.com/FansteJ/survivor-game-backend/commit/f92bf10e3f1f6c3ce43b0adeafaf7a7f059fdf8e))

## [1.3.0](https://github.com/FansteJ/survivor-game-backend/compare/v1.2.0...v1.3.0) (2026-02-13)


### Features

* add JwtAuthenticationFilter ([fbb2216](https://github.com/FansteJ/survivor-game-backend/commit/fbb2216fe2c55bf5fc661b51bca29b4490f1b63d))
* add LoginResponse DTO, login endpoint ([bbbce32](https://github.com/FansteJ/survivor-game-backend/commit/bbbce32bbbc36e59c6000860f26d69bf3ba3b003))
* modify JwtAuthenticationFilter and SecurityConfig to use JWT and add UserController to test ([419c3a1](https://github.com/FansteJ/survivor-game-backend/commit/419c3a1f7ec968fc6155947b819bcaa05b2849bb))

## [1.2.0](https://github.com/FansteJ/survivor-game-backend/compare/v1.1.0...v1.2.0) (2026-02-12)


### Features

* add JwtService with generateToken method ([2418d91](https://github.com/FansteJ/survivor-game-backend/commit/2418d910bd4943542f07ece4d750d6bec245e1e6))
* add LoginRequest DTO ([91ee4f2](https://github.com/FansteJ/survivor-game-backend/commit/91ee4f2ecc5f767299694fe602a760ad7c20384d))
* add validateToken method in JwtService ([1d65830](https://github.com/FansteJ/survivor-game-backend/commit/1d6583041302c96bd07d91139440af77405dd109))

## [1.1.0](https://github.com/FansteJ/survivor-game-backend/compare/v1.0.1...v1.1.0) (2026-02-11)


### Features

* add RegisterRequest (DTO), AuthController (PostMapping - register) and modify SecurityConfig ([24a1975](https://github.com/FansteJ/survivor-game-backend/commit/24a19756ec47adb8b0929a419fc993275c1cf252))
* add SecurityConfig (password hashing) ([5539c8d](https://github.com/FansteJ/survivor-game-backend/commit/5539c8d413c8cb03a95fc7f6a175db2abf70438c))
* add User entity ([691becc](https://github.com/FansteJ/survivor-game-backend/commit/691becc28da7324d86aee16163040999d927e9ac))
* add UserRepository ([6e0535f](https://github.com/FansteJ/survivor-game-backend/commit/6e0535f86fa137b36e80daf7d9b03ac89ef08481))
* add UserService with register method ([cd18bb0](https://github.com/FansteJ/survivor-game-backend/commit/cd18bb010d81eb421bd93616c269ac0f85f1249a))

### 1.0.1 (2026-02-11)
