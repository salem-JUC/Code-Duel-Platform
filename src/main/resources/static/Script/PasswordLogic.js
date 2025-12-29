document.addEventListener('DOMContentLoaded', () => {
    const passwordInput = document.getElementById('passwordRegister');
    const passwordHelp = document.getElementById('passwordHelp');

    if (!passwordInput || !passwordHelp) return;

    // This will be handled by FormValidation.js, but we keep this for backward compatibility
    // The validation rules are now in FormValidation.js
    passwordInput.addEventListener('input', function() {
        // Validation is now handled by FormValidation.js
        // This script can be used for additional password strength indicators if needed
    });
});
