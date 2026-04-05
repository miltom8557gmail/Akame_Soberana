import requests, json

print("🤖 [CHROME]: Iniciando varredura na porta 8080...")
try:
    r = requests.get("http://127.0.0.1:8080")
    dados = r.json()
    print(f"\n--- RELATÓRIO AKAME OMNI 7.4 ---")
    print(f"MESTRE: {dados['mestre']}")
    print(f"ESTADO: {dados['status']}")
    print(f"CONEXÕES: {json.dumps(dados['servicos'], indent=2)}")
    print(f"NÚCLEOS: {dados['nucleos']} ONLINE")
    print(f"--------------------------------\n")
except Exception as e:
    print(f"⚠️ [CHROME]: Falha ao acessar a Akame. Erro: {e}")
