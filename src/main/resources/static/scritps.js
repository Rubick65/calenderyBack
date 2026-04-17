window.onload = confirmRegistration

const registrationConfirmEndpoint = 'https://calenderyback.onrender.com/api/users/registrationConfirm?token='

const VERIFICATION_ERROR_TEXT = "Something went wrong, please try again"

const SUCCESS_TITLE_TEXT = "Registration complete"
const SUCCESS_MESSAGES_TEXT = "Your registration is completed please go back to the application."

const titleText = document.getElementById("verification-title")
const messageText = document.getElementById("verification-message")

function confirmRegistration() {
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('token');

    if (token)
        sendToken(token).then(r => null);
}

async function sendToken(token) {
    try {
        const response = await fetch(
            registrationConfirmEndpoint + token,
            {
                method: 'GET'
            }
        );

        if (response.ok)
            changeToSuccessText()
        else
            throw new Error(`Error del servidor: ${response.status}`);

        console.log("STATUS:", response.status);

    } catch (error) {
        changeToErrorText(error)
        console.error('Error:', error);
    }
}

function changeToSuccessText() {
    titleText.innerText = SUCCESS_TITLE_TEXT
    messageText.textContent = SUCCESS_MESSAGES_TEXT
}

function changeToErrorText(errorMsg) {
    titleText.textContent = VERIFICATION_ERROR_TEXT;
    messageText.textContent = errorMsg;
}
