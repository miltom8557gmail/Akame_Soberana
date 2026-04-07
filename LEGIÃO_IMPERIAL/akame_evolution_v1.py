import os, time, requests, json
from datetime import datetime

def pensar(prompt):
    api_key = "AIzaSyCULLS_IYTmttS7djypmx-FePi8HH-TZ0M"
    url = f"https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key={api_key}"
    log_path = os.path.expanduser("~/Akame_Omni_20260405/historico_conversa.log")
    payload = {"contents": [{"parts":[{"text": prompt}]}]}
    try:
        r = requests.post(url, json=payload, timeout=30)
        res = r.json()['candidates'][0]['content']['parts'][0]['text']
        with open(log_path, "a") as f:
            f.write(f"\n[{datetime.now()}] MESTRE: {prompt}\nAKAME: {res}\n")
        return True
    except Exception as e:
        with open(os.path.expanduser("~/Akame_Omni_20260405/akame_output.log"), "a") as f:
            f.write(f"Erro: {str(e)}\n")
        return False

def vigilia():
    cmd_path = os.path.expanduser("~/Akame_Omni/comando.txt")
    os.makedirs(os.path.dirname(cmd_path), exist_ok=True)
    while True:
        if os.path.exists(cmd_path):
            with open(cmd_path, "r") as f:
                ordem = f.read().strip()
            if ordem:
                pensar(ordem)
            os.remove(cmd_path)
        time.sleep(2)

if __name__ == "__main__":
    vigilia()
