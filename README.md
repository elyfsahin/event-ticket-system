# Event Ticket System

A desktop application for browsing and purchasing event tickets, built with Java Swing as a group project for Object Oriented Programming course at Istanbul Aydın University.

## Features

- **Dual-role architecture** — separate Admin and User interfaces
  - Admin: add, update, and delete events
  - User: browse events, purchase tickets, view purchase history
- **Event categories** — Cinema, Theatre, and Concert in a unified interface
- **Pricing tiers** — Student (discounted) and Adult (full price)
- **Itemized cost breakdown** — base price, transaction fee, and taxes shown before payment
- **JSON file persistence** — all user and event data saved and reloaded automatically on startup

## OOP Principles Applied

- **Inheritance** — `Admin` extends `User` (shared fields: name, email, password)
- **Encapsulation** — all class fields are private, accessed via getters/setters

## Tech Stack

- Java
- Java Swing (GUI)
- JSON (file-based data persistence)

## How to Run

1. Clone the repository
2. Open in Eclipse IDE
3. Run `src/pack/Main.java`
