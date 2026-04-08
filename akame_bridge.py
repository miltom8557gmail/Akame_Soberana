import os
import time
import requests

# A Akame busca as chaves que salvamos no sistema
URL = os.getenv('SUPABASE_URL')
KEY = os.getenv('SUPABASE_KEY')

HEADERS = {
    "apikey": KEY,
    "Authorization": f"Bearer {KEY}",
    "Content-Type": "application/json"
}

def executar_ordem(comando):
    print(f"🔱 Akame: Executando ordem '{comando}'...")
    if comando == "RELATORIO":
        # Chama a inteligência para dar um relatório
        os.system("python3 akame_brain.py")
    elif comando == "VOZ":
        # Testa a voz da Akame
        from akame_voz_fix import falar
        falar("Sistema de voz sincronizado com o portal web. Estou ouvindo, Mestre.")
    elif comando == "LIMPEZA":
        os.system("apt clean && rm -f *.mp3")
        print("🧹 Akame: Cache limpo.")
    elif comando == "INSTAGRAM":
        print("🔍 Akame: Iniciando protocolos de infiltração social...")
        # Aqui você pode chamar seu script de Instagram futuramente

def escutar_nuvem():
    API_ENDPOINT = f"{URL}/rest/v1/ordens_termux?status=eq.pendente&select=*"
    print("🛰️ Nexus Bridge: Ativa. Aguardando comandos do Portal Web...")
    
    while True:
        try:
            response = requests.get(API_ENDPOINT, headers=HEADERS)
            if response.status_code == 200:
                ordens = response.json()
                for ordem in ordens:
                    cmd = ordem['comando']
                    executar_ordem(cmd)
                    
                    # Marca como 'concluido' para não repetir a mesma ordem
                    update_url = f"{URL}/rest/v1/ordens_termux?id=eq.{ordem['id']}"
                    requests.patch(update_url, headers=HEADERS, json={"status": "concluido"})
            else:
                print(f"⚠️ Erro de conexão: {response.status_code}")
        except Exception as e:
            pass # Silêncio tático em caso de oscilação
        
        time.sleep(3) # Verifica a cada 3 segundos

if __name__ == "__main__":
    escutar_nuvem()
