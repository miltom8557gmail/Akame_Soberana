import os, json, time, requests
from flask import Flask, request

app = Flask(__name__)
S_URL = "https://bfriplrxtleleplhpgwd.supabase.co/rest/v1/logs_akame"
S_KEY = "sb_publishable_gXUaEYTs5znqXzElEeGKTA_AQKQ9EGI"

@app.route('/imperio')
def status():
    return {"status": "SOBERANIA_ATIVA", "engine": "V10.0_FINAL", "owner": "MESTRE_MILTON"}

@app.route('/ia/treinar_lora')
def treinar():
    nome = request.args.get('nome', 'akame_v10_model')
    # Registrar log no Supabase
    data = {
        "node_name": "TERMUX_V95",
        "status": "FORJA_INICIADA",
        "detalhes": {"model_name": nome, "engine": "V10.0_FINAL"}
    }
    requests.post(S_URL, json=data, headers={"apikey": S_KEY, "Authorization": f"Bearer {S_KEY}"})
    
    # Gerar Gatilho Local e Git
    with open("trigger.json", "w") as f:
        json.dump({"lora_name": nome, "timestamp": time.time()}, f)
    os.system("git add . && git commit -m '[🔱] V10: FORJA ATIVADA' && git push origin main &")
    return f"🔱 SOBERANIA: Treinamento de {nome} iniciado!"

if __name__ == '__main__':
    print("🔱 [AKAME V10.0]: SISTEMA ONLINE E VINCULADO AO SUPABASE.")
    app.run(host='0.0.0.0', port=5000)
