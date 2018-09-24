package com.itpvt.v360;

public class Config
{
    public static final String URL_CREATE_USER = "http://itpvt.net/v360app/v360/usersignup.php";
    public static final String URL_CREATE_MODEL = "http://itpvt.net/v360app/v360/modelsignup.php";
    public static final String URL_CREATE_PHOTOG = "http://itpvt.net/v360app/v360/photographersignup.php";
    public static final String URL_CREATE_DESIGNER="http://itpvt.net/v360app/v360/designersignup.php";
    public static final String URL_CREATE_ART = "http://itpvt.net/v360app/v360/artsignup.php";

    // url to verify the users
    public static final String URL_VERIFY_USER = "http://itpvt.net/v360app/v360/verify_user.php?Email=";
    public static final String URL_VERIFY_MODEL = "http://itpvt.net/v360app/v360/verify_model.php?Email=";
    public static final String URL_VERIFY_PHOTOG = "http://itpvt.net/v360app/v360/verify_photog.php?Email=";

    //forgot password verification
    public static final String URL_FORG_VERIFY_USER = "http://itpvt.net/v360app/v360/forg_vfuser.php?Email=";
    public static final String URL_FORG_VERIFY_MODEL = "http://itpvt.net/v360app/v360/forg_vfmodel.php?Email=";
    public static final String URL_FORG_VERIFY_PHOTOG = "http://itpvt.net/v360app/v360/forg_vfphotog.php?Email=";

    // for model signup
    public static final String SU_MODEL_NAME = "model_name";
    public static final String SU_MODEL_USERNAME  = "model_username";
    public static final String SU_MODEL_EMAIL = "model_email";
    public static final String SU_MODEL_PHONE  = "model_phone";
    public static final String SU_MODEL_AGE = "model_age";
    public static final String SU_MODEL_GENDER = "model_gender";
    public static final String SU_MODEL_RECENT  = "model_recentwork";
    public static final String SU_MODEL_ADDRESS = "model_address";
    public static final String SU_MODEL_PASS = "model_password";

    // for photographer signup
    public static final String SU_PHOTOG_NAME = "photog_name";
    public static final String SU_PHOTOG_USERNAME  = "photog_username";
    public static final String SU_PHOTOG_EMAIL = "photog_email";
    public static final String SU_PHOTOG_PHONE  = "photog_phone";
    public static final String SU_PHOTOG_AGE = "photog_age";
    public static final String SU_PHOTOG_GENDER = "photog_gender";
    public static final String SU_PHOTOG_RECENT  = "photog_recentwork";
    public static final String SU_PHOTOG_ADDRESS = "photog_address";
    public static final String SU_PHOTOG_PASS = "photog_password";

    // for model signup
    public static final String SU_USER_NAME = "user_name";
    public static final String SU_USER_USERNAME  = "user_username";
    public static final String SU_USER_EMAIL = "user_email";
    public static final String SU_USER_PHONE  = "user_phone";
    public static final String SU_USER_AGE = "user_age";
    public static final String SU_USER_GENDER = "user_gender";
    public static final String SU_USER_RECENT  = "user_recentwork";
    public static final String SU_USER_ADDRESS = "user_address";
    public static final String SU_USER_PASS = "user_password";
    //for Designer signup
    public static final String SU_DESIGN_NAME = "designer_name";
    public static final String SU_DESIGN_USERNAME  = "designer_username";
    public static final String SU_DESIGN_EMAIL = "designer_email";
    public static final String SU_DESIGN_PHONE  = "designer_phone";
    public static final String SU_DESIGN_AGE = "designer_age";
    public static final String SU_DESIGN_GENDER = "designer_gender";
    public static final String SU_DESIGN_RECENT  = "designer_recentwork";
    public static final String SU_DESIGN_ADDRESS = "designer_address";
    public static final String SU_DESIGN_PASS = "designer_password";

    // for user login
    public static final String USER_LOGIN = "http://itpvt.net/v360app/v360/login_user.php";
    // for model login
    public static final String MODEL_LOGIN = "http://itpvt.net/v360app/v360/login_model.php";
    // for photographer login
    public static final String PHOTOG_LOGIN = "http://itpvt.net/v360app/v360/login_photog.php";
    //Desinger Login
    public static final String DESIGN_LOGIN = "http://itpvt.net/v360app/v360/login_design.php";
    //MakeUp Artist Login
    public static final String ART_LOGIN = "http://itpvt.net/v360app/v360/login_art.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_MAIL = "user_email";
    public static final String KEY_PASSWORD = "user_pass";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "v360";
    public static final String SHARED_PREF_USERNAME = "nav_name";
    public static final String SHARED_PREF_GOOGLE_NAME = "GoogleName";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "user_email";

    //This would be used to store the category of current logged in user
    public static final String CATEGORY_SHARED_PREF = "user_type";

    //This would be used to store the phone of current logged in user
    public static final String PHONE_SHARED_PREF = "user_phone";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

    // For verifying the user
    public static final String VF_USER  = "Email";
    public static final String VF_USERNAME  = "Username";
    public static final String JSON_ARRAY = "result";

    // image url
    public static final String URL = "url";
    public static final String IMAGE = "image";
    public static final String PARTIAL_URL = "http://itpvt.net/v360app/v360/uploads/";

    // For reset pass
    public static final String URL_USER_CHANGE_PASS = "http://itpvt.net/v360app/v360/update_userpass.php?";
    public static final String URL_MODEL_CHANGE_PASS = "http://itpvt.net/v360app/v360/update_modelpass.php?";
    public static final String URL_PHOTOG_CHANGE_PASS = "http://itpvt.net/v360app/v360/update_photogpass.php?";
    public static final String R_USER_ID = "user_email";
    public static final String R_USER_PASS = "user_pass";

    // Get profile URL
    public static final String DATA_URL_PHOTOG = "http://itpvt.net/v360app/v360/profile_photographer.php?Email=";
    public static final String DATA_URL_MODEL = "http://itpvt.net/v360app/v360/profile_model.php?Email=";
    public static final String DATA_URL_USER = "http://itpvt.net/v360app/v360/profile_user.php?Email=";
    public static final String DATA_URL_DESIGN = "http://itpvt.net/v360app/v360/profile_design.php?Email=";
    public static final String DATA_URL_ART = "http://itpvt.net/v360app/v360/profile_art.php?Email=";

    //Profile get data keys
    public static final String KEY_NAME = "Name";
    public static final String KEY_USERNAME = "Username";
    public static final String KEY_EMAIL = "Email";
    public static final String KEY_MOBILE = "Phone";
    public static final String KEY_AGE = "Age";
    public static final String KEY_GENDER = "Gender";
    public static final String KEY_RECENT_WORK = "Recent_Work";
    public static final String KEY_ADDRESS = "Address";
    public static final String KEY_URL = "url";

    // Details for Events (marriage, party model_shoot)
    public static final String URL_MARRIAGE = "http://itpvt.net/v360app/v360/marriage.php";
    public static final String URL_PARTY = "http://itpvt.net/v360app/v360/party.php";
    public static final String URL_MODEL_SHOOT = "http://itpvt.net/v360app/v360/model_shoot.php";
    public static final String URL_EXIBITION = "http://itpvt.net/v360app/v360/exibition.php";

    public static final String USER_EMAIL = "user_email";
    public static final String USER_CONTACT = "user_phone";
    public static final String USER_CITY = "user_city";
    public static final String DATE_FROM = "user_fromdate";
    public static final String DATE_TO = "user_todate";
    public static final String USER_DESCRIPTION = "user_description";

    // modell shoot details keys
    public static final String MODEL_EMAIL = "user_email";
    public static final String MODEL_CONTACT = "user_phone";
    public static final String MODEL_VENUE = "user_venue";
    public static final String MODEL_CITY = "user_city";
    public static final String MODEL_DATE = "user_date";
    public static final String MODEL_DESCRIPTION = "user_description";


    //url to get phone number
    public static final String PHONE_URL_USER = "http://itpvt.net/v360app/v360/getphone.php?Email=";
    public static final String PHONE_URL_MODEL = "http://itpvt.net/v360app/v360/getphone_model.php?Email=";

    public static final String PHONE_URL_PHOTOG = "http://itpvt.net/v360app/v360/getphone_photog.php?Email=";
    public static final String PHONE_URL_DESIGN = "http://itpvt.net/v360app/v360/get_phone_design.php?Email=";
    public static final String PHONE_URL_ART = "http://itpvt.net/v360app/v360/get_phone_art.php?Email=";

    // phone data keys
    public static final String PH_EMAIL = "Email";
    public static final String PH_MOBILE = "Phone";

    // gallery url
    public static final String img_path="http://itpvt.net/v360app/v360/gallery/";//Images Folder From Where You get the Images
    public static final String serv_url="http://itpvt.net/v360app/v360/get_gallery.php";//this is the php file from which you get the Json file and name and Url of image
    //model Gallery
    public static final String Model_Galler="http://itpvt.net/v360app/v360/get_model_gallery.php";
    public static final String Wedding_Galler="http://itpvt.net/v360app/v360/get_wedding_gallery.php";
    public static final String Party_Galler="http://itpvt.net/v360app/v360/get_party_gallery.php";
    public static final String Exibition_Galler="http://itpvt.net/v360app/v360/get_party_gallery.php";

    // to get all models
    public static final String uploads="http://itpvt.net/v360app/v360/uploads/";
    public static final String male_models_url = "http://itpvt.net/v360app/v360/get_male_models.php";
    public static final String female_models_url = "http://itpvt.net/v360app/v360/get_female_models.php";

    //to get All Makeupartist
    public static final String male_Art_url = "http://itpvt.net/v360app/v360/get_male_art.php";
    public static final String female_Art_url = "http://itpvt.net/v360app/v360/get_female_art.php";


    // To get All Designers
    public static final String male_desinger_url = "http://itpvt.net/v360app/v360/get_male_design.php";
    public static final String female_designer_url = "http://itpvt.net/v360app/v360/get_female_designer.php";

    // to get all photographers
    public static final String photographers_url = "http://itpvt.net/v360app/v360/getallphotogs.php";
    public static final String male_photographers_url = "http://itpvt.net/v360app/v360/get_male_photogs.php";
    public static final String female_photographers_url = "http://itpvt.net/v360app/v360/get_female_photogs.php";

    // link to get detail of clicked model
    public static final String home_model_profile="http://itpvt.net/v360app/v360/home_profile_model.php?Username=";

    // link to get detail of clicked Art
    public static final String home_Art_profile="http://itpvt.net/v360app/v360/home_profile_art.php?Username=";

    //Link To get Detail of clicked Designer

    public static final String home_Designer_profile="http://itpvt.net/v360app/v360/home_profile_design.php?Username=";

    // link to get detail of clicked photographer
    public static final String home_photographer_profile="http://itpvt.net/v360app/v360/home_profile_photog.php?Username=";

    // url to upload portfolio pictures
    public static final String UPLOAD_PROTFOLIO_MODEL = "http://itpvt.net/v360app/v360/model_portfolio.php?username=";
    public static final String UPLOAD_PROTFOLIO_PHOTOG = "http://itpvt.net/v360app/v360/photog_portfolio.php?username=";
    public static final String UPLOAD_PROTFOLIO_DESIGN = "http://itpvt.net/v360app/v360/design_portfolio.php?username=";
    public static final String UPLOAD_PROTFOLIO_ART = "http://itpvt.net/v360app/v360/art_portfolio.php?username=";

    // url to get portfoilio
    public static final String GETTING_MODEL_PORTFOLIO = "http://itpvt.net/v360app/v360/getting_model_portfolio.php?username=";
    public static final String GETTING_PHOTOG_PORTFOLIO = "http://itpvt.net/v360app/v360/getting_photog_portfolio.php?username=";
    public static final String GETTING_DESIGN_PORTFOLIO = "http://itpvt.net/v360app/v360/getting_desing_portfolio.php?username=";
    public static final String GETTING_ART_PORTFOLIO = "http://itpvt.net/v360app/v360/getting_art_portfolio.php?username=";
    //Query Links
    public static final String QUERY_SUBMITE="http://itpvt.net/v360app/v360/query_submite.php";
    public static final String QUERY_EMAIL = "user_email";
    public static final String QUERY_MOBILE = "user_phone";
    public static final String QUERY_USERNAME = "username";
    public static final String QUERY_SUBJECT= "subject";
    public static final String QUERY_MESSAGE= "message";

    //Update Profiles
    public static final String UPDATE_USER_PROFILE="http://itpvt.net/v360app/v360/update_user_profile.php";
    public static final String UPDATE_PHOTOG_PROFILE="http://itpvt.net/v360app/v360/update_photog_profile.php";
    public static final String UPDATE_MODEL_PROFILE="http://itpvt.net/v360app/v360/update_model_profile.php";
    public static final String UPDATE_DESIGN_PROFILE="http://itpvt.net/v360app/v360/update_designer_profile.php";
    public static final String UPDATE_ART_PROFILE="http://itpvt.net/v360app/v360/update_artf_profile.php";
    public static final String UPDATE_EMAIL="user_email";
    public static final String UPDATE_NAME="name";
    public static final String UPDATE_GENDER="gender";
    public static final String UPDATE_PHONE="phone";
    public static final String UPDATE_RECENT_WORK="recent_work";
    public static final String UPDATE_AGE="age";
    public static final String UPDATE_ADDRESS="address";

    //Designer Signup
}
