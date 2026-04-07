import os, subprocess, time, requests, json

class AkameImortal:
    def __init__(self):
        self.k = "AIzaSyCULLS_IYTmttS7djypmx-FePi8HH-TZ0M"
        
    def log(self, m):
        print(f"[{time.ctime()}] {m}")

    def monitor(self):
        self.log("🔱 Akame: Visão de Job Ativada.")
        while True:
            try:
                # 1. Pega a última falha
                cmd = "gh run list --workflow='FORJA_OMNI.yml' --limit 1 --json status,conclusion,databaseId"
                res = json.loads(subprocess.check_output(cmd, shell=True))
                
                if res and res[0]['status'] == 'completed' and res[0]['conclusion'] == 'failure':
                    rid = res[0]['databaseId']
                    self.log(f"🚨 Analisando falha na Run {rid}...")
                    
                    # 2. Tenta pegar o log via JOB (mais rápido e estável)
                    try:
                        job_id = subprocess.check_output(f"gh run view {rid} --json jobs --jq '.jobs[0].databaseId'", shell=True).decode().strip()
                        logs = subprocess.check_output(f"gh run view --job={job_id} --log", shell=True).decode()[-2500:]
                        
                        self.log("🧠 Akame processando erro Mara...")
                        prompt = f"Erro no GitHub: {logs}. Dê APENAS o comando bash para corrigir o arquivo e mudar para o certo. Se houver erro de permissão no gradlew, use 'git update-index --chmod=+x gradlew'."
                        u = f"https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key={self.k}"
                        r = requests.post(u, json={"contents": [{"parts":[{"text": prompt}]}]}, timeout=30)
                        fix = r.json()['candidates'][0]['content']['parts'][0]['text'].replace("```bash","").replace("```","").strip()
                        
                        if fix:
                            self.log(f"🛠️ Aplicando: {fix}")
                            os.system(fix)
                            os.system("git add . && git commit -m '🔱 Akame: Auto-Cura Síncrona' && git push origin main && gh workflow run FORJA_OMNI.yml")
                            self.log("✅ Correção enviada. Aguardando 3 min...")
                            time.sleep(180)
                    except Exception as inner:
                        self.log(f"⏳ Aguardando liberação do Log... ({inner})")
                
            except Exception as e:
                self.log(f"⚠️ Erro de Monitoramento: {e}")
            
            time.sleep(20)

if __name__ == "__main__":
    AkameImortal().monitor()
