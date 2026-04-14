window.onload = confirmRegistration

function confirmRegistration() {
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('token');
    console.log("Hello")

    if (token) {
        sendToken(token).then(r => null);
    }
}

async function sendToken(token) {
    try {
        const response = await fetch(
            'https://calenderyback.onrender.com/api/users/registrationConfirm?token=' + token,
            {
                method: 'GET'
            }
        );

        console.log("STATUS:", response.status);

        if (!response.ok) {
            throw new Error("Error HTTP: " + response.status);
        }

        const data = await response.json();
        console.log(data);

    } catch (error) {
        console.error('Error:', error);
    }
}
