## BE Requirements
### Веб-сервис должен удовлетворять следующим требованиям:
- [x] API, реализуемый сервисом, должен соответствовать рекомендациям подхода RESTful.
- [x] Необходимо реализовать следующий базовый набор операций с объектами коллекции: добавление нового элемента, получение элемента по ИД, обновление элемента, удаление элемента, получение массива элементов.
- [x] Операция, выполняемая над объектом коллекции, должна определяться методом HTTP-запроса.
- [x] Операция получения массива элементов должна поддерживать возможность сортировки и фильтрации по любой комбинации полей класса, а также возможность постраничного вывода результатов выборки с указанием размера и порядкового номера выводимой страницы.
- [x] Все параметры, необходимые для выполнения операции, должны передаваться в URL запроса.
- [x] Данные коллекции, которыми управляет веб-сервис, должны храниться в реляционной базе данных.
- [x] Информация об объектах коллекции должна передаваться в формате json.
- [x] В случае передачи сервису данных, нарушающих заданные на уровне класса ограничения целостности, сервис должен возвращать код ответа http, соответствующий произошедшей ошибке.
- [ ] Веб-сервис должен быть "упакован" в веб-приложение, которое необходимо развернуть на сервере приложений Payara.
#### Помимо базового набора, веб-сервис должен поддерживать следующие операции над объектами коллекции:
- [x] Вернуть количество объектов, значение поля numberOfParticipants которых меньше заданного.
- [x] Вернуть массив объектов, значение поля description которых меньше заданного.
- [x] Вернуть массив объектов, значение поля label которых больше заданного.
- [x] Эти операции должны размещаться на отдельных URL.

## FE Requirements
### Требования к клиентскому приложению:

- [ ] Клиентское приложение может быть написано на любом веб-фреймворке, который можно запустить на сервере helios.
- [ ] Клиентское приложение должно обеспечить полный набор возможностей по управлению объектами коллекции, предоставляемых веб-сервисом -- включая сортировку, фильтрацию и постраничный вывод.
- [ ] Клиентское приложение должно преобразовывать передаваемые сервисом данные в человеко-читаемый вид -- параграф текста, таблицу и т.д.
- [ ] Клиентское приложение должно информировать пользователя об ошибках, возникающих на стороне сервиса, в частности, о том, что сервису были отправлены невалиданые данные.
- [ ] Веб-сервис и клиентское приложение должны быть развёрнуты на сервере helios.

## Collection requirements
```
public class MusicBand {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long numberOfParticipants; //Поле не может быть null, Значение поля должно быть больше 0
    private String description; //Поле не может быть null
    private MusicGenre genre; //Поле не может быть null
    private Label label; //Поле не может быть null
}
public class Coordinates {
    private double x; //Максимальное значение поля: 142
    private Double y; //Поле не может быть null
}
public class Label {
    private double sales; //Значение поля должно быть больше 0
}
public enum MusicGenre {
    PSYCHEDELIC_ROCK,
    RAP,
    PSYCHEDELIC_CLOUD_RAP,
    POP;
}
```

## TODO:
- [ ] Add swagger to export OpenApi specification 
