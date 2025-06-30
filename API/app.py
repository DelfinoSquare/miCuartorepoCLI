from flask import Flask, jsonify, request, abort

app = Flask(__name__)

# Datos simulados
datos = {1: {'nombre': 'Juan', 'edad': 30}, 2: {'nombre': 'Ana', 'edad': 25}}

@app.route('/')
def home(): return "¡Bienvenido a la API!"

@app.route('/api/personas', methods=['GET'])
def obtener_personas(): return jsonify(datos)

@app.route('/api/personas/<int:id>', methods=['GET'])
def obtener_persona(id):
    persona = datos.get(id)
    if not persona: abort(404, 'Persona no encontrada')
    return jsonify(persona)

@app.route('/api/personas', methods=['POST'])
def agregar_persona():
    if not request.json or 'nombre' not in request.json or 'edad' not in request.json:
        abort(400, 'Faltan datos')
    id_nueva = max(datos.keys()) + 1
    nueva_persona = {'nombre': request.json['nombre'], 'edad': request.json['edad']}
    datos[id_nueva] = nueva_persona
    return jsonify(nueva_persona), 201

@app.route('/api/personas/<int:id>', methods=['PUT'])
def actualizar_persona(id):
    persona = datos.get(id)
    if not persona: abort(404, 'Persona no encontrada')
    persona.update(request.json)
    return jsonify(persona)

@app.route('/api/personas/<int:id>', methods=['DELETE'])
def eliminar_persona(id):
    if id not in datos: abort(404, 'Persona no encontrada')
    del datos[id]
    return jsonify({'mensaje': 'Eliminado'}), 200

@app.errorhandler(404)
def not_found(error): return jsonify({'error': 'No encontrado', 'mensaje': str(error)}), 404

@app.errorhandler(400)
def bad_request(error): return jsonify({'error': 'Solicitud incorrecta', 'mensaje': str(error)}), 400

if __name__ == '__main__':
    app.run(debug=True)
