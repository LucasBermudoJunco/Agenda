#!/bin/bash

# Script para la fusión automática de todas las demás ramas
# con los cambios (coomit y push) que se hayan producido
# en la rama ´´V2.1-InterfazGrafica``.
# Para ejecutarlo, Asegurarse de que el usuario tiene los derechos
# de ejecución de este script (Viéndolos con el comando ´´ls -l`` y,
# en caso de que no los tenga,
# Ejecutando el comando ´´chmod +x FusionDeInterfazGrafica.sh``),
# Situarse en la rama V2.1-InterfazGrafica
# y Ejecutar el siguiente comando:
# ./FusionDeInterfazGrafica.sh

# Rama Integracion
git checkout V2.1-Integracion

echo
echo "Cambio realizado a la rama Integracion"
echo

git merge V2.1-InterfazGrafica

echo
echo "Fusión realizada de la rama Integracion con la rama InterfazGrafica"
echo

git push origin V2.1-Integracion

echo
echo "Push realizado de la rama Integracion"
echo

# Rama Controlador
git checkout V2.1-Controlador

echo
echo "Cambio realizado a la rama Controlador"
echo

git merge V2.1-Integracion

echo
echo "Fusión realizada de la rama Controlador con la rama Integracion"
echo

git push origin V2.1-Controlador

echo
echo "Push realizado de la rama Controlador"
echo

# Rama BaseDeDatos
git checkout V2.1-BaseDeDatos

echo
echo "Cambio realizado a la rama BaseDeDatos"
echo

git merge V2.1-Integracion

echo
echo "Fusión realizada de la rama BaseDeDatos con la rama Integracion"
echo

git push origin V2.1-BaseDeDatos

echo
echo "Push realizado de la rama BaseDeDatos"
echo