package tn.esprit.integration1.Configuration;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//sta3melt.ha bch na5edh lapp key w secret w taw saye
public class FlickrConfiguration {
    @Value("${flickr.apiKey}")//auto inject from app.prop
    private String apiKey;
    @Value("${flickr.apiSecret}")
    private String apiSecret;
    @Value("${flickr.appKey}")
    private String appKey;
    @Value("${flickr.appSecret}")
    private String appSecret;



    /*@Bean//a l'auverture app auto  spring execute cette methode
    public Flickr getFlickr() throws IOException, ExecutionException, InterruptedException, FlickrException {
        Flickr flickr = new Flickr(apiKey,apiSecret,new REST());

        OAuth10aService service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));//delete==Tous les droits

        final Scanner scanner = new Scanner(System.in);//utilise l'autorisation pour activer mon app token

        final OAuth1RequestToken request = service.getRequestToken();//recupere request token

        final String authUrl = service.getAuthorizationUrl(request);//auto app pour utliser l'Api

        System.out.println(authUrl);
        System.out.println("Paste it here >> ");

        final String authVerfier = scanner.nextLine();
        //recupere l'acces token
        OAuth1AccessToken accessToken = service.getAccessToken(request, authVerfier);
        System.out.println(accessToken.getToken());
        System.out.println(accessToken.getTokenSecret());

        //verfier token est valide
        Auth auth = flickr.getAuthInterface().checkToken(accessToken);

        System.out.println("-------------");
        System.out.println(auth.getToken());
        System.out.println(auth.getTokenSecret());
        return flickr;
    }*/


    @Bean
    public Flickr getFlickr(){
        Flickr flickr = new Flickr(apiKey,apiSecret,new REST());

        Auth auth = new Auth();
        auth.setPermission(Permission.DELETE);

        auth.setToken(appKey);//voila mon token
        auth.setTokenSecret(appSecret);//secret pour mon api
        RequestContext requestContext= RequestContext.getRequestContext();
        requestContext.setAuth(auth);

        flickr.setAuth(auth);
        return flickr;
    }
}
