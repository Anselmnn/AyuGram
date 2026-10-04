package org.telegram.tgnet.tl;

import org.telegram.messenger.DialogObject;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

public class TL_stars {

    public static abstract class StarsAmount extends TLObject {
        public long amount;
        public int nanos;

        public static StarsAmount TLdeserialize(InputSerializedData stream, int constructor, boolean exception) {
            StarsAmount result = null;
            switch (constructor) {
                case TL_starsAmount.constructor:
                    result = new TL_starsAmount();
                    break;
                case TL_starsTonAmount.constructor:
                    result = new TL_starsTonAmount();
                    break;
            }
            return TLdeserialize(StarsAmount.class, result, stream, constructor, exception);
        }

        public static StarsAmount ofStars(long stars) {
            TL_starsAmount starsAmount = new TL_starsAmount();
            starsAmount.amount = stars;
            return starsAmount;
        }

        public boolean equals(TL_stars.StarsAmount amount) {
            if (amount == null) return false;
            return this.amount == amount.amount && this.nanos == amount.nanos;
        }

        public double toDouble() {
            return amount + (double) nanos / 1_000_000_000L;
        }

        public boolean positive() {
            return amount == 0 ? nanos > 0 : amount > 0;
        }

        public boolean negative() {
            return amount == 0 ? nanos < 0 : amount < 0;
        }
    }
    public static class TL_starsTonAmount extends StarsAmount {
        public static final int constructor = 0x74aee3e0;

        public void readParams(InputSerializedData stream, boolean exception) {
            amount = stream.readInt64(exception);
        }

        @Override
        public void serializeToStream(OutputSerializedData stream) {
            stream.writeInt32(constructor);
            stream.writeInt64(amount);
        }
    }
    public static class TL_starsAmount extends StarsAmount {
        public static final int constructor = 0xbbb6b4a3;

        public void readParams(InputSerializedData stream, boolean exception) {
            amount = stream.readInt64(exception);
            nanos = stream.readInt32(exception);
        }

        @Override
        public void serializeToStream(OutputSerializedData stream) {
            stream.writeInt32(constructor);
            stream.writeInt64(amount);
            stream.writeInt32(nanos);
        }
    }

    public static class TL_starsSubscriptionPricing extends TLObject {
        public static final int constructor = 0x5416d58;

        public int period;
        public long amount;

        public static TL_starsSubscriptionPricing TLdeserialize(InputSerializedData stream, int constructor, boolean exception) {
            final TL_starsSubscriptionPricing result = TL_starsSubscriptionPricing.constructor != constructor ? null : new TL_starsSubscriptionPricing();
            return TLdeserialize(TL_starsSubscriptionPricing.class, result, stream, constructor, exception);
        }

        @Override
        public void readParams(InputSerializedData stream, boolean exception) {
            period = stream.readInt32(exception);
            amount = stream.readInt64(exception);
        }

        @Override
        public void serializeToStream(OutputSerializedData stream) {
            stream.writeInt32(constructor);
            stream.writeInt32(period);
            stream.writeInt64(amount);
        }
    }

    public static class PaidReactionPrivacy extends TLObject {
        public TLRPC.InputPeer peer;

        public static PaidReactionPrivacy TLdeserialize(InputSerializedData stream, int constructor, boolean exception) {
            PaidReactionPrivacy result = null;
            switch (constructor) {
                case paidReactionPrivacyDefault.constructor:
                    result = new paidReactionPrivacyDefault();
                    break;
                case paidReactionPrivacyAnonymous.constructor:
                    result = new paidReactionPrivacyAnonymous();
                    break;
                case paidReactionPrivacyPeer.constructor:
                    result = new paidReactionPrivacyPeer();
                    break;
            }
            return TLdeserialize(PaidReactionPrivacy.class, result, stream, constructor, exception);
        }

        public long getDialogId() {
            if (this instanceof paidReactionPrivacyDefault)
                return 0;
            if (this instanceof paidReactionPrivacyAnonymous)
                return UserObject.ANONYMOUS;
            if (this instanceof paidReactionPrivacyPeer)
                return DialogObject.getPeerDialogId(peer);
            return 0;
        }
    }
    public static class paidReactionPrivacyDefault extends PaidReactionPrivacy {
        public static final int constructor = 0x206ad49e;

        @Override
        public void serializeToStream(OutputSerializedData stream) {
            stream.writeInt32(constructor);
        }
    }
    public static class paidReactionPrivacyAnonymous extends PaidReactionPrivacy {
        public static final int constructor = 0x1f0c1ad9;

        @Override
        public void serializeToStream(OutputSerializedData stream) {
            stream.writeInt32(constructor);
        }
    }
    public static class paidReactionPrivacyPeer extends PaidReactionPrivacy {
        public static final int constructor = 0xdc6cfcf0;

        @Override
        public void readParams(InputSerializedData stream, boolean exception) {
            peer = TLRPC.InputPeer.TLdeserialize(stream, stream.readInt32(exception), exception);
        }

        @Override
        public void serializeToStream(OutputSerializedData stream) {
            stream.writeInt32(constructor);
            peer.serializeToStream(stream);
        }
    }

    public static class Tl_starsRating extends TLObject {
        public static final int constructor = 0x1b0e4f07;
        public int flags;
        public int level;
        public long current_level_stars;
        public long stars;
        public long next_level_stars;

        public static Tl_starsRating TLdeserialize(InputSerializedData stream, int constructor, boolean exception) {
            final Tl_starsRating result = Tl_starsRating.constructor != constructor ? null : new Tl_starsRating();
            return TLdeserialize(Tl_starsRating.class, result, stream, constructor, exception);
        }

        @Override
        public void readParams(InputSerializedData stream, boolean exception) {
            flags = stream.readInt32(exception);
            level = stream.readInt32(exception);
            current_level_stars = stream.readInt64(exception);
            stars = stream.readInt64(exception);
            if (hasFlag(flags, FLAG_0)) {
                next_level_stars = stream.readInt64(exception);
            }
        }

        @Override
        public void serializeToStream(OutputSerializedData stream) {
            stream.writeInt32(constructor);
            stream.writeInt32(flags);
            stream.writeInt32(level);
            stream.writeInt64(current_level_stars);
            stream.writeInt64(stars);
            if (hasFlag(flags, FLAG_0)) {
                stream.writeInt64(next_level_stars);
            }
        }
    }
}
