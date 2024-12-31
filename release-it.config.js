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
        '@release-it/conventional-changelog': {
            strictSemVer: true,
            infile: "CHANGELOG.md",
            header: "# DekTek Changelog\n\nAll notable changes are listed below.",
            preset: {
                name: "conventionalcommits",
                types: [
                    { type: 'BREAKING CHANGE', section: '⚠️ Major Changes' },
                    { type: 'refactor', section: '⚠️ Major Changes' },
                    { type: 'fix', section: '🐛 Bug Fixes' },
                    { type: 'hotfix', section: '🐛 Bug Fixes' },
                    { type: 'feat', section: '✨ New Features' },
                    { type: 'docs', section: '🛠️ Miscellaneous' },
                    { type: 'style', section: '🛠️ Miscellaneous' },
                    { type: 'test', section: '🛠️ Miscellaneous' },
                    { type: 'chore', section: '🛠️ Miscellaneous' },
                ]
            },
            writerOpts: {
                transform: (commit, context) => {
                    if (commit.pullRequest?.number) {
                        commit.pullRequestLink = `[PR #${commit.pullRequest.number}](${context.repository}/pull/${commit.pullRequest.number})`;
                    }
                    if (commit.hash) {
                        commit.commitLink = `[${commit.hash.substring(0, 7)}](${context.repository}/commit/${commit.hash})`;
                    }
                    if (['BREAKING CHANGE', 'refactor'].includes(commit.type)) {
                        commit.section = "Major Changes";
                    } else if (['fix', 'hotfix'].includes(commit.type)) {
                        commit.section = 'Bug Fixes';
                    } else if (commit.type === 'feat') {
                        commit.section = 'New Features';
                    } else {
                        commit.section = 'Miscellaneous';
                    }
                    commit.subject = commit.subject?.trim();

                    return commit;
                },
                groupBy: "section",
                commitGroupsSort = (a, b) => {
                    const tags = ['⚠️ Major Changes', '🐛 Bug Fixes', '✨ New Features', '🛠️ Miscellaneous']

                    return tags.indexOf(a.title) - tags.indexOf(b.title)
                }
            }
        }
    }
};