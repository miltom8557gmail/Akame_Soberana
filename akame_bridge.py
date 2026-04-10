import os
import time
import requests

# Configurações do seu Supabase
URL = "SUA_URL_DO_SUPABASE"
KEY = "SUA_KEY_DO_SUPABASE"
API_ENDPOINT = f"{URL}/rest/v1/comandos?select=*"
HEADERS = {
    "apikey": KEY,
    "Authorization": f"Bearer {KEY}",
    "Content-Type": "application/json",
    "Prefer": "return=representation"
}

def falar_no_termux(texto):
    print(f"🔱 Akame: {texto}")
    os.system(f"termux-tts-speak '{texto}'")

def processar_ordem(texto_comando):
    cmd = texto_comando.lower()
    
    # 🧬 PROTOCOLO GÊNESE: Criar Agentes
    if "crie um agente" in cmd or "criar script" in cmd:
        nome = cmd.split("chamado")[-1].strip() if "chamado" in cmd else "agente_novo"
        nome = nome.replace(" ", "_")
        os.system(f"mkdir -p ~/AkameApp/agentes")
        caminho = f"/data/data/com.termux/files/home/AkameApp/agentes/{nome}.py"
        with open(caminho, "w") as f:
            f.write(f"# Agente {nome} gerado pela Akame\nprint('🔱 Agente {nome} Online e Operacional')")
        falar_no_termux(f"Mestre, o agente {nome} foi gerado no laboratório.")

    # 🔊 COMANDO DE VOZ SIMPLES
    elif "fale" in cmd:
        fala = cmd.replace("fale", "").strip()
        falar_no_termux(fala)

def escutar_nuvem():
    print("🛰️ Nexus Bridge: Online. Aguardando ordens vocais...")
    while True:
        try:
            response = requests.get(f"{API_ENDPOINT}&lido=eq.false&order=created_at.desc&limit=1", headers=HEADERS)
            comandos = response.json()
            if comandos:
                cmd_data = comandos[0]
                processar_ordem(cmd_data['comando'])
                requests.patch(f"{URL}/rest/v1/comandos?id=eq.{cmd_data['id']}", json={"lido": True}, headers=HEADERS)
            time.sleep(2)
        except Exception as e:
            time.sleep(5)

if __name__ == "__main__":
    escutar_nuvem()
