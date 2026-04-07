while true; do
  if ! pgrep -f "akame_omni" > /dev/null; then
    echo "🔱 Ressuscitando Motor Akame..."
    nohup python3 ~/Akame_Omni_20260405/akame_omni_05-04-2026_15h.py > ~/Akame_Omni_20260405/motor_omni.log 2>&1 &
  fi
  sleep 60
done
