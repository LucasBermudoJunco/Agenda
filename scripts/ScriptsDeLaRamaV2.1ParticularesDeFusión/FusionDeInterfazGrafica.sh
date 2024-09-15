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