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
            strictSemVer: true,
            infile: "CHANGELOG.md",
            header: "# DekTek Changelog\n\nAll notable changes are listed below.",
            preset: {
                name: "conventionalcommits",
                types: [
                    { type: "BREAKING CHANGE", section: "⚠️ Major Changes" },
                    { type: "refactor", section: "⚠️ Major Changes" },
                    { type: "fix", section: "🐛 Bug Fixes" },
                    { type: "hotfix", section: "🐛 Bug Fixes" },
                    { type: "feat", section: "✨ New Features" },
                    { type: "docs", section: "🛠️ Miscellaneous" },
                    { type: "style", section: "🛠️ Miscellaneous" },
                    { type: "test", section: "🛠️ Miscellaneous" },
                    { type: "chore", section: "🛠️ Miscellaneous" },
                ]
            },
            writerOpts: {
                transform: (commit, context) => {
                    const typeToSectionMap = {
                        "BREAKING CHANGE": "⚠️ Major Changes",
                        refactor: "⚠️ Major Changes",
                        hotfix: "🐛 Bug Fixes",
                        fix: "🐛 Bug Fixes",
                        feat: "✨ New Features",
                        chore: "🛠️ Miscellaneous",
                        docs: "🛠️ Miscellaneous",
                        style: "🛠️ Miscellaneous",
                        test: "🛠️ Miscellaneous",
                    };

                    commit.section = typeToSectionMap[commit.type] || "Other";

                    if (commit.pullRequest && commit.pullRequest.number) {
                        commit.pullRequestLink = `[PR #${commit.pullRequest.number}](${context.repository}/pull/${commit.pullRequest.number})`;
                    }
                    if (commit.hash) {
                        commit.commitLink = `[${commit.hash.substring(0, 7)}](${context.repository}/commit/${commit.hash})`;
                    }

                    commit.typeFormatted = commit.type.charAt(0).toUpperCase() + commit.type.slice(1);

                    return commit;
                },
                finalizeContext: (ctx) => {
                    const commitGroups = (ctx && ctx.commitGroups ? ctx.commitGroups : [])
                        .map((group) => {
                            const INCLUDED_SECTIONS = [
                                "⚠️ Major Changes",
                                "🐛 Bug Fixes",
                                "✨ New Features",
                                "🛠️ Miscellaneous",
                            ];

                        const commits = group.commits.filter((commit) => {
                            return INCLUDED_SECTIONS.includes(commit.scope);
                        });

                        return { ...group, commits };
                        });

                    return { ...ctx, commitGroups };
                },
            },
        },
    },
};