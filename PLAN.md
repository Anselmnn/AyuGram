# План: AyuGram Android на базе DrKLO master 12.10.3

Статус: **РАБОТАЕМ (v2.1)** — старт по исходному плану 04.10.2026. Путь «синтетической реализации» (01–03.10) отброшен; возврат к хирургическому rebase по v2.
v2 = v1 (замороженный) + разбор 2026-базы (что добавилось за 3 года) + аудит прав/портов + решения по резам.
v2.1 = полевые уточнения 04.10.2026 (после верификации артефактов на диске/GitHub) — см. §14.

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
- **Репозитории (решение 22.09.2026; уточнено 04.10.2026 — см. §14):**
  - Рабочий репо: **`Anselmnn/AyuGram`** (публичный). Линейка DrKLO на сервере **восстановлена 04.10.2026**: полная история `DrKLO/Telegram` (612 коммитов, unshallow) + ветка `plan-v2` от пин-коммита.
  - **Пин-коммит базы: `9552e5541e1274b9557c9832b204dbfcaf44b3dc`** = DrKLO 12.10.3 (7089), дерево побайтово сверено с upstream 04.10.2026. DrKLO master сдвинулся (04.10.2026: 12.10.6/7112, `f2908b14`) — все работы строго от пина.
  - Ветки: **`plan-v2`** = рабочая (DrKLO-линейка + артефакты плана + CI); `master` = заморожен (41 коммит «синтетики», не трогаем); **`phase2-line`** = архив отброшенного пути (106 коммитов, 01–03.10, хранится на сервере — локальный донор можно удалять).
  - Сборочный аккаунт = `Anselmnn` (второй аккаунт пользователя; CI-раны идут здесь). **По готовности перенос репо в первый аккаунт `darakcheeff`** (`POST /repos/{o}/{r}/transfer` или push).
  - Донор-4A: `darakcheeff/AyuGram4A` (тег **`donor-4a`**, проверен 04.10.2026 — токен достаточен) + upstream `AyuGram/AyuGram4A` (ветка `rewrite`, публичный). Локальный `/tmp/opencode/4a_src` **утрачен** — восстановить shallow-клоном по тегу перед Фазой 3 (в `/tmp/opencode`, не в `/home/tech`).
  - Локальная рабочая копия: **`/home/tech/auegram_android`** (полная история, отсюда коммиты/push). `/home/tech/ayugram` (2.0G, shallow + 106) — донор-объектов `phase2-line`; после пуша — кандидат на удаление (экономия диска, всего 11G свободно).
  - Токен: один активный (Anselmnn, classic `repo`+`workflow`, проверен 04.10.2026: git-refs + Actions API 200) — `/tmp/opencode/.gh_token2`. **Ротация отложена пользователем 04.10.2026**; URL-схемы с токеном в local-конфигах не коммитим и не публикуем.
- `gradle.properties`: `APP_PACKAGE=com.tech.ayugram`, имя AyuGram. **APP_ID/APP_HASH (уточнено 04.10.2026):** в DrKLO-базе они **не** в gradle/local.properties, а в `TMessagesProj/src/main/java/org/telegram/messenger/BuildVars.java` (строка ~29/30); в пин-коммите = DrKLO-свои `APP_ID = 4` / `APP_HASH = 014b35b6184100b085b0d0572f9b5103`. Заменить на официальные `6` / `eb06d4abfb49dc3eeb1aeb98ae0f581e` (R9). `local.properties` (gitignored) нужен в CI только для `BETA_*_URL` (buildConfig) — создавать пустым.
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

### CI-архитектура (решение 22.09.2026, подход из darakcheeff/AyuGramDesktop; поправлена под дерево DrKLO 04.10.2026)
- **Состояние (04.10.2026):** репо `Anselmnn/AyuGram`, рабочая ветка **`plan-v2`** = DrKLO 12.10.3 (пин `9552e554`) + `PLAN.md` (v2.1) + `MOBILE_SPECIFICATION.md` + `.github/workflows/build.yml` (поправленный). Черновик workflow от синтетического пути (модуль `:TMessagesProj:assembleDebug`, системный Gradle 8.5) к дереву DrKLO **не применим** — заменён.
- **Workflow (`.github/workflows/build.yml`, ветка `plan-v2`):** ubuntu-latest, timeout 90 мин; триггеры `workflow_dispatch` + push на `plan-v2`; concurrency per-ref.
  - **Checkout:** `actions/checkout@v4`, `fetch-depth: 0` + **`submodules: recursive`** — 13 сабмодулей в пин-коммите (tlottie, libvpx, dav1d, FFmpeg, jlatexmath-android, ogg, opusfile, opus, libyuv, openh264, boringssl, tdlib/td, Arseny271/media; все публичные, проверены 04.10.2026). Без сабмодулей нативка не соберётся.
  - **Restore Git Timestamps** (python-скрипт, как в Desktop-workflow): mtime каждого файла = время последнего коммита, коснувшегося файла.
  - **JDK 17** (temurin) — по Dockerfile DrKLO `gradle:8.13-jdk17`.
  - **SDK:** platform-36, build-tools 36.0.0, platform-tools, **NDK 27.2.12479018**, **CMake 3.22.1** (версии = `TMessagesProj/build.gradle` + Dockerfile DrKLO), cmdline-tools 15859902, лицензии. Кеш `~/android-sdk`.
  - **Gradle — wrapper репо (8.13), `./gradlew`** (не системный 8.5 — AGP 8.13.2 требует Gradle ≥ 8.13). Кеш `~/.gradle` (wrapper dists + caches), key по hash `gradle-wrapper.properties`.
  - **ccache** для NDK через env-лаунчеры (`CMAKE_{C,CXX,ASM}_COMPILER_LAUNCHER=ccache`), `CCACHE_MAXSIZE=8G`; кеш `~/.ccache`, save `if: always()`.
  - **Сборка:** `./gradlew :TMessagesProj_App:assembleDebug --stacktrace` — APK-модуль в DrKLO = **`TMessagesProj_App`** (`TMessagesProj` — библиотека). Debug buildType несёт `applicationIdSuffix ".beta"` → после Фазы 0 id = `com.tech.ayugram.beta`.
  - **Подпись debug:** база (`TMessagesProj_App/build.gradle`) подписывает debug **публичным `TMessagesProj/config/release.keystore`** (в дереве, пароль в `gradle.properties`) — своих debug-ключей в базе нет. Для Фаз 0–7 приемлемо (ключ публичный); свой keystore — только для release, Фаза 8 (вариант A).
  - **local.properties:** не создаём (в CI не нужен — `getProps()` возвращает "" при отсутствии файла; APP_ID/APP_HASH берутся из `BuildVars.java`, не из props — проверено 04.10.2026; SDK — через env `ANDROID_HOME`).
  - **Артефакт:** APK из `TMessagesProj_App/build/outputs/apk/debug/`, retention 14 дн.; `ccache -s` в лог для контроля хит-рейта.
- **Все сборки — только в GitHub Actions** (`ubuntu-latest`, workflow `.github/workflows/build.yml`). Локальная VM **без** JDK/SDK/Gradle и без локальных сборок — только клон исходников для правок/коммитов (`/home/tech/auegram_android`); не захламляем VM.
- **Инкрементальная пересборка (только изменившиеся файлы)** — три слоя:
  1. **Restore Git Timestamps** (перенос скрипта из Desktop-workflow): после checkout (`fetch-depth: 0`) mtime каждого файла = время его последнего коммита (python, `git log --name-only --pretty=format:%ct`). Неизменённые файлы «старее» артефактов из кеша → CMake/NDK не пересобирает их; изменившиеся в новом коммите — старше → пересобираются. Без этого трюка каждый checkout ставит mtime=now → полный ребилд нативки.
  2. **actions/cache**: `~/.gradle` (зависимости, wrapper, NDK-кач, build-cache) + `$ANDROID_HOME` (platform-36, build-tools, NDK 27.2 — ~2.5G) + `~/.ccache` (ccache-кеш NDK C/C++/ASM) + состояние нативки/gradle (`TMessagesProj/.cxx`, `TMessagesProj/build`, `TMessagesProj_App/.cxx`, `TMessagesProj_App/build`).
  3. **ccache для NDK** без правки исходников: env `CMAKE_C_COMPILER_LAUNCHER=ccache`, `CMAKE_CXX_COMPILER_LAUNCHER=ccache`, `CMAKE_ASM_COMPILER_LAUNCHER=ccache` (CMake читает их из процесса-вызывающего — задокументировано) + `org.gradle.caching=true` в `gradle.properties` (build-cache: неизменённые gradle-задачи — FROM-CACHE).
- **Цикл устранения падений:** push → мониторинг `runs`/`jobs`/`logs` через GitHub REST API (`GET /repos/Anselmnn/AyuGram/actions/runs` — токен имеет доступ, проверено 04.10.2026) → фикс в коде → push → повтор. Без участия пользователя. `ccache -s` и `gradle --scan`-лог в каждом ране для контроля хит-рейта.
- Базовая сборка = `:TMessagesProj_App:assembleDebug` (debug-подпись из публичного keystore репо). Release-джоб с подписью из Secrets (вариант A) добавляется в Фазе 8.

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

- `PLAN.md` (этот файл) + `MOBILE_SPECIFICATION.md` — в репо (ветка `plan-v2`).
- **DrKLO 12.10.3 (целевая база):** remote `drklo` (`DrKLO/Telegram`) в `/home/tech/auegram_android`, пин `9552e554`; полная история также на GitHub (`Anselmnn/AyuGram`, линейка ветки `plan-v2`). Локальный `/tmp/opencode/drklo_master` — утрачен.
- **DrKLO 9.6.6 (точка развилки 4A):** тег `release-9.6.6_3362` в том же remote (зафетчен 04.10.2026); локальный `/tmp/opencode/drklo_base` — утрачен.
- **AyuGram4A (донор):** `darakcheeff/AyuGram4A` @ тег `donor-4a` (доступ по токену `/tmp/opencode/.gh_token2`, проверено 04.10.2026) + публичный `AyuGram/AyuGram4A` @ `rewrite`. Локальный `/tmp/opencode/4a_src` — утрачен; восстановить shallow-клоном по тегу перед Фазой 3.
- `/tmp/opencode/diff4a.txt` — **утрачен** (/tmp чистили); перегенерировать из восстановленных клонов (diff 4A vs 9.6.6; исходные цифры: 293 added / 221 removed / 538 modified, вкл. extera).
- `phase2-line` (GitHub) + `/tmp/opencode/phase2-line.patch` (140M) — экспортированная отброшенная ветка (106 коммитов, 01–03.10); каноничное хранение = GitHub, патч можно удалить.
- Проверено в master: `GcmPushListenerService` — есть; `getReplies` в TLRPC — есть; `stories_*` — 292 ссылок; `TL_message` констр. `0x7600b9d3` (стр. 58254); `paid_suggested_post_stars/ton` (MessageObject 11675/13389); `ChromecastFileServer` порт 61578; `WebProxyTransport` ServerSocket loopback; contact-sync — 0 вызовов; `AppStartReceiver` — boot; `TL_account` split-модуль (44 класса: WallPapers/ChatThemes/SavedRingtones/business/passkey); `AccountInstance` — multi-account facade; `NotificationsSoundActivity` — смешанный пикер (RingtoneManager + облачные).

## 13. Открытые вопросы

- **Решено 22.09.2026:** `READ_CLIPBOARD` — **рез** (подробность в §6; AOSP-верификация: ни системная вставка, ни вставка из клавиатуры, ни кнопки Telegram не ломаются).
- **Внешние (не план):** Signing key — решение принято (вариант A, §R6/Фаза 8): пользователю нужно сгенерировать keystore и заложить 4 секрета в GitHub до Фазы 8 (не блокирует Фазы 0–7).
- **Отложено (решение 04.10.2026):** ротация GitHub-токена (токен попадал в локальные git-remote URL и логи сессий). До ротации: токен живёт только в `/tmp/opencode/.gh_token2` + в local-конфигах репо (не в git-дереве). После ротации — зачистить URL в обоих локальных репо.
- Старт Фазы 0 — **04.10.2026** (чекпоинт 0a — чистая базовая сборка, см. §14.4).

План v2.1. Старт для Фазы 0 — дан.

## 14. Возврат к исходному плану — журнал 04.10.2026 (v2.1)

### 14.1 Что случилось 01–03.10 (почему возврат)
Сессии предыдущего агента (01–03.10) отошли от плана: вместо rebase на DrKLO master была написана «синтетическая» реализация с нуля (199 файлов `com.tech.ayugram` + ~60 Google/Stripe-стубов, ядра Telegram нет, `org/telegram/*` — пустые каталоги). Последняя сессия дегенерировала в цикл повторов (лог 641K, 23 уникальные строки), без единого tool-вызова; сборка застряла на «package R does not exist» (причина: отсутствующие `import com.tech.ayugram.R` в UI-классах). Решение пользователя 04.10.2026: **путь отбрасывается, работаем по плану v2 (вариант A: полная история DrKLO на GitHub + ветка `plan-v2`)**.

### 14.2 Действия 04.10.2026 (до старта Фазы 0)
1. **База верифицирована:** `/home/tech/ayugram` @ `9552e554` (DrKLO 12.10.3/7089) — дерево побайтово = upstream (коммит доставлен по SHA из `DrKLO/Telegram`, tree-hash сравнен).
2. Полная история DrKLO (612 коммитов, unshallow) зафетчена в `/home/tech/auegram_android` (remote `drklo`); пин подтверждён предком `drklo/master`.
3. Ветка **`plan-v2`** создана от `9552e554` + коммит артефактов: `PLAN.md` (v2.1), `MOBILE_SPECIFICATION.md`, `.github/workflows/build.yml` (поправленный под дерево DrKLO). Пушнута на GitHub.
4. Отброшенная ветка (106 коммитов) выгружена на GitHub как **`phase2-line`** (архив) + локальный патч `/tmp/opencode/phase2-line.patch` (140M, можно удалить — каноника на GitHub).
5. Доноры проверены: `darakcheeff/AyuGram4A` @ `donor-4a` — доступен по текущему токену; `AyuGram/AyuGram4A` @ `rewrite` — публичный.
6. Toolchain зафиксирован по **базе DrKLO** (не по синтетическому черновику): Gradle **8.13** (wrapper), AGP **8.13.2**, JDK **17**, compileSdk **36**, NDK **27.2.12479018**, CMake **3.22.1**, 13 публичных сабмодулей.
7. Расхождения плана v2 с реальной базой (учтены в тексте): APP_ID/APP_HASH в `BuildVars.java` (в базе = 4 / `014b…`); APK-модуль CI = `TMessagesProj_App` (debug = суффикс `.beta`); debug-сборка базы подписана публичным `config/release.keystore` (отдельный debug.keystore не нужен); в дереве DrKLO лежат его `google-services.json` (5 шт.) — режутся в Фазе 0 (R5).

### 14.3 Хранение и сборки — правила
- **GitHub = основное хранилище.** Вся каноника проекта (ветки, артефакты, история) — в `Anselmnn/AyuGram`. Локально: рабочая копия (1) + донор (1, удаляется после использования).
- **Сборки — только GitHub Actions.** На VM нет JDK/SDK/Gradle; никаких локальных `./gradlew`, `build/`, `.gradle/`.
- **Не захламлять диск** (свободно ~11G): временные клоны (4a_src, drklo_base) — в `/tmp/opencode`, удалять после использования; патч phase2-линейки — удалить после подтверждения `phase2-line` на GitHub.

### 14.4 Фаза 0 — чекпоинты (уточнение к §9, Фаза 0)
- **0a — «чистая база»:** `plan-v2` + CI, **без кодовых правок** → `:TMessagesProj_App:assembleDebug` зелёный в GitHub Actions. Проверяет toolchain + сабмодули + подпись + кеш. Холодный первый рун: 60–90 мин (ожидается).
- **0b — Фаза 0 по существу:** `APP_PACKAGE=com.tech.ayugram` (gradle.properties); `BuildVars` → официальные APP_ID/APP_HASH (R9); срез зависимостей (T, C1–C3, E6, R19–R26); google-services (plugin + 5 json) — рез (R5); AndroidManifest — финальный набор по §6; мёртвый код (billing/wallet/updater/GSA/car/wear/contact-sync). Чекпоинт: `assembleDebug` зелёный (debug id = `com.tech.ayugram.beta`) + `aapt dump permissions` = §6.

### 14.5 Инцидент CI: sdkmanager (04.10.2026)
- **Симптомы:** два первых рана `plan-v2` (37162887512, 37163288277) падают на шаге установки SDK: `Could not find or load main class com.android.sdklib.tool.sdkmanager.SdkManagerCli` + `ClassNotFoundException`. Тот же шаг проходил в ранах `master` 03.10 (run 37093280845) с тем же URL, тем же runner-image (`ubuntu-24.04/20260927.320.1`) и тем же JDK 17.0.20.
- **Диагностика zip `commandlinetools-linux-15859902_latest.zip`** (скачан локально, 181 833 628 байт, sha256 `4e4c464f…eedf583`, `Pkg.Revision=22.0`): артефакт **цел** — CRC всех записей OK, 141/141 файлов извлекаются (unzip и python zipfile дают побайтово одинаковое дерево), все 74 записи `Class-Path` из `lib/sdkmanager-classpath.jar` присутствуют в архиве, класс `SdkManagerCli` лежит в `lib/sdklib/libsdkmanager_lib.jar` и `tools.sdklib.jar`. Вывод: байты на runner'е = эталон (размер + CRC), причина — в поведении runner'а (unzip 6.0 Ubuntu vs эталонный; и/или резолв Class-Path из MANIFEST) — не в zip.
- **Фикс (workflow):** (1) самовосстановление распаковки — при числе файлов ≠ 141 повтор через `python zipfile`; (2) диагностика в логе (версии unzip/java, файлов на диске, sha256 загрузки); (3) `SdkManagerCli` запускается **напрямую** через `java -cp` с явным classpath по всем jar в `lib/` — без `bin/sdkmanager`-скрипта и без MANIFEST `Class-Path` (устойчиво к обоим кандидатам причины).
- **Статус:** **шаг установки SDK пройден** (run 37164564897, step 7 = success; диагностика в логе показала целое дерево). Сборка ушла дальше и упала на другой, **корневой** причине (см. ниже).
- **Ошибка №2 (run 37164564897, step 12 Build):** `RuntimeException: Several environment variables and/or system properties contain different paths to the SDK`: `ANDROID_HOME=/home/runner/work/AyuGram/AyuGram/$HOME/android-sdk` vs `ANDROID_SDK_ROOT=/usr/local/lib/android/sdk`. Корень: `$HOME` в значениях `env:` workflow **не расширяется** — ушёл в env процесса литералом (относительный путь), а `ANDROID_SDK_ROOT` задан runner-образом; AGP SdkLocator требует совпадения всех источников. Ран `master` 03.10 от этого не страдал: там `ANDROID_HOME` экспортится shell'ом внутри run-шага, а AGP видел только `ANDROID_SDK_ROOT`. **Фикс:** job-level `env:` убран; новый шаг «Export SDK env» после Setup JDK пишет в `$GITHUB_ENV` оба переменные = `$HOME/android-sdk` (расширено shell'ом) — равные значения, один источник.
- **Статус:** ждём подтверждение новым раном; 0a считается незакрытым до зелёной полной сборки.
- **Инцидент закрыт: run 37165012141 (HEAD `ae6b13c37`) = success**, 13 мин (кеш SDK + Gradle/CCache заработал; SDK встал в `/home/runner/android-sdk`, оба env равны). **Чекпоинт 0a пройден 04.10.2026.**

### 14.6 Фаза 0b — ход работ (04.10.2026)
- **0b-1 идентичность — ПРОЙДЕН**: коммит `d2a9ff41c`, ран 37165781223 = success. `APP_PACKAGE=com.tech.ayugram` (gradle.properties:18), `BuildVars` APP_ID `4→6` / APP_HASH `eb06d4abfb49dc3eeb1aeb98ae0f581e` (R9), клиенты `com.tech.ayugram[.beta]` в `TMessagesProj_App/google-services.json` + `TMessagesProj/google-services.json` (копия блока `org.telegram.messenger.beta`: тот же проект `tmessages2`, тот же api_key; mobilesdk_app_id дублируется — FCM аутентифицируется api_key+package). Core остаётся `org.telegram.*`.
- **0b-2 изолированные резы** (car/wear/GSA-appindexing/datatransport) — применён: 9 файлов удалено (car×4, wear×3, `libgsaverification-client.aar`, `automotive_app_desc.xml`), manifest (wear receiver/service + закомментированный car-блок), NotificationsController (wear-reply action + unused import/константа), LaunchActivity (appindexing: 3 импорта + 2 блока), gradle (car×2, datatransport, appindexing, wearable, aar-строка). Референс `/tmp/opencode/0b_staging/0b2_spec.md`. Держим: `EXTRA_ACTION_TOKEN` (исп. в removeExtra), `gms.car.media.*` строки в TelegramMediaSession (pure-string media slots), `Status` (исп. 7453).
- **0b-2 фикс (ран 37166873262 = failure):** рез aar `libgsaverification-client` упёрся в двух потребителей в **TMessagesProj_App** (`GoogleVoiceClientActivity`/`GoogleVoiceClientService` → `com.google.android.search.verification.client` — Google-voice «send message» intent). Верификационный grep в 0b-2 не покрывал этот пакет. Фикс: DELETE оба файла + manifest-записи (app-manifest) + `AndroidUtilities.googleVoiceClientService_performAction` (единственный пользователь — удалённый сервис).
- **0b-2 — ПРОЙДЕН**: фикс-коммит `e2d807915`, ран 37167772299 = success. Чекпоинт 0b-2 закрыт 04.10.2026.
- **0b-3 billing/wallet/auth** — референс `/tmp/opencode/0b_staging/0b3_spec.md` (готов, 04.10.2026). НАЙДЕНА НА РЕВЬЮ: `ConnectionsManager.onIntegrityCheckClassic` — JNI-вход (C++ `TgNetWrapper.cpp:687` вызывает `CallStaticVoidMethod` с фиксированной сигнатурой), тело → немедленный `native_receivedIntegrityCheckClassic(..., "PLAYINTEGRITY_FAILED_GSA_REMOVED")` (C++ не трогаем, @Keep и сигнатура на месте). Финальные решения: `BillingController` **ужать** до formatCurrency/getCurrencyExp/extractCurrencyExp (~60 строк, 33 файла ссылок — ~24 формат-only не трогаются); `DELETE BillingUtilities + PaymentFormActivity + SmsReceiver`; **`IS_BILLING_UNAVAILABLE = true`** (канонический форк-свитч: premium/gift-входы деградируют автоматически через готовый `PremiumNotAvailableBottomSheet`); `useInvoiceBilling()` → `return true` (41 call-site), `SAFETYNET_KEY = ""`; LoginActivity: Firebase-SMS-блок 1770-1890 → fallback resend, Google-блоки SetupEmail/EmailCodeView — рез (ручной email путь держим); gradle: рез auth/wallet/stripe/billing/integrity/safetynet + **exclude на credentials-play-services-auth** (POM подтвердил транзитивный `play-services-auth:21.1.1` + identity-credentials/auth-blockstore/fido/googleid — без exclude GSA остался бы в APK); манифест: BILLING/SmsReceiver/wallet.api.enabled; новая строка `PaymentUnavailable`; TL_payments — отдельный шаг после (держать getPaymentReceipt + paymentReceiptStars до него).
- **TL_payments/TL_fragment из TLRPC** — отдельный шаг после 0b-3 (45 файлов ссылок).
