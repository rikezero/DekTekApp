module.exports = {
  transform: (commit, context) => {
    if (commit.pullRequest?.number) {
      commit.pullRequestLink = `[PR #${commit.pullRequest.number}](${context.repository}/pull/${commit.pullRequest.number})`;
    }
    if (commit.hash) {
      commit.commitLink = `[${commit.hash.substring(0, 7)}](${context.repository}/commit/${commit.hash})`;
    }
    if (["BREAKING CHANGE", "refactor"].includes(commit.type)) {
      commit.section = "Major Changes";
    } else if (["fix", "hotfix"].includes(commit.type)) {
      commit.section = "Bug Fixes";
    } else if (commit.type === "feat") {
      commit.section = "New Features";
    } else {
      commit.section = "Miscellaneous";
    }
    commit.subject = commit.subject?.trim();

    return commit;
  },
  groupBy: "section",
  commitGroupsSort: (a, b) => {
    const order = ["Major Changes", "Bug Fixes", "New Features", "Miscellaneous"];
    return order.indexOf(a.title) - order.indexOf(b.title);
  },
  commitsSort: ["scope", "subject"],
  mainTemplate: `
{{> header}}

{{#each commitGroups}}
## {{title}}
{{#each commits}}
{{> commit}}
{{/each}}

{{/each}}
{{> footer}}
  `,
  headerPartial: `
`,
  commitPartial: `
- {{subject}}
  {{#if pullRequestLink}}
    ({{pullRequestLink}})
  {{else if commitLink}}
    ({{commitLink}})
  {{/if}}
`,
  footerPartial: `
`,
};
