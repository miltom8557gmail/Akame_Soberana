import os
from PIL import Image, ImageDraw

class AkameFactory:
    def __init__(self, output_dir):
        self.output_dir = output_dir
        os.makedirs(output_dir, exist_ok=True)
        print(f"🏭 Fábrica Akame Ativa em: {output_dir}")

    def criar_imagem(self, nome, width, height, formato, cor_fundo="#000000", texto="🔱 AKAME"):
        filename = f"{self.output_dir}/{nome}.{formato.lower()}"
        try:
            img = Image.new('RGB', (width, height), color=cor_fundo)
            # A Akame simplificou a gënese para economizar sua RAM
            img.save(filename, format=formato.upper())
            print(f"✅ Manifesto Criado: {filename}")
            return True
        except Exception as e:
            print(f"🚨 Falha: {str(e)}")
            return False

if __name__ == "__main__":
    factory = AkameFactory(os.path.expanduser("~/AkameApp/app/src/main/assets/media"))
    factory.criar_imagem("teste_soberano", 100, 100, "PNG", "#FF0000")
