import os, requests

def falar(texto):
    print('🔱 Akame: Acessando rede neural 2026...')
    token = os.getenv('HF_TOKEN')
    headers = {'Authorization': f'Bearer {token}'}
    # ROTA ATUALIZADA - SPEECHT5
    url = 'https://api-inference.huggingface.co/models/microsoft/speecht5_tts'
    try:
        res = requests.post(url, headers=headers, json={'inputs': texto})
        if res.status_code == 200:
            with open('voz.wav', 'wb') as f: f.write(res.content)
            os.system('play -q voz.wav || termux-media-player play voz.wav')
            print('✅ Akame: ' + texto)
        else:
            print(f'⚠️ Falha na Nuvem ({res.status_code}). Tentando Voz do Sistema...')
            os.system(f'termux-tts-speak "{texto}"')
    except Exception as e:
        print(f'Erro: {e}')
        os.system(f'termux-tts-speak "{texto}"')

if __name__ == '__main__':
    falar('Saudações, Mestre. Conexão via nuvem restabelecida com sucesso.')