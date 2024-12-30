const writerOpts = {
  transform: (commit, context) => {
    const typeToSectionMap = {
      "BREAKING CHANGE": "⚠️ Major Changes",
      "refactor": "⚠️ Major Changes",
      "fix": "🐛 Bug Fixes",
      "hotfix": "🐛 Bug Fixes",
      "feat": "✨ New Features",
      "docs": "🛠️ Miscellaneous",
      "style": "🛠️ Miscellaneous",
      "test": "🛠️ Miscellaneous",
      "chore": "🛠️ Miscellaneous",
    };

    // Assign section based on type
    commit.section = typeToSectionMap[commit.type] || "🛠️ Miscellaneous";

    // Map PR numbers to links
    if (commit.pullRequest && commit.pullRequest.number) {
      commit.pullRequestLink = `[PR #${commit.pullRequest.number}](${context.repository}/pull/${commit.pullRequest.number})`;
    }

    // Map commit hashes to links
    if (commit.hash) {
      commit.commitLink = `[${commit.hash.substring(0, 7)}](${context.repository}/commit/${commit.hash})`;
    }

    // Format type for display
    commit.typeFormatted = commit.type.charAt(0).toUpperCase() + commit.type.slice(1);

    return commit;
  },
  groupBy: "section", // Group commits by `section` field
  commitPartial: "- {{typeFormatted}}: {{subject}} {{#if pullRequestLink}}({{pullRequestLink}}){{else}}({{commitLink}}){{/if}}",
  mainTemplate: `
    {{> header}}

    {{#each groups}}
    ## {{title}} <!-- Section Title -->
    {{#each commits}}
    {{> commit}}
    {{/each}}
    {{/each}}
  `,
  headerPartial: `
    # DekTek Changelog
    All notable changes are listed below.
  `,
  footerPartial: ``,
};

module.exports = writerOpts;
