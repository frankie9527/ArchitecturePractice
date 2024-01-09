package com.jyh.sixthspace.sdk.bean.live;

import java.util.List;
/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2016/12/15 下午4:15
 **/
public class HomeCarousel {

    /**
     * id : 605964
     * title : WCA2016总决赛
     * pic_url : https://staticlive.douyucdn.cn/upload/signs/201612151306323628.jpg
     * tv_pic_url : https://staticlive.douyucdn.cn/upload/signs/201612151306325967.jpg
     * room : {"room_id":"605964","room_src":"https://rpic.douyucdn.cn/a1612/15/15/605964_161215155824.jpg","vertical_src":"https://rpic.douyucdn.cn/a1612/15/15/605964_161215155824.jpg","isVertical":0,"cate_id":"33","room_name":"WCA2016总决赛","vod_quality":"0","show_status":"1","show_time":"1481778091","owner_uid":"45539616","specific_catalog":"","specific_status":"0","credit_illegal":"0","is_white_list":"0","cur_credit":"12","low_credit":"4","online":50514,"nickname":"穿越火线运营团队","url":"/605964","game_url":"/directory/game/CF","game_name":"穿越火线","game_icon_url":"https://staticlive.douyucdn.cn/upload/game_cate/94691f7a259e7e85c4c65e5849cd94dc.jpg","show_details":"","owner_avatar":"https://apic.douyucdn.cn/upload/avanew/face/201612/14/13/7099c276332634a83f9dd6b586fdb9bd_big.jpg?rltime","cdnsWithName":[{"name":"主线路","cdn":"ws"},{"name":"备用线路5","cdn":"tct"},{"name":"备用线路2","cdn":"ws2"},{"name":"备用线路3","cdn":"dl"}],"is_pass_player":0,"owner_weight":"402.952kg","fans":"63881","column_id":"1","cate_limit":{"limit_type":0,"limit_num":0,"limit_threshold":0,"limit_time":30}}
     */

    private int id;
    private String title;
    private String pic_url;
    private String tv_pic_url;
    /**
     * room_id : 605964
     * room_src : https://rpic.douyucdn.cn/a1612/15/15/605964_161215155824.jpg
     * vertical_src : https://rpic.douyucdn.cn/a1612/15/15/605964_161215155824.jpg
     * isVertical : 0
     * cate_id : 33
     * room_name : WCA2016总决赛
     * vod_quality : 0
     * show_status : 1
     * show_time : 1481778091
     * owner_uid : 45539616
     * specific_catalog :
     * specific_status : 0
     * credit_illegal : 0
     * is_white_list : 0
     * cur_credit : 12
     * low_credit : 4
     * online : 50514
     * nickname : 穿越火线运营团队
     * url : /605964
     * game_url : /directory/game/CF
     * game_name : 穿越火线
     * game_icon_url : https://staticlive.douyucdn.cn/upload/game_cate/94691f7a259e7e85c4c65e5849cd94dc.jpg
     * show_details :
     * owner_avatar : https://apic.douyucdn.cn/upload/avanew/face/201612/14/13/7099c276332634a83f9dd6b586fdb9bd_big.jpg?rltime
     * cdnsWithName : [{"name":"主线路","cdn":"ws"},{"name":"备用线路5","cdn":"tct"},{"name":"备用线路2","cdn":"ws2"},{"name":"备用线路3","cdn":"dl"}]
     * is_pass_player : 0
     * owner_weight : 402.952kg
     * fans : 63881
     * column_id : 1
     * cate_limit : {"limit_type":0,"limit_num":0,"limit_threshold":0,"limit_time":30}
     */

    private RoomEntity room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getTv_pic_url() {
        return tv_pic_url;
    }

    public void setTv_pic_url(String tv_pic_url) {
        this.tv_pic_url = tv_pic_url;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public static class RoomEntity {
        private String room_id;
        private String room_src;
        private String vertical_src;
        private int isVertical;
        private String cate_id;
        private String room_name;
        private String vod_quality;
        private String show_status;
        private String show_time;
        private String owner_uid;
        private String specific_catalog;
        private String specific_status;
        private String credit_illegal;
        private String is_white_list;
        private String cur_credit;
        private String low_credit;
        private int online;
        private String nickname;
        private String url;
        private String game_url;
        private String game_name;
        private String game_icon_url;
        private String show_details;
        private String owner_avatar;
        private int is_pass_player;
        private String owner_weight;
        private String fans;
        private String column_id;
        /**
         * limit_type : 0
         * limit_num : 0
         * limit_threshold : 0
         * limit_time : 30
         */

        private CateLimitEntity cate_limit;
        /**
         * name : 主线路
         * cdn : ws
         */

        private List<CdnsWithNameEntity> cdnsWithName;

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getRoom_src() {
            return room_src;
        }

        public void setRoom_src(String room_src) {
            this.room_src = room_src;
        }

        public String getVertical_src() {
            return vertical_src;
        }

        public void setVertical_src(String vertical_src) {
            this.vertical_src = vertical_src;
        }

        public int getIsVertical() {
            return isVertical;
        }

        public void setIsVertical(int isVertical) {
            this.isVertical = isVertical;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getVod_quality() {
            return vod_quality;
        }

        public void setVod_quality(String vod_quality) {
            this.vod_quality = vod_quality;
        }

        public String getShow_status() {
            return show_status;
        }

        public void setShow_status(String show_status) {
            this.show_status = show_status;
        }

        public String getShow_time() {
            return show_time;
        }

        public void setShow_time(String show_time) {
            this.show_time = show_time;
        }

        public String getOwner_uid() {
            return owner_uid;
        }

        public void setOwner_uid(String owner_uid) {
            this.owner_uid = owner_uid;
        }

        public String getSpecific_catalog() {
            return specific_catalog;
        }

        public void setSpecific_catalog(String specific_catalog) {
            this.specific_catalog = specific_catalog;
        }

        public String getSpecific_status() {
            return specific_status;
        }

        public void setSpecific_status(String specific_status) {
            this.specific_status = specific_status;
        }

        public String getCredit_illegal() {
            return credit_illegal;
        }

        public void setCredit_illegal(String credit_illegal) {
            this.credit_illegal = credit_illegal;
        }

        public String getIs_white_list() {
            return is_white_list;
        }

        public void setIs_white_list(String is_white_list) {
            this.is_white_list = is_white_list;
        }

        public String getCur_credit() {
            return cur_credit;
        }

        public void setCur_credit(String cur_credit) {
            this.cur_credit = cur_credit;
        }

        public String getLow_credit() {
            return low_credit;
        }

        public void setLow_credit(String low_credit) {
            this.low_credit = low_credit;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getGame_url() {
            return game_url;
        }

        public void setGame_url(String game_url) {
            this.game_url = game_url;
        }

        public String getGame_name() {
            return game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public String getGame_icon_url() {
            return game_icon_url;
        }

        public void setGame_icon_url(String game_icon_url) {
            this.game_icon_url = game_icon_url;
        }

        public String getShow_details() {
            return show_details;
        }

        public void setShow_details(String show_details) {
            this.show_details = show_details;
        }

        public String getOwner_avatar() {
            return owner_avatar;
        }

        public void setOwner_avatar(String owner_avatar) {
            this.owner_avatar = owner_avatar;
        }

        public int getIs_pass_player() {
            return is_pass_player;
        }

        public void setIs_pass_player(int is_pass_player) {
            this.is_pass_player = is_pass_player;
        }

        public String getOwner_weight() {
            return owner_weight;
        }

        public void setOwner_weight(String owner_weight) {
            this.owner_weight = owner_weight;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getColumn_id() {
            return column_id;
        }

        public void setColumn_id(String column_id) {
            this.column_id = column_id;
        }

        public CateLimitEntity getCate_limit() {
            return cate_limit;
        }

        public void setCate_limit(CateLimitEntity cate_limit) {
            this.cate_limit = cate_limit;
        }

        public List<CdnsWithNameEntity> getCdnsWithName() {
            return cdnsWithName;
        }

        public void setCdnsWithName(List<CdnsWithNameEntity> cdnsWithName) {
            this.cdnsWithName = cdnsWithName;
        }

        public static class CateLimitEntity {
            private int limit_type;
            private int limit_num;
            private int limit_threshold;
            private int limit_time;

            public int getLimit_type() {
                return limit_type;
            }

            public void setLimit_type(int limit_type) {
                this.limit_type = limit_type;
            }

            public int getLimit_num() {
                return limit_num;
            }

            public void setLimit_num(int limit_num) {
                this.limit_num = limit_num;
            }

            public int getLimit_threshold() {
                return limit_threshold;
            }

            public void setLimit_threshold(int limit_threshold) {
                this.limit_threshold = limit_threshold;
            }

            public int getLimit_time() {
                return limit_time;
            }

            public void setLimit_time(int limit_time) {
                this.limit_time = limit_time;
            }
        }

        public static class CdnsWithNameEntity {
            private String name;
            private String cdn;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCdn() {
                return cdn;
            }

            public void setCdn(String cdn) {
                this.cdn = cdn;
            }
        }
        @Override
        public String toString() {
            return "{" +
                    "room_id:'" + room_id + '\'' +
                    ", room_src:'" + room_src + '\'' +
                    ", vertical_src:'" + vertical_src + '\'' +
                    ", isVertical:" + isVertical +
                    ", cate_id:'" + cate_id + '\'' +
                    ", room_name:'" + room_name + '\'' +
                    ", vod_quality:'" + vod_quality + '\'' +
                    ", show_status:'" + show_status + '\'' +
                    ", show_time:'" + show_time + '\'' +
                    ", owner_uid:'" + owner_uid + '\'' +
                    ", specific_catalog:'" + specific_catalog + '\'' +
                    ", specific_status:'" + specific_status + '\'' +
                    ", credit_illegal:'" + credit_illegal + '\'' +
                    ", is_white_list:'" + is_white_list + '\'' +
                    ", cur_credit:'" + cur_credit + '\'' +
                    ", low_credit:'" + low_credit + '\'' +
                    ", online:" + online +
                    ", nickname:'" + nickname + '\'' +
                    ", url:'" + url + '\'' +
                    ", game_url:'" + game_url + '\'' +
                    ", game_name:'" + game_name + '\'' +
                    ", game_icon_url:'" + game_icon_url + '\'' +
                    ", show_details:'" + show_details + '\'' +
                    ", owner_avatar:'" + owner_avatar + '\'' +
                    ", is_pass_player:" + is_pass_player +
                    ", owner_weight:'" + owner_weight + '\'' +
                    ", fans:'" + fans + '\'' +
                    ", column_id:'" + column_id + '\'' +
                    ", cate_limit:" + cate_limit +
                    ", cdnsWithName:" + cdnsWithName +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", title:'" + title + '\'' +
                ", pic_url:'" + pic_url + '\'' +
                ", tv_pic_url:'" + tv_pic_url + '\'' +
                ", room:" + room +
                '}';
    }
}
