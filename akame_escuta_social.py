import os, time, requests

def vigiar_ambiente():
    url = os.getenv('SUPABASE_URL') + "/rest/v1/status_akame"
    headers = {'apikey': os.getenv('SUPABASE_KEY'), 'Authorization': f'Bearer {os.getenv("SUPABASE_KEY")}'}
    
    print("🔱 Akame: Ouvindo através do ruído... [Modo JARVIS Ativo]")
    while True:
        # Filtra ruído e foca em frequências de voz (300Hz-3000Hz)
        os.system("ffmpeg -y -f android_audiocapturer -i default -t 5 -af 'afftdn,highpass=f=200,lowpass=f=3000' env_check.wav > /dev/null 2>&1")
        
        # Aqui ela enviaria o log de "Presença Detectada" para o Supabase
        payload = {"fase_missao": "VIGILANCIA", "detalhes": {"audio_status": "processado", "ambiente": "monitorado"}}
        requests.post(url, json=payload, headers=headers)
        
        time.sleep(10) # Respira para não pesar no celular

if __name__ == "__main__":
    vigiar_ambiente()
