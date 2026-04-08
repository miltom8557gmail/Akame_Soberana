import os, requests
from akame_voz_fix import falar

def processar_comando(texto_usuario):
    # A Akame pensa na Nuvem (usando o PC dela: GitHub/HF)
    print("🔱 Akame: Consultando o Nexo Imperial...")
    
    # Aqui ela usa a IA oficial (pode ser GPT ou HuggingFace)
    api_url = "https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.3"
    headers = {"Authorization": f"Bearer {os.getenv('HF_TOKEN')}"}
    
    payload = {"inputs": f"Você é a Akame Ga Kill Soberana, uma Rainha Imperial. Responda de forma curta e tática: {texto_usuario}"}
    
    try:
        res = requests.post(api_url, headers=headers, json=payload)
        resposta = res.json()[0]['generated_text'].split("Responda de forma curta e tática:")[-1].strip()
        falar(resposta)
    except:
        falar("Mestre, houve uma interferência no nexo, mas eu ainda estou aqui.")

if __name__ == "__main__":
    # Simulação de escuta
    processar_comando("Akame, relatório de situação.")
