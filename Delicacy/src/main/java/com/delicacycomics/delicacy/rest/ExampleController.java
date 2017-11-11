package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.dto.request.ExampleRequestDto;
import com.delicacycomics.delicacy.dto.response.ExampleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.web.bind.annotation.RequestMethod.*;

// Энд-пойнты контроллера будут доступны по адресу с префиксом http://localhost:8080/example
// Вызовы System.out.println(...) в методах нужны для вывода в консоль значений переменных
// Имена методов контроллере и имена их переменных-параметров - выбраны произвольно, они
// никак не влияют на функциональность. На функциональность влияют только имена и значения,
// указанные в аннотациях
@RestController
@RequestMapping("/example")
public class ExampleController {

    // Энд-пойнт доступен с методом GET, например, по адресу http://localhost:8080/example/10
    // Если вызвать его по такому адресу, метод будет вызван со значением id = 10
    // В URL'е может быть произвольное число
    // @PathVariable связывает переменную со значением из URL'a, указанным на месте {id}
    // Энд-пойнт возвращает ExampleResponseDto, поэтому тело ответа выглядит следующим образом:
    // {
    //     "responseStringField": null,
    //     "responseIntegerField": 0
    // }
    // Поля ответа null и 0, так как мы их не задали, создавая ExampleResponseDto
    @RequestMapping(path = "/{id}", method = GET)
    public ExampleResponseDto getExampleById(@PathVariable("id") Long id) {
        System.out.println("getExampleById");
        System.out.println("id = " + id);
        return new ExampleResponseDto();
    }

    // Энд-пойнт доступен с методом GET по адресу http://localhost:8080/example
    // Так как этот метод принимает большое количество параметров, его можно вызывать с различными query параметрами,
    // в различных комбинациях. Вот пример вызова:
    // http://localhost:8080/example?page=4&size=10&sort=fieldName,desc&stringQueryParam=stringValue&longArrayQueryParam=10,15,56
    // Необязательно все query параметры должны быть указаны, можно указать произвольные их комбинации, или не указать ни одного.
    // Вот другой пример:
    // http://localhost:8080/example?page=4&stringQueryParam=stringValue
    // Теперь более подробно о каждом из них.
    // Аннотация @RequestParam связывает переменные с соответствующими query параметрами из URL'а.
    // Значение в аннотации required = false означает, что query параметр необязательно указывать в URL'е.
    // Если его не указать, в метод будет подставлено дефолтное значение параметра (null).
    // Таким образом, если вызвать энд-пойнт с параметром stringQueryParam=stringValue, то метод будет вызван
    // со значением переменной stringQueryParam, равной stringValue
    // А если вызывать энд-пойнт с параметром longArrayQueryParam=10,15,56, то метод будет вызван
    // со значением переменной longArrayQueryParam, равной массиву из элементов [10, 15, 56]
    // Далее про параметр Pageable. Из приведённого в примере URL'a к нему относятся следующие query параметры:
    // page=4&size=10&sort=fieldName,desc
    // В этом примере мы запрашиваем 4-ю страницу с 10-ю сущностями, и просим отсортировать их по полю fieldName
    // по убыванию (desc - значит по убыванию, asc - значит по возрастанию)
    // Все эти параметры будут переданы в метод внутри объекта класса Pageable
    // Это можно увидеть в консоли, выводящей содержимое переменной pageable
    // Объект этого класса в дальнейшем можно будет передать в класс-сервис, а дальше - в класс-репозиторий,
    // откуда сразу будет получена правильная страница с сущностями - экземпляр Page<...>
    // В данном примере мы не вызываем сервис, поэтому для того, чтобы энд-пойнт что-нибудь возвращал, мы использовали заглушку:
    // return new PageImpl<>(new ArrayList<>(), pageable, 0);
    // Это означает, что возвращаем пустую страницу. Page - это интерфейс, а PageImpl - его стандартная реализация.
    // В дальнейшем мы будем возвращать результат метода из класса PageableUtils, который будет преобразовывать
    // страницу сущностей в страницу DTO-объектов
    // Если вызвать энд-пойнт, он вернёт JSON с большим набором полей, подробно описывающим содержимое полученной страницы
    @RequestMapping(method = GET)
    public Page<ExampleResponseDto> getExamplesPage(@RequestParam(name = "stringQueryParam", required = false) String stringQueryParam,
                                                    @RequestParam(name = "longArrayQueryParam", required = false) long[] longArrayQueryParam,
                                                    Pageable pageable) {
        System.out.println("getExamplesPage");
        System.out.println("stringQueryParam = " + stringQueryParam);
        System.out.println("longArrayQueryParam = " + Arrays.toString(longArrayQueryParam));
        System.out.println("pageable = " + pageable);
        return new PageImpl<>(new ArrayList<>(), pageable, 0);
    }

    // Энд-пойнт доступен с методом POST по адресу http://localhost:8080/example
    // Это типичный пример энд-пойнта для создания нового экземпляра сущности в базе данных
    // На вход метод принимает экземпляр ExampleRequestDto, помеченный аннотацией @RequestBody, это означает,
    // что в энд-пойнт при вызове нужно обязательно передать на вход тело запроса.
    // Иначе вызов упадёт с ошибкой валидации JSON'а (или, может, какой-то другой).
    // Так как указанный параметр - класса ExampleRequestDto, то пример валидного JSON'а, который можно подать
    // в энд-пойнт, выглядит так:
    // {
    //    "requestStringField": "Value",
    //    "requestIntegerField": 123
    // }
    // Если вызвать энд-пойнт с таким JSON'ом, в метод будет передан экземпляр ExampleRequestDto-класса со значениями
    // полей в нём, соответственно, Value и 123
    // Возвращаемое значение - класса ExampleResponseDto, поэтому в теле ответа энд-пойнта - тот же JSON,
    // что и в первом энд-пойнте этого контроллера
    @RequestMapping(method = POST)
    public ExampleResponseDto createExample(@RequestBody ExampleRequestDto exampleRequestDto) {
        System.out.println("createExample");
        System.out.println("exampleRequestDto = " + exampleRequestDto);
        return new ExampleResponseDto();
    }

    // Энд-пойнт доступен с методом POST по адресу http://localhost:8080/example/additional
    // Ещё один пример POST энд-пойнта, принимает на вход JSON такого же вида, что и предыдущий энд-пойнт, так как
    // метод содержит такой же ExampleRequestDto параметр, помеченный @RequestBody
    // Кроме того, метод принимает на вход экземпляр HttpServletRequest, из которого можно получить много
    // полезной информации о запросе. Например, - IP адрес той машины, с которой был сделан запрос. В данном
    // случае этот адрес выводится в консоль.
    // Кроме того, метод возвращает ResponseEntity - возвращаемое значение этого типа используется, чтобы показать, что
    // энд-пойнт возвращает пустое тело ответа, без JSON'a.
    // return new ResponseEntity(HttpStatus.OK);
    // Эта строка сообщает, что код ответа будет 200 (OK). Можно возвращает различный код в различных ситуациях.
    @RequestMapping(path = "/additional", method = POST)
    public ResponseEntity someAdditionalPostRequest(@RequestBody ExampleRequestDto exampleRequestDto,
                                                    HttpServletRequest httpServletRequest) {
        System.out.println("someAdditionalPostRequest");
        System.out.println("exampleRequestDto = " + exampleRequestDto);
        System.out.println("IP address = " + httpServletRequest.getRemoteAddr());
        return new ResponseEntity(HttpStatus.OK);
    }

    // Энд-пойнт доступен с методом PUT, например, по адресу http://localhost:8080/example/56
    // Если вызвать его по такому адресу, метод будет вызван со значением id = 56
    // В URL'е может быть произвольное число
    // @PathVariable связывает переменную со значением из URL'a, указанным на месте {id}
    // Энд-пойнт так же принимает на вход такой же JSON, что и предыдующие энд-пойнты, так как в методе есть
    // параметр класса ExampleRequestDto, помеченный @RequestBody
    // Вообще метод может принимать произвольное количество параметров, помеченных @PathVariable или @RequestParam,
    // и они будут соответствовать различным кускам {...} из URL'a и query параметрам соответственно
    // Но метод может содержать только один параметр, помеченный @RequestBody, и в него всегда будет приходить DTO-объект
    // с содержимым, соответствующим переданному в энд-пойнт JSON'у
    @RequestMapping(path = "/{id}", method = PUT)
    public ResponseEntity updateExampleById(@PathVariable("id") Long id,
                                            @RequestBody ExampleRequestDto exampleRequestDto) {
        System.out.println("updateExampleById");
        System.out.println("id = " + id);
        System.out.println("exampleRequestDto = " + exampleRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    // Энд-пойнт доступен с методом DELETE, например, по адресу http://localhost:8080/example/56
    // Если вызвать его по такому адресу, метод будет вызван со значением id = 56
    // В URL'е может быть произвольное число
    // @PathVariable связывает переменную со значением из URL'a, указанным на месте {id}
    @RequestMapping(path = "/{id}", method = DELETE)
    public ResponseEntity deleteExampleById(@PathVariable("id") Long id) {
        System.out.println("deleteExampleById");
        System.out.println("id = " + id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
