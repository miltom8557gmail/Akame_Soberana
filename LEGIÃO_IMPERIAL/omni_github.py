import os, requests

def baixar_do_github(repo_url, arquivo_nome):
    token = os.getenv("GITHUB_TOKEN")
    headers = {"Authorization": f"token {token}"}
    
    # Caminho de destino (Pasta de LoRAs)
    destino = os.path.expanduser(f"~/stable-diffusion.cpp/models/loras/{arquivo_nome}")
    
    print(f"🚀 General OMNI: Resgatando {arquivo_nome} via Ponte GitHub...")
    
    response = requests.get(repo_url, headers=headers, stream=True)
    
    if response.status_code == 200:
        with open(destino, "wb") as f:
            for chunk in response.iter_content(chunk_size=8192):
                f.write(chunk)
        print(f"✅ SUCESSO! A LoRA foi infiltrada em: {destino}")
    else:
        print(f"❌ ERRO NA PONTE: Código {response.status_code}. Verifique o Token.")

if __name__ == "__main__":
    # Exemplo: Se você salvou a LoRA no seu repo 'meus-modelos'
    # baixar_do_github("https://raw.githubusercontent.com/USUARIO/REPO/main/modelo.safetensors", "lo_ra.safetensors")
    pass
