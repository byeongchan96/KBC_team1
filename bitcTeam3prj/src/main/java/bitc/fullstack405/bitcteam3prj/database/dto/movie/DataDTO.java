package bitc.fullstack405.bitcteam3prj.database.dto.movie;

import lombok.Data;

import java.util.List;

@Data
public class DataDTO {
    String CollName;
    String TotalCount;
    String Count;
    List<ResultDTO> Result;
}
