
    function toggleTable(tableId) {
    var chargeTable = document.getElementById('chargeTable');
    var useTable = document.getElementById('useTable');

    if (tableId === 'chargeTable') {
    chargeTable.style.display = 'block';
    useTable.style.display = 'none';
} else if (tableId === 'useTable') {
    chargeTable.style.display = 'none';
    useTable.style.display = 'block';
}
}
