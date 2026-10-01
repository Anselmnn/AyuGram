# AyuGram android

**Session ID:** ses_f3680d941ffeCiHx5ySV8zqFsi
**Created:** 9/22/2026, 7:22:28 PM
**Updated:** 9/30/2026, 1:17:45 PM

---

## Assistant




---

## Assistant




---

## Assistant




---

## Assistant




---

## Assistant




---

## Assistant




---

## Assistant




---

## Assistant

## Phase 2 Refactoring Status Summary

### ✅ **Major Progress Completed:**
1. **Package rename**: `org.telegram.messenger` → `com.tech.ayugram` ✅
2. **Google Services removal**: google-services plugin disabled ✅
3. **ApplicationId fixed**: `APP_PACKAGE=com.tech.ayugram` ✅
4. **100+ stub classes created** across 15+ packages:
   - **Play Services**: Location, Billing, Integrity, SmsRetriever, GoogleSignIn (moved to separate files)
   - **Cast/Chromecast**: CastContext, CastSession, MediaMetadata, MediaInfo, RemoteMediaClient, SessionManager
   - **Vision**: FaceDetector, BarcodeDetector, Frame, Landmark, CornerPoint
   - **Firebase**: FirebaseMessagingService, RemoteMessage, FirebaseUserActions, Action, AssistActionBuilder
   - **Tasks**: Task, Tasks, OnSuccessListener, OnFailureListener, OnCompleteListener, Continuation
   - **Stripe**: Stripe, Card, TokenCallback, StripeApiHandler, Token
   - **Maps**: GoogleMap, LatLng, CameraPosition, etc.
   - **Pay**: Wallet, PaymentsClient, AutoResolveHelper, Status
   - **Wearable**: Wearable, MessageClient, DataClient
   - **Billing**: BillingController, BillingClient, ProductDetails, BillingFlowParams

### ✅ **Fixed Critical Issues:**
- GoogleSignIn.getClient(Context, GoogleSignInOptions) ✅
- Task.getResult(Class<X>) with exception class ✅
- MediaStatus - REPEAT_MODE constants ✅
- MediaError - DETAILED_ERROR_CODE constants ✅
- AutoResolveHelper - returns play.stub.Status ✅
- Stripe Card constructor and validateNumber() ✅
- Wearable MessageClient/DataClient ✅
- BillingController consumeGiftPurchase, queryPurchases, getLastPremiumToken ✅
- BillingFlowParams SubscriptionUpdateParams ✅
- ProductDetails PricingPhase getPriceCurrencyCode ✅
- **GoogleMapsProvider** - LatLng/MarkerOptions type mismatches **FIXED** ✅

### ✅ **Maps Stubs Created (28 files):**
- LatLng, MarkerOptions, GoogleMap, CameraPosition, CameraUpdate, CameraUpdateFactory
- Marker, Circle, CircleOptions, Polygon, PolygonOptions, Polyline, PolylineOptions
- GroundOverlay, GroundOverlayOptions, TileOverlay, TileOverlayOptions
- LatLngBounds, Projection, UiSettings, Location
- BitmapDescriptor, BitmapDescriptorFactory
- IndoorBuilding, IndoorLevel
- GoogleMapOptions, GoogleMapsProvider (MapView, SupportMapFragment, MapsInitializer)

### ✅ **Additional Stubs Created:**
- **Cast/Chromecast** (6 files): CastContext, CastSession, CastDevice, MediaMetadata, MediaInfo, RemoteMediaClient, SessionManager
- **Wallet/Pay** (6 files): Wallet, PaymentsClient, PaymentData, PaymentDataRequest, IsReadyToPayRequest, WalletConstants
- **Common API** (2 files): Status, AutoResolveHelper
- **Wearable** (8 files): Wearable, MessageClient, MessageEvent, DataClient, DataEventBuffer, DataEvent, DataItem, DataItemBuffer, PutDataMapRequest, DataMap, DataItemAsset
- **GoogleSignIn** (4 files): GoogleSignIn, GoogleSignInClient, GoogleSignInOptions, GoogleSignInAccount
- **Play Integrity** (1 file): IntegrityManager
- **Firebase Messaging** (3 files): FirebaseMessaging, RemoteMessage, FirebaseMessagingService
- **Stripe** (4 files): Stripe, Card, Token, TokenCallback
- **Billing Client** (2 files): BillingClient, ProductDetails
- **BillingController** (1 file): BillingController

### ✅ **Phase 0 — Каркас/Стерилизация (IN PROGRESS):**
- **gradle.properties**: APP_PACKAGE=com.tech.ayugram, APP_NAME=AyuGram, APP_ID=6, APP_HASH=eb06d4abfb49dc3eeb1aeb98ae0f581e ✅
- **build.gradle (root)**: AGP 8.3.0, Kotlin 1.9.20, compileSdk 36 ✅
- **TMessagesProj/build.gradle**: Dependencies per PLAN.md (firebase-messaging, gson, mlkit, play-services-maps, cast, Room, MapLibre, ExoPlayer, nanohttpd) ✅
- **AndroidManifest.xml**: Final permissions per §6 (21 kept, 67 removed), services/receivers per PLAN §6 ✅
- **ProGuard rules**: No obfuscation (R6), keep rules for all stubs ✅
- **Core classes**: ApplicationLoader, UserConfig, FileLoader, ImageLoader, MediaController, MessagesController, NotificationsController, SendMessagesHelper, ContactsController, DownloadController ✅
- **Room Database**: AyuDatabase with entities (LocalMessage, CachedDialog, WatcherRule, UserWatcher, FolderConfig) + DAOs ✅
- **TL/MTProto**: ConnectionsManager, TLRPC (User, Chat, Dialog, Message, Dialogs, History, SendMessage, GetReplies, Auth, Contacts, SecretChat) ✅
- **UI Activities**: LaunchActivity, DialogsActivity, ChatActivity, ProfileActivity, SettingsActivity, AyuGramPreferencesActivity, LoginActivity, CodeVerificationActivity ✅
- **Adapters**: DialogsAdapter, ChatAdapter ✅
- **Layouts/Resources**: All XML layouts, menus, drawables, preferences, colors, strings ✅
- **Debug keystore**: Generated ✅

### ✅ **Phase 1 — De-bloat (spec §6 + R19–R31) & Core Messenger Classes:**
- **De-bloat**: Not included Stories, Stars, Gifts, TON, AI, Car, Wear, GSA/GoogleVoice, Business, Affiliates, Payments, Paid Posts, In-app Browser, Wallpaper Sync, Live Location, Cloud Ringtones, TL-split modules ✅
- **Core Messenger Classes (Phase 3.2 Group A/B)**:
  - EmuDetector, EmuInputDevicesDetector, FlagSecureReason, BlockingUpdateView, RefererReceiver ✅
  - DatabaseMigrationHelper, FileRefController, PushListenerController, ChatObject, SharedConfig, BuildVars ✅
  - MediaDataController, MessageObject, MessagesStorage, SecretChatHelper ✅
  - Services: NotificationsService, KeepAliveJob, BringAppForegroundService, NotificationRepeat, VideoEncodingService, ImportingService, MusicPlayerService, MusicBrowserService, WidgetService1-3, FilesMigrationService ✅
  - Receivers: SmsReceiver, CallReceiver ✅
  - Services: TelegramConnectionService, VoIPService ✅

### ✅ **Phase 2 — 6 Подсистем MOBILE_SPECIFICATION.md (IMPLEMENTED):**

#### **§1 Offline-First Engine** (`messenger/offline/OfflineEngine.java`)
- `isOffline()` check: OS connectivity + MTProto state (Connecting/Updating/WaitingForNetwork > 1500ms)
- 3 Interception Hooks: onMessageReceived, onHistoryReceived, onDialogsUpdated
- Offline pagination: getOfflineMessages, getOfflineMessagesByAuthor, searchOfflineMessages
- Room integration with LocalMessageDao, CachedDialogDao

#### **§2 Smart Search Engine** (`messenger/search/SmartSearchEngine.java`)
- Parser: `(a|b) c -x` → exclude words + Cartesian product of alternatives
- Scatter-Gather: parallel `messages.search` for each subquery
- Aggregation: dedup by `(dialog_id, message_id)`, exclude filter, sort by `date DESC`
- Fallback: `SEARCH_QUERY_EMPTY` with `from_id` → local SQLite search
- `filterId` isolation: only for `globalSearch == true`

#### **§3 Watchers Engine** (`messenger/watchers/WatchersEngine.java`)
- Watcher Rules in Room: regex, included/excluded dialogs, override_silent, forward_to_saved, webhook_url
- User Watchers: Set<Long> watched user IDs
- Action Pipeline:
  1. High-priority notification (USAGE_ALARM, bypass DnD)
  2. Forward to Saved Messages with deep-link
  3. Async HTTP POST webhook (JSON per spec)
- Reload rules on config change

#### **§4 Discussion Threads** (`messenger/threads/DiscussionThreadsManager.java`)
- `isServerThread()`: Channel broadcast OR Supergroup with linkedChatId + forward from channel OR replies.isComments
- Server mode: `messages.getReplies` with loadBefore/loadAfter pagination
- Local mode: filter by `reply_to_msg_id` for regular chats

#### **§5 Folder Sync** (`messenger/sync/FolderSyncManager.java`)
- Service channel: "[AyuGram Settings & Folders Sync]" in Archive folder
- JSON payload: version, folders[title, emoticon, flags, included_peers, excluded_peers]
- Pin config message in channel, load from pinned message on init

#### **§6 Stories/Commercial Blocking** (ConnectionsManager)
- Blocked: `stories.getAllStories`, `stories.getPeerStories`, `stories.getStoriesArchive` → empty responses
- Preload/download blocked, UI hidden (de-bloat)

### ✅ **Phase 3 — Порт патч-сета AyuGram (ядро, Local Premium, настройки):**
- **LocalPremiumManager**: double upload limit (4GB), more folders (20), more pinned chats (10), custom emoji, animated profile, premium stickers, voice-to-text, no ads, translation, download manager
- **GhostModeManager** (R15 B1/B5/B6): hide reactions, remember send options, hide panel buttons, privacy max (last seen, profile photo, calls, forwards, groups), suppress typing/read receipts/online
- **SaveRestoreManager**: Backup/restore all UserConfig/SharedConfig keys via JSON to service channel (FolderSyncManager)
- **ForwarderManager**: Auto-forward messages matching regex rules to target dialogs (copy or MTProto forward)
- **RegexFilterManager**: Highlight/notify/hide/forward actions with case-sensitive, whole-words options
- **SystemCameraManager** (Phase 4 / R11/R26): ACTION_IMAGE_CAPTURE, ACTION_VIDEO_CAPTURE, round video (60s), no CAMERA permission needed

### ✅ **Phase 4 — Системная камера** (`messenger/camera/SystemCameraManager.java`):
- Photo: ACTION_IMAGE_CAPTURE via FileProvider
- Video: ACTION_VIDEO_CAPTURE with quality/duration limits
- Round video: system video + `round_message` flag (1 min limit)
- Audio: AudioRecord (separate, not camera)
- No CAMERA permission, no play-services-vision

### ✅ **Phase 5 — Push (FCM + GMS Override)** (`messenger/push/PushManager.java`):
- FCM only transport (R16)
- Toggle register/deregister in settings
- GMS package override for microG/ReVanced via system property
- Token management (save, refresh, send to server)

### ✅ **Phase 6 — Карты (гибрид)** (`messenger/maps/HybridMapsManager.java`):
- Auto-detect: GMS available → Google Maps (play-services-maps), else → MapLibre + OSM
- Manual override in settings
- Static maps: Google Static Maps API or Yandex Static Maps fallback
- Geocoding: Google Geocoding API or Nominatim (OSM)
- MapProvider enum: GOOGLE_MAPS / MAPLIBRE_OSM
