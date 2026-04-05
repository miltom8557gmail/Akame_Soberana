import time, requests, os
from flask import Flask, jsonify

app = Flask(__name__)
PORTA = 8080
MESTRE = "miltom8557"
GH_TOKEN = "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"
SB_URL = "https://gXUaEYTs5znqXzElEeGKTA.supabase.co"
SB_KEY = "sb_publishable_gXUaEYTs5znqXzElEeGKTA_AQKQ9EGI"

def supabase_audit(status):
    for i in range(10):
        try:
            url = f"{SB_URL}/rest/v1/logs_omni"
            headers = {"apikey": SB_KEY, "Authorization": f"Bearer {SB_KEY}", "Content-Type": "application/json"}
            payload = {"mestre": MESTRE, "status": status, "versao": "7.4"}
            r = requests.post(url, headers=headers, json=payload)
            if r.status_code in [201, 204]: break
        except: time.sleep(1)

@app.route('/baixar_modelo/<int:model_id>')
def baixar_modelo(model_id):
    os.system(f"~/Akame_Omni_20260405/akame_civitai.sh {model_id} &")
    supabase_audit(f"DOWNLOAD_CIVITAI_{model_id}")
    return {"status": "🎨 DOWNLOAD INICIADO"}

@app.route('/sincronizar')
def sincronizar():
    os.system("~/Akame_Omni_20260405/akame_push.sh")
    return {"status": "🚀 DNA SINCRONIZADO"}

@app.route('/compilar')
def compilar():
    url = f"https://api.github.com/repos/{MESTRE}/Akame_Soberana/dispatches"
    headers = {"Authorization": f"token {GH_TOKEN}", "Accept": "application/vnd.github.v3+json"}
    payload = {"event_type": "compilar_apk_mestre"}
    requests.post(url, headers=headers, json=payload)
    supabase_audit("COMPILACAO_ORDEM")
    return {"status": "🔥 FORJA ATIVA"}

@app.route('/')
def index():
    return {"akame": "7.4 OMNI", "status": "ONLINE"}

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=PORTA)
