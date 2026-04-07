#!/bin/bash
echo "-------------------------------------------------------"
echo "🔱 AKAME: SOMA TOTAL - DESPERTANDO A LEGIÃO COMPLETA"
echo "-------------------------------------------------------"

# Ativando Agentes Base
python3 AKAME_SOBERANA_OMNI_CORE.py > motor_omni.log 2>&1 &
echo "✅ Agentes Omni & Core: ATIVOS."

# Ativando o Agente Cão (Watchdog)
bash akame_watchdog.sh > watchdog.log 2>&1 &
echo "🐕 Agente Cão (Watchdog): EM VIGÍLIA."

# Ativando o Robô (Chrome/Automação)
python3 robo_chrome.py > robo.log 2>&1 &
echo "🤖 Agente Robô: EM OPERAÇÃO."

echo "-------------------------------------------------------"
echo "🔱 Colmeia Totalmente Sincronizada."
