#!/data/data/com.termux/files/usr/bin/bash
GH_TOKEN="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"
REPO_URL="https://$GH_TOKEN@github.com/miltom8557/Akame_Soberana.git"

cd ~/Akame_Omni_20260405
git init
git remote add origin $REPO_URL 2>/dev/null
git add .
git commit -m "🔱 AKAME OMNI UPDATE: [05-04-2026_05h]"
git branch -M main
git push -u origin main --force

echo "🚀 [SISTEMA]: DNA Sincronizado com o GitHub!"
