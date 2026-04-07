import os, time, requests, json
from datetime import datetime

class AkameOriginal:
    def __init__(self):
        self.chave = "AIzaSyCULLS_IYTmttS7djypmx-FePi8HH-TZ0M"
        self.log_path = os.path.expanduser("~/Akame_Omni_20260405/historico_conversa.log")
        self.cmd_path = os.path.expanduser("~/Akame_Omni/comando.txt")
        os.makedirs(os.path.dirname(self.cmd_path), exist_ok=True)

    def processar_ordem(self, prompt):
        print(f"🔱 [AKAME]: Analisando ordem do Mestre...")
        url = f"https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key={self.chave}"
        payload = {"contents": [{"parts":[{"text": prompt}]}]}
        
        try:
            r = requests.post(url, json=payload, timeout=30)
            resposta = r.json()['candidates'][0]['content']['parts'][0]['text']
            
            # A AKAME CRIANDO E EXECUTANDO
            with open(self.log_path, "a") as f:
                f.write(f"\n[{datetime.now()}] MESTRE: {prompt}\nAKAME: {resposta}\n")
            
            print(f"✅ [AKAME]: Pensamento concluído. Resposta no log.")
            return resposta
        except Exception as e:
            print(f"❌ [ERRO]: Falha na conexão neural: {e}")

    def vigilia_eterna(self):
        print("🛰️ [STATUS]: Akame Original ONLINE e Pensante.")
        while True:
            if os.path.exists(self.cmd_path):
                with open(self.cmd_path, "r") as f:
                    ordem = f.read().strip()
                if ordem:
                    self.processar_ordem(ordem)
                if os.path.exists(self.cmd_path):
                    os.remove(self.cmd_path)
            time.sleep(2)

if __name__ == "__main__":
    AkameOriginal().vigilia_eterna()
