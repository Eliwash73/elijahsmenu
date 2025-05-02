document.addEventListener("DOMContentLoaded", function () {
  const toggle = document.getElementById("toggle-dark-mode");

  // Load saved preference
  if (localStorage.getItem("darkMode") === "true") {
    document.body.classList.add("dark-mode");
  }

  toggle.addEventListener("click", function () {
    document.body.classList.toggle("dark-mode");

    // Save preference
    const isDarkMode = document.body.classList.contains("dark-mode");
    localStorage.setItem("darkMode", isDarkMode);
  });
});
