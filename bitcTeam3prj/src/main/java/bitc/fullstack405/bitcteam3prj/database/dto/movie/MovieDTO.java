package bitc.fullstack405.bitcteam3prj.database.dto.movie;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class MovieDTO {
    String Query;
    String KMAQuery;
    String TotalCount;
    List<DataDTO> Data;
}
