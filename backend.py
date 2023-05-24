from flask import Flask, jsonify, request

app = Flask(__name__)

# Datos de rutas (pueden ser almacenados en una base de datos)
routes = [
    {"source": "A", "destination": "B", "distance": 10, "duration": 30},
    {"source": "B", "destination": "C", "distance": 15, "duration": 45},
    {"source": "A", "destination": "C", "distance": 25, "duration": 60}
]

@app.route('/routes', methods=['GET'])
def get_routes():
    return jsonify(routes)

@app.route('/route', methods=['POST'])
def find_route():
    source = request.json.get('source')
    destination = request.json.get('destination')

    if not source or not destination:
        return jsonify({'error': 'Source and destination are required'}), 400

    route = None
    for r in routes:
        if r['source'] == source and r['destination'] == destination:
            route = r
            break

    if route:
        return jsonify(route)
    else:
        return jsonify({'error': 'Route not found'}), 404

if __name__ == '__main__':
    app.run()
