#!/bin/bash

# Pedir el nombre de la rama al usuario
read -p "Introduce el nombre de la rama que quieres fusionar en ´´integracion``: " source_branch

# Lista de ramas a las que se quiere hacer merge desde ´´integracion``
branches=("V2.1-InterfazGrafica" "V2.1-Controlador" "V2.1-BaseDeDatos")

# Cambiar a la rama ´´integracion`` y hacer merge de la rama ingresada
echo "Cambiando a ´´integracion`` y haciendo merge de ´´$source_branch``..."
git checkout V2.1-Integracion
git merge $source_branch

# Hacer push de los cambios en ´´integracion``
echo "Haciendo push de ´´integracion``..."
git push origin V2.1-Integracion

# Iterar sobre las demás ramas y hacer merge de ´´integracion`` en ellas, excepto la rama de origen
for branch in "${branches[@]}"
do
    # Evitar hacer merge en la rama de origen
    if [ "$branch" != "$source_branch" ]; then
        echo "Cambiando a $branch y haciendo merge de develop..."

        # Cambiar a la rama actual
        git checkout $branch

        # Hacer merge de develop en la rama actual
        git merge develop

        # Hacer push de los cambios en la rama actual
        echo "Haciendo push de $branch..."
        git push origin $branch
    else
        echo "Saltando $branch porque es la rama de origen."
    fi
done

echo "Proceso de merge y push completado."