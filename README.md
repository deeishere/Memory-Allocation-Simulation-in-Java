# Memory Allocation Simulation in Java

This project simulates dynamic memory allocation strategies in operating systems. It supports **First-Fit**, **Best-Fit**, and **Worst-Fit** allocation techniques. It also provides a way to deallocate memory and track internal fragmentation per process.

## 💡 Features

- Create and manage memory blocks with custom sizes.
- Allocate memory using:
  - **First-Fit**: Assigns the first suitable block.
  - **Best-Fit**: Assigns the smallest suitable block.
  - **Worst-Fit**: Assigns the largest suitable block.
- Deallocate memory by process ID.
- Report on memory status with internal fragmentation info.

## 📂 Files

- `Stimulation.java`: Contains the core logic for memory allocation, deallocation, and reporting.
- `Block.java`: Represents individual memory blocks.

## 🚀 How It Works

1. User defines the number of memory blocks and their sizes.
2. User selects the allocation strategy (First-Fit, Best-Fit, or Worst-Fit).
3. Menu-driven interface allows:
   - Memory allocation for processes.
   - Deallocation of processes.
   - Viewing current memory status.

### Example Output

```text
1) Allocate memory blocks
2) Deallocate memory blocks
3) Print memory report
4) Exit
Enter your choice: 1
Enter the process ID and size of process: P1 100
P1 Allocated at address 0, and the internal fragmentation is 24
