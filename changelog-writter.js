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

    // Format type for display
    commit.typeFormatted = commit.type.charAt(0).toUpperCase() + commit.type.slice(1);

    return commit;
  },
  groupBy: "type", // Group commits by `section` field
  commitPartial: "- {{typeFormatted}}: {{}} {{subject}} {{#if pullRequestLink}}({{pullRequestLink}}){{else}}({{commitLink}}){{/if}}",
  mainTemplate: `
    {{> header}}

    {{#each commitGroups}}
    ## {{section}} <!-- Section Title -->
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
