Proyecto con un servicio web que consta de 3 end-points:
Settings para configurar captcha en la aplicación Captcha-front
Captcha, para back de front de la aplicación Captcha-front
Images, end-point para pasar peticiones con un body y autenticacion.

El formato del body para las peticiones de imágenes es:

["https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/1200/public/media/image/2019/02/IA_personas_no_existen.jpg?itok=oe8Wga4u","https://blog.inmarketing.co/hubfs/Imported_Blog_Media/buyer-persona-que-es-3-1024x538.jpg","https://image.freepik.com/foto-gratis/vista-tierras-cultivo-alto-angulo_23-2148579680.jpg"]

El endPoint es: localhost:8082/api/images/processimages

Para configurar el acceso al API de imagenes de Microsoft, 
hay que poner API-KEY del servicio https://rapidapi.com/microsoft-azure-org-microsoft-cognitive-services/api/microsoft-computer-vision3/
e el fichero resources/variables.properties

Proyecto realizado en Spring-Boot con tomcat embebido,
Se ejecuta en el puerto 8082 para acceder desde la parte front.

