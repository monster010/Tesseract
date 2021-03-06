package com.supermartijn642.tesseract.screen;

import com.supermartijn642.tesseract.RedstoneState;
import com.supermartijn642.tesseract.Tesseract;
import com.supermartijn642.tesseract.TesseractTile;
import com.supermartijn642.tesseract.packets.PacketCycleRedstoneState;
import net.minecraft.util.math.BlockPos;

/**
 * Created 7/5/2020 by SuperMartijn642
 */
public class RedstoneButton extends CycleButton {

    public RedstoneState state;
    private BlockPos pos;

    public RedstoneButton(int buttonId, int x, int y){
        super(buttonId, x, y, 60);
    }

    public void update(TesseractTile tile){
        this.state = tile.getRedstoneState();
        this.pos = tile.getPos();
    }

    @Override
    protected int getCycleIndex(){
        return this.state == RedstoneState.DISABLED ? 0 : this.state == RedstoneState.HIGH ? 1 : 2;
    }

    @Override
    public void onPress(){
        if(this.pos != null)
            Tesseract.channel.sendToServer(new PacketCycleRedstoneState(this.pos));
    }
}
