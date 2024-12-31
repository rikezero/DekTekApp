module.exports = {
  verbose: true,
  git: {
    requireCleanWorkingDir: true,
    commit: true,
    tag: true,
    tagName: "${version}",
    tagAnnotation: "Release ${version}",
    push: true,
  },
  github: {
    release: false,
    tokenRef: "GITHUB_TOKEN",
  },
  npm: {
    publish: false,
  },
  plugins: {
    "@release-it/conventional-changelog": {
      strictSemVer: true,
      infile: "CHANGELOG.md",
      header: "# DekTek Changelog\n\nAll notable changes are listed below.",
      preset: {
        name: "conventionalcommits",
        types: [
          { type: "BREAKING CHANGE", section: "Major Changes" },
          { type: "refactor", section: "Major Changes" },
          { type: "fix", section: "Bug Fixes" },
          { type: "hotfix", section: "Bug Fixes" },
          { type: "feat", section: "New Features" },
          { type: "docs", section: "Miscellaneous" },
          { type: "chore", section: "Miscellaneous" },
          { type: "style", section: "Miscellaneous" },
          { type: "test", section: "Miscellaneous" },
        ],
      },
      writerOpts: "./changelog-writer.js",
    },
  },
};