# _Void_ - League profile checker

# Dev
- run with **_dev_** profile to expose local development endpoints
  - Classes annotated with _@Dev_ run only with dev profile
- fill riot _**api.key**_ in _application-dev.yaml_
- _**dev**_ folder contains
  - hoppscotch collection for local testing. (If you introduce a new endpoint or change an existing one, please update the collection)
  - codestyle file, please import and use this. Let's keep the code consistent.
- _**dev/utils**_ contains python scripts for parsing riot ids

### Environment variables
_**voidapp**_ parent contains environment variables for (so far) 2 big features
- Update throttling _**voidapp.update.enabled**_
  - Throttling users from updating the same summoner too fast.
  - Delay in minutes configurable _voidapp.update.delay_
- Match cleanup service _**voidapp.cleanup**_
  - Removal of old matches, configurable for how old matches can be before deleted (in days) voidapp.cleanup.cutoff-days
  - Cron string configurable _**voidapp.cleanup.cron**_

# Background queue
- Background task processing for summoner and matches processing that is not time sensitive
- For use cases such as
  - Pulling basic data (profile, ranks, masteries), for summoners that are in matches with updated summoner (reducing friction for people who are not updating themselves)
  - Trophy room updates
- Update through _**voidapp.processingQueue**_
  - Change delay between processing _**voidapp.processingQueue.fixedDelay**_
  - Enable through _**voidapp.processingQueue.enabled**_

# Deployment
- _application-prod.yaml_ with api key filled in necessary

# Examples
- example response json files are stored in test resources
