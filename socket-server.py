import socket
import time
from datetime import datetime
import random

HOST = "localhost"  # Standard loopback interface address (localhost)
PORT = 9999  # Port to listen on (non-privileged ports are > 1023)
data_pattern = "{},{},{}\n"
sensorid = [1, 2]#, 3, 4] #4 sensores
if __name__ == '__main__':
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((HOST, PORT))
        s.listen()
        conn, addr = s.accept()
        with conn:
            print(f"Connected by {addr}")
            while True:
                data = data_pattern.format(random.choice(sensorid), int(datetime.timestamp(datetime.now())), random.randint(10, 60))
                print("sending ..." + data)
                conn.sendall(bytes(data, 'utf-8'))
                time.sleep(1)
