import time, requests, os, base64
from flask import Flask, jsonify

app = Flask(__name__)
PORTA = 8080 # Porta oficial da Soberania 7.4
MESTRE = "miltom8557"

def d(t) : return base64.b64decode(t).decode()
G_K = d("Z2hwX3FMZkNSc2hhOWNyNFJnbk9QVW5Zb0UwMHFWWWpMNDFxNVJBbw==")

@app.route('/baixar_apk')
def baixar_apk():
    # Rota para buscar o link do último artefato (APK) gerado no GitHub
    url = f"https://api.github.com/repos/{MESTRE}/Akame_Soberana/actions/runs"
    headers = {"Authorization": f"token {G_K}"}
    r = requests.get(url, headers=headers).json()
    return jsonify({"status": "ARQUIVO LOCALIZADO", "download_url": r['workflow_runs'][0]['html_url']})

@app.route('/sincronizar')
def sinc():
    cmd = f"cd ~/Akame_Omni_20260405 && git add . && git commit -m '🔱 OMNI SYNC 7.4' && git push origin main --force"
    os.system(cmd)
    return jsonify({"status": "DNA ATUALIZADO NO GITHUB"})

@app.route('/')
def index():
    return jsonify({"akame": "7.4 OMNI", "status": "SOBERANA", "mestre": MESTRE})

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=PORTA)
