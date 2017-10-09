package fpo.childhubmanagement.shareModel;

/**
 * Created by TranThaoUyen on 7/12/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DecodeTokenDataModel {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("fullname")
        @Expose
        private String fullname;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("birthday")
        @Expose
        private String birthday;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("permission")
        @Expose
        private Integer permission;
        @SerializedName("school")
        @Expose
        private String school;
        @SerializedName("dbName")
        @Expose
        private String dbName;
        @SerializedName("baseurl")
        @Expose
        private String baseurl;
        @SerializedName("imgurl")
        @Expose
        private String imgurl;
        @SerializedName("realtime")
        @Expose
        private String realtime;
        @SerializedName("avatar")
        @Expose
        private String avatar;

        /**
         * No args constructor for use in serialization
         *
         */
        public DecodeTokenDataModel() {
        }

        /**
         *
         * @param birthday
         * @param phone
         * @param permission
         * @param avatar
         * @param password
         * @param id
         * @param username
         * @param school
         * @param realtime
         * @param dbName
         * @param email
         * @param address
         * @param gender
         * @param baseurl
         * @param fullname
         * @param imgurl
         */
        public DecodeTokenDataModel(String id, String username, String password, String fullname, String gender, String birthday, String phone, String email, String address, Integer permission, String school, String dbName, String baseurl, String imgurl, String realtime, String avatar) {
            super();
            this.id = id;
            this.username = username;
            this.password = password;
            this.fullname = fullname;
            this.gender = gender;
            this.birthday = birthday;
            this.phone = phone;
            this.email = email;
            this.address = address;
            this.permission = permission;
            this.school = school;
            this.dbName = dbName;
            this.baseurl = baseurl;
            this.imgurl = imgurl;
            this.realtime = realtime;
            this.avatar = avatar;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getPermission() {
            return permission;
        }

        public void setPermission(Integer permission) {
            this.permission = permission;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }

        public String getBaseurl() {
            return baseurl;
        }

        public void setBaseurl(String baseurl) {
            this.baseurl = baseurl;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getRealtime() {
            return realtime;
        }

        public void setRealtime(String realtime) {
            this.realtime = realtime;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

    }