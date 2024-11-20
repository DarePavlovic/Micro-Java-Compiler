# MicroJava Compiler Project

## Overview

This project involves the development of a compiler for the MicroJava programming language. The compiler translates syntactically and semantically correct MicroJava programs into MicroJava bytecode, which can be executed on the MicroJava Virtual Machine (MJVM). The compiler consists of four main components: lexical analysis, syntax analysis, semantic analysis, and code generation.

## Features

- **Lexical Analysis**: Tokenizes the source code into meaningful symbols.
- **Syntax Analysis**: Parses tokens to ensure they form grammatically correct sentences.
- **Semantic Analysis**: Checks for semantic correctness and updates the symbol table.
- **Code Generation**: Translates the abstract syntax tree into executable bytecode for MJVM.

## Project Structure

The project is divided into several tasks, each with specific requirements and functionalities.

### Lexical Analysis

- Implemented using JFlex.
- Recognizes identifiers, constants, keywords, operators, and comments.
- Skips whitespace and comments.
- Reports lexical errors with detailed messages.

### Syntax Analysis

- Implemented using AST-CUP.
- Parses tokens based on the LALR(1) grammar of MicroJava.
- Supports error recovery and continues parsing after encountering syntax errors.
- Generates an abstract syntax tree (AST).

### Semantic Analysis

- Traverses the AST to perform semantic checks.
- Updates the symbol table and ensures correct usage of symbols.
- Reports semantic errors with detailed messages.

### Code Generation

- Translates the AST into MicroJava bytecode.
- Supports basic statements, arithmetic expressions, control structures, and method calls.
- Generates executable `.obj` files for MJVM.

## Development Environment

The project is developed using Java (JDK 1.8) and requires the following tools:

- **JFlex**: For lexical analysis.
- **AST-CUP**: For syntax analysis and AST generation.
- **MJVM Tools**: For code generation and execution (Code, disasm, Run).

## Building and Running

To build and run the project, follow these steps:

1. **Generate Lexer and Parser**:
   ```sh
   jflex mjlexer.flex
   java -jar cup_v10k.jar mjparser.cup
   ```

2. **Compile the Compiler**:
   ```sh
   javac -cp .:cup_v10k.jar:./lib/* -d bin src/rs/ac/bg/etf/pp1/*.java
   ```

3. **Run the Compiler**:
   ```sh
   java -cp .:bin:cup_v10k.jar rs.ac.bg.etf.pp1.Compiler input.mj output.obj
   ```

## Testing

Test the compiler using the provided test files. Place input files with the `.MJ` extension in the `test` directory and run the compiler to generate output files with the `.out` and `.err` extensions.
