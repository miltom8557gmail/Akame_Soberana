import os
class Vault:
    @staticmethod
    def get_supabase_creds():
        return {"url": os.getenv('SUPABASE_URL'), "key": os.getenv('SUPABASE_KEY')}
    @staticmethod
    def get_hugging_token():
        return os.getenv('HF_TOKEN')
if __name__ == "__main__":
    print("✅ Cofre de Arquivos Atualizado.")
