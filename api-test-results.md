# iMusic API 测试结果

测试时间: 2025-12-29 18:15:13

---

# User Service

## 1. 用户注册（新用户）
```json
{"success":true,"message":{"ok":"注册成功"}}
```

## 2. 用户登录
```json
{"success":true,"message":{"ok":"登录成功"}}
```

## 3. 用户验证
```json
{"success":true,"message":{"valid":true}}
```

## 4. 登录失败 - 用户不存在
```json
{"success":false,"message":{"error":"用户不存在"}}
```

## 5. 登录失败 - 密码错误
```json
{"success":false,"message":{"error":"密码错误"}}
```

---

# Music Service

## 6. 搜索所有歌曲
```json
{"success":true,"message":{"songs":[{"filePath":"mp3/sleepydude&Mecdoux/BleedingLove-Single/BleedingLove.mp3","coverFilePath":"cover/sleepydude&Mecdoux/BleedingLove-Single/BleedingLove.jpg","title":"Bleeding Love","artist":"sleepy dude & Mecdoux","album":"BleedingLove-Single","albumArtist":"sleepydude&Mecdoux","year":"2021","genre":"电子音乐","trackNumber":"1","bitrate":"256 kbps","duration":"02:19"},{"filePath":"mp3/TaylorSwift/evermore/tolerateit.mp3","coverFilePath":"cover/TaylorSwift/evermore/tolerateit.jpg","title":"tolerate it","artist":"Taylor Swift","album":"evermore","albumArtist":"TaylorSwift","year":"2020","genre":"另类音乐","trackNumber":"5","bitrate":"256 kbps","duration":"04:05"},{"filePath":"mp3/汪苏泷/汪苏泷歌曲合辑,Vol.2/不分手的恋爱.mp3","coverFilePath":"cover/汪苏泷/汪苏泷歌曲合辑,Vol.2/不分手的恋爱.jpg","title":"不分手的恋爱","artist":"汪苏泷","album":"汪苏泷歌曲合辑,Vol.2","albumArtist":"汪苏泷","year":"2010","genre":"国语流行","trackNumber":"8","bitrate":"256 kbps","duration":"03:25"},{"filePath":"mp3/于冬然/其实都没有-EP/于冬然-其实都没有.mp3","coverFilePath":"cover/于冬然/其实都没有-EP/于冬然-其实都没有.jpg","title":"其实都没有","artist":"于冬然","album":"其实都没有-EP","albumArtist":"于冬然","year":"2023-08-25","genre":"国语流行","trackNumber":"1","bitrate":"128 kbps","duration":"03:42"},{"filePath":"mp3/张杰/我是歌手第二季(第十四期Live)/他不懂(Live).mp3","coverFilePath":"cover/张杰/我是歌手第二季(第十四期Live)/他不懂(Live).jpg","title":"他不懂 (Live)","artist":"张杰","album":"我是歌手第二季(第十四期Live)","albumArtist":"张杰","year":"2014","genre":"国语流行","trackNumber":"7","bitrate":"256 kbps","duration":"04:11"},{"filePath":"mp3/周兴哲/学着爱/以后别做朋友.mp3","coverFilePath":"cover/周兴哲/学着爱/以后别做朋友.jpg","title":"以后别做朋友","artist":"周兴哲","album":"学着爱","albumArtist":"周兴哲","year":"2014","genre":"国语流行","trackNumber":"2","bitrate":"256 kbps","duration":"04:18"},{"filePath":"mp3/en/会呼吸的痛-Single/会呼吸的痛.mp3","coverFilePath":"cover/en/会呼吸的痛-Single/会呼吸的痛.jpg","title":"会呼吸的痛","artist":"en","album":"会呼吸的痛-Single","albumArtist":"en","year":"2024","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:56"},{"filePath":"mp3/于文文/尚未界定/体面.mp3","coverFilePath":"cover/于文文/尚未界定/体面.jpg","title":"体面","artist":"于文文","album":"尚未界定","albumArtist":"于文文","year":"2017","genre":"Mandopop","trackNumber":"8","bitrate":"256 kbps","duration":"04:42"},{"filePath":"mp3/周兴哲/爱,教会我们的事/你,好不好(TVBS连续剧《遗憾拼图》片尾曲).mp3","coverFilePath":"cover/周兴哲/爱,教会我们的事/你,好不好(TVBS连续剧《遗憾拼图》片尾曲).jpg","title":"你,好不好  (TVBS连续剧《遗憾拼图》片尾曲)","artist":"周兴哲","album":"爱,教会我们的事","albumArtist":"周兴哲","year":"2016","genre":"国语流行","trackNumber":"4","bitrate":"256 kbps","duration":"04:47"},{"filePath":"mp3/苏星婕/八月的冷月光-Single/八月的冷月光.mp3","coverFilePath":"cover/苏星婕/八月的冷月光-Single/八月的冷月光.jpg","title":"八月的冷月光","artist":"苏星婕","album":"八月的冷月光-Single","albumArtist":"苏星婕","year":"2024","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"02:15"},{"filePath":"mp3/邓紫棋/新的心跳/再见.mp3","coverFilePath":"cover/邓紫棋/新的心跳/再见.jpg","title":"再见","artist":"邓紫棋","album":"新的心跳","albumArtist":"邓紫棋","year":"2015","genre":"国语流行","trackNumber":"2","bitrate":"256 kbps","duration":"03:26"},{"filePath":"mp3/周传雄/恋人创世纪/冬天的秘密.mp3","coverFilePath":"cover/周传雄/恋人创世纪/冬天的秘密.jpg","title":"冬天的秘密","artist":"周传雄","album":"恋人创世纪","albumArtist":"周传雄","year":"2009","genre":"国语流行","trackNumber":"4","bitrate":"256 kbps","duration":"04:32"},{"filePath":"mp3/司南/冬眠-Single/冬眠.mp3","coverFilePath":"cover/司南/冬眠-Single/冬眠.jpg","title":"冬眠","artist":"司南","album":"冬眠-Single","albumArtist":"司南","year":"2019","genre":"Mandopop","trackNumber":"1","bitrate":"256 kbps","duration":"04:29"},{"filePath":"mp3/邓紫棋/摩天动物园/别勉强(feat周兴哲).mp3","coverFilePath":"cover/邓紫棋/摩天动物园/别勉强(feat周兴哲).jpg","title":"别勉强 (feat. 周兴哲)","artist":"邓紫棋","album":"摩天动物园","albumArtist":"邓紫棋","year":"2019","genre":"国语流行","trackNumber":"10","bitrate":"256 kbps","duration":"04:22"},{"filePath":"mp3/卢卢快闭嘴/字字句句-Single/卢卢快闭嘴-字字句句.mp3","coverFilePath":"cover/卢卢快闭嘴/字字句句-Single/卢卢快闭嘴-字字句句.jpg","title":"字字句句","artist":"卢卢快闭嘴","album":"字字句句-Single","albumArtist":"卢卢快闭嘴","year":"2022-12-29","genre":"国语流行","trackNumber":"1","bitrate":"128 kbps","duration":"03:20"},{"filePath":"mp3/于冬然/听说你-EP/听说你.mp3","coverFilePath":"cover/于冬然/听说你-EP/听说你.jpg","title":"听说你","artist":"于冬然","album":"听说你-EP","albumArtist":"于冬然","year":"2022","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:59"},{"filePath":"mp3/苏星婕/咖啡因-Single/咖啡因.mp3","coverFilePath":"cover/苏星婕/咖啡因-Single/咖啡因.jpg","title":"咖啡因","artist":"苏星婕","album":"咖啡因-Single","albumArtist":"苏星婕","year":"2024","genre":"R&B-灵魂乐","trackNumber":"1","bitrate":"256 kbps","duration":"02:24"},{"filePath":"mp3/邓紫棋/喜欢你-Single/喜欢你.mp3","coverFilePath":"cover/邓紫棋/喜欢你-Single/喜欢你.jpg","title":"喜欢你","artist":"邓紫棋","album":"喜欢你-Single","albumArtist":"邓紫棋","year":"2014","genre":"粤语流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:59"},{"filePath":"mp3/盛哲/在你的身边/在你的身边.mp3","coverFilePath":"cover/盛哲/在你的身边/在你的身边.jpg","title":"在你的身边","artist":"盛哲","album":"在你的身边","albumArtist":"盛哲","year":"2017","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"04:22"},{"filePath":"mp3/蔡恩雨PriscillaAbby/夏天的风-Single/夏天的风.mp3","coverFilePath":"cover/蔡恩雨PriscillaAbby/夏天的风-Single/夏天的风.jpg","title":"夏天的风","artist":"蔡恩雨 Priscilla Abby","album":"夏天的风-Single","albumArtist":"蔡恩雨PriscillaAbby","year":"2020","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:14"},{"filePath":"mp3/A-Lin/天若有情(电视剧《锦绣未央》主题曲)-Single/天若有情(电视剧《锦绣未央》主题曲).mp3","coverFilePath":"cover/A-Lin/天若有情(电视剧《锦绣未央》主题曲)-Single/天若有情(电视剧《锦绣未央》主题曲).jpg","title":"天若有情 (电视剧《锦绣未央》主题曲)","artist":"A-Lin","album":"天若有情(电视剧《锦绣未央》主题曲)-Single","albumArtist":"A-Lin","year":"2016","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"04:17"},{"filePath":"mp3/韦礼安/如果可以(电影'月老'主题曲)-Single/如果可以(电影'月老'主题曲).mp3","coverFilePath":"cover/韦礼安/如果可以(电影'月老'主题曲)-Single/如果可以(电影'月老'主题曲).jpg","title":"如果可以 (电影'月老'主题曲)","artist":"韦礼安","album":"如果可以(电影'月老'主题曲)-Single","albumArtist":"韦礼安","year":"2021","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"04:34"},{"filePath":"mp3/孙燕姿/这一刻/孙燕姿-遇见.mp3","coverFilePath":"cover/孙燕姿/这一刻/孙燕姿-遇见.jpg","title":"遇见","artist":"孙燕姿","album":"这一刻","albumArtist":"孙燕姿","year":"2003-08-22","genre":"国语流行","trackNumber":"2","bitrate":"128 kbps","duration":"03:29"},{"filePath":"mp3/周林枫/忘了-EP/忘了.mp3","coverFilePath":"cover/周林枫/忘了-EP/忘了.jpg","title":"忘了","artist":"周林枫","album":"忘了-EP","albumArtist":"周林枫","year":"2023","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:00"},{"filePath":"mp3/周兴哲/终于了解自由-EP/怎么了.mp3","coverFilePath":"cover/周兴哲/终于了解自由-EP/怎么了.jpg","title":"怎么了","artist":"周兴哲","album":"终于了解自由-EP","albumArtist":"周兴哲","year":"2019","genre":"国语流行","trackNumber":"3","bitrate":"256 kbps","duration":"05:21"},{"filePath":"mp3/梁静茹/别再为他流泪/情歌.mp3","coverFilePath":"cover/梁静茹/别再为他流泪/情歌.jpg","title":"情歌","artist":"梁静茹","album":"别再为他流泪","albumArtist":"梁静茹","year":"2009","genre":"国语流行","trackNumber":"5","bitrate":"256 kbps","duration":"04:20"},{"filePath":"mp3/苏星婕/想-Single/想.mp3","coverFilePath":"cover/苏星婕/想-Single/想.jpg","title":"想","artist":"苏星婕","album":"想-Single","albumArtist":"苏星婕","year":"2024","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"02:29"},{"filePath":"mp3/汪苏泷/想到我们-Single/想到我们.mp3","coverFilePath":"cover/汪苏泷/想到我们-Single/想到我们.jpg","title":"想到我们","artist":"汪苏泷","album":"想到我们-Single","albumArtist":"汪苏泷","year":"2024","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"05:05"},{"filePath":"mp3/周兴哲/小时候的我们/我很快乐.mp3","coverFilePath":"cover/周兴哲/小时候的我们/我很快乐.jpg","title":"我很快乐","artist":"周兴哲","album":"小时候的我们","albumArtist":"周兴哲","year":"2020","genre":"国语流行","trackNumber":"10","bitrate":"256 kbps","duration":"04:56"},{"filePath":"mp3/en/我走后(深情版)-Single/我走后(深情版).mp3","coverFilePath":"cover/en/我走后(深情版)-Single/我走后(深情版).jpg","title":"我走后(深情版)","artist":"en","album":"我走后(深情版)-Single","albumArtist":"en","year":"2023","genre":"国际流行","trackNumber":"1","bitrate":"256 kbps","duration":"04:13"},{"filePath":"mp3/欧阳耀莹/戒不掉(原声版)-Single/戒不掉(原声版).mp3","coverFilePath":"cover/欧阳耀莹/戒不掉(原声版)-Single/戒不掉(原声版).jpg","title":"戒不掉 (原声版)","artist":"欧阳耀莹","album":"戒不掉(原声版)-Single","albumArtist":"欧阳耀莹","year":"2023","genre":"粤语流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:06"},{"filePath":"mp3/黄霄雲/星辰大海-Single/星辰大海.mp3","coverFilePath":"cover/黄霄雲/星辰大海-Single/星辰大海.jpg","title":"星辰大海","artist":"黄霄雲","album":"星辰大海-Single","albumArtist":"黄霄雲","year":"2021","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:27"},{"filePath":"mp3/街道办GDC&欧阳耀莹/春娇与志明-Single/春娇与志明.mp3","coverFilePath":"cover/街道办GDC&欧阳耀莹/春娇与志明-Single/春娇与志明.jpg","title":"春娇与志明","artist":"街道办GDC & 欧阳耀莹","album":"春娇与志明-Single","albumArtist":"街道办GDC&欧阳耀莹","year":"2017","genre":"粤语流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:24"},{"filePath":"mp3/苏星婕/最遥远的-Single/最遥远的.mp3","coverFilePath":"cover/苏星婕/最遥远的-Single/最遥远的.jpg","title":"最遥远的","artist":"苏星婕","album":"最遥远的-Single","albumArtist":"苏星婕","year":"2024","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:30"},{"filePath":"mp3/邓紫棋/来自天堂的魔鬼-Single/来自天堂的魔鬼.mp3","coverFilePath":"cover/邓紫棋/来自天堂的魔鬼-Single/来自天堂的魔鬼.jpg","title":"来自天堂的魔鬼","artist":"邓紫棋","album":"来自天堂的魔鬼-Single","albumArtist":"邓紫棋","year":"2015","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"04:05"},{"filePath":"mp3/TizzyT/做旧-EP/梦里面.mp3","coverFilePath":"cover/TizzyT/做旧-EP/梦里面.jpg","title":"梦里面","artist":"Tizzy T","album":"做旧-EP","albumArtist":"TizzyT","year":"2023","genre":"嘻哈-说唱","trackNumber":"6","bitrate":"256 kbps","duration":"03:39"},{"filePath":"mp3/张德伊玲/梧桐-Single/梧桐.mp3","coverFilePath":"cover/张德伊玲/梧桐-Single/梧桐.jpg","title":"梧桐","artist":"张德伊玲","album":"梧桐-Single","albumArtist":"张德伊玲","year":"2023","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:22"},{"filePath":"mp3/周兴哲/如果雨之后/永不失联的爱.mp3","coverFilePath":"cover/周兴哲/如果雨之后/永不失联的爱.jpg","title":"永不失联的爱","artist":"周兴哲","album":"如果雨之后","albumArtist":"周兴哲","year":"2017","genre":"国语流行","trackNumber":"6","bitrate":"256 kbps","duration":"04:18"},{"filePath":"mp3/邹沛沛&Pank/沉溺(你让我的心不再结冰)-Single/沉溺(你让我的心不再结冰).mp3","coverFilePath":"cover/邹沛沛&Pank/沉溺(你让我的心不再结冰)-Single/沉溺(你让我的心不再结冰).jpg","title":"沉溺(你让我的心不再结冰)","artist":"邹沛沛 & Pank","album":"沉溺(你让我的心不再结冰)-Single","albumArtist":"邹沛沛&Pank","year":"2023","genre":"R&B-灵魂乐","trackNumber":"1","bitrate":"256 kbps","duration":"03:13"},{"filePath":"mp3/司南/没有人可以-Single/没有人可以.mp3","coverFilePath":"cover/司南/没有人可以-Single/没有人可以.jpg","title":"没有人可以","artist":"司南","album":"没有人可以-Single","albumArtist":"司南","year":"2023","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"04:15"},{"filePath":"mp3/永彬Ryan.B&周延英/没有理由-Single/没有理由.mp3","coverFilePath":"cover/永彬Ryan.B&周延英/没有理由-Single/没有理由.jpg","title":"没有理由","artist":"永彬Ryan.B & 周延英","album":"没有理由-Single","albumArtist":"永彬Ryan.B&周延英","year":"2018","genre":"中文嘻哈","trackNumber":"1","bitrate":"256 kbps","duration":"03:35"},{"filePath":"mp3/林俊杰/100天/爱不会绝迹.mp3","coverFilePath":"cover/林俊杰/100天/爱不会绝迹.jpg","title":"爱不会绝迹","artist":"林俊杰","album":"100天","albumArtist":"林俊杰","year":"2009","genre":"国语流行","trackNumber":"9","bitrate":"256 kbps","duration":"03:58"},{"filePath":"mp3/徐俊雅/我的秘密-EP/爱你.mp3","coverFilePath":"cover/徐俊雅/我的秘密-EP/爱你.jpg","title":"爱你","artist":"徐俊雅","album":"我的秘密-EP","albumArtist":"徐俊雅","year":"2018","genre":"国际流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:26"},{"filePath":"mp3/单依纯/爱的回归线(Live)-Single/爱的回归线(Live).mp3","coverFilePath":"cover/单依纯/爱的回归线(Live)-Single/爱的回归线(Live).jpg","title":"爱的回归线 (Live)","artist":"单依纯","album":"爱的回归线(Live)-Single","albumArtist":"单依纯","year":"2023","genre":"Mandopop","trackNumber":"1","bitrate":"256 kbps","duration":"03:42"},{"filePath":"mp3/苏星婕/爱问你-Single/爱问你.mp3","coverFilePath":"cover/苏星婕/爱问你-Single/爱问你.jpg","title":"爱问你","artist":"苏星婕","album":"爱问你-Single","albumArtist":"苏星婕","year":"2024","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"02:41"},{"filePath":"mp3/于冬然/空白格-Single/空白格.mp3","coverFilePath":"cover/于冬然/空白格-Single/空白格.jpg","title":"空白格","artist":"于冬然","album":"空白格-Single","albumArtist":"于冬然","year":"2024","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:48"},{"filePath":"mp3/小野来了/童话镇-Single/童话镇.mp3","coverFilePath":"cover/小野来了/童话镇-Single/童话镇.jpg","title":"童话镇","artist":"小野来了","album":"童话镇-Single","albumArtist":"小野来了","year":"2024","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"04:20"},{"filePath":"mp3/于冬然/给我一个理由忘记-Single/给我一个理由忘记.mp3","coverFilePath":"cover/于冬然/给我一个理由忘记-Single/给我一个理由忘记.jpg","title":"给我一个理由忘记","artist":"于冬然","album":"给我一个理由忘记-Single","albumArtist":"于冬然","year":"2024","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"04:01"},{"filePath":"mp3/陈雪凝/绿色-Single/绿色.mp3","coverFilePath":"cover/陈雪凝/绿色-Single/绿色.jpg","title":"绿色","artist":"陈雪凝","album":"绿色-Single","albumArtist":"陈雪凝","year":"2019","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"04:29"},{"filePath":"mp3/苏星婕/虹之间-Single/虹之间.mp3","coverFilePath":"cover/苏星婕/虹之间-Single/虹之间.jpg","title":"虹之间","artist":"苏星婕","album":"虹之间-Single","albumArtist":"苏星婕","year":"2023","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:02"},{"filePath":"mp3/林達浪&h3R3/还是会想你-Single/还是会想你.mp3","coverFilePath":"cover/林達浪&h3R3/还是会想你-Single/还是会想你.jpg","title":"还是会想你","artist":"林達浪 & h3R3","album":"还是会想你-Single","albumArtist":"林達浪&h3R3","year":"2021","genre":"国际流行","trackNumber":"1","bitrate":"256 kbps","duration":"03:10"},{"filePath":"mp3/胡夏/那些年,我们一起追的女孩(电影原声带)/那些年.mp3","coverFilePath":"cover/胡夏/那些年,我们一起追的女孩(电影原声带)/那些年.jpg","title":"那些年","artist":"胡夏","album":"那些年,我们一起追的女孩(电影原声带)","albumArtist":"胡夏","year":"2011","genre":"原声音乐","trackNumber":"13","bitrate":"256 kbps","duration":"06:11"},{"filePath":"mp3/苏星婕/那就算了吧-Single/那就算了吧.mp3","coverFilePath":"cover/苏星婕/那就算了吧-Single/那就算了吧.jpg","title":"那就算了吧","artist":"苏星婕","album":"那就算了吧-Single","albumArtist":"苏星婕","year":"2024","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"02:48"},{"filePath":"mp3/于冬然/错的人-Single/错的人.mp3","coverFilePath":"cover/于冬然/错的人-Single/错的人.jpg","title":"错的人","artist":"于冬然","album":"错的人-Single","albumArtist":"于冬然","year":"2024","genre":"国语流行","trackNumber":"1","bitrate":"256 kbps","duration":"04:14"}],"ok":"查询成功"}}
```

## 7. 按关键词搜索歌曲
```json
{"success":true,"message":{"songs":[{"filePath":"mp3/sleepydude&Mecdoux/BleedingLove-Single/BleedingLove.mp3","coverFilePath":"cover/sleepydude&Mecdoux/BleedingLove-Single/BleedingLove.jpg","title":"Bleeding Love","artist":"sleepy dude & Mecdoux","album":"BleedingLove-Single","albumArtist":"sleepydude&Mecdoux","year":"2021","genre":"电子音乐","trackNumber":"1","bitrate":"256 kbps","duration":"02:19"}],"ok":"查询成功"}}
```

---

# Playlist Service

## 8. 获取用户歌单列表
```json
{"success":true,"message":{"songLists":[]}}
```

## 9. 查询收藏歌曲
```json
{"success":true,"message":{"songs":[],"ok":"歌单音乐查询成功"}}
```

## 10. 添加歌曲到收藏
```json
{"success":true,"message":{"ok":"歌曲已成功添加到歌单"}}
```

## 11. 从收藏删除歌曲
```json
{"success":true,"message":{"ok":"歌曲已成功从歌单中删除"}}
```

## 12. 未登录访问收藏（缺少认证头）
```json
{"success":false,"message":{"error":"未登录"}}
```

## 13. 认证失败（错误密码）
```json
{"success":false,"message":{"error":"登录信息验证失败"}}
```

---

测试完成
