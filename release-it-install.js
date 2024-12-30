const { execSync } = require("child_process");

try {
  console.log("Installing release-it and @release-it/conventional-changelog...");

  // Install release-it as a dev dependency
  console.log("Installing release-it...");
  execSync("npm install -D release-it", { stdio: "inherit" });

  // Install @release-it/conventional-changelog plugin as a dev dependency
  console.log("Installing @release-it/conventional-changelog...");
  execSync("npm install --save-dev @release-it/conventional-changelog", { stdio: "inherit" });

  console.log("Installation of release-it and plugin complete.");
} catch (error) {
  console.error("Error during installation:", error.message);
  console.error("Stack Trace:", error.stack);
  process.exit(1);
}
