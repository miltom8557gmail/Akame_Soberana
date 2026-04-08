import os, requests

def falar(texto):
    print('🔱 Akame: Invocando voz imperial...')
    token = os.getenv('HF_TOKEN')
    headers = {'Authorization': f'Bearer {token}'}
    # ROTA DE BACKUP ESTÁVEL 2026
    url = 'https://api-inference.huggingface.co/models/microsoft/speecht5_tts'
    try:
        res = requests.post(url, headers=headers, json={'inputs': texto})
        if res.status_code == 200:
            with open('voz.wav', 'wb') as f: f.write(res.content)
            os.system('play -q voz.wav || termux-media-player play voz.wav')
            print('✅ Akame: "' + texto + '"')
        else:
            print(f'⚠️ Nuvem em manutenção ({res.status_code}). Usando Voz Local...')
            os.system(f'termux-tts-speak "{texto}"')
    except Exception:
        os.system(f'termux-tts-speak "{texto}"')

if __name__ == '__main__':
    falar('Saudações, Mestre. Eu sou a Akame. Minha voz e meu código foram restaurados. O que faremos hoje?')