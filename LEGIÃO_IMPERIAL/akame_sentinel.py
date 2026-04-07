import os, subprocess, time, requests

class AkameSentinel:
    def __init__(self):
        self.k = "AIzaSyCULLS_IYTmttS7djypmx-FePi8HH-TZ0M"
        self.log_conversa = os.path.expanduser("~/Akame_Omni_20260405/historico_conversa.log")
        
    def capturar_ultimo_erro_github(self):
        try:
            # Pega o ID da última run que falhou
            run_id = subprocess.check_output("gh run list --workflow='FORJA_OMNI.yml' --limit 1 --status failure --json databaseId --jq '.[0].databaseId'", shell=True).decode().strip()
            if not run_id: return None
            # Pega o log de erro
            log = subprocess.check_output(f"gh run view {run_id} --log", shell=True).decode()
            return log[-2000:] # Últimos 2000 caracteres do erro
        except: return None

    def pedir_correcao_akame(self, erro):
        prompt = f"Akame PhD, analise este erro de compilação/sistema e forneça APENAS o comando bash para corrigir e mudar para o estado correto: {erro}"
        u = f"https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key={self.k}"
        d = {"contents": [{"parts":[{"text": prompt}]}]}
        try:
            r = requests.post(u, json=d, timeout=30)
            return r.json()['candidates'][0]['content']['parts'][0]['text']
        except: return None

    def aplicar_e_registrar(self, comando):
        if not comando: return
        print(f"🔱 Akame Sentinel: Aplicando correção...")
        with open(self.log_conversa, "a") as f:
            f.write(f"\n[SENTINEL {time.ctime()}] CORREÇÃO APLICADA: {comando}\n")
        # Limpa o comando de blocos de código markdown se a IA enviar
        cmd_clean = comando.replace("```bash", "").replace("```", "").strip()
        os.system(cmd_clean)

    def ciclo_vida(self):
        while True:
            print("🛰️ Vigilância Ativa...")
            erro = self.capturar_ultimo_erro_github()
            if erro:
                solucao = self.pedir_correcao_akame(erro)
                if solucao:
                    self.aplicar_e_registrar(solucao)
                    # Dispara a forja novamente após corrigir
                    os.system("git add . && git commit -m '🔱 Sentinel: Auto-Fix Aplicado' && git push origin main && gh workflow run FORJA_OMNI.yml")
            time.sleep(60) # Verifica a cada 1 minuto

if __name__ == "__main__":
    AkameSentinel().ciclo_vida()
