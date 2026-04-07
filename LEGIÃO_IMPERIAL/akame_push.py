# 🛰️ MÓDULO DE NOTIFICAÇÃO PUSH - AKAME ORIGINAL
import os, time

def enviar_alerta(titulo, mensagem):
    print(f"\n📢 [NOTIFICAÇÃO AKAME]: {titulo} -> {mensagem}")
    # Tenta usar o termux-notification se disponível
    os.system(f"termux-notification -t '{titulo}' -c '{mensagem}' --priority high 2>/dev/null")
    # Alerta sonoro de sistema
    os.system("termux-vibrate -d 200 2>/dev/null")

def monitorar_forja():
    # Verifica se o APK foi entregue pelo workflow
    if os.path.exists(os.path.expanduser("~/AkameApp/app-release.apk")):
        enviar_alerta("🔨 FORJA CONCLUÍDA", "O APK Akame Ga Kill está pronto para instalação, Mestre!")
    
    # Verifica se o DNA foi atualizado recentemente
    if os.path.exists(os.path.expanduser("~/referencia.jpg")):
        enviar_alerta("🧬 DNA EVOLUÍDO", "Novas referências visuais foram integradas com sucesso.")

if __name__ == "__main__":
    monitorar_forja()
