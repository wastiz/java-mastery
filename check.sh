#!/usr/bin/env bash
# Запуск тестов одной темы курса.
#
#   ./check.sh b05     — тема B05 (строки)
#   ./check.sh c05     — тема C05 (свой HashMap)
#   ./check.sh k       — весь модуль многопоточности
#   ./check.sh rest    — Spring-модуль (core | rest | data | security | questboard)
#   ./check.sh b05 -v  — с подробным выводом Maven
#
set -euo pipefail
cd "$(dirname "$0")"

topic="${1:-}"
verbose="${2:-}"
if [[ -z "$topic" ]]; then
  echo "Использование: ./check.sh <тема>   например: ./check.sh b05, ./check.sh c, ./check.sh rest"
  exit 1
fi
topic="$(echo "$topic" | tr '[:upper:]' '[:lower:]')"
quiet="-q"
[[ "$verbose" == "-v" ]] && quiet=""

case "$topic" in
  core|rest|data|security|questboard)
    exec mvn $quiet -f "06-spring/$topic/pom.xml" test
    ;;
esac

# case вместо ассоциативного массива: macOS по умолчанию поставляется с bash 3.2
case "${topic:0:1}" in
  b) module=01-basics ;;
  c) module=02-core ;;
  k) module=03-concurrency ;;
  a) module=04-algorithms ;;
  p) module=05-projects ;;
  *) echo "Неизвестная тема: $topic"; exit 1 ;;
esac

if [[ ${#topic} -eq 1 ]]; then
  exec mvn $quiet -pl "$module" test
fi

dir=$(find "$module/src/test/java" -type d -name "${topic}_*" | head -1)
[[ -z "$dir" ]] && { echo "Не нашёл тесты для темы $topic"; exit 1; }
pkg=$(basename "$dir")
echo "▶ $module / $pkg"
exec mvn $quiet -pl "$module" test "-Dtest=**/${pkg}/*Test" -Dsurefire.failIfNoSpecifiedTests=false
