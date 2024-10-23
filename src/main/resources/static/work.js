// Define base URL for your backend API
const baseUrl = 'http://localhost:8080/api/employee';  // Change this if your backend is running on a different port

// Fetch and display all employees when the page loads
document.addEventListener('DOMContentLoaded', fetchEmployees);

// Handle form submission to add or update an employee

 document.getElementById('employeeForm').addEventListener('submit', async function (e) {
    e.preventDefault();
    const employeeId = document.getElementById('eId').value;  // Hidden field for employee ID
    const employee = {
        name: document.getElementById('name').value,
        role: document.getElementById('role').value,
        blood: document.getElementById('blood').value,
        phoneNum: document.getElementById('phoneNum').value,
        sal: document.getElementById('sal').value,
        adress: {
            d_No: document.getElementById('d_No').value,
            streetName: document.getElementById('streetName').value,
            city: document.getElementById('city').value,
            district: document.getElementById('district').value,
            state: document.getElementById('state').value,
            pinCode: document.getElementById('pinCode').value
        }
    };

    try {
        const response = await fetch(employeeId ? `${baseUrl}/updateAll/${employeeId}` : `${baseUrl}/saveemployee`, {
            method: employeeId ? 'PUT' : 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(employee)
        });
        if (response.ok) {
            alert(`Employee ${employeeId ? 'updated' : 'saved'} successfully!`);
            fetchEmployees();  // Refresh the employee list
            clearForm();  // Clear form after submission
        } else {
            alert(`Failed to ${employeeId ? 'update' : 'save'} employee`);
        }
    } catch (error) {
        console.error(`Error ${employeeId ? 'updating' : 'saving'} employee:`, error);
    }
});

// Fetch and display all employees
async function fetchEmployees() {
    try {
        const response = await fetch(`${baseUrl}/Getall`);
        const employees = await response.json();
        displayEmployees(employees);
    } catch (error) {
        console.error('Error fetching employees:', error);
    }
}

// Display employees in the table
function displayEmployees(employees) {
    const employeeTableBody = document.getElementById('employeeTableBody');
    employeeTableBody.innerHTML = '';  // Clear existing rows

    employees.forEach(employee => {
        const row = employeeTableBody.insertRow();
        row.innerHTML = `
            <td>${employee.eId}</td>
            <td>${employee.name}</td>
            <td>${employee.role}</td>
            <td>${employee.blood}</td>
            <td>${employee.phoneNum}</td>
            <td>${employee.sal}</td>
            <td>${employee.adress.d_No}, ${employee.adress.streetName}, ${employee.adress.city}, ${employee.adress.district}, ${employee.adress.state} - ${employee.adress.pinCode}</td>
            <td>
                <button onclick="fetchEmployeeById(${employee.eId})">Edit</button>
                <button onclick="deleteEmployee(${employee.eId})">Delete</button>
            </td>
        `;
    });
}

// Fetch employee data by ID and populate the form for editing
async function fetchEmployeeById(id) {
    try {
        const response = await fetch(`${baseUrl}/getById/${id}`);
        if (!response.ok) throw new Error('Employee not found');

        const employee = await response.json(); // Parse the JSON response
        populateForm(employee);  // Populate the form with employee data
    } catch (error) {
        console.error('Error fetching employee:', error);
        alert('Failed to fetch employee data');
    }
}

// Populate form fields with the fetched employee data
function populateForm(employee) {
    document.getElementById('eId').value = employee.eId;  // Hidden ID field to track the employee ID
    document.getElementById('name').value = employee.name;
    document.getElementById('role').value = employee.role;
    document.getElementById('blood').value = employee.blood;
    document.getElementById('phoneNum').value = employee.phoneNum;
    document.getElementById('sal').value = employee.sal;

    // Populate address fields
    document.getElementById('d_No').value = employee.adress.d_No;
    document.getElementById('streetName').value = employee.adress.streetName;
    document.getElementById('city').value = employee.adress.city;
    document.getElementById('district').value = employee.adress.district;
    document.getElementById('state').value = employee.adress.state;
    document.getElementById('pinCode').value = employee.adress.pinCode;

    
}

// Function to clear the form after submitting or canceling the operation
function clearForm() {
    document.getElementById('employeeForm').reset();
    document.getElementById('eId').value = '';  // Clear the hidden ID field
}

// Delete an employee by ID
async function deleteEmployee(id) {
    if (confirm('Are you sure you want to delete this employee?')) {
        try {
            const response = await fetch (`${baseUrl}/DeleteById?id=${id}`, {
                method: 'DELETE'
            });
            if (response.ok) {
                // console.log(response),
                alert('Employee deleted successfully!');
                fetchEmployees();  // Refresh the employee list
            } else {
                alert('Failed to delete employee');
            }
        } catch (error) {
            console.error('Error deleting employee:', error);
        }
    }
}

