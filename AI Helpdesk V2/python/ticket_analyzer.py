import json
import sys

def analyze_ticket(issue_description):
    issue = issue_description.lower()

    category = predict_category(issue)
    priority = predict_priority(issue,category)
    recommendation = generate_recommendations(category)
    confidence = 0.80 if category != "Unknown" else 0.50
    return {
        "category": category,
        "priority": priority,
        "recommendation": recommendation,
        "confidence": confidence
             }


def predict_category(issue):
    categories = {
        "Network": ["wifi", "internet", "vpn", "router","connection"],
        "Email": ["email", "outlook", "mailbox","stmp"],
        "Printer": ["printer", "toner", "paper jam","print"],
        "Hardware": ["laptop", "monitor", "keyboard","mouse","screen"],
        "Software": ["app", "program", "crash","application","install","error"]
    }
    for category, keywords in categories.items():
        for keyword in keywords:
            if keyword in issue:
                return category


    return "Unknown"

def predict_priority(issue,category):
    high_priority_keywords = [
        "everyone",
        "entire office",
        "all users",
        "down",
        "critical",
        "urgent",
        "won't work"
    ]
    for keyword in high_priority_keywords:
        if keyword in issue:
            return "High"

    if category != "Unknown":
        return "Medium"

    return "Low"

def generate_recommendations(category):
    recommendations = {
        "Network": "Restart the router, check Wi-Fi connection, and verify VPN/network settings.",
        "Email": "Restart Outlook, check mailbox storage, and verify internet connection.",
        "Printer": "Check paper tray, toner levels, printer power, and restart the printer.",
        "Hardware": "Check cables, restart the device, and test with another keyboard, mouse, or monitor.",
        "Software": "Restart the application, check for updates, and reinstall if needed."
    }

    return recommendations.get(category, "Review issue manually.")



if __name__ == "__main__":

    if len(sys.argv) > 1:
        issue_description = sys.argv[1]
    else:
        issue_description = input("Enter ticket issue: ")

    result = analyze_ticket(issue_description)

    print(json.dumps(result, indent=4))





