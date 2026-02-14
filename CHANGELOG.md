# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

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
