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

	// Perform an AJAX call or form submission to save the selected roles
	$.ajax({
		url: `/UpdateRole/${userId}`,
		type: 'POST',
		data: { roles: selectedRoles },
		success: function(response) {
			alert('Roles updated successfully');
			$(`#role-text-${userId}`).text(selectedRoles.join(', '));
			showDropdown(userId);
		},
		error: function() {
			alert('Error updating roles');
		}
	});
}

// Hide the dropdown after saving roles
function hideDropdown(userId) {
	document.getElementById('role-dropdown-' + userId).style.display = 'none';
	document.getElementById('save-btn-' + userId).style.display = 'none';
	document.getElementById('role-editBtn-' + userId).style.display = 'block';
	document.getElementById('role-text-' + userId).style.display = 'block';
}
