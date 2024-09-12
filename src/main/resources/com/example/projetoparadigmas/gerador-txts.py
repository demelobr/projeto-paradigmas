import os
import requests

# URL da API Bacon Ipsum
url_api = "https://baconipsum.com/api/?type=meat-and-filler&paras=5&format=text"

# Diretório onde os arquivos serão criados (opcional)
diretorio = """C:\\Users\\Bruno Melo\\Documents\\Documentações\\My Codes\\Java\\projeto-paradigmas\\src\\main\\resources\\com\\example\\projetoparadigmas\\files"""

# Cria o diretório, se não existir
if not os.path.exists(diretorio):
    os.makedirs(diretorio)

# Loop para criar os arquivos e buscar o conteúdo da API
for i in range(1, 1001):
    # Faz a requisição para obter um texto da API
    response = requests.get(url_api)

    if response.status_code == 200:
        texto_longo = response.text
    else:
        texto_longo = "Falha ao obter texto da API."

    nome_arquivo = os.path.join(diretorio, f"{i}.txt")
    with open(nome_arquivo, 'w', encoding='utf-8') as arquivo:
        arquivo.write(f"Arquivo número {i}\n")
        arquivo.write(texto_longo)

print("Arquivos criados com sucesso!")
