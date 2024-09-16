// Show the role dropdown and save button
function showDropdown(userId) {
	document.getElementById('role-dropdown-' + userId).style.display = 'block';
	document.getElementById('save-btn-' + userId).style.display = 'inline-block';
	document.getElementById('role-editBtn-' + userId).style.display = 'none';
	document.getElementById('role-text-' + userId).style.display = 'none';
}

// Save the selected role
function saveRole(userId) {
	let dropdown = document.getElementById('role-dropdown-' + userId);  // Get selected roles from dropdown
	var selectedRoles = Array.from(dropdown.selectedOptions).map(option => parseInt(option.value));
	console.log("Saving roles for user " + userId + ": " + selectedRoles);

	// Send role data using fetch
	fetch(`/UpdateRole/${userId}`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(selectedRoles
		),
	})
		.then(response => {
			if (response.ok) {
				alert('Roles updated successfully');
				window.location.reload(); // Optionally reload page to see changes
			} else {
				alert('Failed to update roles');
			}
		})
		.catch(error => {
			console.error('Error updating roles:', error);
			alert('Error updating roles');
		});
}

// Hide the dropdown after saving roles
function hideDropdown(userId) {
	document.getElementById('role-dropdown-' + userId).style.display = 'none';
	document.getElementById('save-btn-' + userId).style.display = 'none';
	document.getElementById('role-editBtn-' + userId).style.display = 'block';
	document.getElementById('role-text-' + userId).style.display = 'block';
}
