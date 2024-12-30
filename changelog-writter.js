const Handlebars = require("handlebars");

// Register the `or` helper
Handlebars.registerHelper("or", (a, b1, b2) => a === b1 || a === b2);

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

    // Format the type for display
    commit.typeFormatted = commit.type.charAt(0).toUpperCase() + commit.type.slice(1);

    return commit;
  },
  groupBy: "type", // Group commits by type
  commitGroupsSort: "title", // Sort groups by title
  commitsSort: ["type", "scope"], // Sort commits within groups
  commitPartial: "- {{typeFormatted}}: {{subject}} {{#if pullRequestLink}}({{pullRequestLink}}){{else}}({{commitLink}}){{/if}}",
  mainTemplate: `
    {{> header}}
    {{#each commitGroups}}
      {{#if (eq this.title "feat")}}
        ✨ New Features
      {{else if (eq this.title "BREAKING CHANGE" "refactor")}}
        ⚠️ Major Changes
      {{else if (or this.title "fix" "hotfix")}}
        🐛 Bug Fixes
      {{else}}
        🛠️ Miscellaneous
      {{/if}}
      {{#each commits}}
        {{> commit}}
      {{/each}}
    {{/each}}
    {{> footer}}
  `,
  headerPartial: `
    # DekTek Changelog
    All notable changes are listed below.
  `,
  footerPartial: ``,
};

module.exports = writerOpts;