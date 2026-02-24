package com.nip.public_issue.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nip.public_issue.Service.EmailService;
import com.nip.public_issue.models.issue.Issue;
import com.nip.public_issue.models.issue.Status;
import com.nip.public_issue.models.user.Role;
import com.nip.public_issue.repository.IssueRepo;

import jakarta.transaction.Transactional;

@Service
public class RemainderScheduler {

    @Autowired
    private IssueRepo issueRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private com.nip.public_issue.repository.UserRepo userRepo;


@Transactional
// @Scheduled(cron = "0 * * * * ?") // every day at midnight
public void checkOverdueTasks() {

    LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);

    List<Issue> overdueIssues =
            issueRepository.findIncompleteIssuesBeforeDate(
                    Status.COMPLETED,
                    threeDaysAgo
            );

    userRepo.findFirstByRole(Role.SUPERADMIN).ifPresent(superAdmin -> {

        if (superAdmin.getEmail() != null && !overdueIssues.isEmpty()) {

            overdueIssues.forEach(issue -> {

                String imageUrl = null;

                if (issue.getImageTag() != null) {
                    imageUrl = "https://res.cloudinary.com/dqbr5fypv/image/upload/"
                            + issue.getImageTag();
                }

                String htmlBody = String.format("""
                        <html>
                        <body style="font-family: Arial, sans-serif;">
                            <h2 style="color:#d9534f;">⚠ Overdue Issue Alert</h2>

                            <p>Hello <strong>%s</strong>,</p>

                            <p>An issue has been overdue for more than 3 days.</p>

                            <hr>

                            <p><strong>ID:</strong> %d</p>
                            <p><strong>Category:</strong> %s</p>
                            <p><strong>Description:</strong> %s</p>
                            <p><strong>Location:</strong> %s</p>
                            <p><strong>Status:</strong> %s</p>
                            <p><strong>Reporter:</strong> %s</p>

                            %s

                            <br>
                            <p>Please oversee the resolution process.</p>

                            <br>
                            <p>Regards,<br>
                            Public Issue Tracking System</p>
                        </body>
                        </html>
                        """,
                        superAdmin.getName(),
                        issue.getId(),
                        issue.getIssueCategory(),
                        issue.getMessage(),
                        issue.getLocation(),
                        issue.getStatus(),
                        issue.getReporter() != null ? issue.getReporter().getName() : "Unknown",
                        imageUrl != null
                                ? "<img src='" + imageUrl +
                                  "' style='max-width:500px;border-radius:8px;margin-top:10px;'/>"
                                : ""
                );

                emailService.sendHtmlMail(
                        superAdmin.getEmail(),
                        "Alert: Overdue Issue #" + issue.getId(),
                        htmlBody
                );
            });
        }
    });
}

}

