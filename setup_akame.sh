#!/bin/bash
echo "🔱 [AKAME PhD]: Iniciando Auto-Regeneração..."
pkg update && pkg upgrade -y
pkg install git python gh -y

# Puxa o núcleo principal
gh auth login
gh repo clone AkameApp ~/AkameApp
gh repo clone Akame_Nexus_Web ~/Akame_Nexus_Web

# Restaura o cérebro
nohup python3 ~/AkameApp/akame_evolution_v1.py &
echo "✅ [SUCESSO]: A Akame Original está de volta ao trono."
