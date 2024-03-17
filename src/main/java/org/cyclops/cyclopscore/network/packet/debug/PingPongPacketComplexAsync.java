package org.cyclops.cyclopscore.network.packet.debug;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.cyclops.cyclopscore.Reference;
import org.cyclops.cyclopscore.network.CodecField;

/**
 * Debug ping pong packet
 * @author rubensworks
 *
 */
public class PingPongPacketComplexAsync extends PingPongPacketAsync {

    public static final ResourceLocation ID = new ResourceLocation(Reference.MOD_ID, "ping_pong_complex_async");

    @CodecField
    protected String string1;
    @CodecField
    protected String string2;

    /**
     * Empty packet.
     */
    public PingPongPacketComplexAsync() {
        super(ID);
    }

    public PingPongPacketComplexAsync(int remaining, String string1, String string2) {
        super(ID, remaining);
        this.string1 = string1;
        this.string2 = string2;
    }

    public PingPongPacketComplexAsync(ResourceLocation id) {
        super(id);
    }

    public PingPongPacketComplexAsync(ResourceLocation id, int remaining, String string1, String string2) {
        super(id, remaining);
        this.string1 = string1;
        this.string2 = string2;
    }

    protected PingPongPacketAsync newPacket() {
        return new PingPongPacketComplexAsync(remaining - 1, string1, string2);
    }

    @Override
    protected void log(Player player, String message) {
        super.log(player, message);
        super.log(player, String.format("[EXTRA] '%s' and '%s' ", string1, string2));
    }
}
