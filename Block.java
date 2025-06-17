public class Block {
    int blockSize;
    int startAddress;
    int endAddress;
    boolean allocated;
    String processID;
    int intFragmentation;
    
    Block(int start, int size) {
     this.blockSize = size;
     this.startAddress = start;
     this.endAddress = size + start - 1;
     this.allocated = false;
     this.processID = null;
     this.intFragmentation = 0;
    }
  }