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
  groupBy: false, // No grouping; list sequentially
  commitPartial: "- {{typeFormatted}}: {{subject}} {{#if pullRequestLink}}({{pullRequestLink}}){{else}}({{commitLink}}){{/if}}",
  mainTemplate: `
    {{> header}}
    {{> commits}}
  `,
  headerPartial: `
    # Changelog
    All notable changes are listed below.
  `,
  footerPartial: ``
};

module.exports = writerOpts;
