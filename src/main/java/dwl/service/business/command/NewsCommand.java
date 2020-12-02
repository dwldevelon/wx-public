package dwl.service.business.command;

import dwl.config.constant.wx.WxMsgTypeConstant;
import dwl.config.plugins.BeanRepository;
import dwl.model.WXContext;
import dwl.model.entity.NewsDto;
import dwl.model.enums.NewsTypeEnum;
import dwl.model.jvhe.NewsResp;
import dwl.model.wxmsg.resp.WXNewsRespMessage;
import dwl.service.business.WXCommand;
import dwl.utils.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wenlong.ding
 * @date 2020/11/30 13:46
 */
@Service
public class NewsCommand extends BeanRepository implements WXCommand {

    @Override
    public void exec(WXContext context) {
        // 获取新闻
        NewsResp news = jvHeService.getNews(NewsTypeEnum.TOP);
        List<NewsResp.Data> data = news.getData();

        // 持久化数据
        List<NewsDto> existNewsList = CommonUtil.associateList(data, NewsResp.Data::getUniquekey, newsService::findByUniqueKeys);
        List<String> existIds = existNewsList.stream().map(NewsDto::getUniquekey).collect(Collectors.toList());
        List<NewsDto> newNewsDtoList = data.stream()
                .filter(e -> !existIds.contains(e.getUniquekey()))
                .map(e -> {
                    NewsDto newsDto = new NewsDto();
                    newsDto.setUniquekey(e.getUniquekey());
                    newsDto.setTitle(e.getTitle());
                    newsDto.setDate(e.getDate());
                    newsDto.setCategory(e.getCategory());
                    newsDto.setAuthor_name(e.getAuthor_name());
                    newsDto.setUrl(e.getUrl());
                    newsDto.setThumbnailPicS(e.getThumbnailPicS());
                    newsDto.setThumbnailPicS02(e.getThumbnailPicS02());
                    newsDto.setThumbnailPicS03(e.getThumbnailPicS03());
                    return newsDto;
                }).collect(Collectors.toList());
        newsService.saveBatch(newNewsDtoList);


        NewsDto newsDto;
        if(!CollectionUtils.isEmpty(existNewsList)){
            newsDto = existNewsList.get(0);
        }else {
            newsDto = newNewsDtoList.get(0);
        }


        WXNewsRespMessage.Item item = new WXNewsRespMessage.Item();
        item.setTitle(newsDto.getTitle());
        item.setDescription(newsDto.getTitle());
        item.setPicUrl(newsDto.getThumbnailPicS());
        item.setUrl(newsDto.getUrl());
        List<WXNewsRespMessage.Item> itemList = new ArrayList<>();
        itemList.add(item);

        WXNewsRespMessage.Articles articles = new WXNewsRespMessage.Articles();
        articles.setItem(itemList);

        WXNewsRespMessage wxNewsRespMessage = context.castOrCrate(WXNewsRespMessage.class);
        wxNewsRespMessage.setMsgType(WxMsgTypeConstant.NEWS);
        wxNewsRespMessage.setArticleCount(String.valueOf(1));
        wxNewsRespMessage.setArticles(articles);
    }
}
