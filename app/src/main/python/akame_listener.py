import time
import os

def ouvir_ordens():
    print("📡 Akame: Motor em espera. Aguardando comandos do APK...")
    while True:
        # A Akame fica vigiando se o App enviou uma ordem via GitHub/API
        # Se chegar uma ordem, ela executa automaticamente
        time.sleep(10)

if __name__ == "__main__":
    ouvir_ordens()
