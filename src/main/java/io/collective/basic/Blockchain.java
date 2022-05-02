package io.collective.basic;

import java.security.NoSuchAlgorithmException;

public class Blockchain {

    int size = 0;
    Block[] blocks = new Block[10];
    public boolean isEmpty() {
        return size==0;
    }

    public void add(Block block) {
        blocks[size] = block;
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isValid() throws NoSuchAlgorithmException {
        for(int i=0;i<size;i++){
            if(!isMined(this.blocks[i])){
                return false;
            }
            if((i<size-1)&&!(blocks[i+1].getPreviousHash().equals(blocks[i].getHash()))){
                return false;
            }
        }

        return true;
    }

    /// Supporting functions that you'll need.

    public static Block mine(Block block) throws NoSuchAlgorithmException {
        Block mined = new Block(block.getPreviousHash(), block.getTimestamp(), block.getNonce());

        while (!isMined(mined)) {
            mined = new Block(mined.getPreviousHash(), mined.getTimestamp(), mined.getNonce() + 1);
        }
        return mined;
    }

    public static boolean isMined(Block minedBlock) throws NoSuchAlgorithmException {
        return minedBlock.getHash().startsWith("00")&&minedBlock.getHash().equals(minedBlock.calculatedHash());
    }
}