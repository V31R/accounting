# Приложения для учёта платежей
Программа считывает доступные валюты, далее ожидает команды(команды по считыванию файла с платежами, команды продолжения работы или же команды ввода данных). После этого запускается поток, который выполняет вывод текщуего баланса по всем валютам раз в минуту и запускается основной цикл по обработке ввода платежей.

## Инструкция по запуску
Программа обязательно требует только один файл `"currencies.txt"`. В нём должен содержаться список валют, которые программа сможет учитывать. К каждой валюте в дополнение может быть указан её курс относительно доллара США.
Пример содержимого файла:
    usd
    CNY 0.16
    rub
    btc 36969.30
Сами валюты (код по [ISO 4217](https://ru.wikipedia.org/wiki/ISO_4217) могут быть указаны в любом сочетании регистров. Если для валюты указан курс, то он должен быть в формате обычного числа с плавающей точкой.

При успешном считывании файла с валютами программа будет ожидать одну из трёх команд:
* `file`: данная команда подразумевает ввод имени файла с платежами для считывания в формате `file <filename>`.
* `run`: команда корректно продолжает работу приложения без считывания платежей из файла.
* Платёж для ввода

## Платежи 
Формат платежей аналогичен формату валют их их курса - `<Код валюты> <Сумма платежа>`. 
Пример файла с платежами при запуске:
    usd 123
    UsD -100
    RUB 1000000
    cny 100
    btc 1
*Программа сможет обработать платёж только тогда, когда валюта платежа находилась в файле с валютами.*
**Уже во время работы программы пользователь может ввести новые платежи в аналогичном файлу формате.**

Для выхода из программы используется команда `quit`.

## Обработка ошибок
Программа экстренно завершает работу, когда ей не удалось считать файл с валютами (потому что дальнейшая работа программы не будет имет смысла). В остальных же случаях:
* Не удалось считать файл с платежами.
* Неправильный формат команд `file`, `run`, `quit`.
* Указание неподдерживаемой валюты.
* После кода валюты идёт что-то, что не является числом, обозначающим сумму платежа.

Программа сообщает пользователю об произошедшей ошибке и продолжает работу.

