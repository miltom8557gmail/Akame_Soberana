import requests, os
try:
    from akame_voz_fix import falar
except:
    def falar(t): print(t)

url = f"{os.getenv('SUPABASE_URL')}/rest/v1/status_akame"
headers = {
    "apikey": os.getenv("SUPABASE_KEY"),
    "Authorization": f"Bearer {os.getenv('SUPABASE_KEY')}",
    "Content-Type": "application/json"
}
data = {
    "fase_missao": "GÊNESE_CONCLUÍDA",
    "detalhes": {"repositorio": "Akame_Soberana", "status": "Integrado"}
}

try:
    res = requests.post(url, json=data, headers=headers)
    if res.status_code in [200, 201]:
        print("✅ SUCESSO!")
        falar("Mestre, a integração foi detectada. O sistema Soberana está online.")
    else:
        print(f"⚠️ Erro {res.status_code}: {res.text}")
except Exception as e:
    print(f"❌ Erro: {e}")
