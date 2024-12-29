module.exports = {
  extends: ['@commitlint/config-conventional'],
  rules: {
    'type-enum': [
      2, // Error level (2 = error, 1 = warning, 0 = disabled)
      'always', // Rule applies always
      [
        'feat',    // A new feature
        'hotfix',    // A bug hotfix
        'fix',     // A bug fix
        'docs',    // Documentation only changes
        'style',   // Code style changes (formatting, no logic change)
        'refactor', // Refactoring code (no feature or bug fix)
        'test',    // Adding or updating tests
        'chore',   // Changes to the build process or auxiliary tools
        'BREAKING CHANGE', //A major feature, refactor or general change that is large and breakes a lot of places
      ],
    ],
    'type-case': [2, 'always', 'lower-case'], // Type must be lower-case
    'type-empty': [2, 'never'], // Type must not be empty
    'scope-empty': [0], // Disable the requirement for a scope
    'subject-empty': [2, 'never'], // Subject must not be empty
    'subject-full-stop': [2, 'never', '.'], // No full stop at the end of the subject
    'header-max-length': [2, 'always', 98], // Max header length is 72 characters
  }
};
