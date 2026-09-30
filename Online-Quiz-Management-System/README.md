# Online Quiz Management System

College mini project built with Java 17+, Spring Boot, Spring Data JPA, Spring Security, HTML, CSS, JavaScript, and an H2 file database.

**Live application:** https://online-quiz-management-system-ql7w.onrender.com/

## Features
- Student name and email entry
- Five-minute quiz timer
- Ten sample multiple-choice questions seeded on first startup
- Previous/Next navigation and automatic scoring
- Persistent result history
- Admin question management (add/delete)
- Password-protected admin panel and results
- Automatic email of the student's score after submission (requires SMTP configuration)

## Requirements
- JDK 17 or newer
- Maven 3.9+ installed and available on PATH
- VS Code with Extension Pack for Java (recommended)

## Run on Windows
1. Extract the complete ZIP file.
2. Open the `Online-Quiz-Management-System` folder in VS Code or Command Prompt.
3. In the project root (the folder containing `pom.xml`), set admin credentials and run:

```powershell
$env:ADMIN_USERNAME="your-admin-name"
$env:ADMIN_PASSWORD="use-a-strong-password"
mvn spring-boot:run
```

Alternatively, set those environment variables in your terminal and double-click `run.bat`.

## Run on macOS/Linux
```bash
export ADMIN_USERNAME="your-admin-name"
export ADMIN_PASSWORD="use-a-strong-password"
chmod +x run.sh
./run.sh
```

## Admin login configuration
Set `ADMIN_USERNAME` and `ADMIN_PASSWORD` as environment variables before starting the application. The app intentionally refuses to start if either is missing; do not commit credentials to GitHub.

On Render, add both variables under the service's Environment settings and redeploy. Visit `/admin.html`; you should be redirected to the Spring Security login page. The student quiz remains public. Admin question creation/deletion and result viewing require the admin role.

## Student result emails
After a student submits a quiz, the result is saved to the database first. The application then attempts to email the student's name, score, correct answers, total questions, and submission date. If email delivery fails, the saved result remains available in the admin panel; the result page displays that the email could not be sent.

Configure SMTP using environment variables. For Gmail, use an App Password (not your normal account password) and keep it secret.

| Variable | Description | Example |
|---|---|---|
| `MAIL_HOST` | SMTP server | `smtp.gmail.com` |
| `MAIL_PORT` | SMTP port | `587` |
| `MAIL_USERNAME` | SMTP account email | `your-address@gmail.com` |
| `MAIL_PASSWORD` | SMTP app password | Set privately in environment |
| `MAIL_FROM` | Sender address (optional; defaults to `MAIL_USERNAME`) | `your-address@gmail.com` |
| `MAIL_SMTP_AUTH` | SMTP authentication | `true` |
| `MAIL_SMTP_STARTTLS` | STARTTLS | `true` |

For Render, add these variables in the service's Environment settings and redeploy. Email delivery depends on valid SMTP credentials and provider policies.

## Open the application
- Student home: http://localhost:8080
- Admin panel: http://localhost:8080/admin.html
- H2 console: http://localhost:8080/h2-console (disabled by default)

H2 JDBC URL: `jdbc:h2:file:./data/quizdb`  
Username: `sa`  
Password: leave empty

## Main API
- `GET /api/questions` — public question retrieval
- `POST /api/questions` — admin-only question creation
- `DELETE /api/questions/{id}` — admin-only question deletion
- `GET /api/results` — admin-only result viewing
- `POST /api/results` — public result submission

## Notes
- The H2 database is stored in the `data` folder created in the project directory.
- On Render, configure a persistent disk and point the database URL to its mounted path if you need results to survive restarts/redeployments. Without persistent storage, local database files may be lost.
- Never commit admin or SMTP credentials to GitHub.
