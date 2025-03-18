# CHANGELOG
All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html). See the [CONTRIBUTING guide](./CONTRIBUTING.md#Changelog) for instructions on how to add changelog entries.

## [Unreleased 2.x]
### Added
- Improve performace of NumericTermAggregation by avoiding unnecessary sorting([#17252](https://github.com/opensearch-project/OpenSearch/pull/17252))
- Add execution_hint to cardinality aggregator request (#[17419](https://github.com/opensearch-project/OpenSearch/pull/17419))
- [Rule Based Auto-tagging] Add in-memory attribute value store ([#17342](https://github.com/opensearch-project/OpenSearch/pull/17342))

### Dependencies
- Bump `ch.qos.logback:logback-core` from 1.5.16 to 1.5.17 ([#17609](https://github.com/opensearch-project/OpenSearch/pull/17609))

### Changed
- Convert transport-reactor-netty4 to use gradle version catalog [#17233](https://github.com/opensearch-project/OpenSearch/pull/17233)
- Increase force merge threads to 1/8th of cores [#17255](https://github.com/opensearch-project/OpenSearch/pull/17255)
- TieredSpilloverCache took-time threshold now guards heap tier as well as disk tier [#17190](https://github.com/opensearch-project/OpenSearch/pull/17190)

### Deprecated

### Removed
- Remove FeatureFlags.PLUGGABLE_CACHE as the feature is no longer experimental ([#17344](https://github.com/opensearch-project/OpenSearch/pull/17344))

### Fixed
- Fix visit of inner query for FunctionScoreQueryBuilder ([#16776](https://github.com/opensearch-project/OpenSearch/pull/16776))
- Fix case insensitive and escaped query on wildcard ([#16827](https://github.com/opensearch-project/OpenSearch/pull/16827))
- Fix illegal argument exception when creating a PIT ([#16781](https://github.com/opensearch-project/OpenSearch/pull/16781))
- Fix NPE in node stats due to QueryGroupTasks ([#17576](https://github.com/opensearch-project/OpenSearch/pull/17576))

### Security

[Unreleased 2.x]: https://github.com/opensearch-project/OpenSearch/compare/2.19...2.x
