import os, subprocess

def testar_conexao(nome, comando):
    print(f"🔍 Testando {nome}...")
    try:
        subprocess.run(comando, shell=True, check=True, capture_output=True)
        print(f"✅ {nome}: Funcionando perfeitamente.")
    except:
        print(f"❌ {nome}: Falha na conexão ou configuração.")

print("🔱 [AKAME OMNI]: DIAGNÓSTICO DE SISTEMA")
testar_conexao("GitHub Auth", "gh auth status")
testar_conexao("Git Config", "git config --list")
testar_conexao("Hugging Face API", "curl -I https://huggingface.co")
testar_conexao("Civitai API", "curl -I https://civitai.com")
testar_conexao("Supabase Edge", "curl -I https://supabase.co")

if __name__ == "__main__":
    pass
