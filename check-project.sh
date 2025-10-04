#!/bin/bash

echo "🔍 Проверка проекта..."

echo "1. Структура проекта:"
find src -name "*.java" -type f

echo ""
echo "2. Gradle задачи:"
./gradlew tasks --all | grep -E "(boot|jar)"

echo ""
echo "3. Компиляция:"
./gradlew compileJava

echo ""
echo "4. Сборка JAR:"
./gradlew bootJar

echo ""
echo "5. Проверка JAR:"
if [ -f "build/libs/medication-calendar.jar" ]; then
    echo "✅ JAR файл создан: build/libs/medication-calendar.jar"
    ls -la build/libs/
else
    echo "❌ JAR файл не создан"
    echo "Созданные файлы:"
    find build -name "*.jar" -type f 2>/dev/null || echo "Файлы JAR не найдены"
fi