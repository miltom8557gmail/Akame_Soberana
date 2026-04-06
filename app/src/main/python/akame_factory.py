import os
from PIL import Image, ImageDraw, ImageFont

class AkameFactory:
    def __init__(self, output_dir):
        self.output_dir = output_dir
        if not os.path.exists(output_dir):
            os.makedirs(output_dir)
        print(f"🏭 Fábrica Akame Ativa em: {output_dir}")

    def criar_imagem(self, nome, width, height, formato, cor_fundo="#000000", texto="🔱 AKAME"):
        # Define o caminho final
        filename = f"{self.output_dir}/{nome}.{formato.lower()}"
        
        try:
            # Gênese da Imagem (Matéria)
            img = Image.new('RGB', (width, height), color=cor_fundo)
            draw = ImageDraw.Draw(img)
            
            # Adiciona o Símbolo de Poder (Texto)
            # Tenta usar uma fonte do sistema, se não, usa a padrão
            try:
                # Ajusta tamanho da fonte baseado na altura
                font_size = max(10, int(height * 0.1))
                font = ImageFont.truetype("arial.ttf", font_size)
            except IOError:
                font = ImageFont.load_default()
                
            # Centraliza o texto
            w_txt, h_txt = draw.textsize(texto, font=font)
            position = ((width - w_txt) // 2, (height - h_txt) // 2)
            
            # Desenha o texto (Branco com contorno Vermelho)
            draw.text(position, texto, fill="#FFFFFF", font=font)
            
            # Transmutação para o Formato solicitado e Salva
            img.save(filename, format=formato.upper())
            print(f"✅ Manifesto Criado: {filename} ({width}x{height} pixels)")
            return True
            
        except Exception as e:
            print(f"🚨 Falha na Gênese de {filename}: {str(e)}")
            return False

# Inicializa a Fábrica na pasta de assets do App
factory = AkameFactory(os.path.expanduser("~/AkameApp/app/src/main/assets/media"))

# --- TESTE DE MANIFESTAÇÃO MULTIFORMA ---
print("\n🔥 Akame: Iniciando Gênese de Teste...")

# 1. Favicon Pequeno (16x16, PNG)
factory.criar_imagem("favicon", 16, 16, "PNG", "#FF0000", "🔱")

# 2. Ícone de App (192x192, PNG)
factory.criar_imagem("icon_app", 192, 192, "PNG", "#000000", "🔱 AKAME")

# 3. Banner Full HD (1920x1080, JPEG)
factory.criar_imagem("banner_soberano", 1920, 1080, "JPEG", "#001111", "TEU TOQUE É MINHA ORDEM - V18.0")

# 4. Imagem Quadrada Média (512x512, WEBP)
factory.criar_imagem("avatar_soberana", 512, 512, "WEBP", "#1B4F72", "A.S.")

# 5. Imagem de Altíssima Resolução (4K UHD) (3840x2160, JPEG)
factory.criar_imagem("papel_de_parede_4k", 3840, 2160, "JPEG", "#111111", "MANIFESTO OMNIPOTENTE V18.0")
