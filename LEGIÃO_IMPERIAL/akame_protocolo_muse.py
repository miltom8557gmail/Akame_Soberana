import requests, os

def disparar_criacao_influencer():
    print("🔱 [PROTOCOLO MUSE]: Iniciando a criação da Akame-San...")
    
    # Ordem para o GitHub Actions começar o Scrape e Treino
    cmd = "gh workflow run compilar_akame_apk.yml --field task=TRAIN_INFLUENCER"
    os.system(cmd)
    
    # Notificando o Supabase para o APK mostrar "Processando IA..."
    url = "https://bfriplrxtleleplhpgwd.supabase.co/rest/v1/agregador_projeto"
    h = {"apikey": "sb_publishable_gXUaEYTs5znqXzElEeGKTA_AQKQ9EGI", "Authorization": "Bearer sb_publishable_gXUaEYTs5znqXzElEeGKTA_AQKQ9EGI"}
    requests.post(url, json={"modulo_alvo": "IA_TREINO", "nova_feature": "INICIANDO_TREINAMENTO_AKAME_SAN", "status_integracao": "PENDENTE"}, headers=h)

if __name__ == "__main__":
    disparar_criacao_influencer()
