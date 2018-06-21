package com.delicacycomics.delicacy.component;

import com.delicacycomics.delicacy.entity.*;
import com.delicacycomics.delicacy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class StartupListener {

   @Autowired
   private UserRepository userRepository;
   @Autowired
   private SubjectRepository subjectRepository;
   @Autowired
   private TagRepository tagRepository;
   @Autowired
   private ProductRepository productRepository;
   @Autowired
   private OrderRepository orderRepository;
   @Autowired
   private OrderItemRepository orderItemRepository;
   //@Autowired
   //private PictureRepository pictureRepository;

    @EventListener(value = ContextRefreshedEvent.class)
    @Transactional
    public void handleContextRefresh(Object event) { //argument may be deleted, how in that class using context?
        System.out.println("Before debug data population");
        fillEntities(event);
        System.out.println("After debug data population");
    }

   private void fillEntities(Object event) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

        User userAdminExample = insertUser("AdminExample", "123", "Alex", "Suverin", "89890807896", UserStatus.ACTIVE, UserRole.ADMIN); //some fields weren't discribe, is that a good way to move on?
        User userCustomerExample = insertUser("CustomerExample", "1233", "Vanya", "Fydred", "87000212342", UserStatus.ACTIVE,UserRole.CUSTOMER); //some fields weren't discribe, is that a good way to move on?
        User userModeratorExample = insertUser("ModeratorExample", "12212", "Bob", "Avokado", "8915261790", UserStatus.ACTIVE, UserRole.MODERATOR); //some fields weren't discribe, is that a good way to move on?
        User userUnauthorizedExample = insertUser("UnauthorizedExample", "12311", "Bruce", "Wane", "8789089082", UserStatus.ACTIVE, UserRole.UNAUTHORIZED); //some fields weren't discribe, is that a good way to move on?
        Tag tagBatman = insertTag("Бэтмен");
        Tag tagJoker = insertTag("Джокер");
        Tag tagDc = insertTag("DC");
        Tag tagMarvel = insertTag("Marvel");
        Tag tagSpiderman = insertTag("ЧеловекПаук");
        Tag tagAvengers = insertTag("Мстители");
        Tag tagAlanMoore = insertTag("АланМур");
        Tag tagFlash = insertTag("Флэш");
        Tag tagMustRead = insertTag("MustRead");
        Tag tagSuperman = insertTag("Супермен");
        Tag tagDeadpool = insertTag("Дэдпул");
        Tag tagWolverine = insertTag("Росомаха");
        Tag tagXMen = insertTag("ЛюдиИкс");
        Tag tagWonderWoman = insertTag("ЧудоЖенщина");
        Tag tagAction = insertTag("Боевик");
        Tag tagComedy = insertTag("Комедия");
        Tag tagDrama = insertTag("Драма");
        Tag tagSciFi = insertTag("Научная Фантастика");
        Tag tagSuperheroic = insertTag("супергероика");
       Subject subjectDCComics = insertSubject("DC Comics", "DC Comics (основано в 1934 как National Allied Publications) — одно из крупнейших и наиболее популярных издательств комиксов. Издательство было куплено компанией «Warner Bros. Entertainment» в 1969. «DC Comics» издают продукцию, характеризующуюся наличием большого количества широко известных персонажей. В том числе «Супермен», «Бэтмен», «Чудо-женщина», «Зелёный Фонарь», «Флэш», «Аквамен», «Шазам», «Человек-ястреб», «Зелёная стрела», «Джон Константин», «Затанна» и др. В вымышленной вселенной «DC» также имеются команды, такие, как «Лига Справедливости», «Общество Справедливости Америки», «Юные Титаны», «Хранители», и известные злодеи, такие как «Джокер», «Лекс Лютор», «Женщина-кошка», «Дарксайд», «Рас аль Гул», «Детстроук», «Харли Квинн», «Синестро», «Профессор Зум», «Чёрный Адам», «Думсдэй» и «Брейниак»[3].", SubjectType.PUBLISHING_HOUSE);
       Subject subjectMarvel = insertSubject("Marvel", "Marvel Comics (от англ. marvel — чудо) — американская компания, издающая комиксы, подразделение корпорации «Marvel Entertainment». Получившая среди поклонников комиксов прозвище «Дом идей», «Marvel» наиболее известна такими сериями комиксов, как «Фантастическая четвёрка», «Человек-паук», «Невероятный Халк», «Железный человек», «Сорвиголова», «Серебряный Сёрфер», «Призрачный гонщик», «Доктор Стрэндж», «Тор», «Капитан Америка», «Люди Икс», «Каратель» и «Дэдпул». Большинство персонажей комиксов «Marvel» обитают в одной вымышленной вселенной, получившей название Земля-616.", SubjectType.PUBLISHING_HOUSE);
       Subject subjectVertigo = insertSubject("Vertigo", "Vertigo — импринт американского издательства комиксов DC Comics. Выпускает продукцию, ориентированную на более взрослую аудиторию и часто содержащую сцены насилия, злоупотребление алкоголем или наркотиками, а также откровенное (но не явное) изображение сексуальности, богохульства и спорных вопросов. Кроме комиксов ужасов и фэнтези, также публикует произведения в жанрах детектива, социальной сатиры и биографии. Каждый комикс издательства маркируется надписью: «Предназначено для взрослых читателей» (независимо от содержимого конкретного комикса).", SubjectType.PUBLISHING_HOUSE);
       Subject subjectImage = insertSubject("Image Comics", "Image comics — американское издательство комиксов. Его особенность в том, что авторы персонажей оставляют за собой права на этих персонажей, а не передают их издательству, как это делается в крупных компаниях. Является одним из 4 крупнейших издательств комиксов (наряду с Marvel, DC Comics, Dark Horse).", SubjectType.PUBLISHING_HOUSE);
       Subject subjectDarkHorse = insertSubject("Dark Horse", "Dark Horse Comics (с англ. «тёмная лошадка») — одно из крупнейших независимых издательств в США, специализирующееся на комиксах и манге. Основано в 1986 году Майком Ричардсоном, который к тому времени уже владел несколькими магазинами в штате Орегон и занимался продажей комиксов. Именно на доходы с магазинов он и основал Dark Horse Comics. С тех пор издательство расширялось и развивалось, и в настоящее время борется за третье место среди издателей комиксов с компаниями Image Comics и IDW Publishing.Наиболее известными комиксами, выходящими в Dark Horse Comics, стали: «Город грехов» (англ. Sin City) Фрэнка Миллера, Hellboy, цикл комиксов по «Звёздным войнам», «Баффи» (англ. Buffy the Vampire Slayer), Aliens, Конану, Кинг-Конгу (англ. King Kong), «300» (по этому комиксу был снят фильм «300 спартанцев»), Escapist, «Маска» (фильм «Маска»), а также манга Akira. Название «Тёмная лошадка» пришло не случайно. Marvel и DC Comics были олицетворением мира комиксов на рынке, и независимые издатели, пытавшиеся бороться против этих корпоративных гигантов, часто подавлялись возникающими трудностями.[2]", SubjectType.PUBLISHING_HOUSE);
       Subject subjectAzbuka = insertSubject("Азбука", "Издательская группа «Азбука-Аттикус» — одна из крупнейших книгоиздательских групп в России. По оценке Российской книжной палаты в 2016 году группа заняла 5-е место по количеству выпущенных наименований книг", SubjectType.PUBLISHING_HOUSE);
       Subject subjectBoomkniga = insertSubject("Бумкнига", "Главный издатель альтекрнативных комиксов в России", SubjectType.PUBLISHING_HOUSE);
       Subject subjectJellyfishJam = insertSubject("Jellyfish Jam", "", SubjectType.PUBLISHING_HOUSE);
       Subject subjectXlMedia = insertSubject("XL Media", "XL Media (ЭксЭл Медиа) — дистрибьютор аниме в России, на Украине, в Белоруссии, Казахстане, Эстонии, Латвии и Литве. Компания декларирует своей основной задачей продвижение и популяризацию аниме на территории России, обеспечение максимальной доступности аниме для большинства русскоязычных почитателей.", SubjectType.PUBLISHING_HOUSE);
       Subject subjectKomilfo = insertSubject("Комильфо", "Ведущее издательство поп комиксов", SubjectType.PUBLISHING_HOUSE);
       Subject subjectEksmo = insertSubject("Эксмо", "Издательство «Эксмо́» — российская издательская компания, основанная Олегом Новиковым и Андреем Гредасовым в 1991 году. Генеральный директор — Олег Новиков. Издательство быстро вышло в лидеры рынка, благодаря сотрудничеству с популярными авторами, такими как Александра Маринина, Илья Деревянко, Дарья Донцова. Среди известных серий издательства — «Иронический детектив», «Чёрный котёнок», «Золотая серия поэзии» и др.", SubjectType.PUBLISHING_HOUSE);
       Subject subjectAzbukaAttikus = insertSubject("Азбука-Аттикус", "Издательская группа «Азбука-Аттикус» — одна из крупнейших книгоиздательских групп в России. По оценке Российской книжной палаты в 2016 году группа заняла 5-е место по количеству выпущенных наименований книг", SubjectType.PUBLISHING_HOUSE);
       Subject subjectAst = insertSubject("АСТ", "", SubjectType.PUBLISHING_HOUSE);
       Subject subjectBubble = insertSubject("Bubble", "«Bubble Comics» — российский издательский дом, специализирующийся на комиксах, созданный журналистом Артёмом Габреляновым в 2011 году[2]. Является крупнейшим издательством комиксов в России", SubjectType.PUBLISHING_HOUSE);
       Subject subjectAlanMoore = insertSubject("Алан Мур", "Алан Мур (англ. Alan Moore, род. 18 ноября 1953) — английский писатель, известный, прежде всего, работой в комиксах. Создал ряд произведений, положительно отмеченных критиками. Среди них: «Хранители» (англ. Watchmen), «V — значит Вендетта» (англ. V for Vendetta).", SubjectType.PERSON);
       Subject subjectBrayanBolland = insertSubject("Брайан Болланд", "", SubjectType.PERSON);
       Subject subjectArtSpigelman = insertSubject("Арт Шпигельман", "Арт Шпигельман (англ. Art Spiegelman; 15 февраля 1948, Стокгольм) — американский художник и писатель, автор и издатель романов-комиксов. Наиболее известен по комиксу «Маус», получившему Пулитцеровскую премию.", SubjectType.PERSON);
       Subject subjectBraianVon = insertSubject("Брайан К. Вон", "", SubjectType.PERSON);
       Subject subjectFionaStepls = insertSubject("Фиона Стэплз", "", SubjectType.PERSON);
       Subject subjectRobertKirkman = insertSubject("Роберт Киркман", "Ро́берт Ки́ркман (англ. Robert Kirkman; род. 30 ноября 1978 года, Ричмонд (англ.)русск., Кентукки, США) — американский писатель, автор комиксов, наиболее известный своей работой над серией комиксов Ходячие мертвецы[1] и Invincible[2] для Image Comics и Marvel Team-Up и Marvel Zombies для Marvel Comics. ", SubjectType.PERSON);
       Subject subjectTonyMoore = insertSubject("Тони Мур", "", SubjectType.PERSON);
       Subject subjectBrayanBendis = insertSubject("Брайан Бендис", "Брайан Майкл Бендис (англ. Brian Michael Bendis, род. 18 августа 1967, Кливленд, Огайо, США) — американский писатель, сценарист, автор комиксов и бывший художник. Завоевал признание критиков своими работами для Image Comics, Marvel Comics, а также самиздатом, является лауреатом пяти премий Уилла Айснера и является одним из самых успешных писателей комиксов в течение последних десяти лет.", SubjectType.PERSON);
       Subject subjectOliverKoypel = insertSubject("Оливер Койпель", "", SubjectType.PERSON);
       Subject subjectMattFraction = insertSubject("Мэтт Фрэкшн", "", SubjectType.PERSON);
       Subject subjectDavidAha = insertSubject("Дэвид Аха", "", SubjectType.PERSON);
       Subject subjectBrayanAzarello = insertSubject("Брайан Азарелло", "Брайан Азарелло (англ. Brian Azzarello) — американский автор комиксов. Родился 11 августа 1962 года в Кливленде, штат Огайо. Стал известным благодаря серии комиксов о преступности «100 Bullets», изданной DC Comics в импринте Vertigo, рассчитанном под взрослую аудиторию. В 2011 году стал одним из сценаристов перезапущенной серии о Чудо-женщине.", SubjectType.PERSON);
       Subject subjectLiBermeho = insertSubject("Ли Бермехо", "", SubjectType.PERSON);
       Subject subjectPiaGuerra = insertSubject("Пиа Гуэрра", "", SubjectType.PERSON);
       Subject subjectMarkMillar = insertSubject("Марк Миллар", "Марк Ми́ллар (англ. Mark Millar; род. 24 декабря 1969, Котбридж, Северный Ланаркшир, Шотландия) — шотландский автор комиксов с середины 1990-х годов работающий с крупнейшими американскими издательствами, в основном с Marvel Comics. Одной из его наиболее известных работ является комикс «Пипец», экранизированный в 2010 году Мэттью Воном.", SubjectType.PERSON);
       Subject subjectSteveMakniven = insertSubject("Стив Макнивен", "Фрэнсис Манапул", SubjectType.PERSON);
       Subject subjectBraianBuchelatto = insertSubject("Брайан Буччеллато", "", SubjectType.PERSON);
       Subject subjectJeffLoeb = insertSubject("Джеф Лоэб", "", SubjectType.PERSON);
       Subject subjectTimSale = insertSubject("Тим Сэйл", "", SubjectType.PERSON);
       Subject subjectPyerKristen = insertSubject("Пьер Кристин", "Сценарист и писатель", SubjectType.PERSON);
       Subject subjectJanKlodMezyer = insertSubject("Жан Клод Мейзер", "Художник", SubjectType.PERSON);
       Subject subjectShonMerphy = insertSubject("Шон Гордон Мёрфи", "", SubjectType.PERSON);
       Subject subjectJasonHuf = insertSubject("Джейсон М. Хаф", "", SubjectType.PERSON);
       Subject subjectFunkoPop = insertSubject("Funko POP!", "Оригинальная продукция от компании Funko", SubjectType.MANUFACTURER);
       Subject subjectStickeriscoming = insertSubject("Stickeriscoming", "Производитель наклеек", SubjectType.MANUFACTURER);
       Subject subjectFigures = insertSubject("Фигурки", "Различные фигурки для поклонников поп-культуры", SubjectType.PRODUCT_TYPE);
       Subject subjectStickers = insertSubject("Стикеры", "Наклейки с любимыми героями", SubjectType.PRODUCT_TYPE);
       Subject subjectDrinksAndSweets = insertSubject("Напитки и сладости", "Съедобные штуки для всех", SubjectType.PRODUCT_TYPE);
       Subject subjectKeychains = insertSubject("Брелоки", "", SubjectType.PRODUCT_TYPE);
       Subject subjectComicsOnRussianLanguage = insertSubject("Комиксы на русском языке", "Графические истории на русском языке", SubjectType.PRODUCT_TYPE);
       Subject subjectManga = insertSubject("Манга", "Азиатские комиксы, и не только", SubjectType.PRODUCT_TYPE);
       Subject subjectBooks = insertSubject("Книги", "Литература самых разных направлений", SubjectType.PRODUCT_TYPE);
       Subject subjectCyclopaediasAndArtbooks = insertSubject("Энциклопедии и Артбуки", "Большие и красивые издания самых разных предназначений", SubjectType.PRODUCT_TYPE);
       Subject subjectFiguresToysAndAttribute = insertSubject("Фигурки, игрушки, сувениры, атрибутика", "Красивые пылесборники и подарки для поклонника поп-культуры", SubjectType.PRODUCT_TYPE);
       Subject subjectStickersPostcardsPosters = insertSubject("Стикеры, открытки и постеры", "", SubjectType.PRODUCT_TYPE);
       Subject subjectComicsOnEnglishLanguage = insertSubject("Комиксы на английском языке", "Оригинальные издания", SubjectType.PRODUCT_TYPE);
       ArrayList authorsBatmanKillingJoke = new ArrayList();
       authorsBatmanKillingJoke.add(subjectAlanMoore);
       ArrayList artisitsBatmanKillingJoke = new ArrayList();
       artisitsBatmanKillingJoke.add(subjectBrayanBolland);
       ArrayList tagsBatmanKillingJoke = new ArrayList();
       tagsBatmanKillingJoke.add(tagAction);
       tagsBatmanKillingJoke.add(tagBatman);
       tagsBatmanKillingJoke.add(tagJoker);
       tagsBatmanKillingJoke.add(tagDc);
       tagsBatmanKillingJoke.add(tagSuperheroic);
       tagsBatmanKillingJoke.add(tagAlanMoore);
       Book bookBatmanKillingJoke = insertBook("Бэтмен. Убийственная шутка", subjectComicsOnRussianLanguage, "Один плохой день " +
                "«Чтобы превратить самого здравого человека на свете в полного психа, нужен всего один плохой день», – утверждает Джокер. Он снова сбежал из лечебницы Аркхем – лишь для того, чтобы доказать этот тезис. А помочь ему должны, сами того не желая, комиссар полиции Джим Гордон и его красавица-дочь Барбара.\\n\n" +
                "Теперь лишь Бэтмен может спасти двух своих ближайших друзей от угрожающей им смертельной опасности. Под силу ли ему разорвать порочный круг безумия и кровавого насилия, навечно связавший его с Джокером, прежде чем наступит неизбежный, казалось бы, финал? И когда тайна происхождения короля преступного мира будет наконец раскрыта, не лопнет ли тонкая граница, отделяющая Бэтмена с его безупречным благородством от Джокера с его добровольным делирием?\\n\n" +
                "В таких своих легендарных работах, как «ХРАНИТЕЛИ» и «V – ЗНАИТ VЕНДЕТТА», современный классик Алан Мур переписал правила супергеройского жанра. «БЭТМЕН: УБИЙСТВЕННАЯ ШУТКА» – это его попытка по новой ответить на вечный вопрос о зарождении величайшего суперзлодея; попытка, после которой вселенная Бэтмена никогда не останется прежней.\\n\n" +
                "Дополнительные материалы: " +
                "Предисловие Тима Сэйла, послесловие Брайана Болланда, наброски, раскадровки, иллюстрации Брайана Болланда, примечания «Азбуки».);", 650.00, Long.parseLong("4"), "7-Jun-2013", subjectDCComics, subjectAzbuka, "978-5-389-10977-3", "Твердый переплет", Long.parseLong("42"),authorsBatmanKillingJoke, artisitsBatmanKillingJoke, tagsBatmanKillingJoke, formatter);
        ArrayList authorsMaus = new ArrayList();
        authorsMaus.add(subjectArtSpigelman);
        ArrayList artisitsMaus = new ArrayList();
        artisitsMaus.add(subjectArtSpigelman);
        ArrayList tagsMaus = new ArrayList();
        tagsMaus.add(tagDrama);
        tagsMaus.add(tagMustRead);
        Book bookMaus = insertBook("Маус", subjectComicsOnRussianLanguage, "«Маус» Арта Шпигельмана – единственный графический роман, получивший престижную Пулитцеровскую премию. Автору удалось, казалось бы, невозможное – рассказать историю Холокоста в форме комикса. Владек Шпигельман, отец Арта, рассказывает сыну, как прошел через гетто, Освенцим и «марш смерти» на Дахау. Но «Маус» – это и глубоко личная история автора, его попытка разобраться в своих непростых отношениях с семьей. На стыке этих историй и рождается уникальный текст, который без упрощений и пафоса рассказывает об одной из самых чудовищных трагедий XX века. На стыке этих историй и рождается уникальный текст, который без упрощений и пафоса рассказывает об одной из самых чудовищных трагедий XX века.", 740.00, Long.parseLong("1"), "07-Jul-2014", null, subjectBoomkniga, "978-5-17-080250-0", "Твердый переплет", Long.parseLong("296"), authorsMaus, artisitsMaus, tagsMaus, formatter);
        ArrayList authorsSaga = new ArrayList();
        authorsSaga.add(subjectBraianVon);
        ArrayList artisitsSaga = new ArrayList();
        artisitsSaga.add(subjectFionaStepls);
        ArrayList tagsSaga = new ArrayList();
        tagsSaga.add(tagAction);
        tagsSaga.add(tagMustRead);
        Book bookSaga = insertBook("Сага. Книга 1", subjectComicsOnRussianLanguage, "Алана и Марко принадлежат к разным расам. Бесконечная галактическая война должна была развести их по разные стороны баррикад, но они полюбили друг друга и теперь вынуждены скитаться, пытаясь отыскать в галактике мирный уголок для себя и своей новорожденной дочери. \"Сага\" - это увлекательный сюжет, приключения, любовь и огромная удивительная вселенная, в которой возможно буквально всё.\\n\n" +
               "Доп. информация: в книге собраны выпуски Saga #1-6.", 650.00, Long.parseLong("1"), "28-Jul-2014", subjectImage, subjectXlMedia, "978-5-91996-047-8", "Твердый переплет", Long.parseLong("172"), authorsSaga, artisitsSaga, tagsSaga, formatter);
        ArrayList authorsWalkingDead = new ArrayList();
        authorsWalkingDead.add(subjectRobertKirkman);
        ArrayList artisitsWalkingDead = new ArrayList();
        artisitsWalkingDead.add(subjectTonyMoore);
        ArrayList tagsWalkingDead = new ArrayList();
        tagsWalkingDead.add(tagAction);
        Book bookWalkingDead = insertBook("Ходячие Мертвецы. Том 1. Дни минувшие", subjectComicsOnRussianLanguage, "Ходячие Мертвецы. Том 1. Дни минувшие', 54, 'Сколько часов в день вы не тратите, просиживая перед телевизором?\\n\n" +
                "Когда в последний раз кто-нибудь из нас НА САМОМ ДЕЛЕ старался, чтобы получить желаемое?\\n\n" +
                "Давно ли кому-то из нас действительно было НУЖНО то, чего мы постоянно ХОТИМ?\\n\n" +
                "Мир, который мы знали, исчез.\\n\n" +
                "На смену миру торговли и пустого потребительства пришел мир выживания и ответственности.\\n\n" +
                "Эпидемия апокалиптических масштабов прокатилась по планете, пробудив мертвых, которые начали пожирать живых.\\n\n" +
                "За несколько месяцев человеческое общество было разрушено.\\n\n" +
                "Исчезли правительства, магазины, доставка почты, кабельное телевидение.\\n\n" +
                "В мире, где правят мертвые, мы наконец вынуждены начать жить.\\n\n" +
                "Доп. информация:\\n\n" +
                "В книге собраны выпуски The Walking Dead ##1-6.", 350.00, Long.parseLong("3"), "25-Sep-2016", subjectImage, subjectKomilfo, "978-5-990522411", "Твердый переплет", Long.parseLong("144"), authorsWalkingDead, artisitsWalkingDead, tagsWalkingDead, formatter);
        ArrayList authorsHouseM = new ArrayList();
        authorsHouseM.add(subjectBrayanBendis);
        ArrayList artisitsHouseM = new ArrayList();
        artisitsHouseM.add(subjectOliverKoypel);
        ArrayList tagsHouseM = new ArrayList();
        tagsHouseM.add(tagAction);
        tagsHouseM.add(tagMarvel);
        tagsHouseM.add(tagSuperheroic);
        tagsHouseM.add(tagSpiderman);
        tagsHouseM.add(tagXMen);
        Book bookHouseM = insertBook("День М", subjectComicsOnRussianLanguage, "Добро пожаловать в День М.\\n\\n\n" +
              "Мир, где исполнилось ваше самое сокровенное желание. В мир, где мутанты правят планетой, люди довольны, и царит покой.\\n\\n\n" +
              "В мир, слишком совершенный, чтобы быть правдой. В мир, который никогда не был правдой... но один день навеки его изменит.\\n\\n\n" +
              "Добро пожаловать в карточный домик, воздвигнутый на пепелище Мстителей. И пока тайна Дня М не будет раскрыта, реальность никогда не станет прежней.\\n\\n\n" +
              "«День М» – работа сценариста Брайана Майкла Бендиса («Новые Мстители», «Современный Человек-Паук», «Люди Икс») и художника Оливье Койпеля («Мстители», «Осада»), ставшая главным комикс-хитом 2005 года. Это роскошное полотно, на котором личные переживания героев гармонично сочетаются с грандиозными битвами. Действие «Дня М» происходит после распада Мстителей и служит одним из ключей к уже ставшему классическим сюжету «Гражданская Война». На страницах комикса вы встретите всех ключевых героев вселенной Marvel, включая Мстителей, Людей Икс и многих других.\\n\\n\n" +
              "В книгу входят комиксы «День М» #1-8.", 380.00, Long.parseLong("5"), "07-Jul-2017", subjectMarvel, subjectJellyfishJam, "978-5-9909878-1-4", "Мягкий переплет", Long.parseLong("228"), authorsHouseM, artisitsHouseM, tagsHouseM, formatter);

        ArrayList tagsSoyuzniki = new ArrayList();
        tagsSoyuzniki.add(tagAction);
        Book bookSoyuznkiki = insertBook("Союзники №7", subjectComicsOnRussianLanguage, "Естественный отбор. Часть 3\"\\n\\n\n" +

                "«Асулбург» уже давно населяют только призраки когда-то живших и сражавшихся здесь чудовищ... Не считая одного нового чудовища, устроившего охоту на всех, кому не повезло оказаться в крепости!", 60.00, Long.parseLong("7"), "29-Jul-2017", subjectBubble, subjectBubble, null, "Сингл", Long.parseLong("24"), null, null, tagsSoyuzniki, formatter);
        ArrayList tagsHawkeye = new ArrayList();
        tagsHawkeye.add(tagAction);
        tagsHawkeye.add(tagMarvel);
        tagsHawkeye.add(tagSuperheroic);
        tagsHawkeye.add(tagSpiderman);
        ArrayList authorsHawkeye = new ArrayList();
        authorsHawkeye.add(subjectMattFraction);
        ArrayList artisitsHawkeye = new ArrayList();
        artisitsHawkeye.add(subjectDavidAha);
        Book bookHawkeye = insertBook("Хоукай. Соколиный глаз. Полное издание", subjectComicsOnRussianLanguage, "Клинт Бартон, бывалый профи. Кейт Бишоп, талантливый новичок. Два Хоукая зависают вместе, обмениваются стрелами, прикрывают тыл друг друга. Но не так, как вы подумали. Просто два лучших друга, никакой романтики. Никаких натянутых улыбок, господа. Лишь натянутая тетива.\\n\\n\n" +
              "Клинту хватает проблем с бывшей девушкой, женой, текущей «подружкой» и рыжей дамочкой с машиной его мечты. Не говоря о недавно купленном им здании, армии упырей в трениках, которые хотят его вернуть, и невероятной собаке, чующей за версту и убийства, и пиццу. А еще тут Мадам Маска, клоун-убийца и нищеброд-брательник Клинта, Барни. С таким набором битва Мстителей против Таноса покажется невинным пикником. Чаша терпения Кейт переполняется, и она уезжает. Говорят, Лос-Анджелес в это время года просто сказка. Но что об этом думает пес? И кстати, вы когда-нибудь представляли, как может выглядеть комикс на языке жестов?\\n\\n\n" +
              "Получившая «Айснера» история приключений Хоукгая, Кейти-Кейт и Пицца-Пса по имени Счастливчик. Признай, бро, ты сорвал мишень.\\n\n" +
              "Доп. информация:\\n\n" +
              "В книге собраны выпуски: серия Hawkeye (V.2), Annual #1 и Young Avengers Presents #6.", 1500.00, Long.parseLong("8"), "10-Jun-2017", subjectMarvel, subjectKomilfo, "978-5-389-10977-3", "Твердый переплет", Long.parseLong("144"), authorsHawkeye, artisitsHawkeye, tagsHawkeye, formatter);
        ArrayList authorsJoker = new ArrayList();
        authorsJoker.add(subjectBrayanAzarello);
        ArrayList artisitsJoker = new ArrayList();
        artisitsJoker.add(subjectLiBermeho);
        ArrayList tagsJoker = new ArrayList();
        tagsJoker.add(tagAction);
        tagsJoker.add(tagBatman);
        tagsJoker.add(tagJoker);
        tagsJoker.add(tagDc);
        tagsJoker.add(tagSuperheroic);
        Book bookJoker = insertBook("Джокер", subjectComicsOnRussianLanguage, "Джокер выбрался из психушки, но ему, несмотря на улыбку, совсем не весело. За время его отсутствия бывшие подчиненные растащили хозяйский пирог до последней крошки, а самого Джокера списали со счетов, решив, что тот уже не вернется. Однако Джокер снова на свободе и пуще прежнего жаждет утопить Готэм в крови.\\n\n" +
              "В долгих блужданиях по сумеркам души ему составят компанию такие персонажи, как Пингвин, Двуликий, Убийца Крок, Харли Квинн, Загадочник и, конечно же, Бэтмен... и да поможет им всем Господь.\\n\n" +
              "История Джокера, рассказанная от лица его верного, хоть и наивного подручного по имени Джонни Фрост, – классический криминальный нуар, зубодробительный трип по городу среди залитых дождем улиц, грязного белья и сплошных разочарований.", 820.00, Long.parseLong("3"), "15-May-2017", subjectDCComics, subjectAzbuka, "978-5-389-10977-3", "Твёрдый переплёт", Long.parseLong("144"), authorsJoker, artisitsJoker, tagsJoker, formatter);

        ArrayList tagsRickAndMorty = new ArrayList();
        tagsRickAndMorty.add(tagSciFi);
        tagsRickAndMorty.add(tagComedy);
        Book bookRickAndMorty = insertBook("Рик и Морти: Полное издание. Том 1", subjectComicsOnRussianLanguage, "Рик и Морти: Полное издание. Том 1', 54, 'Если вы следите за невероятными приключениями бедолаги Морти и его деда-алкоголика, ученого и по совместительству межгалактического преступника Рика Санчеза и устали ждать выхода третьего сезона, то эта книга определенно поможет вам скоротать время в ожидании. Приготовьтесь к новой порции безумия, но теперь уже в комиксах, потому что таких историй вы еще не слышали и не видели! Сюжет комикса не дублирует мультсериал и не оставит равнодушным ни одного любителя отборного интеллектуально-трешёвого юмора и научной фантастики.", 890.00, Long.parseLong("3"), "30-May-2016", null, subjectKomilfo, null, "Твердый переплет", Long.parseLong("400"), null, null, tagsRickAndMorty, formatter);
        ArrayList authorsYLastMan2 = new ArrayList();
        authorsYLastMan2.add(subjectBraianVon);
        ArrayList artisitsYLastMan2 = new ArrayList();
        artisitsYLastMan2.add(subjectPiaGuerra);
        ArrayList tagsYLastMan2 = new ArrayList();
        tagsYLastMan2.add(tagMustRead);
       Book bookYLastMan2 = insertBook("Y: Последний мужчина. Книга 2", subjectComicsOnRussianLanguage, "В 2002 году мир навсегда изменился. Повсюду на земле все мужчины и мальчики, все млекопитающие с Y-хромосомой разом упали и умерли. Из-за гибели более чем половины человечества шестеренки общества застопорились. Теперь перед женщинами стоит задача: собрать мир по частям и уберечь цивилизацию от полного коллапса.\\n\n" +
               "«Гендерцид», впрочем, оказался не вполне тотальным. По неведомым причинам молодой человек Йорик Браун и его ручной капуцин Амперсанд остались живы. За одну ночь безвестный юнец оказался главным человеком на планете – возможным ключом к загадке гендерноспецифической эпидемии.\\n\n" +
               "А самый важный для самого Йорика человек находится за 10 000 миль от него, и юноша готов на все, чтобы ее отыскать. Однако пускаясь в путь по миру, где остались одни женщины, парнишка и обезьянка обнаруживают, что за ними охотятся – с самыми разными целями.\\n", 860.00, Long.parseLong("2"), "31-May-2017", subjectVertigo, subjectAzbuka, "978-5-389-12021-1", "Твердый переплет", Long.parseLong("144"), authorsYLastMan2, artisitsYLastMan2, tagsYLastMan2, formatter);
        ArrayList authorsOldManLogan = new ArrayList();
        authorsOldManLogan.add(subjectMarkMillar);
        ArrayList artisitsOldManLogan = new ArrayList();
        artisitsOldManLogan.add(subjectSteveMakniven);
        ArrayList tagsOldManLogan = new ArrayList();
        tagsOldManLogan.add(tagAction);
        tagsOldManLogan.add(tagMarvel);
        tagsOldManLogan.add(tagSuperheroic);
        tagsOldManLogan.add(tagMustRead);
       Book bookOldManLogan = insertBook("Росомаха. Старик Логан", subjectComicsOnRussianLanguage, "Когда-то Марк Миллар («Мордобой», «Особо опасен») уже делал историю про Росомаху – «Враг государства», но на этот раз он превзошел самого себя. В этом ему помог его бессменный «брат по оружию» в «Гражданской Войне» – художник Стив Макнивен.\\n\n" +
               "Смешав супергероику далекой антиутопии мира «Людей Икс: Дней минувшего будущего» с диким постапокалиптическим миром «Безумного Макса» и трагическим путешествием героя, напоминающего архетипы Клинта Иствуда, Марк Миллар создал комикс о Росомахе, который находится на распутье и не знает, кто он теперь и кем он хочет быть.\\n\n" +
               "Безумные экшн-сцены Миллара и динамичная раскадровка Макнивена заставят вас с нетерпением перелистывать страницы, пока Росомаха не прикончит последнего злодея в своем бесконечном поиске мирного существования.\\n", 400.00, Long.parseLong("3"), "26-Aug-2015", subjectMarvel, subjectKomilfo, "978-5-91339-329-6", "Мягкий переплет", Long.parseLong("224"), authorsOldManLogan, artisitsOldManLogan,tagsOldManLogan, formatter);
        ArrayList authorsFlash4 = new ArrayList();
        authorsFlash4.add(subjectBraianBuchelatto);
        ArrayList tagsFlash4 = new ArrayList();
        tagsFlash4.add(tagAction);
        tagsFlash4.add(tagDc);
        tagsFlash4.add(tagSuperheroic);
       Book bookFlash4 = insertBook("Флэш. Книга 4. Реверс, или Обратный ход", subjectComicsOnRussianLanguage, "Став Флэшем, Барри Аллен получил доступ к невероятной энергии Силы Скорости, благодаря которой может двигаться, думать и действовать с поразительной быстротой. Но он не просто расходует Силу Скорости: с каждым шагом он ее вырабатывает. Словно живой генератор, он служит источником энергии, которая двигает время вперед.\\n\n" +
               "Но появляется враг, который стремится обратить в прах все труды Флэша.\\n\n" +
               "По улицам Централ-сити рыщет молниеносный убийца, нацелившийся на каждого, кого коснулась Сила Скорости, – а значит, друзья Флэша превращаются в потенциальные мишени. Преступник носит перевернутый символ Флэша и, похоже, подпитывается энергией своих жертв. Но каковы его цели?\\n\n" +
               "Кто же он, Обратный Флэш? Каким образом связан с Силой Скорости – и с Барри Алленом? И сможет ли Флэш остановить его прежде, чем будет поздно?", 850.00, Long.parseLong("0"), "08-Feb-2017", subjectDCComics, subjectAzbuka, "978-5-389-12088-4", "Твердый переплет", Long.parseLong("176"), authorsFlash4, null, tagsFlash4, formatter);
        ArrayList authorsBatmanLongWictory = new ArrayList();
        authorsBatmanLongWictory.add(subjectJeffLoeb);
        authorsBatmanLongWictory.add(subjectTimSale);
        ArrayList tagsBatmanLongWictory = new ArrayList();
        tagsBatmanLongWictory.add(tagAction);
        tagsBatmanLongWictory.add(tagBatman);
        tagsBatmanLongWictory.add(tagJoker);
        tagsBatmanLongWictory.add(tagDc);
        tagsBatmanLongWictory.add(tagSuperheroic);
       Book bookBatmanLongWictory = insertBook("Бэтмен. Темная Победа", subjectComicsOnRussianLanguage, "На заре своей карьеры Темного Рыцаря Бэтмен пытается выследить неуловимого убийцу копов по кличке Палач, прежде чем тот нанесет очередной удар. Единственный намек на личность преступника – листок с детской игрой, приколотый к телам жертв. В качестве главных подозреваемых выступает целая галерея злодеев: Двуликий, Джокер, Загадочник, Женщина-Кошка. Даже полиции во главе с недавно назначенным комиссаром Гордоном доверять нельзя.\\n\n" +
               "Чтобы разрешить загадку, Бэтмену придется заручиться помощью с самой неожиданной стороны. Он возьмет в напарники осиротевшего мальчика, который навсегда изменит его жизнь. Они станут известны как Бэтмен и Робин. Их история перед вами.\\n\\n\n" +
               "В качестве авторов книги выступает знаменитый тандем: лауреаты премии Айснера Джеф Лоэб и Тим Сэйл («Супермен на все времена», «Бэтмен: Долгий Хэллоуин»). Абсолютное издание включает оригинальную серию из 0-13 выпусков, интервью Джефа Лоэба, вступительные статьи Тима Сэйла и сценариста кинотрилогии о Бэтмене Дэвида С. Гойера, а также множество набросков и эскизов.", 1010.00, Long.parseLong("2"), "10-Feb-2017", subjectDCComics, subjectAzbuka, "978-5-389-10782-3", "Твердый переплет", Long.parseLong("408"), authorsBatmanLongWictory, null, tagsBatmanLongWictory, formatter);
        ArrayList tagsSabretooth = new ArrayList();
        tagsSabretooth.add(tagAction);
        tagsSabretooth.add(tagMarvel);
        tagsSabretooth.add(tagSuperheroic);
       Attribute attributeSabretooth = insertAttribute("Фигурка Sabretooth/Саблезубый («Funko POP!», «Marvel X-Men»)", subjectFiguresToysAndAttribute, "Коллекционная фигурка Саблезубого из \"Людей Икс", 1300.00, Long.parseLong("1"), "12-Nov-2016", Long.parseLong("20"), subjectFunkoPop, "Marvel", "пластмасса", tagsSabretooth, formatter);
       Attribute attributeStickers = insertAttribute("Стикеры Marvel (stickeriscoming)", subjectStickers, "Набор наклеек с супергероями", 150.00, Long.parseLong("5"), "28-May-2017", Long.parseLong("20"), subjectStickeriscoming, null, "Виниловая бумага", null, formatter);
       ArrayList tagsAttributeBatman = new ArrayList();
       tagsAttributeBatman.add(tagAction);
       tagsAttributeBatman.add(tagBatman);
       tagsAttributeBatman.add(tagJoker);
       tagsAttributeBatman.add(tagDc);
       tagsAttributeBatman.add(tagSuperheroic);
       Attribute attributeBatman = insertAttribute("Фигурка «Бэтмен на гаргулье»", subjectFiguresToysAndAttribute, "Коллекционная фигурка Бэтмена", 2000.00, Long.parseLong("1"), "15-Feb-2017", null, null, null, "Пластмасса", tagsAttributeBatman, formatter);
       ArrayList authorsСhrononauts = new ArrayList();
       authorsСhrononauts.add(subjectMarkMillar);
       ArrayList artisitsChrononauts = new ArrayList();
       artisitsChrononauts.add(subjectShonMerphy);
       ArrayList tagsChrononauts = new ArrayList();
       tagsChrononauts.add(tagAction);
       tagsChrononauts.add(tagSciFi);
       tagsChrononauts.add(tagComedy);
       Book bookChrononauts = insertBook("Хрононавты", subjectComicsOnRussianLanguage, "Доктор Куинн и доктор Райли — учёные, но не такие, какими мы привыкли видеть учёных. Это не угрюмые старики в белых халатах, а молодые люди, полные жизни и амбиций. Они строят машину времени (в рамках государственной программы США, конечно же, не у себя же в гараже её строить). ", 350.00, Long.parseLong("2"), "07-Feb-2017", subjectImage, subjectKomilfo, "978-5-389-12088-4", "Мягкий переплет", Long.parseLong("112"), authorsСhrononauts, artisitsChrononauts, tagsChrononauts, formatter);
       ArrayList authorsValerian = new ArrayList();
       authorsValerian.add(subjectPyerKristen);
       ArrayList artisitsValerian = new ArrayList();
       artisitsValerian.add(subjectJanKlodMezyer);
       ArrayList tagsValerian = new ArrayList();
       tagsValerian.add(tagAction);
       tagsValerian.add(tagSciFi);
       Book bookValerian = insertBook("Валериан: Полное собрание. Книга 1", subjectComicsOnRussianLanguage, "Валериан и Лорелин, придуманные талантливыми Пьером Кристеном и Жан-Клодом Мезьером, впервые появились на страницах журнала «Pilote» в 1967 году. Эти оригинальные истории сразу же стали звездами первой величины на небосклоне научной фантастики.\\n\\n\n" +
               "В книгу включены дополнительные материалы: кадры из фильма, фотографии со съемочной площадки, интервью с режиссером и многое другое. Серия выпущена по образцу оригинальных сборников издательства «Dargaud» – омнибусами по три книги в одном томе увеличенного формата.", 860.00, Long.parseLong("3"), "19-Jul-2017", null, subjectAzbuka, "978-5-389-12719-7", "Твердый переплет", Long.parseLong("160"), authorsValerian, artisitsValerian, tagsValerian, formatter);
       ArrayList tagsSpiderEnciclopedia = new ArrayList();
       tagsSpiderEnciclopedia.add(tagAction);
       tagsSpiderEnciclopedia.add(tagMarvel);
       tagsSpiderEnciclopedia.add(tagSuperheroic);
       Book bookSpiderEnciclopedia = insertBook("Человек-Паук. Энциклопедия персонажей", subjectCyclopaediasAndArtbooks, null, 1650.00, Long.parseLong("1"), "25-Jul-2017", subjectMarvel, subjectEksmo, null, "Твердый переплет", Long.parseLong("415"), null, null, tagsSpiderEnciclopedia, formatter);
        ArrayList authorsMassEffectAndromeda = new ArrayList();
        authorsMassEffectAndromeda.add(subjectJasonHuf);
        ArrayList tagsMassEffectAndromeda = new ArrayList();
        tagsMassEffectAndromeda.add(tagSciFi);
       Book bookMassEffectAndromeda = insertBook("Mass Effect. Андромеда. Восстание на \"Нексусе", subjectBooks, null, 350.00, Long.parseLong("1"), "25-Jun-2017", null, subjectAzbukaAttikus, null, "Твердый переплет", Long.parseLong("344"), authorsMassEffectAndromeda, null, tagsMassEffectAndromeda, formatter);

       Order orderFirstWithCustomer = insertOrder(userCustomerExample, "02-Feb-2018", UserStatus.ACTIVE, "Что-то там про заказ", UUID.randomUUID().toString(), formatter);
       Order orderSecondWithUnauthorized = insertOrder(userUnauthorizedExample, "03-May-2018", UserStatus.ACTIVE, "Какой-то заказ", UUID.randomUUID().toString(), formatter);
       Order orderThirdWithCustomer = insertOrder(userCustomerExample, "05-Jun-2018", UserStatus.ACTIVE, "Какое-то описание", UUID.randomUUID().toString(), formatter);
       Order orderFourthWithUnauthorized = insertOrder(userUnauthorizedExample, "07-Jun-2018", UserStatus.ACTIVE, "Пометочка про заказ", UUID.randomUUID().toString(), formatter);

       OrderItem orderItemFirstKillingJoke = insertOrderItem(bookBatmanKillingJoke, orderFirstWithCustomer, Long.parseLong("1"));
       OrderItem orderItemFirstLongWictory = insertOrderItem(bookBatmanLongWictory, orderFirstWithCustomer, Long.parseLong("1"));
       OrderItem orderItemFirstStickers = insertOrderItem(attributeStickers, orderFirstWithCustomer, Long.parseLong("3"));
       OrderItem orderItemSecondHouse = insertOrderItem(bookHouseM, orderSecondWithUnauthorized, Long.parseLong("1"));
       OrderItem orderItemSecondSabretooth = insertOrderItem(attributeSabretooth, orderSecondWithUnauthorized, Long.parseLong("1"));
       OrderItem orderItemSecondBatman = insertOrderItem(attributeBatman, orderSecondWithUnauthorized, Long.parseLong("1"));
       OrderItem orderItemThirdRickAndMorty = insertOrderItem(bookRickAndMorty, orderThirdWithCustomer, Long.parseLong("1"));
       OrderItem orderItemFourth = insertOrderItem(bookMaus, orderFourthWithUnauthorized, Long.parseLong("1"));

    }

   private User insertUser(String loginContent, String passwordHashContent, String nameContent, String surnameContent, String phoneNumberContent, UserStatus userStatusContent, UserRole roleContent) {
        User user = new User(loginContent, passwordHashContent, nameContent, surnameContent, phoneNumberContent, userStatusContent, roleContent);
        userRepository.save(user);
        return user;
    }

   private Tag insertTag(String tagContent) {
        Tag tag = new Tag(tagContent);
        tagRepository.save(tag);
        return tag;
    }

   private Subject insertSubject(String nameContent, String descriptionContent, SubjectType subjectTypeContent) {
        Subject subject = new Subject(nameContent, descriptionContent, subjectTypeContent);
        subjectRepository.save(subject);
        return subject;
    }

   private Book insertBook(String titleContent, Subject typeContent, String descriptionContent, Double priceContent, Long remainderContent, String dateContent, Subject publisherContent, Subject publisherLocalContent, String isbnContent, String formatContent, Long pagesCountContent, ArrayList<Subject> authorsContent, ArrayList<Subject> artistsContent, ArrayList<Tag> tagsContent, SimpleDateFormat formatter) {
        Book book = new Book();
        book.setTitle(titleContent);
        book.setType(typeContent);
        book.setDescription(descriptionContent);
        book.setPrice(priceContent);
        book.setRemainder(remainderContent);
      try {
          book.setDate(formatter.parse(dateContent));
      } catch (ParseException e) {
          e.printStackTrace();
      }
        book.setPublisher(publisherContent);
        book.setPublisherLocal(publisherLocalContent);
        book.setIsbn(isbnContent);
        book.setFormat(formatContent);
        book.setPagesCount(pagesCountContent);
        book.setAuthors(authorsContent);
        book.setArtists(artistsContent);
        book.setTags(tagsContent);
        productRepository.save(book);
        return book;
  }

   private Attribute insertAttribute(String titleContent, Subject typeContent, String descriptionContent, Double priceContent, Long remainderContent, String dateContent, Long heightContent, Subject manufacturerContent, String seriesContent, String materialContent, ArrayList<Tag> tagsContent, SimpleDateFormat formatter) {
        Attribute attribute = new Attribute();
        attribute.setTitle(titleContent);
        attribute.setType(typeContent);
        attribute.setDescription(descriptionContent);
        attribute.setPrice(priceContent);
        attribute.setRemainder(remainderContent);
        try {
            attribute.setDate(formatter.parse(dateContent));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        attribute.setHeight(heightContent);
        attribute.setManufacturer(manufacturerContent);
        attribute.setMaterial(materialContent);
        attribute.setSeries(seriesContent);
        attribute.setTags(tagsContent);
        productRepository.save(attribute);
        return attribute;
    }

   private Order insertOrder(User userContent, String dateOfCreationContent, UserStatus statusContent, String noteContent, String identifierContent, SimpleDateFormat formatter) {
       Order order = new Order();
       order.setUser(userContent);
       try {
           order.setDateOfCreation(formatter.parse(dateOfCreationContent));
       } catch (ParseException e) {
           e.printStackTrace();
       }
       order.setStatus(statusContent);
       order.setNote(noteContent);
       order.setIdentifier(identifierContent);
       orderRepository.save(order);
       return order;
   }

   private  OrderItem insertOrderItem(Product productContent, Order orderContent, Long amountContent){
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(productContent);
        orderItem.setOrder(orderContent);
        orderItem.setAmount(amountContent);
        orderItemRepository.save(orderItem);
        return  orderItem;
    }

}
