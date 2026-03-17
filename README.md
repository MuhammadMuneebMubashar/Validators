# Validators

> A lightweight, dependency-free Java utility for validating common user input fields.

[![Repository](https://img.shields.io/badge/GitHub-Repository-blue.svg)](https://github.com/MuhammadMuneebMubashar/Validators.git)
[![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://www.oracle.com/java/)

**Validators** provides a simple, static utility class (`validateField`) to verify the integrity of names, contact numbers, and email addresses. Built with pure Java, it requires no external libraries or heavy regex engines, making it incredibly fast and easy to integrate into any project.

---

## Features

The `validateField` class currently supports three core validation methods:

* **Name Validation (`isNameParamValid`)**: 
    * Ensures the string is between 2 and 50 characters.
    * Validates that the name starts and ends with a letter.
    * Allows single spaces, hyphens (`-`), and apostrophes (`'`) internally.
    * Rejects digits and consecutive special characters.
* **Contact Number Validation (`isContactValid`)**: 
    * Verifies phone numbers containing between 7 and 15 digits.
    * Supports international prefixes (`+`) and area code brackets (`()`).
    * Allows standard formatting separators like spaces and hyphens (`-`).
    * Prevents consecutive special characters and mismatched brackets.
* **Email Validation (`isEmailValid`)**: 
    * Enforces standard email length constraints (max 254 total characters, max 64 for the local part).
    * Ensures a single `@` symbol separates the local part and domain.
    * Validates alphanumeric boundaries for both local and domain parts.
    * Permits valid special characters (`.`, `_`, `+`, `-`) but rejects consecutive special characters to prevent malformed addresses.

---

## Installation

Since this is a single-file utility, installation is as simple as dropping the class into your project.

1. Clone the repository:
   ```bash
   git clone [https://github.com/MuhammadMuneebMubashar/Validators.git](https://github.com/MuhammadMuneebMubashar/Validators.git)
