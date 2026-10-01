# План: AyuGram Android на базе DrKLO master 12.10.3

Статус: **ЗАМОРОЗКА (v2)** — 22.09.2026. Реализация не начата; ждём команду «старт».
v2 = v1 (замороженный) + разбор 2026-базы (что добавилось за 3 года) + аудит прав/портов + решения по резам.

---

## 1. Целевое решение

Собрать модифицированный клиент **AyuGram** (Android, Java/TLRPC) на основе **DrKLO/Telegram master @ 12.10.3** (APP_VERSION_CODE=7089, compileSdk 36, minSdk 21, targetSdk 36).

1. **Основа функциональности: AyuGram4A** (Java/TLRPC; exteraGram ← Telegraher ← DrKLO). Точка развилки 4A = `DrKLO/Telegram release-9.6.6_3362` (подтверждено по version code 3362).
2. **AyuGramX** (Kotlin/TDLib/Compose) — архитектура несовместима со спекой; переносим только отдельные UI-фичи (B1 hide reactions, B5 remember send options, B6 hide panel buttons), не код-базу.
3. **Стратегия rebase: хирургический перенос «нашего» патч-сета hunk-ами на чистый master.** 3-way-merge всего дерева 4A невозможен:
   - патч-сет AyuGram = **52 своих файла** (`com.radolyn.ayugram`) + **43 изменённых core-файла** (~8.8k строк diff);
   - upstream-churn тех же 43 файлов за 3 года = **~221k строк** (TLRPC 73.7k, ChatActivity 30k, ChatMessageCell 19.7k, PhotoViewer 12.1k, MessagesController 10.4k…).
   - Extera-визуальный слой (режется) просто не завозится.

## 2. Зафиксированные решения

### 2.1. Общие (v1, все закрыты)

| # | Решение |
|---|---|
| R1 | Пакет **`com.tech.ayugram`** (1 строка `APP_PACKAGE` в gradle.properties; core остаётся `org.telegram.*`; свой код пишем в `com.tech.ayugram.*`) |
| R2 | **Cast-стек оставляем** (play-services-cast, mediarouter, nanohttpd, ChromecastFileServer, CastSync). Оговорка зафиксирована: `ChromecastFileServer` слушает LAN-порт **61578** только во время кастинга (назначение — стриминг на свой TV) |
| R3 | Имя приложения — **AyuGram** (финально) |
| R4 | **In-app updater вырезан полностью** (E4 + updater-флоу); дистрибуция только через GitHub |
| R5 | Удалить весь tracking/analytics/external: T1–T7 (Firebase analytics/crashlytics/appindexing/config/datatransport + google-services.json), C1–C3 (Stripe, Play Billing, wallet), E6 (libgsaverification / GoogleVoiceClient) |
| R6 | **Нет обфускации**; публикация на GitHub; сборка через GitHub Actions; подпись — **GitHub Actions Secrets (вариант A, решение 22.09.2026)**, keystore вне репо |
| R7 | **Multi-account — держим** (в master 2026 он на `AccountInstance`-facade — см. §5) |
| R8 | **Watchers — полностью** по спеке §3: локальные правила + HTTP-webhook |
| R9 | Официальные API-ключи: `APP_ID=6`, `APP_HASH=eb06d4abfb49dc3eeb1aeb98ae0f581e` |
| R10 | **Скриншоты всегда работают** во всех чатах, без уведомлений/ограничений (вырезать механизм, повторить на master) |
| R11 | **Системная камера для всего**, включая круги (round video = системное видео + флаг `round_message`; аудио-кружки — AudioRecord). Trade-off без live-кольца/таймера зафиксирован |
| R12 | **Перевод держим** (mlkit:language-id); **карты держим**, должны работать без GMS |
| R13 | Device info = массовый: `samsung SM-S911B`, `SDK 34`, официальная версия + fingerprint (в `ConnectionsManager`) |
| R14 | **A12-визуал режем полностью** (Monet, Solar icons, ForceBlur/Snow, сезонные, LNavigation, анимация drawer, превью стикеров, углы аватаров, DoubleTap, BottomButton, shortcuts, system-fonts…). Ребазе extera-слой не завозится |
| R15 | **Local Premium держим**; из 4A держим B1 (hide reactions), B5 (remember send options), B6 (hide panel buttons) |
| R16 | **Push: FCM единственный транспорт** + тумблер register/deregister в настройках + override пакета GMS (microG/revanced) — реализация с нуля |
| R17 | **Карты: гибрид** (GMS → Google, иначе MapLibre + OSM) + выбор провайдера в настройках. B-база: master уже имеет цепочку превью `maps.googleapis.com` → `static-maps.yandex.ru` в `AndroidUtilities` |
| R18 | Premium-апселл вырезать; de-bloat по спеке §6 |

### 2.2. Решения v2 (рез 2026-базы + права, все закрыты)

| # | Решение |
|---|---|
| R19 | **AI-фичи — рез**: `RichAIComposeSheet` (AI-набор текста), `AiTonesController`, `TL_aicompose` |
| R20 | **Android Car — рез**: `messenger/car` + `androidx.car.app` + `TelegramCarAppService` |
| R21 | **Wear OS — рез**: `WearAuthListenerService`, `WearReplyReceiver`, `WearAuthSheet`, `AuctionWearingSheet` |
| R22 | **Платежи — рез целиком**: `PaymentFormActivity` + Stripe + `TL_payments` + `TL_fragment` + `com.android.vending.BILLING` + `StakedDiceSheet` (TON-ставки). Бот-инвойсы деградируют в «платежи недоступны» |
| R23 | **Реклама/платные посты не рендерим**: `TL_message.paid_suggested_post_stars/ton` — бейджи «Paid» в `ChatMessageCell`/`ChatActionCell`, `SuggestionOffer`, `MessageSuggestionOfferSheet` (MessageObject стр. 11675/13389). В сервер не пишем |
| R24 | **`REQUEST_INSTALL_PACKAGES` — рез**: APK из чата открывается внешним менеджером (`AndroidUtilities.openForView`) |
| R25 | **Vendor-разрешения на бейджи (14 шт.) — рез** (samsung/oppo/htc/huawei/sony/majeur…); `INSTALL_SHORTCUT` + 2 vendor-аналога для закрепа чата держим |
| R26 | **`CAMERA` + `play-services-vision` — рез** вместе с системной камерой (Фаза 4) |
| R27 | **Бизнес-аккаунты — рез**: `ui/Business` (22 файла) + точки входа. TL-поля (`via_business_bot_id`, `guestchat_via_from`, business-часть `TL_account`) остаются — сообщения через бизнес-бот рендерятся как обычные, без краха |
| R28 | **Аффилиаты — рез**: `SuggestedAffiliateProgramsFragment`, `ChannelAffiliateProgramsFragment` + cells |
| R29 | **TON/крипта — рез**: `ui/TON`, TON-флаги в UI, wallet (C3) — дублирует R22 + спека §6.2 |
| R30 | **Встроенный браузер — рез**: `ui/web/*` (браузер, история, закладки, search engine, MHTML). Внешние URL → **CustomTabs** (системный браузер/Brave; vendored-клиент custom tabs уже в коде). Превью ссылок (карточки в чате) **держим**. **Bot-webview держим** (R33) |
| R31 | **Обои: своя картинка + цвет/градиент; синхронизация — нет.** Держим «Choose from gallery» (локальный файл) и COLOR-тип; локальное хранение. Режем: серверный каталог обоев/тем (`getWallPapers`/`getMultiWallPapers`/`installWallPaper`/`saveWallPaper`/`getChatThemes`), gift-обои (`messenger/wallpaper/pgm`, gift-темы), per-chat `ChatThemes`, облачный синк тем. `WallpapersListActivity` остаётся урезанная: галерея + цвет/градиент + сброс |
| R32 | **Права — рез**: `READ_PHONE_STATE`, `READ_PHONE_NUMBERS`, `ACCESS_BACKGROUND_LOCATION`, `BLUETOOTH`, `BLUETOOTH_CONNECT`, `SYSTEM_ALERT_WINDOW`, `GET_ACCOUNTS`, `USE_FULL_SCREEN_INTENT`, `RECEIVE_BOOT_COMPLETED`, `REQUEST_INSTALL_PACKAGES`, `MANAGE_OWN_CALLS` (мёртвое), `AUTHENTICATE_ACCOUNTS`, `MANAGE_ACCOUNTS`, `READ_SYNC_SETTINGS`, `WRITE_SYNC_SETTINGS`, `READ_PROFILE` (мёртвый contact-sync: `AuthenticatorService` + `ContactsSyncAdapterService` + `res/xml/auth.xml` + `res/xml/sync_contacts.xml` — 0 вызовов в коде). `READ_MEDIA_*` — **не трогать** |
| R33 | **Bot-webview держим** (inline-боты, бот-кнопки, статьи — системный Chromium; рез убил бы фичи чата) |
| R34 | **Гео: только разовая отправка.** Держим `ACCESS_FINE/COARSE_LOCATION` (one-shot fix при «Отправить геолокацию»), входящие геоточки на гибридной карте. Режем: `ACCESS_BACKGROUND_LOCATION` (включая `config/debug|release/AndroidManifest.xml`), **live-локация целиком**: `LocationSharingService`, live-ветка `LocationActivity` (`share_live_location`), live-маркеры/аватары, `SharingLocationsAlert`, live-панель вложений |
| R35 | **Звуки: только системные.** Держим выбор из `RingtoneManager` (системные), per-chat звук (системный), default = системный. Режем: `messenger/ringtone/RingtoneDataStore.java` + `RingtoneUploader.java`, облачные ячейки в `NotificationsSoundActivity` (смешанный пикер: строки 374–545 — облачные `cell.tone.uri` вырезаем, `RingtoneManager`-ветки держим), TL `SavedRingtones` (`getSavedRingtones/saveRingtone` в `MessagesController`/`MediaDataController`, `TL_update`-обновления — игнор) |
| R36 | **Аккаунты: ноль доступа** — после R32 приложение не имеет ни своих аккаунтов (contact-sync удалён), ни доступа к чужим (`GET_ACCOUNTS` резан). E-mail в «Добавить контакт» — ручной ввод или из `READ_CONTACTS` |
| R37 | **Монетизация каналов — рез** (R22–R24, R28, R29 вместе) |

## 3. Стек и идентичность

- **База:** DrKLO/Telegram `master @ 12.10.3`.
- **Идентичность:** `APP_PACKAGE=com.tech.ayugram`, имя AyuGram, `local.properties` с APP_ID=6 + APP_HASH.
- **Завозим:** патч-сет AyuGram (36 файлов + ~35 core-ханков), 6 подсистем MOBILE_SPECIFICATION.md, privacy-набор.
- **Не завозим:** extera-визуал, AyuSync (16 файлов `sync/*` — E5), встроенную камеру, всё по §4.
- **Зависимости — убрать:** `firebase-appindexing`, `firebase-datatransport` (Analytics/Crashlytics DrKLO сам вырезал — проверено), `stripe 2.0.2`, `billingclient 8.0.0`, `play-services-wallet`, `play-services-vision` (R26), `play-services-auth` (GSA-verification, R5/E6), `androidx.car.app` (R20), `libgsaverification-client.aar` (E6). **Оставить:** `firebase-messaging 22.0.0` (push), `gson 2.11.0`, `mlkit:language-id`, `play-services-maps` (карты), Cast-стек (R2). **Добавить:** Room (БД AyuGram), MapLibre (карты без GMS).
- **tlrpc-patch.py:** переписать regex'ы под новый TLRPC (`ayuDeleted`, `ayuNoforwards`, `restricted=false`, `history_deleted=false`). Точки patch'а в монолите на месте (`TL_message` конструктор `0x7600b9d3`, строка ~58254).

## 4. Полный список резов 2026-базы (v2)

### 4.1. Функциональные блоки (цели для вырезания)

| Блок | Кодовые цели |
|---|---|
| Stories (§6.1) | `ui/Stories` (101 файл), `TL_stories`, `StoryUploadingService`, `StoriesTabsView`, градиентные ободки, перехват `stories.getAllStories/getPeerStories/getStoriesArchive` → пустые ответы, игнор stories-updates |
| Stars (§6.2) | `ui/Stars` (21 файл), `TL_stars`, точки в `SettingsActivity`/`ProfileActivity`/`ChatActivity` |
| Gifts/аукционы | `ui/Gifts` (14), `GiftAuctionController`, `ResaleGiftsFragment`, gift-части обоев |
| TON/крипта | `ui/TON`, `StakedDiceSheet`, TON-флаги `paid_suggested_post_ton` в UI |
| AI | `RichAIComposeSheet`, `AiTonesController`, `TL_aicompose` |
| Car | `messenger/car` (4 файла), `TelegramCarAppService` |
| Wear | 3 файла + `WearAuthSheet` |
| GSA/GoogleVoice | `TMessagesProj_App`: `GoogleVoiceClientActivity/Service`, `libgsaverification-client.aar` (E6) |
| Бизнес | `ui/Business` (22), business-UI в `ProfileActivity`/`ChatActivity` |
| Аффилиаты | 2 фрагмента + cells |
| Платежи | `PaymentFormActivity`, Stripe-импорты, `TL_payments`, `TL_fragment`, BILLING |
| Платные/рекламные посты | бейджи + офферы (R23) |
| Встроенный браузер | `ui/web` (15), переход на CustomTabs (R30) |
| Обои-синх | облачный каталог/темы/gift/per-chat (R31) |
| Live-локация | `LocationSharingService` + live-ветки UI (R34) |
| Облачные рингтоны | `ringtone/RingtoneDataStore`+`Uploader`, облачные ветки пикера (R35) |
| Мёртвый contact-sync | `AuthenticatorService`, `ContactsSyncAdapterService`, xml (R32) |

### 4.2. TL-split-модули для вырезания целиком

`tgnet/tl/`: `TL_stories` (100 классов), `TL_stars` (98), `TL_payments` (10), `TL_fragment` (5), `TL_aicompose` (5); внутри `TL_account`: business-классы + `ChatThemes`/gift-chat-themes + `SavedRingtones` + wallpaper-сет (кроме локального пути). Монолит `TLRPC.java` — не трогаем (кроме patch).

### 4.3. Что ДЕРЖИМ из нового 2026 (проверено)

- **`proxy` (3 файла, ~1.4k)** — MTProto через WebRTC-proxy (WebView-мост «tdesktop-web-proxy-bridge-v1»). Аудит: единственный `new ServerSocket(0, …, 127.0.0.1)` — **loopback, случайный порт**, не экспортируется. Privacy-фича — держим.
- `ui/community` (14) — «Сообщества» (ядро супергрупп).
- `ui/iv` (47) — rich-контент: inline-боты, LaTeX, таблицы (AI-часть резан — R19).
- `ui/bots` (23) — бот-логин QR, bot-webview (аффилиаты резаны — R28).
- `ui/recyclerview` (3), `messenger/pip` (21 — PiP-видео), `messenger/postdrawcompat` (3), `localization` (1), `messenger/car` **резан** (R20), `messenger/chromecast` держим (R2).
- `tgnet/tl/TL_forum`, `TL_keyboard`, `TL_bots`, `TL_iv`, `TL_ephemeral`, `TL_chatlists`, `TL_communities`, `TL_phone`, `TL_stats`, `TL_update`, `TL_legacy_message`.

## 5. Архитектурные особенности 2026-базы (влияют на порт)

| Было (9.6.6 / 4A) | Стало (12.10.3) | Влияние |
|---|---|---|
| `TLRPC.java` — монолит со всеми TL | Монолит = ядро (`TL_message`, `TL_auth_*`, `TL_account_saveMusic/setMainProfileTab` + 2063 классов); домены — `tgnet/tl/TL_<ns>` вложенные camelCase | Новый TL-код — в split-стиле; patch-точки монолита на месте |
| `account.getUserSettings` — монолитный объект | Разложен на доменные вызовы (`getAccountTTL`, `getNotifySettings`, `getPrivacy`, `getAutoDownloadSettings`…) + **passkey-логин** (`registerPasskey/…`) | Ханки 4A против getUserSettings переносить «по смыслу» в новые владельцы |
| `AccountManager.java` — монолит (login/settings/sync) | `AccountInstance`-facade (multi-account: `getInstance(num)`) + доменные контроллеры (`BillingController`, `ChannelBoostsController`, `StatsController`, `CaptchaController`, `CacheByChatsController`…) | Хани 4A против `AccountManager` → `AccountInstance` + новые контроллеры |
| `SearchController.java` — чат-поиск | Убран: `SearchAdapter` + `MessagesSearchAdapter` + `DialogsSearchAdapter` + `ChatActivitySearchContainer` + `SearchTabsAndFiltersLayout` | Фаза 7 §2: целевые классы — перечисленные |
| `DrawerLayoutAdapter` | Убран: drawer = `DialogsActivity` + `ActionBar/DrawerLayoutContainer` | Ghost-тогл → новый drawer |
| `ChatActivity` монолит | Расколот на `ChatActivityContainer`/`DialogsActivity` + `Components/chat/*` | Перенос «по смыслу» |

## 6. Финальный manifest (после всех резов)

**Держим (~21):** `INTERNET`, `ACCESS_NETWORK_STATE`, `ACCESS_WIFI_STATE`, `RECORD_AUDIO`, `MODIFY_AUDIO_SETTINGS`, `READ_CONTACTS`, `WRITE_CONTACTS`, `READ_MEDIA_IMAGES`, `READ_MEDIA_VIDEO`, `READ_MEDIA_AUDIO`, `READ_EXTERNAL_STORAGE`, `WRITE_EXTERNAL_STORAGE`, `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`, `POST_NOTIFICATIONS`, `FOREGROUND_SERVICE` (+ типы `dataSync`, `mediaPlayback`, `mediaProjection`, `microphone`), `VIBRATE`, `WAKE_LOCK`, `USE_BIOMETRIC`, `USE_FINGERPRINT`, `INSTALL_SHORTCUT`, `com.android.launcher.permission.INSTALL_SHORTCUT`, `com.android.launcher.permission.UNINSTALL_SHORTCUT`.

**Удаляем (67 → ~20):** `ACCESS_BACKGROUND_LOCATION`, `AUTHENTICATE_ACCOUNTS`, `MANAGE_ACCOUNTS`, `READ_SYNC_SETTINGS`, `WRITE_SYNC_SETTINGS`, `READ_PROFILE`, `GET_ACCOUNTS`, `BLUETOOTH`, `BLUETOOTH_CONNECT`, `CAMERA`, `FOREGROUND_SERVICE_CAMERA`, `MANAGE_OWN_CALLS`, `READ_APP_BADGE`, `READ_CLIPBOARD`, `READ_PHONE_NUMBERS`, `READ_PHONE_STATE`, `RECEIVE_BOOT_COMPLETED`, `REQUEST_INSTALL_PACKAGES`, `SYSTEM_ALERT_WINDOW`, `USE_FULL_SCREEN_INTENT`, `FOREGROUND_SERVICE_LOCATION`, `com.android.vending.BILLING`, 14 vendor-бейдж-разрешений.

\* `READ_CLIPBOARD` — **рез** (решение 22.09.2026, проверено по AOSP Android 13/14): стандартная вставка (долго-тап → «Вставить», фреймворк `TextView`) и вставка из клавиатуры (Gboard/IME: IME сам читает буфер — `isDefaultIme`-ветка, текст вводится через `InputConnection`) работают **без** этого права — гейт в `ClipboardService.getPrimaryClip` = «app в фокусе» (`isDefaultDeviceAndUidFocused`) + AppOps `OP_READ_CLIPBOARD` со значением по умолчанию `MODE_ALLOWED` (AOSP `AppOpsManager.sAppOpInfos` — для любого приложения, независимо от манифеста). Все 19 вызовов `getPrimaryClip()`/`hasPrimaryClip()` в коде Telegram (11 файлов: поле сообщения `ChatActivityEnterView`, подписи `EditTextCaption`, логин-код `LoginActivity`/`CodeNumberField`, proxy, опросы, rich editor, `TextSelectionHelper`) — собственные кнопки «Вставить» или системное меню вставки, все в фокусе, работают без права. Копирование (`setPrimaryClip`) тоже работает — `OP_WRITE_CLIPBOARD` разрешён без фокуса. Сами runtime-права `READ_CLIPBOARD` в гейте не проверяется (проверяется только signature-`READ_CLIPBOARD_IN_BACKGROUND`); оно давало только видимость в системных настройках + возможность пользователю явно запретить. Ничего не ломается.

**Сервисы — резать:** `StoryUploadingService`, `TelegramCarAppService`, `WearAuthListenerService`, `AuthenticatorService`, `ContactsSyncAdapterService`, `LocationSharingService` (R34). **Держим:** `NotificationsService`, `VoIPService` (camera→уберётся с R26 → `microphone|mediaPlayback|mediaProjection`), `TelegramConnectionService`, `KeepAliveJob`, `BringAppForegroundService`, `NotificationRepeat`, `VideoEncodingService` (кодирование видео — нужно для кругов), `ImportingService`, `MusicPlayerService`, `MusicBrowserService`, 3 widget-сервиса, `FilesMigrationService`. **Receivers — резать:** `WearReplyReceiver`, `AppStartReceiver` (с R32 boot). **Держим:** `SmsReceiver` (коды, foreground-SMS — стандарт), `CallReceiver`, остальные.

## 7. Таблица деградаций (часть спецификации)

| Ушло право | Потеря |
|---|---|
| `ACCESS_BACKGROUND_LOCATION` | live-локаций нет; только разовая точка (R34) |
| `BLUETOOTH` / `BLUETOOTH_CONNECT` | звонки без BT-гарнитуры/колонки (динамик, провод) |
| `READ_PHONE_STATE` / `READ_PHONE_NUMBERS` | номер при входе — ручной ввод; тип сети — fallback на ConnectivityManager |
| `SYSTEM_ALERT_WINDOW` | нет оверлеев (PiP-звонки, вьювер поверх звонка) — звонки в обычном окне |
| `USE_FULL_SCREEN_INTENT` | входящий звонок при выключенном экране — только нотификация с кнопками |
| `RECEIVE_BOOT_COMPLETED` | passcode-лок при первом открытии после ребута; push-сервис поднимется при первом открытии (WorkManager подтянет) |
| `GET_ACCOUNTS` | e-mail в «Добавить контакт» — вручную / из контактов |
| `REQUEST_INSTALL_PACKAGES` | APK из чата — во внешний менеджер |
| `CAMERA` / `play-services-vision` | камера только системная, без face-detection |
| Встроенный браузер | внешние URL — в CustomTabs (Brave/системный) |
| Облачные обои/рингтоны | только локальный выбор; синка на другие устройства нет (R31, R35) |

## 8. Аудит базового клиента (ответ на вопрос «превышает ли?»)

- **Порты:** MTProto — исходящие TCP 443 (норма); WebRTC-звонки — эфемерные UDP во время звонка (норма); `WebProxyTransport` — `ServerSocket(0, 127.0.0.1)` loopback (норма, R: держим); `ChromecastFileServer` — LAN **61578** только во время кастинга (принято с R2). Внешних слушающих портов нет.
- **Список приложений:** `QUERY_ALL_PACKAGES` — нет; `getInstalledPackages/Applications` — 0 вызовов. Точечные `queryIntentActivities` (custom tabs/Play/launcher'ы/EmuDetector) — в пределах package visibility. **Приложения не перечисляются.**
- **Поиск устройств:** собственного обнаружения нет (`NsdManager`=0, `BluetoothSocket`=0, `LeScanner`=0, `NfcAdapter`=0). Оговорка: Cast-фреймворк внутри GMS делает mDNS для Cast-устройств (часть R2).
- **Нормальные разрешения:** location (гео, R34), BT (звонки), `SYSTEM_ALERT_WINDOW` (PiP), clipboard (по действиям), `READ_PHONE_STATE` (номер/сеть), biometric (passcode), shortcuts, boot (R32).
- **Итог:** существенных превышений нет; рез — по табл. §6.

## 9. Фазы реализации

### Фаза 0 — Каркас: чистый master, «стерилизация»
- **Репозитории (решение 22.09.2026):**
  - Рабочий репо: **`Anselmnn/AyuGram`** — форк DrKLO/Telegram (полная история на сервере, для CI-mtime; форк-бейдж честно показывает линейку). Имя финальное = AyuGram.
  - Сборочный аккаунт = `Anselmnn` (второй аккаунт пользователя; CI-раны идут здесь). **По готовности перенос репо в первый аккаунт `darakcheeff`** (`POST /repos/{o}/{r}/transfer` или push).
  - Донор-4A: `darakcheeff/AyuGram4A` (тег `donor-4a` = ветка rewrite) + upstream `AyuGram/AyuGram4A` + локальная копия `/tmp/opencode/4a_src`.
  - Локальная рабочая копия: `/home/tech/ayugram` (shallow depth-1; история для mtime — на сервере).
  - Первый токен (darakcheeff, fine-grained) — только git-refs; второй токен (Anselmnn, classic `repo`+`workflow`) — основной. Токены в `/tmp/opencode/.gh_token2` (не в репо).
- `gradle.properties`: `APP_PACKAGE=com.tech.ayugram`, имя AyuGram (APP_ID/APP_HASH уже в master: `6` / `eb06...`).
- build.gradle: срез T (appindexing, datatransport), C1–C3, E6, R19–R26 (deps); AndroidManifest: финальный набор прав по §6; вырезать манифестные записи (services/receivers/xml) по §6.
- Вырезать код удалённых зависимостей (billing, wallet, updater, GSA, car, wear, contact-sync).
- **Чекпоинт:** `assembleDebug` зелёный; `aapt dump permissions` = §6.

### Фаза 1 — De-bloat (спека §6 + R19–R31)
- §6.1 Stories: перехват в `ConnectionsManager` → пустые ответы; отключение предзагрузки; скрытие `StoriesTabsView` + ободков; игнор stories-updates.
- §6.2 + R-решения: `ui/Stars`, `ui/Gifts`, `ui/TON`, `GiftAuctionController`, `StakedDiceSheet`, `ui/Business`, аффилиаты, платные посты (R23), AI (R19), Car (R20), Wear (R21), `PaymentFormActivity` (R22), встроенный браузер → CustomTabs (R30), обои-синх (R31), live-локация (R34), облачные рингтоны (R35), TL-split-модули по §4.2.
- **Чекпоинт:** stories-updates не роняют; в UI нет stars/gifts/TON/promo/AI/car/wear/business; URL → CustomTabs; обои/звуки — локальные.

### Фаза 2 — Privacy и идентичность
- `ConnectionsManager`: device = `samsung SM-S911B` / `SDK 34` / офиц. версия+fingerprint.
- `EmuDetector` / `EmuInputDevicesDetector` → `return false`.
- Скриншоты всегда разрешены: 12.10.3-аналоги `MediaController.processMediaObserver` + `FLAG_SECURE` (`SecretChatActivity`/`SecretMediaViewer`) + `SharedConfig` — выключить для всех чатов.
- Переписать и прогнать `tlrpc-patch.py` (монолит, точки на месте; новые домены — split-модули, не трогать).
- **Чекпоинт:** скриншоты без уведомлений в любых чатах; deviceModel в логах.

### Фаза 3 — Порт патч-сета AyuGram (ядро)
- **3.1** Копируем **36 файлов** (из 52 минус 16 `sync/*`) в `com.tech.ayugram.*`; чистим `AyuConfig` от sync-полей. Gradle: + Room.
- **3.2** Core-ханки из 43 файлов (~35) по 3 группам (учитывая §5):
  - **A — механическая** (~16): EmuDetector, EmuInputDevicesDetector, FlagSecureReason, BlockingUpdateView, RefererReceiver, UserConfig, DatabaseMigrationHelper, FileRefController, FilesMigrationService, PushListenerController, ChatObject, SharedConfig, BuildVars, FileLoader, ContactsController, ImageLocation.
  - **B — средняя** (~13): AndroidUtilities, ApplicationLoader, ImageLoader, MediaController, MediaDataController, MessageObject, MessagesController, MessagesStorage, NotificationsService, SecretChatHelper, SendMessagesHelper, DownloadController, ConnectionsManager.
  - **C — сложная** («по смыслу», с учётом §5): TLRPC (через patch), ChatActivity→ChatActivityContainer/DialogsActivity, PhotoViewer, ProfileActivity, ChatMessageCell, DialogCell, LaunchActivity, AlertsCreator.
  - **Спец:** ghost-тогл → drawer master (`DialogsActivity` + `DrawerLayoutContainer`); `InstantCameraView` не переносим (Фаза 4); `Theme.java` (0 ayu-маркеров) — пропуск; ханки против `AccountManager` → `AccountInstance`+контроллеры; ханки против `SearchController` → `SearchAdapter` и соотв.
- **3.3** Local Premium: UserConfig, MessagesController, AyuGramPreferencesActivity.
- **3.4** Настройки AyuGram: ghost, save/restore, regex-фильтры, forwarder.
- **Чекпоинт:** ghost, save/restore, фильтры, forwarder, Local Premium работают.

### Фаза 4 — Системная камера
- Удалить `org.telegram.messenger.camera.*` + `InstantCameraView` (master-версии); замена на `ACTION_IMAGE_CAPTURE` / `ACTION_VIDEO_CAPTURE`.
- Круги: системное видео + `round_message`; аудио-кружки — `AudioRecord`.
- Убрать `CAMERA`-пермо + `play-services-vision` (R26).
- **Чекпоинт:** фото/видео/круги/голос через системную камеру.

### Фаза 5 — Push (FCM + GMS)
- FCM единственный транспорт (`GcmPushListenerService` — в master есть).
- Тумблер register/deregister токена; override пакета GMS (microG/revanced) — с нуля.
- **Чекпоинт:** push работает; off → нет токена; override → через microG.

### Фаза 6 — Карты (гибрид)
- GMS → Google (`play-services-maps`), иначе **MapLibre + OSM** (новая зависимость).
- Настройка выбора провайдера; точка инъекции — где master создаёт map object; B-база: цепочка `maps.googleapis.com` → `static-maps.yandex.ru` в `AndroidUtilities` (R34-совместимо: без BG-location).
- **Чекпоинт:** карта открывается с и без GMS.

### Фаза 7 — 6 подсистем спеки (основной новый код)
1. **§1 Offline-First:** `local_messages` + `cached_dialogs` (Room-БД `AyuDatabase`); 3 хука (updates / getHistory / folder sync); `isOffline()` (нет сети ИЛИ MTProto Connecting/Updating/WaitingForNetwork > 1500 мс); офлайн-пагинация из SQLite.
2. **§2 Smart Search:** парсер `(a|b) c -x` → декартово произведение → параллельные `messages.search` → дедуп по `(dialog_id, message_id)` → фильтр исключений → `date DESC`; fallback FTS. **Целевые классы (2026):** `SearchAdapter`, `MessagesSearchAdapter`, `DialogsSearchAdapter`, `ChatActivitySearchContainer`, `SearchTabsAndFiltersLayout`. **§2.2:** `filterId` только при globalSearch; `SEARCH_QUERY_EMPTY` с `from_id` → локальная выборка.
3. **§3 Watchers Engine:** правила в Room; перехват `UpdateNewMessage`; high-priority `NotificationChannel` (`USAGE_ALARM`); форвард в Saved + deep-link; async HTTP-webhook (JSON по спеке); «Все сообщения пользователя» в профиле.
4. **§4 Серверные треды:** `isServerThread()` по алгоритму; `messages.getReplies` (в master TLRPC есть) с loadBefore/loadAfter; локальное дерево для ЛС.
5. **§5 Синх папок:** сервис-канал «AyuGram Service» + pin-JSON; `channels.createChannel`, `sendMessage`+`pinChatMessage`, `getPinnedDialogs`/`getHistory limit=1`; синх при изменении папок.
6. **§6** — Фазы 0–1.
- **Чекпоинт после каждой:** сборка + сценарии из спеки.

### CI-архитектура (решение 22.09.2026, подход из darakcheeff/AyuGramDesktop)
- **Работает (22.09.2026):** репо `Anselmnn/AyuGram`, ветка `master`, коммит `de190066` = базовый CI-пиплайн (workflow `.github/workflows/build.yml`). Первый холодный раунд `assembleDebug` (-P CI_ABIS=arm64-v8a) — в прогоне; после зелёного — старт Фазы 0 поверх него.
- **Черновик workflow готов:** `auegram_android/ci/build.yml` (копируется в `.github/workflows/build.yml` при старте): ubuntu-latest, `fetch-depth: 0`, git-mtime-скрипт, JDK 17, SDK 36/NDK 27.2/CMake 3.22.1 (кач. из cmdline-tools 15859902, по версии Dockerfile DrKLO), ccache через env-лаунчеры, 5 слоёв кеша, `assembleDebug`, APK-артефакт, save `if: always()`.
- **Все сборки — только в GitHub Actions** (`ubuntu-latest`, workflow `.github/workflows/build.yml`). Локальная VM **без** JDK/SDK/Gradle и без локальных сборок — только клон исходников для правок/коммитов (`/home/tech/ayugram`); не захламляем VM.
- **Инкрементальная пересборка (только изменившиеся файлы)** — три слоя:
  1. **Restore Git Timestamps** (перенос скрипта из Desktop-workflow): после checkout (`fetch-depth: 0`) mtime каждого файла = время его последнего коммита (python, `git log --name-only --pretty=format:%ct`). Неизменённые файлы «старее» артефактов из кеша → CMake/NDK не пересобирает их; изменившиеся в новом коммите — старше → пересобираются. Без этого трюка каждый checkout ставит mtime=now → полный ребилд нативки.
  2. **actions/cache**: `~/.gradle` (зависимости, wrapper, NDK-кач, build-cache) + `$ANDROID_HOME` (platform-36, build-tools, NDK 27.2 — ~2.5G) + `~/.ccache` (ccache-кеш NDK C/C++/ASM).
  3. **ccache для NDK** без правки исходников: env `CMAKE_C_COMPILER_LAUNCHER=ccache`, `CMAKE_CXX_COMPILER_LAUNCHER=ccache`, `CMAKE_ASM_COMPILER_LAUNCHER=ccache` (CMake читает их из процесса-вызывающего — задокументировано) + `org.gradle.caching=true` в `gradle.properties` (build-cache: неизменённые gradle-задачи — FROM-CACHE).
- **Цикл устранения падений:** push → мониторинг `runs`/`jobs`/`logs` через GitHub REST API → фикс в коде → push → повтор. Без участия пользователя. `ccache -s` и `gradle --scan`-лог в каждом ране для контроля хит-рейта.
- Базовая сборка = `:TMessagesProj_App:assembleDebug` (debug-ключ репо). Release-джоб с подписью из Secrets (вариант A) добавляется в Фазе 8.

### Фаза 8 — Финальный QA + CI + публикация
- Сетевой сниффер: нет запросов к analytics/stars/stories/ai/business endpoints; нет `wallpapers.get`/`savedRingtones`/`liveLocation`.
- `aapt dump`: перми = §6.
- Смоук-лист: логин, multi-account, push on/off, скриншоты, камера (4 типа), поиск (операторы/автор/offline), watchers (3 действия), треды (канал+ЛС), синх папок (2 устройства), карты (±GMS), ghost, geo (one-shot), обои/звуки (локальные), URL → CustomTabs.
- GitHub Actions: toolchain под compileSdk 36 (Dockerfile DrKLO), артефакт — подписанный APK.
- **Подпись (вариант A, решение 22.09.2026):** один keystore RSA-4096/PKCS12, validity ~10000 дней (~27 лет), генерится **один раз локально** — одна и та же подпись во всех релизах (иначе Android не даст обновляться, signature mismatch). Keystore **не в репо** (в отличие от DrKLO, который держит `config/release.keystore` с паролем `android` в git — не копируем).
  - GitHub Secrets репо: `KEYSTORE_BASE64` (keystore в base64), `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD`.
  - Workflow: декод keystore → временный файл (чистится после) → `signingConfigs.release` из env/gradle properties → `assembleRelease` → APK в GitHub Release. Ключ не попадает в код, логи и артефакты.
  - Генерация (у пользователя): `keytool -genkeypair -keystore ayugram-release.keystore -storetype PKCS12 -alias ayugram -keyalg RSA -keysize 4096 -validity 10000`.
  - Бэкап: 2–3 офлайн-копии (зашифрованная флешка + менеджер паролей + бумажная копия паролей); потеря ключа = конец обновлений приложения.
  - Доступ к секретам = у всех с admin-правами репо → круг доступа к репо минимальный.
- Публикация репозитория на GitHub (без обфускации).

## 10. Горячие точки конфликтов (v2)

| Файл | Upstream churn (9.6.6→master) | Действие |
|---|---|---|
| `tgnet/TLRPC.java` | 73 735 | **не merge** — tlrpc-patch.py (точки на месте, констр. `TL_message` `0x7600b9d3`) |
| `ui/ChatActivity.java` | 29 890 | расколот → `ChatActivityContainer`/`DialogsActivity` — «по смыслу» |
| `ui/Cells/ChatMessageCell.java` | 19 664 | ghost/ayuDeleted/forward-маркеры + рез R23-бейджей |
| `ui/PhotoViewer.java` | 12 105 | нужные ханки |
| `messenger/MessagesController.java` | 10 386 | тонкие интеграции `AyuMessagesController` |
| `ui/Components/ChatActivityEnterView.java` | 10 186 | системная камера для кругов (или рез в Фазе 4) |
| `ui/Adapters/DrawerLayoutAdapter.java` | **нет в master** | ghost-тогл → `DialogsActivity` drawer |
| `AccountManager.java` | **нет в master** | ханки → `AccountInstance` + доменные контроллеры |
| `SearchController.java` | **нет в master** | ханки → `SearchAdapter`/`ChatActivitySearchContainer` |
| `ui/WallpapersListActivity.java` | рефакторинг | урезка по R31 (галерея+цвет) |
| `messenger/NotificationsService.java` | 84 | push + срез boot (R32) |

## 11. Риски

1. UI-рефакторинг 3 лет + **архитектурные сдвиги** (§5: AccountInstance, TL-split, Search-классы) — «по смыслу» порт — главный ручной объём (Группа C).
2. De-bloat глубже, чем на базе 2023: stories/stars/gifts/TON/AI/business глубоко вшиты в ChatActivity/ProfileActivity/MessagesController — основной риск Фазы 1.
3. compileSdk 36 — свежий toolchain в CI (Dockerfile DrKLO).
4. tlrpc-patch.py — rewrite regex'ов; TL-слой частично split'нут — patch только монолит, split-модули режутся целыми.
5. Новые фичи 2026 (business, communities, rich, pip) не трогаем; de-bloat строго по §4.
6. Multi-account на `AccountInstance` — проверить, что 4A-ханки (UserConfig/SharedConfig/DB) корректно маппятся на multi-instance (фаза 3.3).

## 12. Референсы и артефакты анализа

- `/home/tech/auegram_android/MOBILE_SPECIFICATION.md` — спека (6 подсистем).
- `/tmp/opencode/4a_src/` — исходник AyuGram4A.
- `/tmp/opencode/drklo_base/` — DrKLO 9.6.6 (точка развилки).
- `/tmp/opencode/drklo_master/` — DrKLO master 12.10.3 (целевая база).
- `/tmp/opencode/diff4a.txt` — diff 4A vs 9.6.6 (293 added / 221 removed / 538 modified, вкл. extera).
- Проверено в master: `GcmPushListenerService` — есть; `getReplies` в TLRPC — есть; `stories_*` — 292 ссылок; `TL_message` констр. `0x7600b9d3` (стр. 58254); `paid_suggested_post_stars/ton` (MessageObject 11675/13389); `ChromecastFileServer` порт 61578; `WebProxyTransport` ServerSocket loopback; contact-sync — 0 вызовов; `AppStartReceiver` — boot; `TL_account` split-модуль (44 класса: WallPapers/ChatThemes/SavedRingtones/business/passkey); `AccountInstance` — multi-account facade; `NotificationsSoundActivity` — смешанный пикер (RingtoneManager + облачные).

## 13. Открытые вопросы

- **Решено 22.09.2026:** `READ_CLIPBOARD` — **рез** (подробность в §6; AOSP-верификация: ни системная вставка, ни вставка из клавиатуры, ни кнопки Telegram не ломаются).
- **Внешние (не план):** точное время старта. Signing key — решение принято (вариант A, §R6/Фаза 8): пользователю нужно сгенерировать keystore и заложить 4 секрета в GitHub до Фазы 8 (не блокирует Фазы 0–7).

План v2 заморожен. Ждём «старт» для Фазы 0.
