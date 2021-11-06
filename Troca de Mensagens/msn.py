import socket
 
HOST = "localhost"
PORT = 4444
 
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect((HOST, PORT))
sock.sendall("Conectado")
print "Conectado\n"

receive = sock.recv(1024)
print "Mensagem recebida:\n", receive

while  receive.strip() != "Tchau!" :
    mensagem = input("Digite sua mensagem:\n")
    sock.sendall(mensagem)
    if mensagem == "Tchau!":
        sock.close()
        break
    else:
        print("Mensagem enviada\n")
        receive = sock.recv(1024)
        print "Mensagem recebida:\n", receive

print "Conexão encerrada"
