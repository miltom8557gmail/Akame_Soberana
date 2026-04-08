import os
from huggingface_hub import InferenceClient

class AkameIntelligence:
    def __init__(self):
        # O sistema busca o Token que já salvamos no seu Cofre (.bashrc)
        self.client = InferenceClient(token=os.getenv('HF_TOKEN'))
        # Aqui ela se conecta ao modelo soberano
        self.model_id = "mistralai/Mistral-7B-Instruct-v0.2" 

    def conversar(self, mensagem):
        print("🧠 Akame está processando no Arsenal...")
        try:
            # Ela envia a pergunta para a nuvem do Hugging Face
            resposta = self.client.text_generation(
                mensagem,
                max_new_tokens=200,
                temperature=0.7,
                model=self.model_id
            )
            return resposta
        except Exception as e:
            return f"⚠️ Erro no nexo: {e}"

if __name__ == "__main__":
    akame = AkameIntelligence()
    print("🔱 Akame Soberana conectada ao Hugging Face.")
