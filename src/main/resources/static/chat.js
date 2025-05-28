const token = localStorage.getItem('token');
let currentResponseId = null;

function getUserIdFromToken() {
    if (!token) {
        console.error('Token is missing in localStorage');
        return null;
    }
    try {
        const tokenPayload = JSON.parse(atob(token.split('.')[1]));
        console.log('Token payload:', tokenPayload);
        return tokenPayload.id || null;
    } catch (e) {
        console.error('Error parsing token:', e.message);
        return null;
    }
}

async function fetchUnreadCount(responseId) {
    const userId = getUserIdFromToken();
    if (!userId) {
        console.warn('User ID not found in token, skipping fetchUnreadCount');
        return 0;
    }
    try {
        const response = await fetch(`http://localhost:8080/api/notifications/count?responseId=${responseId}&userId=${userId}`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
        if (!response.ok) {
            console.error('Failed to fetch unread count:', await response.text());
            return 0;
        }
        return await response.json();
    } catch (error) {
        console.error('Error fetching unread count:', error.message);
        return 0;
    }
}

async function openChat(responseId, refreshCallback) {
    if (!responseId) {
        console.error('responseId is undefined or null');
        alert('Ошибка: не указан ID отклика.');
        return;
    }
    console.log('Opening chat with responseId:', responseId);
    currentResponseId = responseId;
    const chatContainer = document.getElementById('chat-container');
    chatContainer.classList.add('active');

    try {
        const userId = getUserIdFromToken();
        if (!userId) {
            throw new Error('User ID not found in token');
        }
        const markReadResponse = await fetch('http://localhost:8080/api/messages/markRead', {
            method: 'POST',
            headers: { 'Authorization': `Bearer ${token}`, 'Content-Type': 'application/json' },
            body: JSON.stringify({ responseId: responseId, userId: userId })
        });
        if (!markReadResponse.ok) {
            const errorText = await markReadResponse.text();
            throw new Error(`HTTP error marking messages as read! Status: ${markReadResponse.status}, Message: ${errorText}`);
        }
        console.log(`Messages marked as read for responseId: ${responseId}`);

        await loadMessages(responseId);
        refreshCallback();
    } catch (error) {
        console.error(`Error marking messages as read for responseId: ${responseId}:`, error.message);
        alert(error.message);
    }
}

async function loadMessages(responseId) {
    try {
        const response = await fetch(`http://localhost:8080/api/messages?responseId=${responseId}`, {
            headers: { 'Authorization': `Bearer ${token}`, 'Cache-Control': 'no-cache' }
        });
        if (!response.ok) throw new Error('Ошибка загрузки сообщений');
        const messages = await response.json();
        console.log('Loaded messages for responseId', responseId, ':', JSON.stringify(messages, null, 2));
        const userEmail = getUserEmail();
        console.log('Current user email:', userEmail);
        const chatMessages = document.getElementById('chat-messages');
        chatMessages.innerHTML = messages.length > 0 ? messages.map(msg => {
            console.log('Message sender email:', msg.sender.email);
            // Извлекаем email из msg.sender.email
            const senderEmail = msg.sender && msg.sender.email ? msg.sender.email.toLowerCase() : '';
            const currentUserEmail = userEmail ? userEmail.toLowerCase() : '';
            const isSender = senderEmail === currentUserEmail && senderEmail !== '';
            return `
                <div class="chat-message ${isSender ? 'sender' : 'receiver'}">
                    ${msg.content}
                    <div class="timestamp">${new Date(msg.sentAt).toLocaleString()}</div>
                </div>
            `;
        }).join('') : '<p class="text-gray-500 text-center">Нет сообщений</p>';
        chatMessages.scrollTop = chatMessages.scrollHeight;
    } catch (error) {
        console.error(`Error loading messages for responseId: ${responseId}:`, error.message);
        alert(error.message);
    }
}

async function sendMessage() {
    const input = document.getElementById('chat-input');
    const message = input.value.trim();
    if (message && currentResponseId) {
        try {
            const userId = getUserIdFromToken();
            if (!userId) {
                throw new Error('User ID not found in token');
            }
            const responseData = await fetch(`http://localhost:8080/api/responses/${currentResponseId}`, {
                headers: { 'Authorization': `Bearer ${token}` }
            });
            if (!responseData.ok) {
                const errorText = await responseData.text();
                throw new Error(`Failed to fetch response data: ${errorText}`);
            }
            const response = await responseData.json();
            console.log('Response data:', response);
            const receiverId = userId === response.applicantId ? response.employerId : response.applicantId;
            if (!receiverId) throw new Error('Receiver ID not found for this response');

            const responseSend = await fetch('http://localhost:8080/api/messages', {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    sender: { id: userId },
                    receiver: { id: receiverId },
                    response: { id: currentResponseId },
                    content: message
                })
            });
            if (!responseSend.ok) {
                const errorText = await responseSend.text();
                throw new Error(`Ошибка отправки сообщения: ${responseSend.status} - ${errorText}`);
            }
            console.log('Message sent successfully for responseId:', currentResponseId);
            input.value = '';
            loadMessages(currentResponseId);
        } catch (error) {
            console.error('Error sending message for responseId:', currentResponseId, error.message);
            alert(error.message);
        }
    } else {
        console.warn('Cannot send message: message or responseId is missing');
        alert('Введите сообщение и выберите отклик для отправки.');
    }
}

function getUserEmail() {
    if (!token) {
        console.error('Token is missing in localStorage');
        return 'default@example.com';
    }
    try {
        const tokenPayload = JSON.parse(atob(token.split('.')[1]));
        return tokenPayload.sub || 'default@example.com';
    } catch (e) {
        console.error('Error parsing token:', e.message);
        return 'default@example.com';
    }
}