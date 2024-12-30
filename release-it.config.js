module.exports = {
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
      infile: "CHANGELOG.md",
      header: "# DekTek Changelog\n\nAll notable changes are listed below.",
      preset: {
        name: "conventionalcommits",
        types: [
          {
            type: "BREAKING CHANGE",
            section: "⚠️ Major Changes",
          },
          {
            type: "refactor",
            section: "⚠️ Major Changes",
          },
          {
            type: "fix",
            section: "🐛 Bug Fixes",
          },
          {
            type: "hotfix",
            section: "🐛 Bug Fixes",
          },
          {
            type: "feat",
            section: "✨ New Features",
          },
          {
            type: "docs",
            section: "🛠️ Miscellaneous",
          },
          {
            type: "style",
            section: "🛠️ Miscellaneous",
          },
          {
            type: "test",
            section: "🛠️ Miscellaneous",
          },
          {
            type: "chore",
            section: "🛠️ Miscellaneous",
          },
        ],
      },
      writerOpts: {
        transform: (commit, context) => {
          const issues = [];

          if (commit.scope === '*') {
            commit.scope = '';
          }

          if (typeof commit.hash === 'string') {
            commit.shortHash = commit.hash.substring(0, 7);
          }

          if (typeof commit.subject === 'string') {
            commit.subject = commit.subject.replace(/#([0-9]+)/g, (_, issue) => {
              issues.push(issue);
              return `[#${issue}](${context.repository}/issues/${issue})`;
            });
            commit.subject = commit.subject.replace(/@([a-zA-Z0-9_-]+)/g, '[@$1](https://github.com/$1)');
          }

          commit.hash = `[${commit.shortHash}](${context.repository}/commit/${commit.shortHash})`;

          return commit;
        },
        groupBy: "type",
        commitsSort: ["scope", "subject"],
        noteGroupsSort: "title",
        notesSort: "text",
        mainTemplate: `
{{> header}}

{{#each commitGroups}}

### {{title}}

{{#each commits}}
- {{type}}: {{subject}} ({{hash}})
{{/each}}

{{/each}}
{{> footer}}
`,
        commitPartial: "- {{type}}: {{subject}} ({{hash}})",
      },
    },
  },
};
