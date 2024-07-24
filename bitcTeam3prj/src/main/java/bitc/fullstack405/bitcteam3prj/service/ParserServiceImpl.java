package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.dto.movie.MovieDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ParserServiceImpl implements ParserService {
    @Override
    public MovieDTO getMovieData(String serviceURL) throws Exception {
        MovieDTO movieData = null;

        URL url = null;
        HttpURLConnection urlConn = null;
        BufferedReader br = null;

        try{
            url = new URL(serviceURL);
            urlConn =(HttpURLConnection)url.openConnection();
            urlConn.setRequestMethod("GET");

            br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null){
                sb.append(line);
            }

            Gson gson = new Gson();
            movieData = gson.fromJson(sb.toString(), MovieDTO.class);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(br != null){br.close();}
            if(urlConn != null) urlConn.disconnect();
        }

        return movieData;
    }
}
