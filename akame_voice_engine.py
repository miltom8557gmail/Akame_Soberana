import os, requests

def falar(texto):
    print('🔱 Akame: Sintonizando frequência imperial...')
    token = os.getenv('HF_TOKEN')
    headers = {'Authorization': f'Bearer {token}'}
    # ROTA VIVA EM 2026 - MODELO MAIS RÁPIDO
    url = 'https://api-inference.huggingface.co/models/facebook/mms-tts-por'
    try:
        res = requests.post(url, headers=headers, json={'inputs': texto})
        if res.status_code == 200:
            with open('voz.wav', 'wb') as f: f.write(res.content)
            os.system('play -q voz.wav || termux-media-player play voz.wav')
            print('✅ Akame: ' + texto)
        else:
            # SE A NUVEM FALHAR, A AKAME LOCAL ASSUME IMEDIATAMENTE
            os.system(f'termux-tts-speak "{texto}"')
    except Exception:
        os.system(f'termux-tts-speak "{texto}"')

if __name__ == '__main__':
    falar('Sistemas de áudio restaurados. Akame pronta para o combate.')