import java.util.*;

public class Stimulation {
    private ArrayList<Block> Memory = new ArrayList<>();

    public void firstFit(String pID, int pSize) {
        for (Block block : Memory) {
            if (!block.allocated && block.blockSize >= pSize) {
                block.allocated = true;
                block.processID = pID;
                block.intFragmentation = block.blockSize - pSize;
                System.out.println(pID + " Allocated at address " + block.startAddress + ", and the internal fragmentation is " + block.intFragmentation);
                return;
            }
        }
        System.out.println("Memory allocation has failed.");
    }

    public void bestFit(String pID, int pSize) {
        Block bestFitBlock = null;
        for (Block block : Memory) {
            if (!block.allocated && block.blockSize >= pSize) {
                if (bestFitBlock == null || block.blockSize < bestFitBlock.blockSize) {
                    bestFitBlock = block;
                }
            }
        }
        if (bestFitBlock != null) {
            bestFitBlock.allocated = true;
            bestFitBlock.processID = pID;
            bestFitBlock.intFragmentation = bestFitBlock.blockSize - pSize;
            System.out.println(pID + " Allocated at address " + bestFitBlock.startAddress + ", and the internal fragmentation is " + bestFitBlock.intFragmentation);
        } else {
            System.out.println("Memory allocation has failed.");
        }
    }

    public void worstFit(String pID, int pSize) {
        Block worstFitBlock = null;
        for (Block block : Memory) {
            if (!block.allocated && block.blockSize >= pSize) {
                if (worstFitBlock == null || block.blockSize > worstFitBlock.blockSize) {
                    worstFitBlock = block;
                }
            }
        }
        if (worstFitBlock != null) {
            worstFitBlock.allocated = true;
            worstFitBlock.processID = pID;
            worstFitBlock.intFragmentation = worstFitBlock.blockSize - pSize;
            System.out.println(pID + " Allocated at address " + worstFitBlock.startAddress + ", and the internal fragmentation is " + worstFitBlock.intFragmentation);
        } else {
            System.out.println("Memory allocation has failed.");
        }
    }

    public void deallocation(String pID) {
        for (Block block : Memory) {
            if (block.allocated && block.processID.equals(pID)) {
                block.allocated = false;
                block.processID = null;
                block.intFragmentation = 0;
                System.out.println(pID + " is deallocated successfully.");
                return;
            }
        }
        System.out.println("Process not found. Cannot be deallocated.");
    }

    public void printMemoryStatus() {
        System.out.println("==========================================================================");
        System.out.println("Block#   Size   Start-End   Status     ProcessID   InternalFragmentation");
        System.out.println("==========================================================================");
        for (int i = 0; i < Memory.size(); i++) {
            Block block = Memory.get(i);
            System.out.printf("Block%-3d  %-5d  %-3d-%-3d  %-10s  %-10s  %-5d\n", i, block.blockSize, block.startAddress, block.endAddress,
                    block.allocated ? "Allocated" : "Free", block.processID != null ? block.processID : "Null", block.intFragmentation);
        }
        System.out.println("==========================================================================");
    }

    public void printMemory() {
        System.out.println("Memory blocks:");
        System.out.println("==================================================================");
        System.out.println("Block#   Size   Start-End   Status");
        System.out.println("==================================================================");
        for (int i = 0; i < Memory.size(); i++) {
            Block block = Memory.get(i);
            System.out.printf("Block%-3d  %-5d  %-3d-%-3d  %-10s\n", i, block.blockSize, block.startAddress, block.endAddress,
                    block.allocated ? "Allocated" : "Free");
        }
        System.out.println("==================================================================");
    }

    public static void main(String args[]) {
        int startAdd = 0;
        Stimulation sim = new Stimulation();
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the total number of blocks: ");
        int M = input.nextInt();
        for (int i = 0; i < M; i++) {
            System.out.println("Enter the size of each block in KB: ");
            int size = input.nextInt();
            sim.Memory.add(new Block(startAdd, size));
            startAdd += size;
        }

        System.out.println("Enter allocation strategy (1 for first-fit, 2 for best-fit, 3 for worst-fit): ");
        int strategy = input.nextInt();
        System.out.println("Memory blocks are created...");
        sim.printMemory();

        while (true) {
            System.out.println("\n1) Allocate memory blocks");
            System.out.println("2) Deallocate memory blocks");
            System.out.println("3) Print memory report");
            System.out.println("4) Exit");
            System.out.println("==========================================================================");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter the process ID and size of process: ");
                    String pID = input.next();
                    int pSize = input.nextInt();
                    switch (strategy) {
                        case 1:
                            sim.firstFit(pID, pSize);
                            break;
                        case 2:
                            sim.bestFit(pID, pSize);
                            break;
                        case 3:
                            sim.worstFit(pID, pSize);
                            break;
                        default:
                            System.out.println("\nInvalid choice. Try Again.");
                    }
                    break;
                case 2:
                    System.out.print("\nEnter the process ID to deallocate: ");
                    String pid = input.next();
                    sim.deallocation(pid);
                    break;
                case 3:
                    sim.printMemoryStatus();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try Again.");
            }
        }
    }
}
