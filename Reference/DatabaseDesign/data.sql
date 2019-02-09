
INSERT INTO `administrator` (`administrator_id`, `administrator_name`, `administrator_password`) VALUES (1, 'root', 'E10ADC3949BA59ABBE56E057F20F883E');

INSERT INTO `author` ( `author_name`, `author_description`) VALUES ('Jaye','北卡罗莱纳大学哲学系教授');
INSERT INTO `author` ( `author_name`, `author_description`) VALUES ('Xueqin Cao','The Story of the Stone');
INSERT INTO `author` ( `author_name`, `author_description`) VALUES ('Chengen Wu','Journey to the West');
INSERT INTO `author` ( `author_name`, `author_description`) VALUES ('司马迁','西汉史学家');
INSERT INTO `author` ( `author_name`, `author_description`) VALUES ('陈忠实','中国当代著名作家，中国作家协会副主席');
INSERT INTO `author` ( `author_name`, `author_description`) VALUES ('钱钟书','作家、文学研究家、翻译家');

INSERT INTO `publisher` (`publisher_id`, `publisher_name`, `publisher_description`) VALUES (1,'广西师范大学出版社','广西师范大学出版社1986年11月成立于历史文化名城桂林，主要出版教育、学术人文、珍稀文献等图书。');
INSERT INTO `publisher` (`publisher_id`, `publisher_name`, `publisher_description`) VALUES (2,'中华书局','中华书局，全名为中华书局股份有限公司，是中国一家集编辑、印刷、出版、发行于一体的出版机构，于1912年1月1日由陆费逵筹资创办于上海。');
INSERT INTO `publisher` (`publisher_id`, `publisher_name`, `publisher_description`) VALUES (3,'南海出版公司','南海出版公司，成立于1988年10月，是出版社会科学、文学艺术（包括外国文学、摄影美术）的具有一定规模的综合性出版社。');
INSERT INTO `publisher` (`publisher_id`, `publisher_name`, `publisher_description`) VALUES (4,'上海人民出版社','上海人民出版社成立于1951年3月。是一家出版各学科各专业的综合性图书出版机构，现为上海世纪出版集团成员之一， 主要出版哲学、社会科学、政治、法律、财经、管理、历史等学术专著和大众读物。');
INSERT INTO `publisher` (`publisher_id`, `publisher_name`, `publisher_description`) VALUES (5,'人民文学出版社','1951年3月成立于北京，系国家级专业文学出版机构，现为中国出版集团公司成员单位。');

INSERT INTO `book` (`isbn`, `book_price`, `book_name`, `book_description`, `publisher_id`) VALUES ('9787020026906',28.00,'白鹿原','这是一部渭河平原五十年变迁的雄奇史诗，一轴中国农村班斓多彩、触目惊心的长幅画卷。主人公六娶六丧，神秘的序曲预示着不祥。一个家族两代子孙，为争夺白鹿原的统治代代争斗不已，上演了一幕幕惊心动魄的活剧：巧取风水地，恶施美人计，孝子为匪，亲翁杀媳，兄弟相煎，情人反目……大革命、日寇入侵、三年内战，白鹿原翻云覆雨，王旗变幻，家仇国恨交错缠结，冤冤相报代代不已……古老的土地在新生的阵痛中颤栗。厚重深邃的思想内容，复杂多变的人物性格，跌宕曲折的故事情节，绚丽多彩的风土人情，形成作品鲜明的艺术特色和令人震撼的真实感。',5);
INSERT INTO `book` (`isbn`, `book_price`, `book_name`, `book_description`, `publisher_id`) VALUES ('9787101003048',125.00,'史记（全十册）','史记是我国第一部通史。在史记之前，有以年代为次的“编年史”如春秋，有以地域为限的“国别史”如国语、战国策，有以文告档卷形式保存下来的“政治史”如尚书，可是没有上下几千年，包罗各方面，而又融会贯通，脉络分明，像史记那样的通史。',2);
INSERT INTO `book` (`isbn`, `book_price`, `book_name`, `book_description`, `publisher_id`) VALUES ('9787208061644',29.00,'追风筝的人','故事如此残忍而又美丽，作者以温暖细腻的笔法勾勒人性的本质与救赎，读来令人荡气回肠。',4);
INSERT INTO `book` (`isbn`, `book_price`, `book_name`, `book_description`, `publisher_id`) VALUES ('9787544253994',39.50,'百年孤独','《百年孤独》是魔幻现实主义文学的代表作，描写了布恩迪亚家族七代人的传奇故事，以及加勒比海沿岸小镇马孔多的百年兴衰，反映了拉丁美洲一个世纪以来风云变幻的历史。作品融入神话传说、民间故事、宗教典故等神秘因素，巧妙地糅合了现实与虚幻，展现出一个瑰丽的想象世界，成为20世纪最重要的经典文学巨著之一。',3);
INSERT INTO `book` (`isbn`, `book_price`, `book_name`, `book_description`, `publisher_id`) VALUES ('9787559809940',42.00,'哲学是做出来的：初学者哲学技术指南','不同于一般的哲学导论，本书纯讲哲学“技术”：哲学论证的方法有哪些；什么样的哲学思考和论证才是有效的；面对一个问题时，应该如何分解问题、构建论证，如何批判地阅读前人在这一问题上的写作和论证，如何提出自己的原创性观点、形成哲学写作，等等。',1);
INSERT INTO `book` (`isbn`, `book_price`, `book_name`, `book_description`, `publisher_id`) VALUES ('9787020024759',19.00, '围城', '很多人认为这是一部幽默作品。除了各具特色的人物语言之外，作者夹叙其间的文字也显着机智与幽默。这是本书的一大特色。', '5');


INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES (1,'9787020026906','2F13S','reserve');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES (2,'9787020026906','2F13S','borrowed');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES (3,'9787020026906','2F13S','reserve');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES (4,'9787101003048','1F7S','reserve');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES (5,'9787101003048','1F7S','reserve');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES ('7', '9787020024759', '2F7S', 'inlib');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES ('8', '9787020024759', '2F7S', 'borrowed');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES ('9', '9787020024759', '2F7S', 'inlib');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES (10,'9787020026906','2F13S','inlib');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES (11,'9787020026906','2F13S','inlib');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES (12,'9787020026906','2F13S','inlib');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES (13,'9787020026906','2F13S','inlib');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES ('14', '9787020024759', '2F7S', 'inlib');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES ('15', '9787020024759', '2F7S', 'inlib');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES ('16', '9787020024759', '2F7S', 'inlib');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES ('17', '9787020024759', '2F7S', 'inlib');
INSERT INTO `book_in_library` (`book_id`, `isbn`, `book_location`, `state`) VALUES ('18', '9787020024759', '2F7S', 'inlib');

INSERT INTO `librarian` (`librarian_name`, `state`, `librarian_password`) VALUES ('Aby','blockade','E10ADC3949BA59ABBE56E057F20F883E');
INSERT INTO `librarian` (`librarian_name`, `state`, `librarian_password`) VALUES ('Baker','unlock','E10ADC3949BA59ABBE56E057F20F883E');
INSERT INTO `librarian` (`librarian_name`, `state`, `librarian_password`) VALUES ('Carlos','unlock','E10ADC3949BA59ABBE56E057F20F883E');
INSERT INTO `librarian` (`librarian_name`, `state`, `librarian_password`) VALUES ('Donny','blockade','E10ADC3949BA59ABBE56E057F20F883E');
INSERT INTO `librarian` (`librarian_name`, `state`, `librarian_password`) VALUES ('Eddy','unlock','E10ADC3949BA59ABBE56E057F20F883E');


INSERT INTO `reader` ( `reader_name`, `reader_password`, `reader_email`, `state`) VALUES ('EdSheeran','E10ADC3949BA59ABBE56E057F20F883E','EdSheeran@google.com','unlock');
INSERT INTO `reader` (`reader_name`, `reader_password`, `reader_email`, `state`) VALUES ('Bruno','E10ADC3949BA59ABBE56E057F20F883E','Mars@nwpu.com','unlock');
INSERT INTO `reader` (`reader_name`, `reader_password`, `reader_email`, `state`) VALUES ('Maroon','E10ADC3949BA59ABBE56E057F20F883E','Maroon@npu.com','unlock');
INSERT INTO `reader` (`reader_name`, `reader_password`, `reader_email`, `state`) VALUES ('Lola','E10ADC3949BA59ABBE56E057F20F883E','Lola@qq.com','unlock');
INSERT INTO `reader` (`reader_name`, `reader_password`, `reader_email`, `state`) VALUES ('Michael','E10ADC3949BA59ABBE56E057F20F883E','Jackson@nwpu.com','blockade');

INSERT INTO `writes` (`author_id`, `isbn`) VALUES (1,'9787559809940');
INSERT INTO `writes` (`author_id`, `isbn`) VALUES (2,'9787544253994');
INSERT INTO `writes` (`author_id`, `isbn`) VALUES (3,'9787208061644');
INSERT INTO `writes` (`author_id`, `isbn`) VALUES (4,'9787101003048');
INSERT INTO `writes` (`author_id`, `isbn`) VALUES (5,'9787020026906');
INSERT INTO `writes` (`author_id`, `isbn`) VALUES (6,'9787020024759');




INSERT INTO `borrow_cart` (`book_id`, `reader_id`, `submit_time`) VALUES (1,1001,'2018-11-11 16:33:30');
INSERT INTO `borrow_cart` (`book_id`, `reader_id`) VALUES (9,1001);
INSERT INTO `borrow_cart` (`book_id`, `reader_id`, `submit_time`) VALUES (3,1002,'2018-11-01 12:12:12');
INSERT INTO `borrow_cart` (`book_id`, `reader_id`, `submit_time`) VALUES (4,1002,'2018-09-09 00:00:00');
INSERT INTO `borrow_cart` (`book_id`, `reader_id`, `submit_time`) VALUES (5,1004,'2018-10-01 00:00:00');

INSERT INTO `borrow_item` (`borrow_id`, `reader_id`, `book_id`, `borrow_librarian_id`, `borrow_time`, `return_time`) VALUES (1,1001,2,2,'2019-01-03 18:05:02',NULL);
INSERT INTO `borrow_item` (`borrow_id`, `reader_id`, `book_id`, `borrow_librarian_id`, `return_librarian_id`, `borrow_time`, `return_time`) VALUES (2,1001,2,3,3,'2018-11-10 18:05:54','2018-11-11 00:00:00');
INSERT INTO `borrow_item` (`borrow_id`, `reader_id`, `book_id`, `borrow_librarian_id`, `return_librarian_id`, `borrow_time`, `return_time`) VALUES (3,1002,3,2,3,'2018-09-09 00:00:00','2018-10-10 00:00:00');
INSERT INTO `borrow_item` (`borrow_id`, `reader_id`, `book_id`, `borrow_librarian_id`, `borrow_time`, `return_time`) VALUES (4,1002,8,2,'2018-12-20 00:00:00',NULL);
INSERT INTO `borrow_item` (`borrow_id`, `reader_id`, `book_id`, `borrow_librarian_id`, `borrow_time`, `return_time`) VALUES (5,1003,7,3,'2018-12-20 00:00:00',NULL);