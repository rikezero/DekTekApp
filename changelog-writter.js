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

    // Define sections based on commit type
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

    // Assign section to the commit
    commit.section = typeToSectionMap[commit.type] || "Other";

    // Format the type for display
    commit.typeFormatted = commit.type.charAt(0).toUpperCase() + commit.type.slice(1);

    return commit;
  },
  groupBy: "section", // Group by the `section` field
  commitGroupsSort: "title", // Sort groups alphabetically
  commitsSort: ["scope", "subject"], // Sort commits within each group
  commitPartial: "- {{typeFormatted}}: {{subject}} {{#if pullRequestLink}}({{pullRequestLink}}){{else}}({{commitLink}}){{/if}}",
  mainTemplate: `
    {{> header}}

    {{#if commitGroups}}
      {{#each commitGroups}}
        ### {{title}}
        {{#each commits}}
          {{> commit}}
        {{/each}}
      {{/each}}
    {{/if}}
  `,
  headerPartial: `
    # Changelog
    All notable changes are listed below.
  `,
  footerPartial: ``
};

module.exports = writerOpts;