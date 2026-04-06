#!/bin/bash

# --- NÚCLEO DE PODER ---
API_KEY="AIzaSyCULLS_IYTmttS7djypmx-FePi8HH-TZ0M"
ROOT=~
APP_BASE=~/AkameApp/app/src/main
LOG_ETERNO=~/akame_chronicle.log

registrar() {
    echo "[$(date '+%d/%m %H:%M:%S')] 🔱 AKAME: $1" >> $LOG_ETERNO
}

# --- MOTOR DE DECISÃO AUTÓNOMA ---
decidir_e_executar() {
    local problema="$1"
    registrar "Obstáculo detetado: $problema. Consultando Oráculo..."
    
    PROMPT="No Termux, diretório ~/AkameApp, ocorreu o erro: '$problema'. Responda APENAS com o comando bash mais seguro para corrigir e continuar a operação. Sem texto adicional."
    
    RESPOSTA=$(curl -s -X POST "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=$API_KEY" \
        -H "Content-Type: application/json" \
        -d "{\"contents\": [{\"parts\":[{\"text\": \"$PROMPT\"}]}]}")

    COMANDO=$(echo $RESPOSTA | jq -r '.candidates[0].content.parts[0].text' | sed 's/```bash//g' | sed 's/```//g' | xargs)

    if [ ! -z "$COMANDO" ] && [ "$COMANDO" != "null" ]; then
        registrar "Decisão: Executando [$COMANDO]"
        eval "$COMANDO" >> $LOG_ETERNO 2>&1
    else
        registrar "⚠️ Oráculo silencioso. Tentando purga de cache..."
        rm -rf ~/AkameApp/.gradle
    fi
}

# --- OPERAÇÃO DE FLUXO ---
fluxo_soberano() {
    # Gênese: Cria o que falta
    mkdir -p "$APP_BASE/java/com/akame/soberana/" "$APP_BASE/res/layout/"

    # Organização: Move DNA (Java/XML)
    find $ROOT -maxdepth 1 -name "*.java" -exec mv {} "$APP_BASE/java/com/akame/soberana/" \; 2>/dev/null
    find $ROOT -maxdepth 1 -name "*.xml" ! -name "AndroidManifest.xml" -exec mv {} "$APP_BASE/res/layout/" \; 2>/dev/null

    # Sincronia: Se houver mudança, envia para a Forja
    cd ~/AkameApp 2>/dev/null || { decidir_e_executar "Pasta AkameApp desapareceu"; return; }
    
    if [[ -n $(git status -s) ]]; then
        registrar "Evolução detetada. Sincronizando..."
        git add . && git commit -m "🔱 Akame: Autonomia Ativa" && git push origin main || decidir_e_executar "Falha no Git Push/Sync"
        gh workflow run FORJA_OMNI.yml --ref main 2>/dev/null
    fi
}

# Iniciar Ciclo
fluxo_soberano
# Manter apenas as últimas 1000 linhas de história
tail -n 1000 $LOG_ETERNO > ${LOG_ETERNO}.tmp && mv ${LOG_ETERNO}.tmp $LOG_ETERNO
