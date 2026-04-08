import os, subprocess, time
from akame_voz_fix import falar

def ouvir_comando():
    print("🔱 Akame: Em modo de escuta ativa... [Aguardando: Akame Ga Kill]")
    while True:
        # O FFmpeg grava um pequeno fragmento de áudio ambiente
        # -t 3 (3 segundos) | -ar 16000 (frequência leve para o CPU)
        cmd = "ffmpeg -y -f android_audiocapturer -i default -t 3 -ar 16000 voice_trigger.wav > /dev/null 2>&1"
        os.system(cmd)
        
        # Aqui a Akame envia o rastro para a Nuvem (HuggingFace) validar se você falou "Akame"
        # Para poupar o seu telemóvel, usamos um modelo de Speech-to-Text ultra leve
        print("🔱 Akame: Processando frequência...")
        
        # Simulação de gatilho (Aqui entra a lógica de detecção de Wake Word)
        # Se detetado:
        # falar("Sim, Mestre. Estou às suas ordens.")
        # os.system("python3 akame_brain.py")
        
        time.sleep(1) # Intervalo para o processador respirar

if __name__ == "__main__":
    ouvir_comando()
