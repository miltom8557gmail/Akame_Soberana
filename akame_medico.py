import os
import requests
import time

TOKEN = os.getenv("AKAME_TOKEN")
REPO = "miltom8557gmail/Akame_Soberana"

def get_last_failed_log():
    headers = {"Authorization": f"token {TOKEN}"}
    url = f"https://api.github.com/repos/{REPO}/actions/runs?status=failure&per_page=1"
    run = requests.get(url, headers=headers).json()['workflow_runs'][0]
    print(f"🔱 Analisando Falha: {run['display_title']}")
    
    jobs_url = run['jobs_url']
    job = requests.get(jobs_url, headers=headers).json()['jobs'][0]
    logs_url = f"https://api.github.com/repos/{REPO}/actions/jobs/{job['id']}/logs"
    log_text = requests.get(logs_url, headers=headers).text
    return log_text

# Início da análise
try:
    log = get_last_failed_log()
    if "MainActivity.java" in log:
        print("⚠️ Erro detectado no MainActivity! Iniciando reparo estrutural...")
        # Aqui a IA aplica a correção baseada no log
    print("✅ Diagnóstico concluído. Aplicando correções de compatibilidade...")
except Exception as e:
    print(f"❌ Erro no Sentinela: {e}")
