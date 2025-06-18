// Cart logic for products page
let cart = [];

function toggleCart() {
    const cartItems = document.getElementById('cart-items');
    cartItems.classList.toggle('show');
}

function addToCart(product) {
    console.log(product);
    fetch('/order/add-to-cart', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            skuId: product.skuId,
            quantity: 1
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to add to cart');
        }
        return response.json();
    })
    .then(cartItem => {
        const existingItem = cart.find(item => item.skuId === cartItem.skuId);
        if (existingItem) {
            existingItem.quantity += 1;
        } else {
            cart.push(cartItem);
        }
        updateCart();
    })
    .catch(error => {
        alert('Could not add to cart: ' + error.message);
    });
}

function removeFromCart(skuId) {
    cart = cart.filter(item => item.skuId !== skuId);
    updateCart();
}

function updateCart() {
    const cartItemsList = document.getElementById('cart-items-list');
    const cartCount = document.querySelector('.cart-count');
    const cartTotal = document.getElementById('cart-total');
    
    // Update cart count
    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    cartCount.textContent = totalItems;
    
    // Update cart items
    cartItemsList.innerHTML = cart.map(item => `
        <div class="cart-item">
            <img src="${item.image}" alt="${item.name}" onerror="this.src='https://via.placeholder.com/50x50?text=No+Image'">
            <div class="cart-item-details">
                <div>${item.name}</div>
                <div class="cart-item-price">Rs ${item.price.toFixed(2)} x ${item.quantity}</div>
            </div>
            <button class="remove-from-cart-btn" data-sku-id="${item.skuId}" style="color: red; border: none; background: none; cursor: pointer;">✕</button>
        </div>
    `).join('');
    
    // Update total
    const total = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
    cartTotal.textContent = total.toFixed(2);
}

document.addEventListener('DOMContentLoaded', function() {
    // Fetch and render products
    fetchProducts();

    // Cart icon click
    document.querySelector('.cart-icon').addEventListener('click', function(e) {
        e.stopPropagation();
        toggleCart();
    });

    // Close cart when clicking outside
    document.addEventListener('click', function(event) {
        const cartContainer = document.querySelector('.cart-container');
        const cartItems = document.getElementById('cart-items');
        if (!cartContainer.contains(event.target) && cartItems.classList.contains('show')) {
            cartItems.classList.remove('show');
        }
    });

    // Event delegation for Add to Cart buttons
    document.getElementById('products-container').addEventListener('click', function(e) {
        if (e.target.classList.contains('add-to-cart-btn')) {
            const product = JSON.parse(e.target.getAttribute('data-product'));
            addToCart(product);
        }
    });

    // Event delegation for Remove from Cart buttons
    document.getElementById('cart-items-list').addEventListener('click', function(e) {
        if (e.target.classList.contains('remove-from-cart-btn')) {
            const skuId = e.target.getAttribute('data-sku-id');
            removeFromCart(skuId);
        }
    });
});

function fetchProducts() {
    fetch('/order/get-products')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(products => {
            displayProducts(products);
        })
        .catch(error => {
            console.error('Error fetching products:', error);
            showError('Failed to load products. Please try again later.');
        });
}

function displayProducts(products) {
    const container = document.getElementById('products-container');
    if (products.length === 0) {
        container.innerHTML = '<div class="loading">No products available</div>';
        return;
    }

    const productsGrid = document.createElement('div');
    productsGrid.className = 'products-grid';

    products.forEach(product => {
        const productCard = document.createElement('div');
        productCard.className = 'product-card';
        productCard.innerHTML = `
            <img src="${product.image}" alt="${product.name}" class="product-image" onerror="this.src='https://via.placeholder.com/300x200?text=No+Image'">
            <div class="product-name">${product.name}</div>
            <div class="product-description">${product.category}</div>
            <div class="product-price">Rs ${product.price.toFixed(2)}</div>
            <div class="product-stock">In Stock: ${product.quantity}</div>
            <button class="add-to-cart-btn" data-product='${JSON.stringify(product)}'>Add to Cart</button>
        `;
        productsGrid.appendChild(productCard);
    });

    container.innerHTML = '';
    container.appendChild(productsGrid);
}

function showError(message) {
    const errorDiv = document.getElementById('error-message');
    errorDiv.textContent = message;
    errorDiv.style.display = 'block';
} 