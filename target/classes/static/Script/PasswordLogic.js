
    const passwordInput = document.getElementByClassName('password');
    const passwordHelp = document.getElementById('passwordHelp');

    const pattern = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{8,}$/;

    passwordInput.addEventListener('input', function() {
        const value = passwordInput.value;
        if (pattern.test(value)) {
            passwordHelp.textContent = "Password is strong.";
            passwordHelp.classList.add('valid');
            passwordHelp.classList.remove('invalid');
        } else {
            passwordHelp.textContent = "Password Must have at least 8 characters, one uppercase letter, one number, and one special character.";
            passwordHelp.classList.add('invalid');
            passwordHelp.classList.remove('valid');
        }
    });

