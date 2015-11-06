# DCC
dispatch control center

Писал центр управления полетами.
Есть 3 класса - Самолеты, Вертолеты, Центр управления полетами.
Все 3 класса должны работать вместе следующим образом - центр управления полетами слушает эфир(один приемник), а самолеты и вертолеты при изминении позиции должны передавать в эфир новые координаты(несколько передатчиков). Центр управления полетами должен выводить данные в лог.

Прием-передача между классами реализована на JGroup, также использовалась Gson для сериализации объектов.


Лог в ввиде json обьектов:

{"codeName":"BettaB","id":-1,"type":"L39","latitude":9,"longitude":7,"altitude":203,"direction":"W","kindOf":"airplane"}
{"codeName":"BettaA","id":-2,"type":"A300","latitude":10,"longitude":8,"altitude":202,"direction":"S","kindOf":"airplane"}
{"codeName":"AlfaB7","id":0,"type":"AlfaB","latitude":16,"longitude":7,"altitude":135,"direction":"W","kindOf":"helicopter"}
{"codeName":"Mi-17","id":0,"type":"Mi-17","latitude":16,"longitude":7,"altitude":135,"direction":"W","kindOf":"helicopter"}
