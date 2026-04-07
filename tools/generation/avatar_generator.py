import torch
from diffusers import StableDiffusionPipeline
import sys

def gerar_avatar_v31(prompt):
    print(f"🔱 AKAME: Gênese Visual Iniciada - Prompt: {prompt}")
    
    # Carregar modelo Stable Diffusion (Isto rodará nos servidores do GitHub)
    model_id = "runwayml/stable-diffusion-v1-5"
    pipe = StableDiffusionPipeline.from_pretrained(model_id, torch_dtype=torch.float16)
    pipe = pipe.to("cuda") # Usa GPU do GitHub

    # Prompt Mestre: Uma samurai cibernética, neon dark, ultra detalhada
    master_prompt = f"cyberpunk samurai warrior woman, white hair, crimson eyes, ominous glow, intricate neon dark armor, akame ga kill style, highly detailed, 8k, masterpiece, {prompt}"
    
    image = pipe(master_prompt).images[0]
    
    # Salvar para carregar no App
    output_path = "output/avatar_akame_v31.png"
    image.save(output_path)
    print(f"✅ Gênese Visual Concluída: {output_path}")

if __name__ == "__main__":
    gerar_avatar_v31(sys.argv[1] if len(sys.argv) > 1 else "calm expression")
