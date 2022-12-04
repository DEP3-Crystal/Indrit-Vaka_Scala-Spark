# BigQuery Cost

- Storage costs
- Query costs
- Free operations

# Storage cost

Storage cost deffer based on whether your data is:

- active
- Long-term Data

## Active data

Data modified on last 90 days are considered active data,
and it cost more to store.

| Active data                                     | Long-term Data                                                       |
|-------------------------------------------------|----------------------------------------------------------------------|
| Data in tables modified in last 90 days         | Data not mod in last 90 days                                         |
| Currently approximately 2 cents/GB/month        | About 50% lower. Currently about 1 cent/GB/month                     |
| First 10GB is free                              | First 10GB is free                                                   |
| When table is edited, pricing reverts to active | when a table is not edited, pricing automatically drops to long-term |

# Query cost

- On-demand
- Flat-rate

On demand, you pay based on usage.
For flat-rate, you have predictable cost from one month to another.

| On-Demand                             | Flat-rate                                                     |
|---------------------------------------|---------------------------------------------------------------|
| Based solely on usage                 | Predictable, fixed monthly costs                              |
| $5 per YB/month. First 1TB/month free | $40_000/month for 200 slots, $10_000 per 500 additional slots |

# Free data

- Loading data into BQ
    - streaming insert is not free
- Copying data
- Exporting data
- deleting dataset
- deleting tables, views, partitions
- Metadata operations

# Streaming inserts

1 cent/200 MB
Only successfully insert rows are charged

# Minimizing Costs

Query only columns you need

- under the hood, bigQuery is columnar
- each column stored separately in encrypted, replicated file

Use table preview to explore data

- Don't run queries just to explore

# Calculation Query Price

- dry_run flag in CLI
- query validator UI
- GCP Pricing calculator