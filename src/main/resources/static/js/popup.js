function showPopup(message, type) {
    // Create popup element
    const popup = document.createElement('div');
    popup.className = `popup ${type}-popup`;
    
    // Create popup content
    const content = document.createElement('div');
    content.className = 'popup-content';
    
    // Create close button
    const closeBtn = document.createElement('span');
    closeBtn.className = 'close';
    closeBtn.innerHTML = '&times;';
    
    // Create message paragraph
    const messageP = document.createElement('p');
    messageP.textContent = message;
    
    // Assemble popup
    content.appendChild(closeBtn);
    content.appendChild(messageP);
    popup.appendChild(content);
    document.body.appendChild(popup);
    
    // Show popup
    popup.style.display = 'block';
    
    // Auto hide after 3 seconds
    setTimeout(() => {
        popup.style.display = 'none';
        popup.remove();
    }, 3000);
    
    // Close button functionality
    closeBtn.addEventListener('click', () => {
        popup.style.display = 'none';
        popup.remove();
    });
}

// Function to show success popup
function showSuccessPopup(message) {
    showPopup(message, 'success');
}

// Function to show error popup
function showErrorPopup(message) {
    showPopup(message, 'error');
} 