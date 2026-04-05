import os
import subprocess
import requests
import time
from flask import Flask, jsonify
from threading import Thread

app = Flask(__name__)

SUPABASE_URL = "https://bfriplrxtleleplhpgwd.supabase.co/rest/v1/logs_akame"
S_KEY = "sb_publishable_gXUaEYTs5znqXzElEeGKTA_AQKQ9EGI"

def registrar_no_supabase(status_msg):
    try:
        df = subprocess.check_output("df -h . | awk 'NR==2 {print $4}'", shell=True).decode().strip()
        hf_status = "ATIVO" if os.getenv("HF_TOKEN") else "AUSENTE"
        
        data = {
            "status": status_msg,
            "espaco_disco": df,
            "huggingface_status": hf_status,
            "detalhes": {"engine": "V9.6", "nexus": "AKAME_NEXUS", "mode": "AUTO_WATCH"}
        }
        
        headers = {"apikey": S_KEY, "Authorization": f"Bearer {S_KEY}", "Content-Type": "application/json"}
        requests.post(SUPABASE_URL, json=data, headers=headers, timeout=5)
    except:
        pass

@app.route('/sincronizar')
def sync():
    registrar_no_supabase("SINCRO_MANUAL")
    return jsonify({"status": "SINCRONIZADO", "nexus": "V9.6"})

# Loop de monitoramento em tempo real (a cada 60 segundos)
def monitor_loop():
    while True:
        registrar_no_supabase("PULSO_AUTOMATICO")
        time.sleep(60)

if __name__ == "__main__":
    # Inicia o vigilante em uma thread separada
    Thread(target=monitor_loop, daemon=True).start()
    app.run(host='0.0.0.0', port=5000)
