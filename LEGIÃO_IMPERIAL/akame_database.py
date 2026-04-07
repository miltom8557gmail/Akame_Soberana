import os, json

def sincronizar_projetos_apk():
    # Caminho onde o APK registra os novos projetos
    projeto_file = os.path.expanduser("~/Akame_Omni_20260405/database_projetos.json")
    
    if os.path.exists(projeto_file):
        with open(projeto_file, "r") as f:
            dados = json.load(f)
            for p in dados['projetos']:
                print(f"🔱 [SINCRONIA]: Atualizando estrutura de {p['nome']}...")
                os.makedirs(f"~/Akame_Projetos/{p['nome']}", exist_ok=True)
    
if __name__ == "__main__":
    sincronizar_projetos_apk()
