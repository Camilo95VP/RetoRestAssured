#language: es

  Característica: Crear token de autorización
    Como administrador del hotel
    necesito obtener un token de autorización
    para poder realizar reservas y otras operaciones

  Escenario: Crear un token exitosamente
    Dado que el administrador navegó hasta la sección de autorizaciones
    Cuando el administrador suministre los datos de autorización y confirme
    Entonces el administrador debera ver un código de respuesta exitoso y un token