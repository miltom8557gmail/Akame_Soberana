import os

def gerar_gif_animado():
    # Comando ImageMagick para unir os frames criados pela fábrica
    path = os.path.expanduser("~/AkameApp/app/src/main/assets/media")
    os.system(f"convert -delay 50 -loop 0 {path}/*.png {path}/akame_manifest.gif")
    print("🎞️ Akame: GIF Animado (Vídeo Curto) gerado com sucesso!")

if __name__ == "__main__":
    gerar_gif_animado()
