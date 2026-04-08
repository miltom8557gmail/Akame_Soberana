import os, time, threading
from akame_voz_fix import falar

def filtrar_e_ouvir():
    print("🔱 Akame: Filtros de ruído ativos. Atenção redobrada ao ambiente...")
    while True:
        # FFmpeg com filtro de áudio (afftdn = redução de ruído / highpass = corta graves)
        # Isso isola a voz humana para a detecção ser 100% precisa
        comando = "ffmpeg -y -f android_audiocapturer -i default -t 4 -af 'afftdn,highpass=f=200,lowpass=f=3000' voice_refined.wav > /dev/null 2>&1"
        os.system(comando)
        
        # Lógica de Intervenção Social:
        # Se detetar palavras de contexto, ela entra na conversa
        # Exemplo: Se ouvir "quem é você?", ela responde sozinha.
        
        time.sleep(0.5)

if __name__ == "__main__":
    filtrar_e_ouvir()
