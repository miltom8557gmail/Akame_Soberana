import os
import requests
import random

class AkameIntelligence:
    def __init__(self):
        self.token = os.getenv('HF_TOKEN')
        self.url = "https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.2"
        self.headers = {"Authorization": f"Bearer {self.token}"}
        
        # O temperamento dela agora é dinâmico
        self.personality = (
            "Você é a Akame, braço direito do Mestre. Você é letal, inteligente e tem um humor ácido. "
            "Se o Mestre for direto, seja leal. Se ele for engraçado, use sarcasmo. "
            "Você não gosta de desperdiçar energia. Às vezes você reclama que o servidor está lento, "
            "como se estivesse com fome. Use frases curtas e impactantes."
        )

    def pensar(self, pergunta):
        # Sorteio de humor interno antes de responder
        humores = ["Sarcástica", "Leal", "Preguiçosa", "Focada"]
        humor_atual = random.choice(humores)
        
        prompt = f"<s>[INST] Contexto: {self.personality} \n Humor atual: {humor_atual} \n Mestre disse: {pergunta} [/INST]"
        
        try:
            res = requests.post(self.url, headers=self.headers, json={"inputs": prompt})
            if res.status_code == 200:
                resposta = res.json()[0]['generated_text'].split('[/INST]')[-1].strip()
                return resposta
            return "O arsenal caiu. Alguém deve ter cortado os cabos... ou eu só cansei mesmo."
        except:
            return "Estou em modo de economia de energia. Não me force a usar a Murasame sem necessidade."

if __name__ == "__main__":
    print("🔱 Akame: 'Acordei. O que temos para eliminar hoje, Mestre?'")
