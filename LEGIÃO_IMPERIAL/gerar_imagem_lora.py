import requests
import sys
import random

# CONFIGURAÇÃO DO GENERAL
# Lembre-se de colocar seu Token real aqui!
TOKEN_HF = "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"

# O NOVO ENDEREÇO (ROUTER)
API_URL = "https://router.huggingface.co/hf-inference/models/digiplay/Lustify_v2"
headers = {"Authorization": f"Bearer {TOKEN_HF}"}

def gerar(prompt):
    print(f"🎨 General OMNI abrindo o novo portal para: {prompt}")
    payload = {"inputs": prompt}
    try:
        response = requests.post(API_URL, headers=headers, json=payload)
        
        if response.status_code == 200:
            nome_arquivo = f"reliquia_{random.randint(1000, 9999)}.png"
            with open(nome_arquivo, "wb") as f:
                f.write(response.content)
            print(f"✅ Relíquia forjada com sucesso: {nome_arquivo}")
            print(f"📍 Local: {nome_arquivo}")
        else:
            print(f"❌ Erro no portal (Status {response.status_code}): {response.text}")
    except Exception as e:
        print(f"❌ Falha na conexão astral: {e}")

if __name__ == "__main__":
    if len(sys.argv) > 1:
        gerar(sys.argv[1])
    else:
        print("Mestre, o General aguarda o prompt para a criação.")
