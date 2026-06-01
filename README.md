# CandidateInsight – Unit 03 – Starter (Student Version)

## Your Task

Implement the TODO methods so that `Client.main()` runs successfully
and produces the expected output (see exercise sheet).

## Group assignments

| Group | Files to implement | Use Cases |
|---|---|---|
| A | `UserManagement.java` | UC1 (Register), UC2 (Log In) |
| B | `TalentHiveConnector.java`, `CandidatePipeline.java` (Steps 2a–2d) | UC3 (Sync), UC4 (View Pipeline) |
| C | `Candidate.java`, `CandidatePipeline.getEvaluationSummary()` (Step 3d) | UC5 (Rate), UC6 (Summary) |
| D | `CandidateInsight.java` (Steps 4a–4e) | UC3–UC6 (Facade) |

All interfaces and given classes (enums, value objects, Composer, Client) are already complete.
Do NOT modify those files.

## How to build and run

```bash
mvn compile
mvn package
java -jar target/candidateinsight-unit03-starter-1.0.0.jar
```

Or without Maven:
```bash
find src -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out de.bankolino.candidateinsight.Client
```
