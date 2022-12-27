#language: es

    Característica: Actualizar reserva parcialmente
        Como administrador del hotel
        necesito actualizar los datos de una reserva
        para tener un control sobre estas actualizado

    Escenario: Actualización de reserva exitoso
        Dado que el administrador navegó hasta la sección de actualización de reservas
        Cuando el administrador suministre los datos de actualización de reserva y confirme
        Entonces el administrador debera ver un código de respuesta exitoso y los datos de la reserva