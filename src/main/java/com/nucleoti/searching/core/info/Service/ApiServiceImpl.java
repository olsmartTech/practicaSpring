package com.nucleoti.searching.core.info.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nucleoti.searching.core.info.config.CheckoutdataProperties;
import com.nucleoti.searching.core.info.model.RequesSunatJuri;
import com.nucleoti.searching.core.info.model.RequesSunatNatu;
import com.nucleoti.searching.core.info.model.ResponGenerally;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class ApiServiceImpl implements ApiService {

    //UserRepository userRepository;

    @Autowired
    CheckoutdataProperties checkoutdataProperties;

    @Override
    public ResponGenerally retrieveInfoPersonaSunat(String numDocum) {
        String USER_AGENT = "google.com";
        String ruc_dni = "";
        String typeDocum = numDocum.length()==11? "RUC":"DNI";
        System.out.println("MOSTRANDO DOCUMENTO-->:  " + numDocum);
        String url =  checkoutdataProperties.getSunatUrl()+ numDocum;
        //String url = "https://api.sunat.cloud/ruc/" + numDocum;
        //String url = "https://api.sunat.cloud/ruc/20572294111";
        
        System.out.println("MOSTRANDO URL-->:  " + checkoutdataProperties.getSunatUrl());
        RequesSunatJuri respJuri=null;
        RequesSunatNatu respNatu=null;
        ResponGenerally respon=new ResponGenerally();
        try {
            URL urlobj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlobj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            StringBuffer json = new StringBuffer();
            while ((line = in.readLine()) != null) {
                json.append(line);
            }
            System.out.println("RESPUESTA SERVER 1.1.0>>: : " + json);
            ObjectMapper mapper = new ObjectMapper();
            if(typeDocum.equals("RUC")) {
                String jsonNew = json.toString().replace("    ", " ").replace("  ", "").replace(": {", ":{");
                int pos = jsonNew.indexOf("DNI");
                String cad1 = jsonNew.substring(0, pos);
                String cad2 = jsonNew.substring(pos, pos + 3);
                String cad3 = jsonNew.substring(pos + 12, jsonNew.length());
                String cad5 = cad3.substring(0, cad3.indexOf("empleados") - 3);
                String cad6 = cad3.substring(cad3.indexOf("locales") - 2, cad3.length());
                String cad4 = cad1.concat("documIden").concat(cad5.concat("," + cad6));
                System.out.println("DATOS DE JSON 1: " + cad4);
                respJuri = mapper.readValue(cad4, RequesSunatJuri.class);
                System.out.println("DATOS PERSONA JURI: " + respJuri);
                respon.setJuri(respJuri);
            }
            if(typeDocum.equals("DNI")) {
                System.out.println("<<<DATOS PERSONA NATU>>>");
                String jsonNew = json.toString().replace("Oficio","oficio");
                respNatu = mapper.readValue(jsonNew.toString(), RequesSunatNatu.class);
                respon.setNatu(respNatu);
                System.out.println("DATOS PERSONA NATU: " + respNatu);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respon;
    }

    /*
    private void toMap(JSONObject object, String userId) throws JSONException {
        //System.out.println("valores map: " + object);
        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);
            System.out.println(key + " valores map: " + value);
            ObjectMapper JSON_MAPPER = new ObjectMapper();
            try {
                System.out.println("End Call");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
*/

}
