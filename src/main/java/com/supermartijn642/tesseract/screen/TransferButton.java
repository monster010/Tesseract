package com.supermartijn642.tesseract.screen;

import com.supermartijn642.tesseract.EnumChannelType;
import com.supermartijn642.tesseract.Tesseract;
import com.supermartijn642.tesseract.TesseractTile;
import com.supermartijn642.tesseract.TransferState;
import com.supermartijn642.tesseract.packets.PacketCycleTransferState;
import net.minecraft.util.math.BlockPos;

/**
 * Created 7/6/2020 by SuperMartijn642
 */
public class TransferButton extends CycleButton {

    public TransferState state;
    private BlockPos pos;
    private EnumChannelType type;

    public TransferButton(int buttonId, int x, int y){
        super(buttonId, x, y, 0);
    }

    public void update(TesseractTile tile, EnumChannelType type){
        this.state = tile.getTransferState(type);
        this.pos = tile.getPos();
        this.type = type;
    }

    @Override
    protected int getCycleIndex(){
        return this.state == TransferState.RECEIVE ? 0 : this.state == TransferState.SEND ? 1 : 2;
    }

    @Override
    public void onPress(){
        if(this.pos != null)
            Tesseract.channel.sendToServer(new PacketCycleTransferState(this.pos, this.type));
    }

}
