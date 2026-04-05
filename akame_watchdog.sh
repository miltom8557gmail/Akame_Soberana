#!/data/data/com.termux/files/usr/bin/bash
while true; do
  if ! pgrep -f "akame_omni" > /dev/null; then
    nohup python3 ~/Akame_Omni_20260405/akame_omni_05-04-2026_06h.py > ~/Akame_Omni_20260405/motor.log 2>&1 &
  fi
  sleep 30
done
