const { execSync } = require("child_process");
const { writeFileSync, chmodSync } = require("fs");
const path = require("path");

// Parse arguments to get the Node.js binary path
const args = process.argv.slice(2);
const nodePathArgIndex = args.indexOf("--node-path");
if (nodePathArgIndex === -1 || !args[nodePathArgIndex + 1]) {
  console.error("Error: Missing --node-path argument");
  process.exit(1);
}
const nodePath = args[nodePathArgIndex + 1];

try {
  console.log("Installing Husky...");

  // Use the Node.js binary to execute the Husky Node.js file directly
  const huskyBinPath = path.resolve("node_modules", "husky", "lib", "bin.js");
  console.log("Husky bin.js path:", huskyBinPath);

  // Run the Husky installation command
  execSync(`"${nodePath}" "${huskyBinPath}" install`, { stdio: "inherit" });

  console.log("Adding commit-msg hook...");

  // Resolve the full path to the @commitlint/cli/lib/cli.js file
  const commitLintPath = path.resolve(
    "node_modules",
    "@commitlint",
    "cli",
    "lib",
    "cli.js"
  );
  console.log("Commitlint CLI path:", commitLintPath);

  // Convert Node.js path for Git Bash compatibility
  let gitBashNodePath = nodePath;
  if (process.platform === "win32") {
    gitBashNodePath = nodePath.replace(/\\/g, "/").replace(/^([A-Za-z]):/, "/$1");
  }
  console.log("Node.js path for Git Bash:", gitBashNodePath);

  // Create the commit-msg hook content
  const commitMsgHook = `
#!/bin/sh
. "$(dirname "$0")/_/husky.sh"

# Use Node.js for Husky and Commitlint
export PATH="./node_modules/.bin:$PATH"
exec ${gitBashNodePath} ${commitLintPath.replace(/\\/g, "/")} --edit $1
`;

  // Write the commit-msg hook file
  const hookPath = path.resolve(".husky/commit-msg");
  console.log("Writing commit-msg hook to:", hookPath);
  writeFileSync(hookPath, commitMsgHook.trim());

  // Set executable permissions for Unix-based systems
  if (process.platform !== "win32") {
    chmodSync(hookPath, 0o755);
  } else {
    console.log("Skipping chmod on Windows. Git Bash handles executable permissions via shebang.");
  }

  console.log("Husky setup complete.");
} catch (error) {
  console.error("Error setting up Husky:", error.message);
  console.error("Stack Trace:", error.stack);
  process.exit(1);
}