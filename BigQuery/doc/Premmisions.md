# UpdatePermission

- bq show --format=prettyjson spikeysales_dataset > dataset_spikeysales.json
This will write all ds permission in a json file

After making changes to the file you can update the permissions by
- bq update --source dataset_spikeysales.json spikeysales_dataset




