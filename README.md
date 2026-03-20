# 🛡️ Zenshield — AI-Powered Income Protection for Gig Workers
### Guidewire DEVTrails 2026 | Phase 1 Submission

> *"We don't insure events. We insure actual income loss."*

---

## 📋 Table of Contents

1. [Problem Statement](#-problem-statement)
2. [Our Solution](#-our-solution)
3. [Persona & Use Case Scenarios](#-persona--use-case-scenarios)
4. [Application Workflow](#-application-workflow)
5. [Weekly Premium Model](#-weekly-premium-model)
6. [Parametric Triggers](#-parametric-triggers)
7. [AI/ML Integration Plan](#-aiml-integration-plan)
8. [Platform Architecture: Mobile-First + Admin Web Dashboard](#-platform-architecture-mobile-first--admin-dashboard)
9. [Tech Stack & Development Plan](#-tech-stack--development-plan)
10. [Financial Sustainability](#-financial-sustainability)

---

## 🔴 Problem Statement

India's **platform-based food delivery partners** (Zomato, Swiggy) are the invisible backbone of the urban economy. Yet they face a structural vulnerability that no existing product addresses:

| Factor | Reality |
|--------|---------|
| Income model | Per-order earnings, no fixed salary |
| Disruption impact | 20–30% monthly income lost during rain, heat, or zone shutdowns |
| Existing safety net | **None** |
| Current insurance solutions | Event-triggered, not validated against actual income loss |

When heavy rain hits a city, a delivery partner doesn't just get wet — they lose ₹300–₹700 in a single day. Multiply that by 3–4 disruption days per month and you have a **₹1,000–₹2,500/month income gap** with zero recourse.

> **The core problem:** Existing parametric insurance solutions pay based on *events happening*, not *income actually being lost*. This creates unfair payouts — the dreaded **basis risk** — where workers either get nothing or get paid even when they worked normally.

---

## 💡 Our Solution

**Zenshield** is a Hybrid AI + Parametric Income Protection Platform designed exclusively for **food delivery partners on Zomato and Swiggy**.

### Core Philosophy
```
Disruptions trigger evaluation.
Payouts happen only when real income loss is verified.
```

Instead of a traditional "event occurred → pay out" model, Zenshield uses a **3-layer validation**:

```
Layer 1: Parametric Trigger     →  Was there an external disruption?
Layer 2: AI Income Validation   →  Did the worker's income actually drop?
Layer 3: Z-Score Verification   →  Was the drop statistically significant?

Only if all 3 layers confirm → Payout is calculated and credited
```

---

## 👤 Persona & Use Case Scenarios

### Primary Persona: Food Delivery Partner

**Profile:**
- Name: Ravi Kumar (representative persona)
- Platform: Zomato / Swiggy (Food delivery)
- City: Hyderabad / Bengaluru / Mumbai
- Avg. daily earnings: ₹700–₹1,200
- Work hours: 8–12 hours/day, 6–7 days/week
- Weekly income: ₹4,900–₹8,400
- Weekly premium capacity: ₹50–₹150

---

### Scenario 1: Heavy Rain Disruption (Most Common)

**Situation:** Hyderabad receives 65mm rainfall on a Wednesday. Restaurants reduce active orders; customers cancel; Ravi can only complete 4 orders instead of his usual 12.

| Metric | Value |
|--------|-------|
| Expected Daily Income (AI predicted) | ₹1,000 |
| Actual Income Earned | ₹380 |
| Daily Loss | ₹620 |
| Coverage Plan | 60% |
| Trust Score | 0.92 |
| **Final Payout** | **₹620 × 0.60 × 0.92 = ₹342** |

**System Action:** Rain threshold crossed → Claim evaluation auto-initiated → Income compared → Z-score confirms abnormal drop → ₹342 credited to UPI within 2 hours.

---

### Scenario 2: Extreme Heat Day

**Situation:** Bengaluru records 42°C on a May afternoon. Ravi limits work to morning hours (6 AM–11 AM) for safety. His income drops by 55%.

| Metric | Value |
|--------|-------|
| Expected Daily Income | ₹900 |
| Actual Income | ₹405 |
| Daily Loss | ₹495 |
| Coverage Plan | 80% |
| Trust Score | 0.88 |
| **Final Payout** | **₹495 × 0.80 × 0.88 = ₹348** |

---

## 🔄 Application Workflow

```
┌─────────────────────────────────────────────────────────────┐
│                    USER JOURNEY                             │
└─────────────────────────────────────────────────────────────┘

  [ONBOARDING]
       │
       ▼
  Register with mobile OTP
       │
       ▼
  Enter Profile: Name, Platform (Zomato/Swiggy), Avg. work hours, UPI ID
       │
       ▼
  AI builds Income Profile (baseline expected daily/weekly income)
       │
       ▼
  [PLAN SELECTION]
       │
       ▼
  View weekly premium (auto-calculated)
  Select coverage tier: 30% / 60% / 80%
  Pay weekly premium via UPI
       │
       ▼
  [ACTIVE COVERAGE WEEK]
       │
       ▼
  System monitors daily: weather, AQI, zone alerts, disruption events
       │
       ├─── No disruption → Daily income tracked, no claim
       │
       └─── Disruption detected ──────────────────────────┐
                                                          │
                                                          ▼
                                               [CLAIM EVALUATION]
                                                          │
                                                          ▼
                                          Fetch actual income data (platform API / simulated data)
                                                          │
                                                          ▼
                                          Z-Score: Is income drop significant?
                                                          │
                                          ┌───── NO ──────┴────── YES ─────┐
                                          │                                 │
                                          ▼                                 ▼
                                     No payout                    Fraud detection check
                                                                          │
                                                             ┌─── FLAGGED ─┴─ CLEAN ───┐
                                                             │                          │
                                                             ▼                          ▼
                                                       Suspend claim           Calculate Payout:
                                                       + lower trust         Loss × Coverage × Trust
                                                                                        │
                                                                                        ▼
                                                                           Credit to UPI (< 2 hours)
```

---

## 💰 Weekly Premium Model

### Formula

```
Weekly Premium = k × E[Weekly Income] × R × V × C × CRF
```

| Variable | Meaning | Example Value |
|----------|---------|---------------|
| `k` | Affordability scaling factor | 0.025 |
| `E[Weekly Income]` | AI-predicted weekly income | ₹6,000 |
| `R` | Risk factor (weather/AQI/season) | 1.2 (monsoon) |
| `V` | Income variability score | 1.1 |
| `C` | Coverage multiplier (30/60/80%) | 1.3 (for 60%) |
| `CRF` | Correlation Risk Factor (group exposure) | 1.05 |

### Example Premium Calculation

For a Swiggy partner in Hyderabad during monsoon season:
```
Weekly Premium = 0.025 × ₹6,000 × 1.2 × 1.1 × 1.3 × 1.05
               = ₹270 / week
               ≈ ₹38.57 / day
```

### Coverage Tiers

| Plan | Coverage % | Weekly Premium (example) | Best For |
|------|-----------|--------------------------|----------|
| Basic | 30% | ₹120 | Occasional disruption areas |
| Standard | 60% | ₹270 | High-disruption cities |
| Premium | 80% | ₹400 | Coastal/flood-prone zones |

### Why Weekly?

Gig workers are paid weekly by platforms (Zomato/Swiggy settle partner payments every 7 days). Aligning premium cycles with income cycles ensures:
- Workers can afford coverage without upfront monthly commitment
- Premium deduction feels natural alongside platform payout
- Risk assessment resets weekly based on fresh weather forecasts

---

## ⚡ Parametric Triggers

These triggers **start the evaluation process only** — they do not directly cause a payout.

| Trigger | Threshold | Data Source |
|---------|-----------|-------------|
| Heavy Rain | Rainfall > 35mm in 3 hours | OpenWeatherMap API |
| Extreme Heat | Temperature > 40°C for > 4 hours | OpenWeatherMap API |
| Severe AQI | AQI > 300 (Hazardous) | AQI API / CPCB data |
| Flood / Waterlogging | Zone flood alert issued | Civic body APIs / mock |
| Local Shutdown | Curfew/strike notification in zone | News API / manual admin flag |

### Trigger → Payout Logic

```
Trigger fires
    │
    ▼
Fetch worker's actual income for that day (from platform API / mock)
    │
    ▼
Compare to AI-predicted expected income
    │
    ▼
Z = (Actual - Expected) / StdDev
    │
    ├── Z > -1.5  →  Normal variance → NO PAYOUT
    │
    └── Z ≤ -1.5  →  Significant drop → Proceed to fraud check → PAYOUT
```

---

## 🧠 AI/ML Integration Plan

### 1. Income Prediction Model (Core)
- **Purpose:** Predict expected daily/weekly income for each worker
- **Algorithm:** XGBoost Regressor
- **Features:** Day of week, time of year, historical earnings, work hours logged, zone, platform, weather baseline
- **Output:** Expected daily income (₹) + standard deviation (for Z-score)
- **Training data:** Synthetic + historical gig platform data patterns

### 2. Z-Score Loss Validation
- **Purpose:** Ensure only statistically significant income drops trigger payouts
- **Formula:** `Z = (Actual Income - Expected Income) / StdDev`
- **Threshold:** Z ≤ -1.5 (configurable per zone)
- **Prevents:** False payouts on days with naturally lower orders

### 3. Risk Assessment Engine
- **Purpose:** Dynamically price weekly premiums
- **Inputs:** Zone history, 7-day weather forecast, seasonal risk, area flood data
- **Output:** Risk score `R` fed into premium formula
- **Update frequency:** Weekly (before the new coverage period begins)

### 4. Fraud Detection System

Zenshield uses a dedicated **4-layer fraud detection architecture**. It runs as a separate gate — after Z-score confirms a real income drop but before any payout is released.

> Z-score only confirms *that* income dropped. Fraud detection confirms *why* — ensuring the drop was genuine and not manipulated.

---
### Additional Innovations (Beyond Requirements)

Zenshield goes beyond basic requirements by introducing:

- Trust Score-Based Payout System  
  → Adjusts payouts based on user behavior and history  

- Correlation Risk Factor (CRF)  
  → Handles large-scale events affecting multiple users  

- Weekly + Daily Hybrid Model  
  → Weekly premium, daily monitoring, instant payouts  

- Transparent Payout UI  
  → Shows Expected vs Actual income and exact calculation  

- AI Insights for Admin  
  → Risk prediction, fraud alerts, and system analytics  

---

#### Why Fraud Detection is a Separate Layer

A bad actor can easily pass Z-score validation by:
- **GPS spoofing** — faking location to appear in a disrupted zone while working normally elsewhere
- **Forced inactivity** — logging off the platform temporarily during rain to show ₹0 income, then resuming
- **Duplicate claims** — trying to claim the same event twice across multiple devices
- **Coordinated fraud** — colluding with other workers to all show low income on disruption days

All of these produce a genuine Z-score below −1.5, so Z-score alone cannot catch them. Fraud detection is mandatory.

---

#### Layer A — Rule-Based Checks (instant, first-pass)

Run on every single triggered claim before any ML is invoked:

| Rule | What it checks | On fail |
|------|---------------|---------|
| GPS Validation | Claimed zone vs actual order pickup/drop GPS coordinates from platform API | Block + flag |
| Activity Check | Was the app showing normal activity, then suddenly went offline during disruption? | Flag for review |
| Duplicate Prevention | Same worker, same event, same day — across all registered devices | Auto-reject + log |

---

#### Layer B — XGBoost Classifier (ML fraud probability)

- **Algorithm:** XGBoost binary classifier
- **Input features:** claim timing, GPS consistency score, income drop magnitude, historical claim frequency, zone disruption severity, app activity log pattern
- **Output:** Fraud probability `P(fraud)` between 0.0 and 1.0

```
If P(fraud) > 0.85  →  Auto-reject + Trust Score penalty
If P(fraud) > 0.65  →  Flag for manual review + soft Trust penalty
If P(fraud) ≤ 0.65  →  Pass to Layer C
```

---

#### Layer C — Isolation Forest (anomaly detection)

- **Algorithm:** Isolation Forest (unsupervised ML)
- **Purpose:** Catches fraud patterns XGBoost has never seen in training data — new tactics, coordinated attacks, edge cases
- **What it detects:**
  - Claim frequency abnormally high vs zone peers during the same disruption
  - Income drop magnitude doesn't match the disruption's actual measured severity
  - Social clusters: groups of connected users all claiming simultaneously

---

#### Trust Score (0.0 – 1.0)

The Trust Score is the **long-term memory** of the entire fraud system. It doesn't just block individual fraud attempts — it permanently adjusts how much a user receives on every future claim.

**Built from three signals:**
- Claim history (verified clean vs disputed)
- Behaviour consistency (GPS, activity timing, drop patterns)
- Activity patterns over a rolling 30-day window

```
Trust Score — full scoring table:

Base Score:  1.0

  INCREASES
  + Successful verified claim           +0.02 (each)
  + Clean 30-day history bonus          +0.05

  DECREASES
  − GPS spoofing detected               −0.30
  − Fraudulent claim confirmed          −0.30
  − Duplicate claim attempt             −0.20
  − Inconsistent GPS data               −0.10
  − XGBoost soft flag (P > 0.65)        −0.05

  FLOOR:   0.0  →  account suspended from payouts
  CEILING: 1.0
```

**Trust Score labels and effect:**

| Score range | Label | Payout effect |
|-------------|-------|--------------|
| 0.85 – 1.0 | Excellent | Full payout |
| 0.65 – 0.84 | Good | Minor reduction |
| 0.40 – 0.64 | Average | Significant reduction |
| 0.00 – 0.39 | Risk | Payouts blocked, account review |

---

#### Final Payout Formula

```
Final Payout = Daily Loss × Coverage % × Trust Score

Example:
  Daily Loss   =  ₹400
  Coverage     =  60%
  Trust Score  =  0.92

  Final Payout = ₹400 × 0.60 × 0.92  =  ₹221
```

**Why Trust Score makes fraud economically self-defeating:**
Even if a fraudulent claim slips through Layers A, B, and C, the low trust score means the payout is drastically reduced. And every flag — permanently recorded — makes all future payouts smaller. There is no benefit to attempting fraud in this system. The cost compounds every time.

### 5. Correlation Risk Factor (CRF)
- Measures how many users in the same zone will claim simultaneously
- A citywide flood → high CRF → premiums adjusted upward pre-event
- Prevents catastrophic loss concentration risk

---

## 📱 Platform Architecture: Mobile-First + Admin Dashboard

Zenshield follows a modular, scalable architecture designed for real-time monitoring and intelligent payouts.

### System Components

1. Mobile Application (User)
- Used by delivery partners
- Shows earnings, loss, and payouts
- Handles onboarding, plan selection, and tracking

2. Admin Dashboard (Web)
- Monitors system-wide metrics
- Displays risk zones, fraud alerts, and payout trends
- Enables operational control

3. Backend API Layer (FastAPI)
- Handles all business logic
- Manages user data, claims, and payouts
- Connects frontend with AI models and database

4. AI/ML Layer
- Income prediction (XGBoost)
- Fraud detection (XGBoost + Isolation Forest)
- Risk scoring engine

5. Database (PostgreSQL)
- Stores users, income data, claims, payouts, and logs

6. External Integrations
- Weather API → detects rainfall, temperature
- AQI API → pollution levels
- Payment Gateway (Razorpay test mode) → payouts

### Data Flow

User App → Backend API → AI Models → Decision Engine → Payout Processing → User Wallet (UPI)

Admin Dashboard → Backend API → Analytics & Monitoring

---

## 🔧 Tech Stack & Development Plan

### Tech Stack

| Layer | Technology | Reason |
|-------|-----------|--------|
| Mobile Frontend | React Native / React.js (PWA) | Cross-platform, fast development |
| Admin Dashboard | React.js | Component-based, rich UI libraries |
| Backend API | FastAPI (Python) | Async, fast, perfect for ML integration |
| AI/ML Models | XGBoost, scikit-learn, SciPy | Income prediction + fraud detection |
| Database | PostgreSQL | Relational integrity for financial data |
| Weather API | OpenWeatherMap (free tier) | Real-time trigger monitoring |
| AQI API | OpenAQ / CPCB (mock) | Pollution trigger data |
| Payments | Razorpay (test mode) | Simulated UPI payouts |
| Auth | Firebase Auth (OTP) | Mobile OTP login |

### Development Phases

**Phase 1 (Current — March 4–20): Ideation & Foundation**
- ✅ Problem research and persona definition
- ✅ Workflow design and financial model documentation
- ✅ README and prototype UI design
- ✅ GitHub repository setup

**Phase 2 (March 21 – April 4): Automation & Protection**
- Build registration + onboarding flow
- Implement dynamic premium calculator
- Build 3–5 parametric trigger monitors (weather, AQI, zone alerts)
- Zero-touch claim initiation
- Mock income validation with XGBoost model

**Phase 3 (April 5–17): Scale & Optimise**
- Advanced fraud detection (GPS spoofing, anomaly scoring)
- Trust score system
- Razorpay test-mode payout integration
- Worker dashboard (earnings protected, active coverage)
- Admin dashboard (loss ratios, predictive analytics)
- Final pitch deck + 5-minute demo video

---

## 📊 Financial Sustainability

Zenshield is designed to be **actuarially sound**, not just technically impressive.

### Loss Ratio Target
```
Loss Ratio = Total Payouts / Total Premiums Collected

Target: 60–70% (industry standard for sustainable parametric insurance)
```

### Why Our Model is Sustainable
1. **Z-score filtering** eliminates false payouts (estimated 40% of naive parametric triggers)
2. **CRF pricing** prevents underpricing during correlated events (monsoon season)
3. **Trust score system** reduces fraud-driven payouts
4. **Weekly model** allows rapid repricing based on forecasts — no long-term mispricing risk

### Unit Economics (Example: 1,000 users, Hyderabad, 1 week during monsoon)

| Metric | Value |
|--------|-------|
| Avg. weekly premium | ₹270 |
| Total premiums collected | ₹2,70,000 |
| Expected claim rate | ~30% of users on disruption days |
| Avg. payout per claim | ₹280 |
| Total payouts | ₹84,000 |
| Platform operating cost | ₹27,000 (~10%) |
| **Net (before reinsurance)** | **₹1,59,000** |

This demonstrates the model is financially viable while providing meaningful protection to workers.

---
### ⚡ Why Zenshield is Different

- Hybrid parametric + AI validation  
- Payouts based on real income loss  
- Z-score ensures statistical accuracy  
- Trust score reduces fraud  
- Transparent and user-trust focused  

👉 Result: Fair, scalable, and sustainable system

📌 Note:
Zenshield strictly covers income loss only and does NOT include physical damage, health insurance, or vehicle repair coverage.
---
## 📌 Summary

Zenshield transforms the insurance experience for gig workers from a passive, event-based payout to an **intelligent, income-validated protection system**. By combining parametric triggers with AI-driven income verification and statistical validation, we ensure that:

- Workers are **fairly compensated** for real income loss
- The platform remains **financially sustainable**
- Fraud is **detected and prevented** automatically
- The experience is **zero-touch** — no manual claim filing needed
- Zenshield eliminates basis risk — the biggest flaw in traditional parametric insurance.
**The goal is simple: when Ravi Kumar can't work because it's raining too hard, he shouldn't lose sleep about paying rent.**

---

*Guidewire DEVTrails 2026 | Phase 1 Submission | Team: Zenshield*
