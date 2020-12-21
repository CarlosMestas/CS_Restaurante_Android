## Información general

**Curso:** 

Construcción de Software

**Profesores:**

* Jorge Cristhian Chamby Diaz

* Marisol Cristel Galarza Flores

**Proyecto:** Sistema de restaurante

**Grupo:** 6

**Alumnos:** 

* Luis Aliaga Mariaca

* Lizet Cuevas Mamani 

* Edilson Herrera Villa  

* Armando Lopez Espinosa 

* Jose Mejia Huayhua 

* Carlos Mestas Escarcena  

* Arnold Mollo Aquima

* Lizette Quispe Flores 

* Lenin Usca Huamani

# CS_Restaurante_Android

Este repositorio contiene la parte del desarrollo del proyecto de Sistema de restuarante del grupo #6

## Capturas de pantalla de las interfaces

### Interfaz principal

<img src="https://github.com/CarlosMestas/CS_Restaurante_Android/blob/master/app/src/main/res/ss/ss%201.jpg" width="350" />

Nuestra interfaz principal nos muestra el nombre del restaurante, así como también unos botones para realizar la configuración de la aplicación, el funcionamiento de la misma está explicada en los diagramas de casos de uso, tenemos el botón de cocinero que nos va a permitir observar los diversos pedidos que se han realizado para prepararlos, el botón de ingresar permite a los clientes comenzar a realizar sus pedidos y el texto de ¿Eres Nuevo? les muestra a los clientes un pequeño video de youtube para que puedan tener una guía de como usar la aplicación.

### Ventana emergente para la configuración

<img src="https://github.com/CarlosMestas/CS_Restaurante_Android/blob/master/app/src/main/res/ss/ss%202.jpg" width="350" />

En esta ventana emergente se podrá realizar la configuración para la conexión a la base de datos y también ingresar el número de mesa a la cual pertenece la tablet para que de esa manera se puedan realizar los pedidos, en el caso de ingresar correctamente la clave para la configuración entonces se mostrará el lector de códigos QR con la información necesaria para la configuración final, en el caso de no ingresar correctamente entonces no se podrá configurar.

### Interfaz video de explicación del uso de la aplicación

<img src="https://github.com/CarlosMestas/CS_Restaurante_Android/blob/master/app/src/main/res/ss/ss%203.jpg" width="350" />

En esta interfaz tenemos un video de youtube que va a permitir a los usuarios del restaurante como realizar sus pedidos.

### Interfaz de categorías

<img src="https://github.com/CarlosMestas/CS_Restaurante_Android/blob/master/app/src/main/res/ss/ss%204.jpg" width="350" />

Se puede observar luego de ingresar las diversas opciones que tenemos para nuestro pedido, en este caso la visualización de estas categorías está relacionada con la base de datos y su administración, se muestran únicamente las categorías que están permitidas por el administrador. También se tiene un botón que nos va a permitir observar qué es lo que se está solicitando.

### Interfaz de platos

<img src="https://github.com/CarlosMestas/CS_Restaurante_Android/blob/master/app/src/main/res/ss/ss%205.jpg" width="350" />

Luego de seleccionar una categoría entonces el cliente puede seleccionar qué es lo que desea pedir. Podemos ver información del producto, así como empezar a seleccionar las diversas cantidades que desea el cliente.

### Interfaz de pedido

<img src="https://github.com/CarlosMestas/CS_Restaurante_Android/blob/master/app/src/main/res/ss/ss%206.jpg" width="350" />

En esta interfaz el cliente podrá observar que es lo que a pedido, realizar modificaciones y eliminaciones, y cuando esté seguro de lo que está pidiendo, entonces con el botón pasará a la interfaz de usuario.

### Interfaz usuarios

<img src="https://github.com/CarlosMestas/CS_Restaurante_Android/blob/master/app/src/main/res/ss/ss%207.jpg" width="350" />

En esta interfaz el usuario podrá ingresar su DNI si previamente se registró en el sistema para que aparezcan sus datos en el pedido, en caso contrario podrá registrarse donde aparecerá un pequeño formulario que se mostrará en la siguiente imagen y también podrá ingresar como invitado, en este caso no se necesita ningún dato.

<img src="https://github.com/CarlosMestas/CS_Restaurante_Android/blob/master/app/src/main/res/ss/ss%208.jpg" width="350" />

En el caso de seleccionar registrarse entonces se visualizará un pequeño formulario donde podrá ingresar sus datos, en este caso si coloca continuar e ingresar los datos, automáticamente se colocará el DNI del usuario nuevo que se registró en Ingrese sus datos.

### Interfaz boleta

<img src="https://github.com/CarlosMestas/CS_Restaurante_Android/blob/master/app/src/main/res/ss/ss%209.jpg" width="350" />

En esta interfaz podemos ver lo relacionado al pedido que el usuario acaba de realizar, costo total, etc. En este momento cada uno de los pedidos detalles que se tiene pasan a estar en espera de preparación para la visualización de los mismos en su respectiva interfaz. Al momento de presionar Finalizar, entonces la aplicación está lista para recibir un nuevo pedido.

<img src="https://github.com/CarlosMestas/CS_Restaurante_Android/blob/master/app/src/main/res/ss/ss%2010.jpg" width="350" />

Ejemplo de la generación de todo el pedido con el caso de un usuario invitado.

### Ventana emergente para ingresar a la interfaz mozo / cocinero

<img src="https://github.com/CarlosMestas/CS_Restaurante_Android/blob/master/app/src/main/res/ss/ss%2011.jpg" width="350" />

En esta interfaz se podrán visualizar los distintos pedidos de las mesas para poder prepararlos y entregarlos, en este caso se le pide una clave para poder ingresar, previamente también se tiene que realizar la configuración antes de ingresar a esta interfaz.

### Interfaz mozo / cocinero

<img src="https://github.com/CarlosMestas/CS_Restaurante_Android/blob/master/app/src/main/res/ss/ss%2012.jpg" width="350" />

En esta interfaz tanto el cocinero como el mozo va a poder visualizar todos los pedidos que estan en espera, se muestra información del producto que se a pedido, la mesa a cual pertenece y también la cantidad, se tiene el manejo de colores para una mejor visualización, por ejmplo el color plomo indica que el pedido esta en espera, el color amarillo indica que se esta preparando, el color verde indica que el pedido esta listo, por ejemplo el cocinero termina de preparar un plato entonces este esta en color verde, y el mozo realiza la entrega del pedido a la mesa correspondiente, luego de ello el mozo pasa a eliminar el pedido de la lista, para que no se tenga como pendiente, en el caso también de que por algún error se cambien los estados de los productos, no se tendría ningún problema ya que luego de realizar el cambio a listo (color verde) y se presiona de nuevo este pasa a en espera (color plomo), de esta manera se puede tener un control en caso existan errores al momento de la entrega. También se cuenta con un botón que sirve para realizar actualizaciones de los pedidos, en este caso se propone una tablet tanto para el cocinero como para el mozo, entonces estos deberán de presionar el botón para tener los pedidos actualizados.

<img src="https://github.com/CarlosMestas/CS_Restaurante_Android/blob/master/app/src/main/res/ss/ss%2013.jpg" width="350" />

















