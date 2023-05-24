from collections import defaultdict
import heapq

class Graph:
    def __init__(self):
        self.graph = defaultdict(list)

    def add_edge(self, source, destination, distance, duration):
        self.graph[source].append((destination, distance, duration))
        self.graph[destination].append((source, distance, duration))

    def find_route(self, source, destination):
        distances = {node: float('inf') for node in self.graph}
        durations = {node: float('inf') for node in self.graph}
        previous_node = {node: None for node in self.graph}
        pq = [(0, source)]  # priority queue (distance, node)
        distances[source] = 0
        durations[source] = 0

        while pq:
            current_distance, current_node = heapq.heappop(pq)
            if current_distance > distances[current_node]:
                continue
            for neighbor, distance, duration in self.graph[current_node]:
                new_distance = current_distance + distance
                new_duration = durations[current_node] + duration
                if new_distance < distances[neighbor]:
                    distances[neighbor] = new_distance
                    durations[neighbor] = new_duration
                    previous_node[neighbor] = current_node
                    heapq.heappush(pq, (new_distance, neighbor))

        # Construir la ruta
        route = []
        current_node = destination
        while current_node is not None:
            route.insert(0, current_node)
            current_node = previous_node[current_node]

        return route, distances[destination], durations[destination]

class TransitApp:
    def __init__(self):
        self.graph = Graph()

    def add_route(self, source, destination, distance, duration):
        self.graph.add_edge(source, destination, distance, duration)

    def find_route(self, source, destination):
        return self.graph.find_route(source, destination)

# Crear una instancia de la aplicación de tránsito
app = TransitApp()

# Agregar rutas a la aplicación
app.add_route("A", "B", 10, 30)
app.add_route("B", "C", 15, 45)
app.add_route("A", "C", 25, 60)

# Buscar una ruta
source = input("Ingrese la ubicación de origen: ")
destination = input("Ingrese la ubicación de destino: ")

route, distance, duration = app.find_route(source, destination)
if route:
    print(f"Ruta encontrada: {route}")
    print(f"Distancia: {distance} km")
    print(f"Duración: {duration} min")
else:
    print("No se encontró una ruta para los puntos ingresados.")
