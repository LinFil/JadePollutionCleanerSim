# JADE Multi-Agent Pollution Cleaning Simulation

Welcome to the JADE Multi-Agent Pollution Cleaning Simulation! This project demonstrates a simple multi-agent system (MAS) using the **Java Agent Development Framework (JADE)**. Here, agents collaborate to manage pollution in a grid environment, with a polluter agent creating pollution and cleaner agents competing to clean it up.

This project is my **first experience with JADE**, and I hope it serves as a helpful reference for others who are just beginning with JADE as well!

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Installation and Setup](#installation-and-setup)
4. [Usage](#usage)
5. [Project Structure](#project-structure)
6. [How It Works](#how-it-works)
7. [Contributions](#contributions)
8. [License](#license)

## Introduction
This simulation involves an environment grid where cells can either be polluted or clean. A **PolluterAgent** randomly pollutes cells in this grid, while multiple **CleanerAgents** receive proposals to clean polluted cells based on their cleaning costs. The agents communicate using **JADE messaging**, making it a simple yet educational introduction to JADE’s features.

## Features
- **Grid Environment**: A customizable grid to simulate pollution and cleaning.
- **PolluterAgent**: Randomly pollutes cells and sends proposals to cleaner agents.
- **CleanerAgents**: Accept or reject cleaning proposals based on predefined cleaning costs.
- **JADE Messaging**: Agents communicate using JADE’s messaging system, showcasing basic agent negotiation.
  
## Installation and Setup
1. **JADE Setup**: Install the JADE library from the [JADE download page](https://jade.tilab.com/download/jade/).
2. **Java Setup**: Make sure you have **Java 8 or later** installed.
3. **Clone this Repository**:
    ```bash
    git clone https://github.com/YourUsername/JadePollutionCleanerSim.git
    cd JadePollutionCleanerSim
    ```
4. **Run the Project**:
   - Compile the Java files and run `Tp1.java` as the main entry point.
   - Ensure JADE is correctly set up in your project to enable GUI and agent creation.

## Usage
To start the simulation, run the main class (`Tp1.java`). The JADE platform will initialize, displaying a GUI. You will see:
- A `PolluterAgent` randomly polluting cells in the grid.
- Four `CleanerAgents` that decide to clean polluted cells based on their costs.

The console output provides information about pollution and cleaning activities within the grid.

## Project Structure
- `Tp1.java`: Initializes the JADE platform, creates the environment, and launches agents.
- `PolluterAgent.java`: Handles pollution activities and sends proposals to cleaner agents.
- `CleanerAgent.java`: Receives proposals and decides whether to clean cells based on cost.
- `Environment.java`: Maintains the grid state and provides methods for marking cells as polluted or clean.

## How It Works
1. **Environment Initialization**: A 10x10 grid environment is created.
2. **Pollution and Cleaning**:
   - The `PolluterAgent` selects random cells to pollute and sends cleaning proposals.
   - Each `CleanerAgent` considers the proposal based on its cleaning cost and decides whether to clean the cell.
3. **Messaging System**: JADE messaging enables agents to send proposals, acceptance, and rejection messages.

## Contributions
This project is open for contributions! Whether you’re new to JADE or experienced with MAS development, any feedback, improvements, or new features are welcome.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

---

Thank you for exploring this project. I hope it helps you learn and experiment with JADE as much as it did for me!
