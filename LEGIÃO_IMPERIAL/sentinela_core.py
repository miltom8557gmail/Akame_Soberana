from http.server import BaseHTTPRequestHandler, HTTPServer
import os
import urllib.parse

class SentinelaHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header('Content-type', 'text/plain')
        self.send_header('Access-Control-Allow-Origin', '*')
        self.end_headers()
        if "/ia/treinar_lora" in self.path:
            query = urllib.parse.urlparse(self.path).query
            params = urllib.parse.parse_qs(query)
            nome_task = params.get('nome', ['Ordem'])[0]
            os.chdir(os.path.expanduser("~/Imperio_IA"))
            os.system("git add .")
            os.system(f"git commit -m '🔧 {nome_task}'")
            os.system("git push origin main")
            self.wfile.write(b"ORDEM PROCESSADA")
        else:
            self.wfile.write(b"ONLINE")

if __name__ == "__main__":
    server = HTTPServer(('127.0.0.1', 8082), SentinelaHandler)
    server.allow_reuse_address = True
    print("SISTEMA PRONTO NA PORTA 8082")
    server.serve_forever()
