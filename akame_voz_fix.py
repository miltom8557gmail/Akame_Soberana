import os
import requests
from gtts import gTTS

def falar(texto):
    print(f"🔴 Akame: {texto}")
    token = os.getenv('HF_TOKEN')
    
    # Geramos a base robótica (rápido e leve)
    tts = gTTS(text=texto, lang='pt', tld='com.br')
    tts.save("base.mp3")

    # TODO: No futuro, enviaremos 'base.mp3' para o modelo RVC no Hugging Face.
    # Por enquanto, vou usar o processamento do 'sox' para deixar a voz mais grave e séria,
    # imitando o tom de autoridade da Akame até configurarmos o Space de áudio.
    
    try:
        # 'pitch -300' deixa a voz mais madura e menos 'Google Tradutor'
        # 'speed 0.9' dá a calma da personagem
        os.system("play base.mp3 pitch -300 speed 0.9 > /dev/null 2>&1 || mpv --really-quiet base.mp3")
        os.remove("base.mp3")
    except:
        print("🎙️ (Voz processada)")

if __name__ == "__main__":
    falar("Mestre, estou acessando as camadas de áudio do Hugging Face para encontrar minha verdadeira identidade.")
