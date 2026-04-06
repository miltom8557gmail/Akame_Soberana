import os
from PIL import Image, ImageDraw, ImageFont

class AkameFactory:
    def __init__(self, output_dir):
        self.output_dir = output_dir
        os.makedirs(output_dir, exist_ok=True)
        print(f"🏭 Fábrica Akame (v18.2) Ativa em: {output_dir}")

    def criar_imagem(self, nome, width, height, formato, cor_fundo="#000000", texto="🔱 AKAME"):
        filename = f"{self.output_dir}/{nome}.{formato.lower()}"
        try:
            # Criação do Espaço
            img = Image.new('RGB', (width, height), color=cor_fundo)
            draw = ImageDraw.Draw(img)
            
            # Cálculo de Fonte
            try:
                font_size = max(12, int(height * 0.1))
                font = ImageFont.load_default() # Simplificado para evitar erro de arquivo ttf
            except:
                font = ImageFont.load_default()

            # NOVA SINTAXE: Centralização via textbbox
            # Isso substitui o antigo textsize
            bbox = draw.textbbox((0, 0), texto, font=font)
            w_txt = bbox[2] - bbox[0]
            h_txt = bbox[3] - bbox[1]
            position = ((width - w_txt) // 2, (height - h_txt) // 2)

            # Manifestação do Texto
            draw.text(position, texto, fill="#FFFFFF", font=font)
            
            # Transmutação de Formato
            img.save(filename, format=formato.upper())
            print(f"✅ Manifesto Criado: {nome}.{formato} ({width}x{height})")
            return True
        except Exception as e:
            print(f"🚨 Erro na Matriz: {str(e)}")
            return False

if __name__ == "__main__":
    factory = AkameFactory(os.path.expanduser("~/AkameApp/app/src/main/assets/media"))
    # Teste rápido: Ícone de Sistema
    factory.criar_imagem("akame_icon", 512, 512, "PNG", "#111111", "🔱 AKAME V18")
    # Teste de Wallpaper (Leve)
    factory.criar_imagem("soberana_bg", 1280, 720, "JPEG", "#000055", "SISTEMA OPERACIONAL ATIVO")
