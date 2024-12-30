const writerOpts = {
  transform: (commit, context) => {
    // Map PR numbers to links
    if (commit.pullRequest && commit.pullRequest.number) {
      commit.pullRequestLink = `[PR #${commit.pullRequest.number}](${context.repository}/pull/${commit.pullRequest.number})`;
    }

    // Map commit hashes to links
    if (commit.hash) {
      commit.commitLink = `[${commit.hash.substring(0, 7)}](${context.repository}/commit/${commit.hash})`;
    }

    // Define sections for grouping
    const typeToSectionMap = {
      "BREAKING CHANGE": "⚠️ Major Changes",
      refactor: "⚠️ Major Changes",
      fix: "🐛 Bug Fixes",
      hotfix: "🐛 Bug Fixes",
      feat: "✨ New Features",
      docs: "🛠️ Miscellaneous",
      style: "🛠️ Miscellaneous",
      test: "🛠️ Miscellaneous",
      chore: "🛠️ Miscellaneous",
    };

    // Add section to commit if type matches
    commit.section = typeToSectionMap[commit.type] || "Uncategorized";

    return commit;
  },
  groupBy: "section", // Group by section
  commitPartial: "- {{type}}: {{subject}} {{#if pullRequestLink}}({{pullRequestLink}}){{else}}({{commitLink}}){{/if}}",
  mainTemplate: `
    {{> header}}

    {{#each groups}}
    ## {{title}}
    {{#each commits}}
    {{> commit}}
    {{/each}}
    {{/each}}
  `,
  headerPartial: `
    # DekTek Changelog
    All notable changes are listed below.
  `,
  groupedCommitPartial: "- {{subject}} {{#if pullRequestLink}}({{pullRequestLink}}){{else}}({{commitLink}}){{/if}}",
};

module.exports = writerOpts;