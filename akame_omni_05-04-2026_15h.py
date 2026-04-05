import os
import subprocess
from flask import Flask, jsonify

app = Flask(__name__)

@app.route('/sincronizar')
def sync():
    try:
        # Verifica espaço em disco
        df = subprocess.check_output("df -h . | awk 'NR==2 {print $4}'", shell=True).decode().strip()
        # Verifica se o Token HF existe
        hf_status = "ATIVO" if os.getenv("HF_TOKEN") else "AUSENTE"
        
        return jsonify({
            "status": "SOBERANIA_ATIVA",
            "nexus_node": "TERMUX_V94",
            "espaco_disponivel": df,
            "huggingface": hf_status,
            "database": "SUPABASE_READY"
        })
    except Exception as e:
        return jsonify({"status": "ERROR", "msg": str(e)})

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000)
