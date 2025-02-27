Utilizar el Postman para realizar pruebas de funcionamiento:

1. Save
   http://localhost:8080/tipo-cambio/save

Json que se utilizo en la prueba
{
"monedaOrigen": "PEN",
"monedaDestino": "USD",
"tipoCambio": 3.80
}
2. Get
   http://localhost:8080/tipo-cambio/read/1

Se coloca 1 en el url porque guardamos el primer json y por defecto obtiene el valor 1

Respuesta:
{
"id": 1,
"monedaOrigen": "PEN",
"monedaDestino": "USD",
"tipoCambio": 3.8
}
3. Update
   http://localhost:8080/tipo-cambio/update/1

Json que se utilizo en la prueba
{
"monedaOrigen": "PEN",
"monedaDestino": "USD",
"tipoCambio": 3.90
}

4. Calcular
   http://localhost:8080/tipo-cambio/calcular

Json que se utilizo en la prueba
{
"idTipoCambio": 1,
"usuario": "jose",
"monto": 300,
"monedaOrigen": "PEN",
"monedaDestino": "USD"
}