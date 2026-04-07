from huggingface_hub import hf_hub_download
import os

def baixar_modelo_influencer(repo_id):
    print(f"💾 [HD OMNI]: Baixando modelo de IA: {repo_id}")
    path = hf_hub_download(repo_id=repo_id, filename="pytorch_model.bin")
    print(f"✅ Modelo fixado em: {path}")

if __name__ == "__main__":
    # Aqui a Akame pode baixar qualquer LoRA de influencer
    print("🔱 Akame pronta para absorver modelos do Hugging Face.")
