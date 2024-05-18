![Descripción de la imagen](https://codeki.moodlecloud.com/pluginfile.php/1/core_admin/logo/0x200/1711445276/logo-secundario.png)

![Descripción de la imagen](https://media.licdn.com/dms/image/C4D0BAQE085qupw4Z-w/company-logo_200_200/0/1630496983258?e=2147483647&v=beta&t=CD8bbuAexWnfPy3DdYbxXmRxwD2mVmbNw7zWU1sMEts)
![Descripción de la imagen](https://cessi.org.ar/wp-content/uploads/2022/03/it-patagonia.png)

## Bootcamp de Java 
Organizado por: 
Agencia de Aprendizaje a lo largo de la vida, academia educativa Codeki y IT Patagonia

# Proyecto de API de Vuelos

Este proyecto es un monorepositorio desarrollado en Java con Spring Boot, que consiste en una API de vuelos que se comunica con una API de tickets a través de FeignClient, y con una API externa de cotización del dólar mediante RestTemplate. Además, se utilizan microservicios gestionados con Eureka y una configuración centralizada mediante Config Server.

## Contenido

- [Descripción General](#descripción-general)
- [Arquitectura](#arquitectura)
- [Servicios](#servicios)
  - [API de Vuelos](#api-de-vuelos)
  - [API de Tickets](#api-de-tickets)
  - [API de Cotización del Dólar](#api-de-cotización-del-dólar)
- [Configuración Centralizada](#configuración-centralizada)
- [Descubrimiento de Servicios](#descubrimiento-de-servicios)
- [Instalación](#instalación)
- [Ejecución](#ejecución)


## Descripción General

Este proyecto tiene como objetivo gestionar y coordinar la reserva de vuelos, la compra de tickets y la consulta de la cotización del dólar para ofrecer precios actualizados. Utiliza un enfoque de microservicios para mejorar la escalabilidad y la mantenibilidad.

## Arquitectura

La arquitectura del proyecto incluye los siguientes componentes:

- **API de Vuelos**: Gestiona la información y las operaciones relacionadas con los vuelos.
- **API de Tickets**: Gestiona la información y las operaciones relacionadas con la compra de tickets.
- **API de Cotización del Dólar**: Consulta una API externa para obtener la cotización actual del dólar.
- **Eureka Server**: Gestiona el registro y descubrimiento de microservicios.
- **Config Server**: Centraliza la configuración de todos los microservicios utilizando un repositorio de configuración.

## Servicios

### API de Vuelos

La API de Vuelos permite:

- Consultar vuelos disponibles.
- Reservar vuelos.
- Cancelar reservas.

Se comunica con:

- **API de Tickets**: Mediante FeignClient para gestionar la compra de tickets.
- **API de Cotización del Dólar**: Mediante RestTemplate para obtener la cotización actual del dólar.

### API de Tickets

La API de Tickets permite:

- Comprar tickets.
- Consultar el estado de tickets comprados.
- Cancelar tickets.

### API de Cotización del Dólar

La API de Cotización del Dólar permite:

- Obtener la cotización actual del dólar desde una API externa.

## Configuración Centralizada

Utilizamos Spring Cloud Config Server para centralizar la configuración de los microservicios. Las configuraciones se almacenan en el repositorio `config-data`.

## Descubrimiento de Servicios

Eureka Server se utiliza para el registro y descubrimiento de microservicios, permitiendo una comunicación flexible y escalable entre ellos.

## Instalación

Para instalar y configurar el proyecto, sigue estos pasos:

1. Clona el repositorio:
   ```sh
   git clone https://github.com/tu-usuario/nombre-del-repositorio.git

## Ejecución

Ejecuta los microservicios en el siguiente orden:
- Config Server
- Eureka Server
- API de Vuelos
- API de Tickets




Asegúrate de que el Config Server y el Eureka Server estén en ejecución antes de iniciar los demás servicios.
