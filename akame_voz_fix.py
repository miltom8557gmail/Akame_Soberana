from gtts import gTTS
import os

def falar(texto):
    print(f"🔱 Akame: {texto}")
    try:
        tts = gTTS(text=texto, lang='pt', slow=False)
        tts.save("akame_voz.mp3")
        # O MPV toca direto no hardware do seu celular
        os.system("mpv --no-video akame_voz.mp3 > /dev/null 2>&1")
        os.remove("akame_voz.mp3") # Limpeza para manter o celular leve
    except Exception as e:
        print(f"❌ Erro na voz: {e}")

if __name__ == "__main__":
    falar("Mestre, troquei minha frequência. O nexo de voz está restabelecido. Eu sou a Akame, sua Rainha Imperial.")
