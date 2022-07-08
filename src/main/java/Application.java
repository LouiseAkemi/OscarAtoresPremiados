import mappers.DadosOscarMapper;
import model.AtorGanhadorOscar;
import service.OscarService;
import util.ConcatOptionalList;
import util.FileUtil;

import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        String fileFemale = "oscar_age_female.csv";
        String fileMale = "oscar_age_male.csv";

        FileUtil<AtorGanhadorOscar> fileUtilFemale = new FileUtil<>(fileFemale);
        Optional<List<AtorGanhadorOscar>> dadosAtrizesOscar = fileUtilFemale.readFile(new DadosOscarMapper());

        FileUtil<AtorGanhadorOscar> fileUtilMale = new FileUtil<>(fileMale);
        Optional<List<AtorGanhadorOscar>> dadosAtoresOscar = fileUtilMale.readFile(new DadosOscarMapper());

        List<AtorGanhadorOscar> atoresOscar = ConcatOptionalList.concat(dadosAtrizesOscar,dadosAtoresOscar);

        OscarService oscarService = new OscarService(atoresOscar);
        oscarService.atorMaisJovemPremiado();
        oscarService.atorMaisPremiado();
        oscarService.atorJovemMaisPremiado();

    }
}
