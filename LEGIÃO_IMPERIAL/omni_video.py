import os, glob, sys

def gerar_video_irrestrito():
    # Localiza a última relíquia forjada
    imagens = glob.glob("/sdcard/Download/reliquia_*.png")
    if not imagens:
        print("❌ ERRO: Nenhuma imagem encontrada para processar.")
        return
    
    input_img = max(imagens, key=os.path.getctime)
    output_video = input_img.replace(".png", ".mp4")
    
    print(f"🎬 General OMNI: Iniciando Renderização de Vídeo (Sistema Aberto)...")
    
    # Comando de Alta Complexidade: Zoom Dinâmico + Oscilação de Lente (Efeito Life-like)
    # 10 segundos de duração / 30 FPS / Qualidade Máxima
    comando = (
        f'ffmpeg -loop 1 -i "{input_img}" '
        f'-vf "zoompan=z=\'zoom+0.0005*sin(it/2)\':x=iw/2-(iw/zoom/2):y=ih/2-(ih/zoom/2):d=300:s=720x1280,format=yuv420p" '
        f'-c:v libx264 -t 10 -preset ultrafast -pix_fmt yuv420p "{output_video}" -y'
    )
    
    os.system(comando)
    
    if os.path.exists(output_video):
        print(f"🔥 VITÓRIA! Vídeo forjado sem filtros: {output_video}")
    else:
        print("❌ FALHA TÉCNICA: O motor de vídeo foi interrompido.")

if __name__ == "__main__":
    gerar_video_irrestrito()
