const API_URL = "http://localhost:8080/filme";

document.addEventListener("DOMContentLoaded", () => {
  carregarFilmes();

  const form = document.getElementById("filmeForm");

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const id = document.getElementById("id").value;
    const nomeFilme = document.getElementById("nome").value;
    const ano = parseInt(document.getElementById("ano").value);
    const genero = document.getElementById("genero").value;

    const filme = { nomeFilme, ano, genero };

    if (id) {
      // Edição
      await fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(filme),
      });
    } else {
      // Novo cadastro
      await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(filme),
      });
    }

    form.reset();
    document.getElementById("id").value = "";
    carregarFilmes();
  });
});

async function carregarFilmes() {
  const lista = document.getElementById("listaFilmes");
  const mensagemVazia = document.getElementById("mensagemListaVazia");

  lista.innerHTML = "";

  try {
    const res = await fetch(API_URL);
    const filmes = await res.json();

    if (filmes.length === 0) {
      mensagemVazia.style.display = "block";
      return;
    }

    mensagemVazia.style.display = "none";

    filmes.forEach((filme) => {
      const li = document.createElement("li");
      li.innerHTML = `
        <strong>${filme.nomeFilme}</strong> (${filme.ano}) - ${filme.genero}
        <button onclick="editarFilme(${filme.id}, '${filme.nomeFilme}', ${filme.ano}, '${filme.genero}')">Editar</button>
        <button onclick="deletarFilme(${filme.id})">Excluir</button>
      `;
      lista.appendChild(li);
    });
  } catch (erro) {
    console.error("Erro ao carregar filmes:", erro);
  }
}

function editarFilme(id, nome, ano, genero) {
  document.getElementById("id").value = id;
  document.getElementById("nome").value = nome;
  document.getElementById("ano").value = ano;
  document.getElementById("genero").value = genero;
}

async function deletarFilme(id) {
  if (confirm("Deseja realmente excluir este filme?")) {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    carregarFilmes();
  }
}
