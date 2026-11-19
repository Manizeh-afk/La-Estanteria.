# Scripts y comandos utilizados

Este proyecto utiliza Gradle Kotlin DSL para configurar y construir la aplicación.

## Sincronizar dependencias
Desde Android Studio: File > Sync Project with Gradle Files.

## Compilar
En la terminal, desde la raíz del proyecto:

```bash
./gradlew :app:assembleDebug
```

En Windows:

```powershell
.\gradlew.bat :app:assembleDebug
```

## Ejecutar pruebas
```bash
./gradlew test
```

## Instalar en un dispositivo conectado
```bash
./gradlew :app:installDebug
```

Los comandos documentados son scripts de construcción y verificación; no se incluyen credenciales ni archivos locales del entorno.
