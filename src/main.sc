require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        image: https://248305.selcdn.ru/zfl_prod/64069/64072/Y6nDSc64tgJWac7N.png
        a: Здравствуйте! Я – Наталья, виртуальный ассистент кинотеатра «Киносфера». Что вас интересует?
        buttons:
            "Ошибка оплаты" -> /Оплата
            "Проблема с сайтом" -> /Сайт
            "Проблема с приложением" -> /Приложение
            "Электронный билет" -> /Билет
            "Проблема с личным кабинетом" -> /Личный кабинет

# стейты для  диалоговых ситуаций
    state: Оплата
        q!: *{(сбой/ошибк*/не могу/не получается/не проходит) * (*плат*/*куп*) [билет*]}*
        a: Понимаю, возникла ошибка при покупке билета. Давайте разбираться! :) Такая ошибка может возникнуть в следующих случаях: 1. (описание проблемы, способы ее устранения) 2. (описание проблемы, способы ее устранения) ... Скажите, пожалуйста, помогли ли данные инструкции решить проблему?
        go!: /Помогло? 
            
    state: Сайт
        q!: *{([возник*/появил*/есть] * (проблем*/ошибк*/сбой)) * [(с/на)] сайт*}*
        q!: *{(не могу/не получается/не заходит/не работает/не груз*/*вис*) * [*йти] [на] сайт*}*
        q!: *{что [(случилось/произошло)] * (с/на) * сайт*}*
        q!: *{*доступ* сайт*}*
        
        a: Понимаю, возникла проблема с сайтом. Предлагаю такие пути решения: 1. Проверить интернет-соединение 2. Очистить кэш и обновить страницу 3. Попробуйте использовать другой браузер. Скажите, пожалуйста, помогли ли данные инструкции решить проблему? 
        go!: /Помогло? 
        
            
    state: Приложение
        q!: *{([возник*/появил*/есть] * (проблем*/ошибк*/сбой)) * [(с/на)] приложени*}
        q!: *{(не могу/не получается/не заходит/не работает) * [*йти] [на] приложени*}*
        q!: *{[(случилось/произошло)] * (с/на) * приложени*}*
        q!: *{(*доступ*/*вис*/*глю*/обнов*/верси*/устан*/*груз*/*лет*) * приложени*}*
        a: Понимаю, возникла проблема при использовании нашего приложения. Уточните, в чем конкретно нужна помощь?
    
        state:
            q: *(*обнов*)*
            a: Чтобы обновить приложение, нажмите кнопку "обновить" рядом с иконкой приложения. Скажите, пожалуйста, удалось ли решить проблему?
            go!: /Помогло?
        
        state:
            q: *(*верси*)*
            a: Поняла вас. Вероятно, приложение несовместимо с версией вашей операционной системы. Предлагаю такие пути решения: 1. ... 2 ... Скажите, пожалуйста, помогли ли данные инструкции решить проблему? 
            go!: /Помогло?
            
        state:
            q: *{[не] * (*устан*/загруз*)}*
            a: Поняла вас, приложение не удается установить. Давайте разберемся, в чем может быть причина: 1. ... 2. ... Скажите, пожалуйста, помогли ли данные инструкции решить проблему?
            go!: /Помогло?
            
        state:
            q: *(*вис*/глю*/*лет*)*
            q: *{(не могу/не получается/проблем*/ошибк*/сбой) * (*йти/вход*)}
            a: Поняла вас, наше приложение работает нестабильно. Давайте разберемся, в чем может быть причина: 1. ... 2. ... Скажите, пожалуйста, помогли ли данные инструкции решить проблему?
            go!: /Помогло?
        
        
    state: Билет    
        q!: *{[электрон*] билет*}*
        a: Понимаю, возникла проблема c электронным билетом. Уточните, в чем конкретно нужна помощь?
    
        state:
            q: *{[(не/нет)] * (приш*/получ*/отправ*/присла*/восстан*/*тер*) [(билет*/его)]}*
            a: Поняла вас, вам требуется восстановление билета. Для этого следуйте, пожалуйста, нашим инструкциям: 1. ... 2. ...
            go!: /Помогло?
            
        state:
            q: *{(*действ*/*чит*) * номер * [билет*]}* 
            a: Поняла вас, номер вашего билета по неясной причине оказался недействительным. В этом случае нам с вами не разобраться без помощи оператора :(
            go!: /Оператор
        
            
    state: Личный кабинет
        q!: *{(не могу/не получается/не заходит/не работает) * [*йти] [в] * (личн* кабинет*/аккаунт*)}*
        q!: *{([возник*/появил*/есть] * (проблем*/ошибк*/сбой)) * [с] * (личн* кабинет*/аккаунт*)}*
        q!: *{что [(случилось/произошло)] * [c] * (личн* кабинет*/аккаунт*)}*
        q!: *{*доступ* [в] (личн* кабинет*/аккаунт*)}*
        q!: *{взлом* * (личн* кабинет*/аккаунт*)}*
        a: Понимаю, возникла проблема c вашим личным кабинетом. Уточните, в чем конкретно нужна помощь?
        
        state:
            q: *{(обнов*/добав*/удал*) * [информаци*]}*
            a: Поняла вас. Ознакомьтесь с инструкцией для настройки вашего личного кабинета на нашем сайте. Скажите, пожалуйста, помогли ли данные инструкции решить проблему?
            inlineButtons:
                {text:"Перейти", url:"https://example.com"}
            go!: /Помогло?
        
        state:
            q: *{(восстан*/*тер*/узнать) пароль}*
            a: Поняла вас. Чтобы восстановить пароль от личного кабинета, следуйте инструкциям: 1. ... 2. ... Скажите, пожалуйста, помогли ли данные инструкции решить проблему?
            go!: /Помогло?
            
        state:
            q: *{взлом*[(личн* кабинет*/аккаунт*)]}*
            a: Если произошел взлом вашего аккаунта, следуйте инструкциям: 1. ... 2. ... Скажите, пожалуйста, помогли ли данные инструкции решить проблему?
            go!: /Помогло?
            
        state:
            q: *{(не могу/не получается/не заходит/не работает) * [*йти] [в] * (личн* кабинет*/аккаунт*)}*
            a: Если не получается зайти в своей личный кабинет, следуйте инструкциям: 1. ... 2. ... Скажите, пожалуйста, помогли ли данные инструкции решить проблему?
            go!: /Помогло?
            
            
    state: Яндекс
        q!: *(*яндекс*)*
        a: Пожалуйста, ознакомьтесь с инструкцией для привязки сервиса Яндекс Пэй к личному кабинету. Скажите, пожалуйста, помогли ли данные инструкции решить проблему?Скажите, пожалуйста, помогли ли данные инструкции решить проблему?
        inlineButtons:
            {text:"Перейти", url:"https://example.com"}
        go!: /Помогло?
        
    
    state: Сбер
        q!: *(*сбер*)*
        a: Пожалуйста, ознакомьтесь с инструкцией для привязки сервиса Сбер ID к личному кабинету. Скажите, пожалуйста, помогли ли данные инструкции решить проблему?
        inlineButtons:
            {text:"Перейти", url:"https://example.com"}
        go!: /Помогло?
        
        
# общие стейты
    state: NoMatch || sessionResult = "Тут обрабатываем непонятные запросы"
        event!: noMatch
        random:
            a: Простите, я вас не поняла. Не могли бы вы уточнить вопрос? || html = "Простите, я вас не поняла. Не могли бы вы уточнить вопрос?"
            a: Извините, я не поняла. Переформулируйте ваш вопрос, пожалуйста. || html = "Извините, я не поняла. Переформулируйте ваш вопрос, пожалуйста."
        go!: /Меню

    state: Меню
        a: Чем еще я могу вам помочь? || htmlEnabled = false, html = "Чем еще я могу вам помочь?"
        buttons:
            "Ошибка оплаты" -> /Оплата
            "Проблема с сайтом" -> /Сайт
            "Проблема с приложением" -> /Приложение
            "Электронный билет" -> /Билет
            "Проблема с личным кабинетом" -> /Личный кабинет
    
    state: Помогло?
        state: Да
            q: *(*да*/конечно/ага/угу/*ес)*
            a: Была рада вам помочь!
            go!: /Меню
                
        state: Нет
            q: *(*не*)*
            go!: /Не помогли инструкции

    state: Не помогли инструкции
        a: Мне жаль, что ни один из способов не помог решить проблему. 
        go!: /Оператор
       
        
    state: Оператор
        q!: *(*оператор*)*
        a: Перевести вас на оператора?
       
        state: Да
            q: *(*да*/конечно/ага/ок*/хорошо/ладно/угу/*ес)*
            a: К сожалению, наши операторы сегодня недоступны. Пожалуйста, свяжитесь с нами по номеру +12345678901 || html = "К сожалению, наши операторы сегодня недоступны. Пожалуйста, свяжитесь с нами по номеру +12345678901"
            go!: /Меню
            
        state: Нет
            q: *(*не*/ноу*)*
            go!: /Меню
            
        
           

