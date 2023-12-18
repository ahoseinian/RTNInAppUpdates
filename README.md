# RTNInAppUpdates

<!-- [![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/your-username/your-repo/blob/main/LICENSE)
[![GitHub Issues](https://img.shields.io/github/issues/your-username/your-repo.svg)](https://github.com/your-username/your-repo/issues)
[![GitHub Stars](https://img.shields.io/github/stars/your-username/your-repo.svg)](https://github.com/your-username/your-repo/stargazers) -->

## Description

RTNInAppUpdates is a React Native wrapper for the Android in-app-updates feature. It provides an easy-to-use interface for integrating the [In-App-Update API](https://developer.android.com/guide/playcore/in-app-updates) provided by Google.

## Installation

To install RTNInAppUpdates, run the following command:

```sh
npm install rtn-in-app-updates
```

Make sure to set `newArchEnabled` to `true` in `gradle.properties` for this module to work.

## Usage

```js
import RTNInAppUpdates from "rtn-in-app-updates/js/NativeInAppUpdates";

// checkForUpdate must always be called before any other method
async function update() {
  const result = await RTNInAppUpdates.checkForUpdate({ stalenessDays: 1 });
  if (result.updateAvailable) {
    await RTNInAppUpdates.startUpdate();
  }
}
```

## Contributing

Contributions are welcome! If you would like to contribute to RTNInAppUpdates, please follow the guidelines in [CONTRIBUTING.md](CONTRIBUTING.md).

## Troubleshooting

If you encounter any issues or errors while using RTNInAppUpdates, please refer to the [troubleshooting guide](TROUBLESHOOTING.md) for possible solutions.

Feel free to customize the content and links based on your project's specific needs.

## License

RTNInAppUpdates is licensed under the [MIT License](LICENSE).
