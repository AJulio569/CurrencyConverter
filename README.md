
![img](https://github.com/user-attachments/assets/6fec0fd5-c079-4c69-a07f-836372e1cf9a)

# 💰 Conversor de Monedas  
_Un proyecto Java para conversiones rápidas y precisas entre distintas divisas._  

## 📖 Descripción  
Este conversor de monedas permite calcular valores entre diferentes divisas en tiempo real, utilizando tasas de cambio actualizadas desde una API externa. Ofrece una interfaz **gráfica con `JOptionPane`**, formato numérico estandarizado y funcionalidad para convertir entre cualquier moneda disponible.  

## 🚀 Funcionalidades  
✅ Conversión entre **USD, COP, ARS, BRL, CLP, BOB** y más.  
✅ **Interfaz visual elegante** con `JOptionPane`.  
✅ **Formato correcto de números** (`4.209,044 COP`).  
✅ **Redondeo de valores** con `BigDecimal` para mayor precisión.  
✅ **Actualización de tasas de cambio en tiempo real** con `ApiService`.  

## 🛠️ Tecnologías utilizadas  
- **Java** (JDK 21)  
- **Gradle** (Manejo de dependencias)  
- **JOptionPane** (Interfaz gráfica)  
- **Gson** (Parseo de JSON)  
- **BigDecimal y DecimalFormat** (Formateo y redondeo)  
- **API ExchangeRate** (Fuente de tasas de cambio)  

## 🏗️ Instalación  
### 1️⃣ Clonar el repositorio  
```sh
git clone  https://github.com/AJulio569/CurrencyConverter.git

```
### 2️⃣  Instalar dependencia  
```sh
./gradlew build

```

## 📌 Uso
## 1️⃣ Al iniciar el programa, aparecerá un **menú interactivo** con opciones
- **Convertir desde USD** a otra moneda. 
- **Convertir entre dos monedas distintas**.
- **Salir** del programa.
## 2️⃣ Ingresa el monto a convertir y selecciona la moneda de origen y destino.
## 3️⃣ La aplicación mostrará el valor convertido con formato correcto (#,###.000)



