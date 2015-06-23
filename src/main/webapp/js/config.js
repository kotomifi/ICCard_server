(function($){
    $.URL = {
        "user":{
            "add":"rest/user/add",
            "update":"rest/user/update",
            "delete":"rest/user/delete",
            "list":"rest/user/allUser",
            "getId":"rs/user/getIdByName" ,
            "currentUserId": "rs/user/currentUserId"
        },
        "installation":{
            "add":"rest/installation/add",
            "update":"rest/installation/update",
            "delete":"rest/installation/delete",
            "list":"rest/installation/allInstallation",
            "getId":"rs/installation/getIdByName"
        },
        "repair":{
            "add":"rest/repair/add",
            "update":"rest/repair/update",
            "delete":"rest/repair/delete",
            "list":"rest/repair/allRepair",
            "getId":"rs/repair/getIdByName"
        },
        "menu" :{
            "addParentMenu" :"rs/menu/addParentMenu" ,
            "addSonMenu" :"rs/menu/addSonMenu" ,
            "update":"rs/menu/update",
            "fsupdate":"rs/menu/fsupdate",
            "delete":"rs/menu/delete",
            "list":"rs/menu/list",
            "getParentInfoById": "rs/menu/getParentInfoById"  ,
            "getMenuByUserId": "rs/menu/getMenuByUserId"

        }
    }
})(jQuery);