function loadProcedimentos() {
    return fetch('http://localhost:8080/procedimentos')
        .then(response => response.json())
        .catch(error => {
            console.error('Erro ao carregar procedimentos:', error);
            return []; // Retorna um array vazio em caso de erro
        });
}
