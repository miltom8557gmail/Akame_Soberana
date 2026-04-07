import os
from datetime import datetime

def criar_projeto_isolado(nome_projeto):
    print(f"🔱 [AKAME PhD]: Iniciando Gestação do Projeto: {nome_projeto}")
    
    # 1. Criação de Diretório Isolado
    path = os.path.expanduser(f"~/{nome_projeto}")
    os.makedirs(path, exist_ok=True)
    
    # 2. Setup de Ecossistema (GitHub Repo)
    os.system(f"cd {path} && git init")
    with open(f"{path}/README.md", "w") as f:
        f.write(f"# {nome_projeto}\nGerado autonomamente pela Akame PhD para o Mestre miltom.jose8557.")
    
    # 3. Integração com Web (HTML5/CSS3 Cyberpunk)
    with open(f"{path}/index.html", "w") as f:
        f.write("<html><body style='background:#000;color:#00FFCC'><h1>Akame Nexus Deploy</h1></body></html>")
    
    # 4. Registro no Cofre Google
    os.system(f"rclone mkdir miltom.jose8557@gmail.com:Akame_Projetos/{nome_projeto}")
    
    print(f"✅ [SUCESSO]: Projeto '{nome_projeto}' está vivo e isolado no Ecossistema.")

if __name__ == "__main__":
    # Teste de criação: Sistema de Monitoramento Web Isolado
    criar_projeto_isolado("Akame_Web_Intelligence")
