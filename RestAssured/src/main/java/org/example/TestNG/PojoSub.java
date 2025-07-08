package org.example.TestNG;

import java.util.Collections;
import java.util.List;

public class PojoSub {

    private List<PojoSub1> WebAutomation;
    private List<PojoSub2> Api;
    private List<PojoSub3> Mobile;

    public List<PojoSub1> getWebAutomation() {
        return WebAutomation;
    }

    public void setWebAutomation(List<PojoSub1> webAutomation) {
        this.WebAutomation = webAutomation;
    }

    public List<PojoSub2> getApi() {
        return Api;
    }

    public void setApi(List<PojoSub2> api) {
        this.Api = api;
    }

    public List<PojoSub3> getMobile() {
        return Mobile;
    }

    public void setMobile(List<PojoSub3> mobile) {
        this.Mobile = mobile;
    }





}
