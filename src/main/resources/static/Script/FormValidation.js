// Form Validation System for Code Fight Platform

document.addEventListener('DOMContentLoaded', () => {
    // Validation Rules
    const validationRules = {
        username: {
            minLength: 6,
            maxLength: 20,
            pattern: /^[a-zA-Z0-9]+$/,
            message: 'Username must be 6-20 characters, letters and numbers only'
        },
        email: {
            pattern: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
            message: 'Please enter a valid email address'
        },
        password: {
            minLength: 8,
            requireUppercase: true,
            requireLowercase: true,
            requireNumber: true,
            requireSpecial: false,
            message: 'Password must be at least 8 characters with uppercase, lowercase, and number'
        }
    };

    // Initialize validation for all forms
    initializeValidation();

    function initializeValidation() {
        // Register form validation
        const usernameRegister = document.getElementById('usernameRegister');
        const emailRegister = document.getElementById('emailRegister');
        const passwordRegister = document.getElementById('passwordRegister');

        // Login form validation
        const usernameLogin = document.getElementById('usernameLogin');
        const passwordLogin = document.getElementById('passwordLogin');

        // Register form validators
        if (usernameRegister) {
            setupFieldValidation(usernameRegister, validateUsername, 'username');
        }
        if (emailRegister) {
            setupFieldValidation(emailRegister, validateEmail, 'email');
        }
        if (passwordRegister) {
            setupFieldValidation(passwordRegister, validatePassword, 'password');
        }

        // Login form validators
        if (usernameLogin) {
            setupFieldValidation(usernameLogin, validateUsername, 'username');
        }
        if (passwordLogin) {
            setupFieldValidation(passwordLogin, validatePasswordLogin, 'password');
        }
    }

    function setupFieldValidation(input, validator, fieldType) {
        const inputBox = input.closest('.Input-box');
        if (!inputBox) return;

        // Create a container for validation messages outside the Input-box
        let validationContainer = inputBox.parentElement.querySelector(`.validation-container[data-field="${input.id}"]`);
        if (!validationContainer) {
            validationContainer = document.createElement('div');
            validationContainer.className = 'validation-container';
            validationContainer.setAttribute('data-field', input.id);
            // Insert after the Input-box
            inputBox.parentElement.insertBefore(validationContainer, inputBox.nextSibling);
        }

        // Create error message element if it doesn't exist
        let errorMsg = validationContainer.querySelector('.error-message');
        if (!errorMsg) {
            errorMsg = document.createElement('div');
            errorMsg.className = 'error-message';
            validationContainer.appendChild(errorMsg);
        }

        // Create validation rules display if it doesn't exist
        let rulesDisplay = validationContainer.querySelector('.validation-rules');
        if (!rulesDisplay && fieldType !== 'passwordLogin') {
            rulesDisplay = document.createElement('div');
            rulesDisplay.className = 'validation-rules';
            rulesDisplay.style.display = 'none'; // Initially hidden
            validationContainer.appendChild(rulesDisplay);
            showValidationRules(rulesDisplay, fieldType);
        }

        // Show rules on focus if field is empty
        input.addEventListener('focus', () => {
            if (!input.value && rulesDisplay) {
                rulesDisplay.style.display = 'block';
            }
        });

        // Real-time validation on input
        input.addEventListener('input', () => {
            const isValid = validator(input.value);
            updateFieldValidation(input, inputBox, errorMsg, isValid, fieldType);
        });

        // Validation on blur
        input.addEventListener('blur', () => {
            const isValid = validator(input.value);
            updateFieldValidation(input, inputBox, errorMsg, isValid, fieldType);
        });
    }

    function validateUsername(value) {
        if (!value) return null; // Don't show error for empty field until blur
        const rules = validationRules.username;
        if (value.length < rules.minLength || value.length > rules.maxLength) {
            return false;
        }
        if (!rules.pattern.test(value)) {
            return false;
        }
        return true;
    }

    function validateEmail(value) {
        if (!value) return null;
        return validationRules.email.pattern.test(value);
    }

    function validatePassword(value) {
        if (!value) return null;
        const rules = validationRules.password;
        
        if (value.length < rules.minLength) return false;
        if (rules.requireUppercase && !/[A-Z]/.test(value)) return false;
        if (rules.requireLowercase && !/[a-z]/.test(value)) return false;
        if (rules.requireNumber && !/\d/.test(value)) return false;
        
        return true;
    }

    function validatePasswordLogin(value) {
        // For login, just check if password is not empty
        return value.length > 0;
    }

    function updateFieldValidation(input, inputBox, errorMsg, isValid, fieldType) {
        // Get validation container
        const validationContainer = inputBox.parentElement.querySelector(`.validation-container[data-field="${input.id}"]`);
        const rulesDisplay = validationContainer ? validationContainer.querySelector('.validation-rules') : null;
        
        if (isValid === null) {
            // Field is empty, remove validation state
            inputBox.classList.remove('valid', 'invalid');
            if (errorMsg) {
                errorMsg.textContent = '';
                errorMsg.style.display = 'none';
            }
            // Show rules when field is empty or focused
            if (rulesDisplay) {
                rulesDisplay.style.display = 'block';
            }
            return;
        }

        if (isValid) {
            inputBox.classList.add('valid');
            inputBox.classList.remove('invalid');
            if (errorMsg) {
                errorMsg.textContent = '';
                errorMsg.style.display = 'none';
            }
            // Hide rules when field is valid
            if (rulesDisplay) {
                rulesDisplay.style.display = 'none';
            }
        } else {
            inputBox.classList.add('invalid');
            inputBox.classList.remove('valid');
            if (errorMsg) {
                errorMsg.textContent = getErrorMessage(fieldType, input.value);
                errorMsg.style.display = 'block';
            }
            // Hide rules when showing error to avoid overlap
            if (rulesDisplay) {
                rulesDisplay.style.display = 'none';
            }
        }
    }

    function getErrorMessage(fieldType, value) {
        const rules = validationRules[fieldType];
        if (!rules) return 'Invalid input';

        switch (fieldType) {
            case 'username':
                if (!value) return rules.message;
                if (value.length < rules.minLength) {
                    return `Username must be at least ${rules.minLength} characters`;
                }
                if (value.length > rules.maxLength) {
                    return `Username must be no more than ${rules.maxLength} characters`;
                }
                if (!rules.pattern.test(value)) {
                    return 'Username can only contain letters and numbers';
                }
                return rules.message;
            case 'email':
                return rules.message;
            case 'password':
                if (!value) return rules.message;
                if (value.length < rules.minLength) {
                    return `Password must be at least ${rules.minLength} characters`;
                }
                if (rules.requireUppercase && !/[A-Z]/.test(value)) {
                    return 'Password must contain at least one uppercase letter';
                }
                if (rules.requireLowercase && !/[a-z]/.test(value)) {
                    return 'Password must contain at least one lowercase letter';
                }
                if (rules.requireNumber && !/\d/.test(value)) {
                    return 'Password must contain at least one number';
                }
                return rules.message;
            default:
                return 'Invalid input';
        }
    }

    function showValidationRules(rulesDisplay, fieldType) {
        const rules = validationRules[fieldType];
        if (!rules) return;

        let html = '<div class="rules-title">Requirements:</div><ul class="rules-list">';
        
        switch (fieldType) {
            case 'username':
                html += `<li>6-20 characters</li>`;
                html += `<li>Letters and numbers only</li>`;
                html += `<li>No symbols or spaces</li>`;
                break;
            case 'email':
                html += `<li>Valid email format</li>`;
                html += `<li>Example: user@example.com</li>`;
                break;
            case 'password':
                html += `<li>At least ${rules.minLength} characters</li>`;
                html += `<li>One uppercase letter (A-Z)</li>`;
                html += `<li>One lowercase letter (a-z)</li>`;
                html += `<li>One number (0-9)</li>`;
                break;
        }
        
        html += '</ul>';
        rulesDisplay.innerHTML = html;
    }

    // Form submission validation
    window.validateRegisterForm = function() {
        const username = document.getElementById('usernameRegister');
        const email = document.getElementById('emailRegister');
        const password = document.getElementById('passwordRegister');
        const termsCheckbox = document.querySelector('.T');

        let isValid = true;
        const errors = [];

        // Validate username
        if (!username || !validateUsername(username.value)) {
            isValid = false;
            errors.push('Invalid username');
            if (username) {
                const inputBox = username.closest('.Input-box');
                if (inputBox) {
                    inputBox.classList.add('invalid');
                    const validationContainer = inputBox.parentElement.querySelector(`.validation-container[data-field="${username.id}"]`);
                    const errorMsg = validationContainer ? validationContainer.querySelector('.error-message') : null;
                    if (errorMsg) {
                        errorMsg.textContent = validationRules.username.message;
                        errorMsg.style.display = 'block';
                    }
                }
            }
        }

        // Validate email
        if (!email || !validateEmail(email.value)) {
            isValid = false;
            errors.push('Invalid email');
            if (email) {
                const inputBox = email.closest('.Input-box');
                if (inputBox) {
                    inputBox.classList.add('invalid');
                    const validationContainer = inputBox.parentElement.querySelector(`.validation-container[data-field="${email.id}"]`);
                    const errorMsg = validationContainer ? validationContainer.querySelector('.error-message') : null;
                    if (errorMsg) {
                        errorMsg.textContent = validationRules.email.message;
                        errorMsg.style.display = 'block';
                    }
                }
            }
        }

        // Validate password
        if (!password || !validatePassword(password.value)) {
            isValid = false;
            errors.push('Invalid password');
            if (password) {
                const inputBox = password.closest('.Input-box');
                if (inputBox) {
                    inputBox.classList.add('invalid');
                    const validationContainer = inputBox.parentElement.querySelector(`.validation-container[data-field="${password.id}"]`);
                    const errorMsg = validationContainer ? validationContainer.querySelector('.error-message') : null;
                    if (errorMsg) {
                        errorMsg.textContent = validationRules.password.message;
                        errorMsg.style.display = 'block';
                    }
                }
            }
        }

        // Validate terms checkbox
        if (!termsCheckbox || !termsCheckbox.checked) {
            isValid = false;
            errors.push('You must accept the terms and conditions');
        }

        if (!isValid) {
            showGameError('Please fix the errors in the form');
        }

        return isValid;
    };

    window.validateLoginForm = function() {
        const username = document.getElementById('usernameLogin');
        const password = document.getElementById('passwordLogin');

        let isValid = true;

        // Validate username
        if (!username || !username.value || !validateUsername(username.value)) {
            isValid = false;
            if (username) {
                const inputBox = username.closest('.Input-box');
                if (inputBox) {
                    inputBox.classList.add('invalid');
                    const validationContainer = inputBox.parentElement.querySelector(`.validation-container[data-field="${username.id}"]`);
                    const errorMsg = validationContainer ? validationContainer.querySelector('.error-message') : null;
                    if (errorMsg) {
                        errorMsg.textContent = username.value ? validationRules.username.message : 'Username is required';
                        errorMsg.style.display = 'block';
                    }
                }
            }
        }

        // Validate password
        if (!password || !password.value) {
            isValid = false;
            if (password) {
                const inputBox = password.closest('.Input-box');
                if (inputBox) {
                    inputBox.classList.add('invalid');
                    const validationContainer = inputBox.parentElement.querySelector(`.validation-container[data-field="${password.id}"]`);
                    const errorMsg = validationContainer ? validationContainer.querySelector('.error-message') : null;
                    if (errorMsg) {
                        errorMsg.textContent = 'Password is required';
                        errorMsg.style.display = 'block';
                    }
                }
            }
        }

        if (!isValid) {
            showGameError('Please fill in all required fields correctly');
        }

        return isValid;
    };
});

// Game-style error message display
function showGameError(message, duration = 5000) {
    // Remove existing error message if any
    const existingError = document.querySelector('.game-error-message');
    if (existingError) {
        existingError.remove();
    }

    // Create error message element
    const errorDiv = document.createElement('div');
    errorDiv.className = 'game-error-message';
    errorDiv.innerHTML = `
        <div class="error-icon">⚠</div>
        <div class="error-text">${message}</div>
        <button class="error-close" onclick="this.parentElement.remove()">×</button>
    `;

    document.body.appendChild(errorDiv);

    // Animate in
    setTimeout(() => {
        errorDiv.classList.add('show');
    }, 10);

    // Auto remove after duration
    setTimeout(() => {
        errorDiv.classList.remove('show');
        setTimeout(() => {
            if (errorDiv.parentElement) {
                errorDiv.remove();
            }
        }, 300);
    }, duration);
}

// Game-style success message display
function showGameSuccess(message, duration = 3000) {
    const existingSuccess = document.querySelector('.game-success-message');
    if (existingSuccess) {
        existingSuccess.remove();
    }

    const successDiv = document.createElement('div');
    successDiv.className = 'game-success-message';
    successDiv.innerHTML = `
        <div class="success-icon">✓</div>
        <div class="success-text">${message}</div>
        <button class="success-close" onclick="this.parentElement.remove()">×</button>
    `;

    document.body.appendChild(successDiv);

    setTimeout(() => {
        successDiv.classList.add('show');
    }, 10);

    setTimeout(() => {
        successDiv.classList.remove('show');
        setTimeout(() => {
            if (successDiv.parentElement) {
                successDiv.remove();
            }
        }, 300);
    }, duration);
}

// Export for use in other scripts
window.showGameError = showGameError;
window.showGameSuccess = showGameSuccess;

