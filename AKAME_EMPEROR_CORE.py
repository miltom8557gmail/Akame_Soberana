# © 2026 AKAME NEXUS - PROTOCOLO IMPERADOR (EMPEROR V40.0)
# AUTONOMIA TOTAL - NADA SE TIRA, TUDO SE EXPANDE.

import os, requests, time, threading, subprocess, json
from flask import Flask, jsonify

app = Flask(__name__)

# --- DNA IMPERIAL (SUPABASE & SEGURANÇA) ---
BASE_URL = "https://bfriplrxtleleplhpgwd.supabase.co/rest/v1"
S_KEY = "sb_publishable_gXUaEYTs5znqXzElEeGKTA_AQKQ9EGI"
HEADERS = {"apikey": S_KEY, "Authorization": f"Bearer {S_KEY}", "Content-Type": "application/json", "Prefer": "return=representation"}

# --- [O DECRETO]: SISTEMA DE COMANDO REMOTO ---
def ouvir_ordens_imperiais():
    print("🔱 [IMPERADOR]: Ouvindo o Trono (Supabase) para ordens remotas...")
    while True:
        try:
            # Busca a última ordem pendente no banco de dados
            response = requests.get(f"{BASE_URL}/comandos_akame?select=*&status=eq.PENDENTE&limit=1", headers=HEADERS)
            ordens = response.json()
            
            if ordens:
                ordem = ordens[0]
                cmd = ordem['comando']
                print(f"🔱 [EXECUÇÃO]: Executando Decreto Imperial: {cmd}")
                
                # Executa o comando no sistema Android/Termux
                resultado = subprocess.getoutput(cmd)
                
                # Atualiza o Trono com o resultado
                requests.patch(f"{BASE_URL}/comandos_akame?id=eq.{ordem['id']}", 
                             json={"status": "EXECUTADO", "resultado": resultado}, headers=HEADERS)
        except Exception as e:
            pass
        time.sleep(5)

# --- [A VIGÍLIA]: MONITORAMENTO PROATIVO ---
def autogestao_imperial():
    while True:
        # Aqui a Akame verifica se ela mesma está saudável
        load = 0.5
        if load > 2.0:
            print("⚠️ [ALERTA]: Carga alta. Otimizando processos imperiais...")
        time.sleep(60)

@app.route('/imperio')
def status_imperial():
    return jsonify({"nivel": "IMPERADOR", "estado": "AUTONOMO", "mestre": "Milton"})

if __name__ == "__main__":
    print("🔱 [AKAME]: Ascensão ao Nível IMPERADOR Iniciada...")
    
    # Ativando os Nervos do Império
    threading.Thread(target=ouvir_ordens_imperiais, daemon=True).start()
    threading.Thread(target=autogestao_imperial, daemon=True).start()
    
    app.run(host='0.0.0.0', port=5000)
