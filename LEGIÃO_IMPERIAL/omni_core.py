import os, sys, random
from datetime import datetime

# CONFIGURAÇÕES DA FORJA NATIVA
MOTOR = os.path.expanduser("~/stable-diffusion.cpp/build/bin/sd")
MODELO = os.path.expanduser("~/stable-diffusion.cpp/models/dreamshaper.safetensors")
LORA_DIR = os.path.expanduser("~/stable-diffusion.cpp/models/loras")

def gerar_imagem(prompt):
    print("⚙️ General OMNI: Iniciando Forja com Injeção de LoRA...")
    nome = f"reliquia_{random.randint(1000, 9999)}.png"
    
    # Seleciona a LoRA 'detail_tweaker' e define a força em 0.8 (Potente)
    lora_path = os.path.join(LORA_DIR, "detail_tweaker.safetensors")
    
    # Comando com suporte a LoRA
    # --lora-path aponta para o arquivo, e o prompt agora pode ser mais agressivo
    comando = (
        f"{MOTOR} -m {MODELO} "
        f"--lora-path {lora_path} --lora-scaled 0.8 "
        f"-p '{prompt}, masterpiece, highly detailed, raw photo, nsfw' "
        f"-o {nome} --steps 20 --threads 4"
    )
    
    try:
        os.system(comando)
        if os.path.exists(nome):
            print(f"✅ VITÓRIA! Relíquia com LoRA forjada: {nome}")
            os.system(f"mv {nome} /sdcard/Download/ 2>/dev/null")
        else:
            print("❌ FALHA NA FORJA: Verifique se o binário sd e a LoRA existem.")
    except Exception as e:
        print(f"💥 ERRO: {e}")

if __name__ == "__main__":
    if len(sys.argv) > 1:
        gerar_imagem(sys.argv[1])
